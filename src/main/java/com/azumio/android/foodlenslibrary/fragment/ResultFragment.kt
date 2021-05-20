package com.azumio.android.foodlenslibrary.fragment

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.os.bundleOf
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.azumio.android.foodlenslibrary.R
import com.azumio.android.foodlenslibrary.activity.AddFoodActivity
import com.azumio.android.foodlenslibrary.activity.AddFoodActivity.FIND_FOOD_REQUEST_CODE
import com.azumio.android.foodlenslibrary.adapter.*
import com.azumio.android.foodlenslibrary.api.Resource
import com.azumio.android.foodlenslibrary.api.Status
import com.azumio.android.foodlenslibrary.common.DataWrapper
import com.azumio.android.foodlenslibrary.model.FoodSegment
import com.azumio.android.foodlenslibrary.model.FoodSuggestionCategory
import com.azumio.android.foodlenslibrary.model.SegmentResponse
import com.azumio.android.foodlenslibrary.utils.CaloriesManager
import com.azumio.android.foodlenslibrary.utils.FileUtils
import com.azumio.android.foodlenslibrary.utils.datetime.MealTimeHelper
import com.azumio.android.foodlenslibrary.viewModel.ResultViewModel
import com.azumio.android.foodlenslibrary.viewModel.ResultViewModelModelFactory
import com.azumio.android.foodlenslibrary.views.SegmentView
import com.azumio.android.foodlenslibrary.views.SegmentViewMode
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.orhanobut.logger.Logger.i
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator
import kotlinx.android.synthetic.main.foodlens_result_bottom_sheet.*
import kotlinx.android.synthetic.main.foodlens_result_fragment.*



class ResultFragment : Fragment() {

    companion object {
        fun newInstance(imageURI: Uri, meal: String?): ResultFragment {
            val resultFragment = ResultFragment()
            resultFragment.arguments = bundleOf(Pair(IMAGE_URI_ARGUMENT, imageURI), Pair(MEAL_ARGUMENT, meal))
            return resultFragment
        }
        private const val MEAL_ARGUMENT = "MEAL_ARGUMENT"
        private const val IMAGE_URI_ARGUMENT = "IMAGE_URI_ARGUMENT"
        private const val TAG = "ResultFragment"
        private const val SERVING_SIZE_DIALOG_TAG = "SERVING_SIZE_DIALOG_TAG"
    }

    private lateinit var adapter: ResultListAdapter
    private lateinit var imageURI: Uri
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>
    private lateinit var root : View
    var selectedMeal = MealTimeHelper.getMealLabelByTimeOfDay()

    private val viewModel by viewModels<ResultViewModel> {
        ResultViewModelModelFactory(this.imageURI)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        loadArguments()

        if(!this::root.isInitialized) {
            root = inflater.inflate(R.layout.foodlens_result_fragment, container, false)
        }
        return root
    }

    private fun loadArguments() {
        this.imageURI = arguments?.getParcelable(IMAGE_URI_ARGUMENT) ?: Uri.EMPTY
        val defaultMeal = MealTimeHelper.getMealLabelByTimeOfDay()
        this.selectedMeal = arguments?.getString(MEAL_ARGUMENT, defaultMeal) ?: defaultMeal
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (!this::adapter.isInitialized)
        {
            setupUI()
            setupObservers()
        }
    }

