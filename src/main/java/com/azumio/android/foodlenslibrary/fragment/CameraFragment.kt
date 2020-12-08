package com.azumio.android.foodlenslibrary.fragment

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.hardware.display.DisplayManager
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.MimeTypeMap
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.net.toFile
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.navigation.Navigation
import com.azumio.android.foodlenslibrary.FoodLens
import com.azumio.android.foodlenslibrary.R
import com.azumio.android.foodlenslibrary.activity.AddFoodActivity
import com.azumio.android.foodlenslibrary.activity.FoodLensCameraActivity
import com.azumio.android.foodlenslibrary.activity.ResultActivity
import com.azumio.android.foodlenslibrary.common.DataWrapper
import com.azumio.android.foodlenslibrary.core.FoodLensConfig
import com.azumio.android.foodlenslibrary.model.FoodCheckin
import com.azumio.android.foodlenslibrary.model.FoodSearchData
import com.azumio.android.foodlenslibrary.utils.*
import com.azumio.android.foodlenslibrary.utils.datetime.MealTimeHelper
import com.azumio.android.foodlenslibrary.utils.reachability.InternetReachabilityManager
import com.azumio.android.foodlenslibrary.views.CenteredCustomFontView
import kotlinx.android.synthetic.main.fragment_camera.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.*
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min


class CameraFragment : Fragment() {
    private lateinit var container: ConstraintLayout
    private lateinit var viewFinder: PreviewView
    private lateinit var outputDirectory: File
    private lateinit var broadcastManager: LocalBroadcastManager

    private var displayId: Int = -1
    private var lensFacing: Int = CameraSelector.LENS_FACING_BACK
    private var preview: Preview? = null
    private var imageCapture: ImageCapture? = null
    private var imageAnalysis: ImageAnalysis? = null
    private var camera: Camera? = null
    private var cameraProvider: ProcessCameraProvider? = null
    private val imageResizer = ImageResizer()
    private val displayManager by lazy {
        requireContext().getSystemService(Context.DISPLAY_SERVICE) as DisplayManager
    }

    /** Blocking camera operations are performed using this executor */
    private lateinit var cameraExecutor: ExecutorService

    var selectedMeal = MealTimeHelper.getMealLabelByTimeOfDay()
    override fun onResume() {
        super.onResume()
        // Make sure that all permissions are still present, since the
        // user could have removed them while the app was in paused state.
        if (!PermissionsFragment.hasPermissions(requireContext())) {
            Navigation.findNavController(requireActivity(), R.id.fragment_container).navigate(
                R.id.action_camera_to_permissions
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Shut down our background executor
        cameraExecutor.shutdown()

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.foodlens_fragment_camera, container, false)


    private fun setGalleryThumbnail(uri: Uri) {
        /*
        // Reference of the view that holds the gallery thumbnail
        val thumbnail = container.findViewById<ImageButton>(R.id.photo_view_button)

        // Run the operations in the view's thread
        thumbnail.post {

            // Remove thumbnail padding
            thumbnail.setPadding(resources.getDimension(R.dimen.stroke_small).toInt())

            // Load thumbnail into circular button using Glide
            Glide.with(thumbnail)
                .load(uri)
                .apply(RequestOptions.circleCropTransform())
                .into(thumbnail)
        }
        */

    }
    private fun initBackArrow() {
        val arrow: CenteredCustomFontView = container.findViewById(R.id.back_arrow)
        arrow.setText(ArgusIconMap.getInstance()[ArgusIconMap.ARROW_LEFT])
        arrow.setOnClickListener { view: View? -> activity?.finish() }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("CameraFragment", "onViewCreated")
        container = view as ConstraintLayout
        viewFinder = container.findViewById(R.id.view_finder)

        // Initialize our background executor
        cameraExecutor = Executors.newSingleThreadExecutor()

        broadcastManager = LocalBroadcastManager.getInstance(view.context)

        // Determine the output directory
        outputDirectory = FoodLensCameraActivity.getOutputDirectory(requireContext())

        // Wait for the views to be properly laid out
        viewFinder.post {

            // Keep track of the display in which this view is attached
            displayId = viewFinder.display.displayId

            // Build UI controls
            updateCameraUi()

            // Set up the camera and its use cases
            setUpCamera()

            initBackArrow()
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == GALLERY_IMAGE_REQUEST_CODE) {
            data?.data?.let {
                setGalleryThumbnail(it)
            }
        }
        if(requestCode == AddFoodActivity.FIND_FOOD_REQUEST_CODE)
        {
            data?.getSerializableExtra(CaloriesManager.PROPERTY_DATA)?.let {
                it as DataWrapper

            processData(it)?.let { checkin ->
                val intent = Intent()
                intent.putExtra(FoodLens.FOODLENS_FOOD_CHECKIN,checkin.jsonString())
                activity?.setResult(Activity.RESULT_OK, intent)
                activity?.finish()
            }

            }
        }
    }

    private fun  processData(data:DataWrapper):FoodCheckin
    {

        val logs = data.list.map { FoodSearchData.initFromJson(it)}

        val timezone = TimeUnit.HOURS.convert(TimeZone.getDefault().rawOffset.toLong(), TimeUnit.MILLISECONDS).toDouble()

        val foodlogs = logs.map { FoodCheckin.FoodLog(it.meal?:this.selectedMeal,it.name,it.numberOfServings,it.nutrition,it.parentId,
            it.remoteid ?: UUID.randomUUID().toString() ,it.servingSize,it.statusId,null,null,
            Date().time,it.type ?: CaloriesManager.LOG_TYPE_FOOD,it.validated) }

        val nutrients = CaloriesManager.getNutritionSummation(logs)

        return FoodCheckin(foodlogs,null,null, FoodSearchData.nutritionFromMap(nutrients),null,UUID.randomUUID().toString(),Date().time,timezone,CaloriesManager.LOG_TYPE_FOOD)

    }

    /** Initialize CameraX, and prepare to bind the camera use cases  */
    private fun setUpCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())
        cameraProviderFuture.addListener(Runnable {

            // CameraProvider
            cameraProvider = cameraProviderFuture.get()

            // Select lensFacing depending on the available cameras
            lensFacing = when {
                hasBackCamera() -> CameraSelector.LENS_FACING_BACK
                hasFrontCamera() -> CameraSelector.LENS_FACING_FRONT
                else -> throw IllegalStateException("Back and front camera are unavailable")
            }


            // Build and bind the camera use cases
            bindCameraUseCases()
        }, ContextCompat.getMainExecutor(requireContext()))
    }