    private fun setupUI() {
        setupBottomSheet()
        setupRecycler()
        Glide.with(this).asBitmap().load(imageURI).into(object : CustomTarget<Bitmap>() {
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                food_image.setImageBitmap(resource)
            }

            override fun onLoadCleared(placeholder: Drawable?) {

            }
        })
        image_container.isLongClickable = true
        image_container.isClickable = true



    }
    private var longPressEvent:List<Float>? = null
    private  fun setupListeners()
    {
        image_container.setOnLongClickListener {
                longPressEvent?.let {
                    addSegment(it[0],it[1])
                }
            return@setOnLongClickListener true
        }
        /*
        image_container.onLongPressedListerner = object : ImageContainerLayout.LongPressedListerner{
            override fun onLongPressed(x: Float, y: Float) {
                i("onLongPressedListerner")
                addSegment(x,y)
            }

        }

         */



        image_container.setOnTouchListener { p0, p1 ->
            i("setOnTouchListener$p1.x,$p1.y")
            longPressEvent = listOf( p1.x,p1.y )
            if(image_container.alpha <= 0.5f) {
                addSegment(p1.x,p1.y)
                return@setOnTouchListener true
            }
            else
            {
                setSegmentsMode(SegmentViewMode.NORMAL)
                return@setOnTouchListener false
            }
        }



        add_more_button.setOnClickListener {

            add_more_container.alpha = 0.0f
            add_more_container.visibility = View.VISIBLE
            add_more_container.animate().apply {
                interpolator = LinearInterpolator()
                duration = 500
                alpha(1f)
                start()
            }
            save_button_container.visibility = View.GONE
            image_container.alpha = 0.5f

            Handler(Looper.getMainLooper()).postDelayed({
                if(lifecycle.currentState.isAtLeast(Lifecycle.State.CREATED)) {
                    if(image_container.alpha <= 0.5f) {
                        add_more_container.visibility = View.GONE
                        save_button_container.visibility = View.VISIBLE
                        image_container.alpha = 1.0f
                    }
                }

            }, 5000) //millis
        }

        save_meal_button.setOnClickListener {

            val logs = viewModel.foodSegments.value?.flatMap { it.foodLogs }
            if((logs?.size ?: 0) <=0)
            {
                val dialogBuilder = AlertDialog.Builder(requireActivity())
                dialogBuilder.setMessage(requireActivity().getString( R.string.foodlens_no_item_logged))
                    .setCancelable(false)
                    .setPositiveButton(requireActivity().getString( R.string.foodlens_action_ok), DialogInterface.OnClickListener { dialog, id ->
                        dialog.cancel()
                    })

                val alert = dialogBuilder.create()
                alert.setTitle("")
                alert.show()

            }
            else {

                val fragment = ReviewFragment.newInstance(
                    this.imageURI,
                    viewModel.foodSegments.value ?: emptyList(),
                    this.viewModel.imageCacheId
                )
                fragment.selectedMeal = selectedMeal

                requireActivity().supportFragmentManager.commit {
                    setCustomAnimations(
                        R.anim.foodlens_slide_in,
                        R.anim.foodlens_fade_out,
                        R.anim.foodlens_fade_in,
                        R.anim.foodlens_slide_out
                    )
                    replace(R.id.fragment_container, fragment)
                    addToBackStack(ReviewFragment.TAG)
                }
            }

        }

        //#3 Listening to State Changes of BottomSheet
        bottomSheetBehavior.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(bs: View, slideOffset: Float) {
            //    Log.i(TAG, "offset=$slideOffset")
                /*
                val maxHeight =bottomSheet.maxHeight
                val bottomMargin =
                    resources.getDimensionPixelSize(R.dimen.result_bottom_sheet_peek_height)

                val params: CoordinatorLayout.LayoutParams =
                    image_container.layoutParams as CoordinatorLayout.LayoutParams
                params.topMargin =
                    -(((maxHeight - bottomMargin) * slideOffset)).toInt()
                 image_container.layoutParams = params
              //  redrawSegments()

                 */
            }

            override fun onStateChanged(bottomSheet: View, newState: Int) {

            }
        })

    }

    private  fun addSegment(x: Float, y: Float)
    {
        i("touch$x-$y")
        add_more_container.visibility = View.GONE
        save_button_container.visibility = View.VISIBLE
        image_container.alpha = 1.0f

        val imageSizePixel = FileUtils.sizeOfImageAtPath(imageURI)
        val inverse = Matrix()
        food_image.imageMatrix.invert(inverse)
        val touchPoint = floatArrayOf(x, y)
        inverse.mapPoints(touchPoint)
        val xCoord = touchPoint[0]/imageSizePixel.width
        val yCoord = touchPoint[1]/imageSizePixel.height

        val center = SegmentResponse.TraceSegment.Center(xCoord.toDouble(),yCoord.toDouble())
        viewModel.newSegment.value?.let {
            removeUISegment(it)
        }
        viewModel.createNewSegment(center)
    }

    private fun setupRecycler() {
        this.adapter = ResultListAdapter(emptyList(),onHeaderClick = {
                this.selectFoodGroup(it)
        },onEditServingClick = { item ->

            activity?.supportFragmentManager?.let { fragManager ->

                val  foodItem = when( item)
                 {
                     is ResultListSelectedFoodItem -> item.item.underlyingFoodLog
                     is ResultListFoodItem -> item.item.foodItem
                    else -> null
                } !!


               val dialogue = ServingSizeDialog.newInstance(foodItem.numberOfServings,foodItem.servingSizes,foodItem.servingSize ?: foodItem.servingSizes.first())
                dialogue.listener = object : ServingSizeDialog.ServingSizeDialogListener{
                    override fun onSelect(
                        serving: SegmentResponse.FoodItem.ServingSize,
                        numberOfServings: Double
                    ) {

                        viewModel.servingSizeEdited(serving,numberOfServings,item)

                    }

                }
                dialogue.show(fragManager,
                    SERVING_SIZE_DIALOG_TAG)
            }


        },onSearchClick = {
            AddFoodActivity.startForFoodResult(this,this.selectedMeal)
        })
        val foodLayoutManager =
            LinearLayoutManager(recgonized_list.context, RecyclerView.VERTICAL, false)

          recgonized_list.layoutManager = foodLayoutManager

          recgonized_list.adapter = this.adapter

        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun getMovementFlags(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ): Int {


                val position = viewHolder.adapterPosition
                val adapter = recgonized_list.adapter as ResultListAdapter
                if(position >= adapter.data.size)
                {
                    return  0
                }

                return when (adapter.data[position]) {
                    is ResultListFoodItem -> {
                        makeMovementFlags(0, ItemTouchHelper.RIGHT)
                    }
                    is ResultListSelectedFoodItem -> {
                        makeMovementFlags(0, ItemTouchHelper.LEFT)
                    }
                    else -> {
                        0
                    }
                }
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val adapter = recgonized_list.adapter as ResultListAdapter
                if (direction == ItemTouchHelper.RIGHT) {
                    (adapter.data[position] as? ResultListFoodItem)?.let {
                        adapter.notifyItemRemoved(position)
                        viewModel.logItem(it.item.foodItem,category = it.category)

                    }
                } else {
                    (adapter.data[position] as? ResultListSelectedFoodItem)?.let {
                        adapter.notifyItemRemoved(position)
                        viewModel.removeLog(it.item)

                    }
                }
            }


            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {

                RecyclerViewSwipeDecorator.Builder(c,recyclerView,viewHolder,dX,dY,actionState,isCurrentlyActive)
                    .addSwipeLeftActionIcon(R.drawable.foodlens_ic_food_log_delete)
                    .addSwipeLeftBackgroundColor(recyclerView.context.getColor(R.color.foodlens_food_log_delete_color))
                    .addSwipeRightActionIcon(R.drawable.foodlens_ic_food_log_check)
                    .addSwipeRightBackgroundColor(recyclerView.context.getColor(R.color.foodlens_food_log_swipe_color))
                    .create()
                    .decorate()

                super.onChildDraw(
                    c,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                )

            }
        }

        val helper = ItemTouchHelper(callback)
        helper.attachToRecyclerView(recgonized_list)

    }

    fun onItemUpdated()
    {
        save_button_container.visibility = View.VISIBLE
        viewModel.foodSegments.value?.firstOrNull { it.identifier == viewModel.selectedSegment.value?.identifier }?.let { seg ->
            getUISegment(seg)?.let {
                selectUISegment(it)
            }
        }
        selectNextUISegment()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == FIND_FOOD_REQUEST_CODE)
        {
            data?.getSerializableExtra(CaloriesManager.PROPERTY_DATA)?.let {
                it as DataWrapper
                viewModel.onSearchFood(it)
            }
        }

    }





    private fun setupBottomSheet() {
        //#2 Initializing the BottomSheetBehavior
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)


        bottomSheet.maxHeight = (getScreenHeight() * 0.50).toInt()
    }

    private  fun redrawSegments()
    {
        val height = requireContext().resources.getDimension(R.dimen.foodlens_segment_height)
        val imageSizePixel = FileUtils.sizeOfImageAtPath(imageURI)
        (image_container as ViewGroup).children.iterator().forEach {
            (it as? SegmentView)?.let { view ->

                val segment = view.foodSegment!!
                val coords = floatArrayOf(
                    ((imageSizePixel.width * segment.center.x).toFloat()),
                    (imageSizePixel.height * segment.center.y).toFloat()
                )
                val matrix = food_image.imageMatrix
                matrix.mapPoints(coords)

               var params =  view.layoutParams as ConstraintLayout.LayoutParams
                params.topMargin = (coords[1].toInt() - (height/2).toInt())
                view.layoutParams =  params

            }
        }
    }

    var segmentsLoaded = false
    private fun setupObservers() {

        viewModel.isNotFood.observe(
            requireActivity(),
            Observer { if (it == true) showNotFoodDialogue() })
        viewModel.foodSegments.observe(viewLifecycleOwner, Observer {
            if(!segmentsLoaded) {
                loadUIWithSegments(it)
                segmentsLoaded = true
            }

        })

        viewModel.selectedSegment.observe(viewLifecycleOwner, Observer {
            it?.let { food -> selectUISegment(food) }
        })

        viewModel.selectedItemState.observe(viewLifecycleOwner, Observer {
            onItemUpdated()
        })

        viewModel.segmentDeleted.observe(viewLifecycleOwner, Observer {
           it?.let { seg -> removeUISegment(seg) }
        })

        viewModel.newSegment.observe(viewLifecycleOwner, Observer {
            it?.let { foodSegment ->

                i("newSegment.observe")
                if(foodSegment.hasCategories)
                {
                        loadUIWithSegments(listOf(foodSegment))
                }
                else
                {
                    loadUIWithSegments(listOf(foodSegment))
                    viewModel.recognizeSegment(foodSegment).observe(viewLifecycleOwner, Observer {res ->
                        setupLoadingResource(res)
                    })
                }
            }

        })

        viewModel.allSegmentsDeleted.observe(viewLifecycleOwner, Observer {
            adapter.setData(listOf(ResultListFooterItem(title = requireContext().getString(R.string.foodlens_nothing_was_logged))))
            adapter.notifyDataSetChanged()
        })


        setupLoadingSegmentsObserver()
    }


    fun getScreenHeight(): Int {
        val displayMetrics = view?.context?.resources?.displayMetrics?.let {
            return it.heightPixels
        }
         return 0

    }
    private fun setupLoadingSegmentsObserver() {
        viewModel.recognizeImage().observe(viewLifecycleOwner, Observer { it ->
            it?.let { resource ->
                setupLoadingResource(resource)
                when (resource.status) {
                    Status.SUCCESS -> {
                        setupListeners()
                    }
                    Status.ERROR -> {
                        setupListeners()
                    }
                    else -> {}
                }
            }
        })
    }
   private var saveButtonVisibility : Int = View.GONE
    private  fun setupLoadingResource(resource:Resource<Boolean>)
    {

        when (resource.status) {
            Status.SUCCESS -> {
                bottomSheetBehavior.setPeekHeight(requireContext().resources.getDimension(R.dimen.foodlens_result_bottom_sheet_peek_height).toInt() ,true)
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
                recgonized_list.visibility = View.VISIBLE
                image_container.isEnabled = true
                image_container.isClickable = true
                progressBar.visibility = View.GONE
                save_button_container.visibility = saveButtonVisibility
            }
            Status.ERROR -> {
                bottomSheetBehavior.setPeekHeight(requireContext().resources.getDimension(R.dimen.foodlens_result_bottom_sheet_peek_height).toInt() ,true)
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
                progressBar.visibility = View.GONE
                recgonized_list.visibility = View.VISIBLE
                image_container.isEnabled = true
                image_container.isClickable = true
                save_button_container.visibility = saveButtonVisibility
                showNotFoodDialogue()
                  //  Toast.makeText(requireActivity(), resource.msg, Toast.LENGTH_LONG).show()
            }
            Status.LOADING -> {
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
                bottomSheetBehavior.setPeekHeight(requireContext().resources.getDimension(R.dimen.foodlens_result_bottom_sheet_loading_peek_height).toInt() ,true)
                progressBar.visibility = View.VISIBLE
                recgonized_list.visibility = View.GONE
                image_container.isEnabled = false
                image_container.isClickable = false
                saveButtonVisibility = save_button_container.visibility
                save_button_container.visibility = View.GONE
            }
        }
    }

    private fun showNotFoodDialogue() {
        val dialogBuilder = AlertDialog.Builder(requireActivity())
        dialogBuilder.setMessage(requireActivity().getString( R.string.foodlens_not_food_message))
            .setCancelable(false)
            // negative button text and action
            .setNegativeButton(requireActivity().getString( R.string.foodlens_retake_photo), DialogInterface.OnClickListener { dialog, id ->
                activity?.finish()
            })
        val alert = dialogBuilder.create()
        alert.setTitle("")
        alert.show()
    }


    private fun showDeleteDialogue(seg:FoodSegment) {
        val dialogBuilder = AlertDialog.Builder(requireActivity())
        dialogBuilder.setMessage(requireActivity().getString( R.string.foodlens_delete_segment_message))
            .setCancelable(false)
            .setPositiveButton(requireActivity().getString(R.string.foodlens_yes), DialogInterface.OnClickListener { dialog, id ->
                viewModel.deleteSegment(seg)
            })
            // negative button text and action
            .setNegativeButton(requireActivity().getString(R.string.foodlens_no), DialogInterface.OnClickListener { dialog, id ->

            })
        val alert = dialogBuilder.create()
        alert.setTitle("")
        alert.show()
    }

    private fun getUISegment(segment: FoodSegment):SegmentView?
    {
        (image_container as ViewGroup).children.iterator().forEach {
            (it as? SegmentView)?.let { view ->
                if(view.foodSegment?.identifier == segment.identifier)
                {
                    return  view

                }
            }
        }
        return null
    }

    private fun selectUISegment(segment: FoodSegment)
    {
       getUISegment(segment)?.let { selectUISegment(it) }
    }


    private  fun selectFoodGroup(result:FoodSuggestionCategory)
    {
        viewModel.selectedSegment.value?.let {
            getUISegment(it)?.let { view ->
                view.setMode(SegmentViewMode.SELECTED)
            }
        }
        adapter.setFoodItems(result.suggestions.map { ResultListFoodItem(item = it,category = result) })
        adapter.notifyDataSetChanged()
    }

    private fun selectSegment(segment:FoodSegment, selectedGroup:String)
    {
        val list = mutableListOf<ResultListBaseItem>()

        val foodLogs = viewModel.foodSegments.value?.flatMap { it.foodLogs } ?: emptyList()

       list.addAll( foodLogs.map {
            ResultListSelectedFoodItem(item = it)
        })

        list.add(ResultListHeaderItem(item = segment.categories))
        segment.categories.firstOrNull { it.group == selectedGroup }?.let { cat ->
            list.addAll(cat.suggestions.filter { sug ->  foodLogs.filter { log -> log.underlyingFoodLog.id == sug.foodItem.id && log.categoryId == cat.identifier }.count() < 1 } .map { item -> ResultListFoodItem(item = item,category = cat) })
        }
        adapter.setData(list)
        adapter.notifyDataSetChanged()
    }

    private fun  selectNextUISegment()
    {
        viewModel.foodSegments.value?.firstOrNull { it.foodLogs.count() <=0 }?.let {
            viewModel.selectedSegment.postValue(it)
        } ?: kotlin.run {
            val datalist = mutableListOf<ResultListBaseItem>()
            adapter.data.filterIsInstance<ResultListSelectedFoodItem>().let {
                datalist.addAll(it)
            }
            datalist.add(ResultListFooterItem(title = requireContext().getString(R.string.foodlens_all_items_logged)))
            adapter.setData(datalist)
            adapter.notifyDataSetChanged()
        }
    }

    private  fun selectUISegment(view:SegmentView)
    {
        setSegmentsMode(SegmentViewMode.NORMAL)
        view.setMode(SegmentViewMode.SELECTED)
        view.foodSegment?.let { segment ->
            selectSegment(segment,segment.categories?.firstOrNull()?.group ?: "")
        }

    }

    private fun setSegmentsMode(segmentViewMode: SegmentViewMode)
    {
        (image_container as ViewGroup).children.iterator().forEach {
            (it as? SegmentView)?.let { view ->
                view.setMode(segmentViewMode)
            }
        }
    }

    private  fun removeUISegment(segment:FoodSegment)
    {
        var viewToRemove:View? = null
        (image_container as ViewGroup).children.iterator().forEach {
            (it as? SegmentView)?.let { view ->
               if( view.foodSegment?.identifier == segment.identifier)
               {
                  viewToRemove = view
               }
            }
        }
        viewToRemove?.let {
            (image_container as ViewGroup).removeView(it)
        }
    }

    private fun loadUIWithSegments(segments: List<FoodSegment>) {

        bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED

        val height = requireContext().resources.getDimension(R.dimen.foodlens_segment_height)
        val width = requireContext().resources.getDimension(R.dimen.foodlens_segment_width)

        val imageSizePixel = FileUtils.sizeOfImageAtPath(imageURI)
        val parentLayout = image_container as ConstraintLayout
        segments.forEach {

            removeUISegment(it)
            val segmentView = SegmentView(requireContext())
            segmentView.foodSegment = it
            val coords = floatArrayOf(
                ((imageSizePixel.width * it.center.x).toFloat()),
                (imageSizePixel.height * it.center.y).toFloat()
            )
            val matrix = food_image.imageMatrix
            matrix.mapPoints(coords)
            Log.i(TAG, matrix.toString())

            val set = ConstraintSet()
            segmentView.id = View.generateViewId()
            parentLayout.addView(segmentView)
            set.clone(parentLayout)
            set.connect(
                segmentView.id,
                ConstraintSet.START,
                parentLayout.id,
                ConstraintSet.START,
                (coords[0].toInt() - (width/2 ).toInt())
            )
            set.connect(
                segmentView.id,
                ConstraintSet.TOP,
                parentLayout.id,
                ConstraintSet.TOP,
                (coords[1].toInt() - (height/2).toInt())
            )
            set.applyTo(parentLayout)
            segmentView.setOnClickListener { view ->

                if(segmentView.getMode() == SegmentViewMode.DELETE)
                {
                    segmentView.foodSegment?.let { seg ->
                       showDeleteDialogue(seg)
                    }

                }
                else {

                    viewModel.selectedSegment.postValue((view as SegmentView).foodSegment)
                }
            }
            segmentView.setOnLongClickListener { _ ->
               setSegmentsMode(SegmentViewMode.DELETE)
                return@setOnLongClickListener true
            }
        }

       viewModel.selectedSegment.postValue(segments.firstOrNull())
        bottomSheet.postDelayed({
            if(lifecycle.currentState.isAtLeast(Lifecycle.State.CREATED)) {
                setSegmentsMode(SegmentViewMode.NORMAL)
                adapter.unselectHeaders()
            }
        },50)

    }

}