    /** Declare and bind preview, capture and analysis use cases */
    private fun bindCameraUseCases() {

        // Get screen metrics used to setup camera for full screen resolution
        val metrics = DisplayMetrics().also { viewFinder.display.getRealMetrics(it) }
        Log.d(TAG, "Screen metrics: ${metrics.widthPixels} x ${metrics.heightPixels}")

        val screenAspectRatio = aspectRatio(metrics.widthPixels, metrics.heightPixels)
        Log.d(TAG, "Preview aspect ratio: $screenAspectRatio")

        val rotation = viewFinder.display.rotation

        // CameraProvider
        val cameraProvider = cameraProvider
            ?: throw IllegalStateException("Camera initialization failed.")

        // CameraSelector
        val cameraSelector = CameraSelector.Builder().requireLensFacing(lensFacing).build()

        // Preview
        preview = Preview.Builder()
            // We request aspect ratio but no resolution
            .setTargetAspectRatio(screenAspectRatio)
            // Set initial target rotation
            .setTargetRotation(rotation)
            .build()

        // ImageCapture
        imageCapture = ImageCapture.Builder()
            .setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY)
            // We request aspect ratio but no resolution to match preview config, but letting
            // CameraX optimize for whatever specific resolution best fits our use cases
            .setTargetAspectRatio(screenAspectRatio)
            // Set initial target rotation, we will have to call this again if rotation changes
            // during the lifecycle of this use case
            // .setTargetResolution(Size(1088, 1088))
            .setTargetRotation(rotation)
            .build()


        // Must unbind the use-cases before rebinding them
        cameraProvider.unbindAll()

        try {
            // A variable number of use-cases can be passed here -
            // camera provides access to CameraControl & CameraInfo
            camera = cameraProvider.bindToLifecycle(
                this, cameraSelector, preview, imageCapture
            )


            // Attach the viewfinder's surface provider to preview use case
            preview?.setSurfaceProvider(viewFinder.createSurfaceProvider())
        } catch (exc: Exception) {
            Log.e(TAG, "Use case binding failed", exc)
        }
    }

    /**
     *  [androidx.camera.core.ImageAnalysisConfig] requires enum value of
     *  [androidx.camera.core.AspectRatio]. Currently it has values of 4:3 & 16:9.
     *
     *  Detecting the most suitable ratio for dimensions provided in @params by counting absolute
     *  of preview ratio to one of the provided values.
     *
     *  @param width - preview width
     *  @param height - preview height
     *  @return suitable aspect ratio
     */
    private fun aspectRatio(width: Int, height: Int): Int {
        val previewRatio = max(width, height).toDouble() / min(width, height)
        if (abs(previewRatio - RATIO_4_3_VALUE) <= abs(previewRatio - RATIO_16_9_VALUE)) {
            return AspectRatio.RATIO_4_3
        }
        return AspectRatio.RATIO_16_9
    }

    /** Method used to re-draw the camera UI controls, called every time configuration changes. */
    private fun updateCameraUi() {

        // Remove previous UI if any
        container.findViewById<ConstraintLayout>(R.id.camera_ui_container)?.let {
            container.removeView(it)
        }

        // Inflate a new view containing all UI for controlling the camera
        val controls = View.inflate(requireContext(), R.layout.camera_ui_container, container)

        // In the background, load latest photo taken (if any) for gallery thumbnail
        lifecycleScope.launch(Dispatchers.IO) {
            outputDirectory.listFiles()?.max()?.let {
                setGalleryThumbnail(Uri.fromFile(it))
            }
        }

        controls.findViewById<AppCompatButton>(R.id.btn_search).setOnClickListener {
            it.isEnabled = false
            AddFoodActivity.startForFoodResult(this,this.selectedMeal)
            it.isEnabled = true

        }


        // Listener for button used to capture photo
        controls.findViewById<ImageButton>(R.id.camera_capture_button).setOnClickListener {

            // Get a stable reference of the modifiable image capture use case
            imageCapture?.let { imageCapture ->

                // Create output file to hold the image
                val photoFile = createFile(outputDirectory, FILENAME, PHOTO_EXTENSION)

                // Setup image capture metadata
                val metadata = ImageCapture.Metadata().apply {

                    // Mirror image when using the front camera
                    isReversedHorizontal = lensFacing == CameraSelector.LENS_FACING_FRONT
                }

                // Create output options object which contains file + metadata
                val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile)
                    .setMetadata(metadata)

                    .build()



                // Setup image capture listener which is triggered after photo has been taken
                imageCapture.takePicture(
                    outputOptions, cameraExecutor, object : ImageCapture.OnImageSavedCallback {
                        override fun onError(exc: ImageCaptureException) {
                            Log.e(TAG, "Photo capture failed: ${exc.message}", exc)
                        }

                        override fun onImageSaved(output: ImageCapture.OutputFileResults) {
                            val savedUri = output.savedUri ?: Uri.fromFile(photoFile)

                            Log.d(TAG, "Photo capture succeeded: $savedUri")

                            if(DeviceUtil.isEmulator())
                            {
                                copyFiletoExternalStorage(R.raw.test_food_image,savedUri.toFile())
                            }


                            imageResizer.resizeBitmapFile(
                                savedUri.toFile(),
                                savedUri.toFile(),
                                FoodLensConfig.imageSize.width,
                                FoodLensConfig.imageSize.height,
                                ImageResizer.ScaleType.RESIZE_AND_CROP,
                                false
                            )
                            onImageTaken(savedUri)

                        }

                    })


/*
                imageCapture.takePicture(cameraExecutor,object :
                    ImageCapture.OnImageCapturedCallback() {

                    override fun onCaptureSuccess(image: ImageProxy) {
                        // Use the image, then make sure to close it.
                        val bitmap = image.convertImageProxyToBitmap()
                        val stream = ByteArrayOutputStream()
                        bitmap.compress(
                            Bitmap.CompressFormat.JPEG,
                            100,
                            stream
                        ) //100 is the best quality possibe
                        val bytes = stream.toByteArray()
                      // val bytes = cropImage(bitmap,viewFinder,cross_hair)
                        val photoFile = createFile(outputDirectory, FILENAME, PHOTO_EXTENSION)
                        try {
                            if (!photoFile.exists()) {
                                photoFile.createNewFile()
                            }
                            val fos = FileOutputStream(photoFile)
                            fos.write(bytes)
                            fos.close()
                        } catch (e: java.lang.Exception) {
                            Log.e(TAG, e.message!!)
                        }
                        onImageTaken(Uri.fromFile(photoFile))

                    }

                    override fun onError(exception: ImageCaptureException) {

                        Log.e(TAG, "Photo capture failed: ${exception.message}", exception)
                    }
                })

*/

                // We can only change the foreground Drawable using API level 23+ API
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                    // Display flash animation to indicate that photo was captured
                    container.postDelayed({
                        container.foreground = ColorDrawable(Color.WHITE)
                        container.postDelayed(
                            { container.foreground = null }, ANIMATION_FAST_MILLIS
                        )
                    }, ANIMATION_SLOW_MILLIS)
                }
            }
        }

/*
        // Listener for button used to view the most recent photo
        controls.findViewById<ImageButton>(R.id.photo_view_button).setOnClickListener {
            // Only navigate when the gallery has photos
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, GALLERY_IMAGE_REQUEST_CODE)
        }
        */
 */
    }

    private  fun onImageTaken(savedUri:Uri)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // Update the gallery thumbnail with latest picture taken
            setGalleryThumbnail(savedUri)
        }


        // Implicit broadcasts will be ignored for devices running API level >= 24
        // so if you only target API level 24+ you can remove this statement
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            requireActivity().sendBroadcast(
                Intent(android.hardware.Camera.ACTION_NEW_PICTURE, savedUri)
            )
        }

        // If the folder selected is an external media directory, this is
        // unnecessary but otherwise other apps will not be able to access our
        // images unless we scan them using [MediaScannerConnection]
        val mimeType = MimeTypeMap.getSingleton()
            .getMimeTypeFromExtension(savedUri.toFile().extension)
        MediaScannerConnection.scanFile(
            context,
            arrayOf(savedUri.toFile().absolutePath),
            arrayOf(mimeType)
        ) { _, uri ->
            Log.d(TAG, "Image capture scanned into media store: $uri")
        }



        if(!InternetReachabilityManager.isOnline())
        {

            requireActivity().runOnUiThread {
                Toast.makeText(
                    requireContext(),
                    requireContext().getString(R.string.no_internet),
                    Toast.LENGTH_LONG
                ).show()
            }
            return
        }

        val bundle = Bundle()
        bundle.putString(ResultActivity.IMAGE_FILE_KEY, savedUri.toString())
        FoodLens.lastAuthorizedInstance?.let {
            it.launchImageRecognizationActivityForResult(
                requireActivity(),
                options = bundle
            )
        } ?: kotlin.run {
            progressBar.show()
            FoodLens.renewToken { b: Boolean, exception: java.lang.Exception? ->
                progressBar.hide()
                if (b) {
                    FoodLens.lastAuthorizedInstance?.let {
                        it.launchImageRecognizationActivityForResult(
                            requireActivity(),
                            options = bundle
                        )
                    }
                } else {
                    requireActivity().runOnUiThread {
                        Toast.makeText(
                            requireContext(),
                            "Error renewing token ${exception.toString()}",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }

    }


    private fun copyFiletoExternalStorage(
        resourceId: Int,
        filePath: File
    ) {

        try {
            val `in`: InputStream = resources.openRawResource(resourceId)
            var out: FileOutputStream? = null
            out = FileOutputStream(filePath)
            val buff = ByteArray(1024)
            var read = 0
            try {
                while (`in`.read(buff).also({ read = it }) > 0) {
                    out.write(buff, 0, read)
                }
            } finally {
                `in`.close()
                out.close()
            }
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }


    private fun cropImage(bitmap: Bitmap, frame: View, reference: View): ByteArray {
        val heightOriginal = frame.height
        val widthOriginal = frame.width
        val heightFrame = reference.height
        val widthFrame = reference.width
        val leftFrame = reference.left
        val topFrame = reference.top
        val heightReal = bitmap.height
        val widthReal = bitmap.width
        val widthFinal = widthFrame * widthReal / widthOriginal
        val heightFinal = heightFrame * heightReal / heightOriginal
        val leftFinal = leftFrame * widthReal / widthOriginal
        val topFinal = topFrame * heightReal / heightOriginal
        val bitmapFinal = Bitmap.createBitmap(
            bitmap,
            leftFinal, topFinal, widthFinal, heightFinal
        )

        val stream = ByteArrayOutputStream()
        bitmapFinal.compress(
            Bitmap.CompressFormat.JPEG,
            100,
            stream
        ) //100 is the best quality possibe
        return stream.toByteArray()
    }

    fun ImageProxy.convertImageProxyToBitmap(): Bitmap {
        val buffer = planes[0].buffer
        buffer.rewind()
        val bytes = ByteArray(buffer.capacity())
        buffer.get(bytes)
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
    }

    /** Returns true if the device has an available back camera. False otherwise */
    private fun hasBackCamera(): Boolean {
        return cameraProvider?.hasCamera(CameraSelector.DEFAULT_BACK_CAMERA) ?: false
    }

    /** Returns true if the device has an available front camera. False otherwise */
    private fun hasFrontCamera(): Boolean {
        return cameraProvider?.hasCamera(CameraSelector.DEFAULT_FRONT_CAMERA) ?: false
    }


    companion object {

        private const val TAG = "CameraFragment"
        private const val FILENAME = "yyyy-MM-dd-HH-mm-ss-SSS"
        private const val PHOTO_EXTENSION = ".jpg"
        private const val RATIO_4_3_VALUE = 4.0 / 3.0
        private const val RATIO_16_9_VALUE = 16.0 / 9.0
        private const val GALLERY_IMAGE_REQUEST_CODE = 9007

        /** Helper function used to create a timestamped file */
        private fun createFile(baseFolder: File, format: String, extension: String) =
            File(
                baseFolder, SimpleDateFormat(format, Locale.US)
                    .format(System.currentTimeMillis()) + extension
            )
    }

}