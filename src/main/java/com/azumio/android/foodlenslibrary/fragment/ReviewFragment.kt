package com.azumio.android.foodlenslibrary.fragment

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.azumio.android.foodlenslibrary.FoodLens.Companion.FOODLENS_FOOD_CHECKIN
import com.azumio.android.foodlenslibrary.R
import com.azumio.android.foodlenslibrary.model.FoodSegment
import com.azumio.android.foodlenslibrary.utils.CaloriesManager
import com.azumio.android.foodlenslibrary.utils.FileUtils
import com.azumio.android.foodlenslibrary.viewModel.ReviewViewModel
import com.azumio.android.foodlenslibrary.viewModel.ReviewViewModelFactory
import com.azumio.android.foodlenslibrary.views.SegmentView
import com.azumio.android.foodlenslibrary.views.SegmentViewMode
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import kotlinx.android.synthetic.main.foodlens_fragment_review.*
import kotlinx.android.synthetic.main.foodlens_result_fragment.food_image
import kotlinx.android.synthetic.main.foodlens_result_fragment.image_container
import kotlinx.coroutines.*

class ReviewFragment constructor() : Fragment() {

    private lateinit var imageURI: Uri
    private lateinit var imageCacheId: String
    private lateinit var foodSegments: List<FoodSegment>
    lateinit var selectedMeal:String
    companion object {
        fun newInstance(imageURI: Uri, foodSegments: List<FoodSegment>,imageCacheId:String) =
            ReviewFragment(imageURI, foodSegments,imageCacheId)

        const val TAG = "ReviewFragment"

    }

    private val viewModel by viewModels<ReviewViewModel> {
        ReviewViewModelFactory(this.imageURI, this.foodSegments,this.imageCacheId)
    }

    constructor(imageURI: Uri, foodSegments: List<FoodSegment>,imageCacheId:String) : this() {
        this.imageURI = imageURI
        this.foodSegments = foodSegments
        this.imageCacheId = imageCacheId
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.foodlens_fragment_review, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupObservers()
        viewModel.loadNutrientProgress()
    }

    private fun setupUI() {

        Glide.with(this).asBitmap().load(imageURI).into(object : CustomTarget<Bitmap>() {
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                food_image.setImageBitmap(resource)
                GlobalScope.launch {
                    delay(100)
                    withContext(Dispatchers.Main) {
                        loadUIWithSegments(foodSegments)
                    }
                }

            }

            override fun onLoadCleared(placeholder: Drawable?) {

            }
        })

        val data = CaloriesManager.MEAL_ORDER.map { it.toUpperCase() }
        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item, data
        )
        spinner_meal.adapter = adapter
        spinner_meal.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {
                selectedMeal = CaloriesManager.MEAL_ORDER[position]
            }
            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }

        spinner_meal.setSelection( CaloriesManager.MEAL_ORDER.indexOf(selectedMeal))
        looks_good_button.setOnClickListener {
            viewModel.processData(selectedMeal)
        }



    }

    private fun setupObservers() {


        viewModel.nutrientSummary.observe(requireActivity(), androidx.lifecycle.Observer { summary ->
            summary.firstOrNull { it.nutrientTag == ReviewViewModel.TOTAL_FAT }?.let {
                fats_value.text = "${it.value.toInt()}g"
            }
            summary.firstOrNull { it.nutrientTag == ReviewViewModel.PROTEIN }?.let {
                protiens_value.text = "${it.value.toInt()}g"
            }
            summary.firstOrNull { it.nutrientTag == ReviewViewModel.TOTAL_CARBS }?.let {
                carbs_value.text = "${it.value.toInt()}g"
            }
            summary.firstOrNull { it.nutrientTag == ReviewViewModel.DIETARY_FIBER }?.let {
                fibers_value.text = "${it.value.toInt()}g"
            }
            summary.firstOrNull { it.nutrientTag == ReviewViewModel.CALORIES }?.let {
                cal_value.text = "${it.value.toInt()}"
            }
        })

        viewModel.checkin.observe(requireActivity(), androidx.lifecycle.Observer {
            val intent = Intent()
            intent.putExtra(FOODLENS_FOOD_CHECKIN,it.jsonString())
            activity?.setResult(Activity.RESULT_OK, intent)
            activity?.finish()
        })
    }

    private fun loadUIWithSegments(segments: List<FoodSegment>) {
        val height = requireContext().resources.getDimension(R.dimen.foodlens_segment_height)
        val width = requireContext().resources.getDimension(R.dimen.foodlens_segment_width)

        val imageSizePixel = FileUtils.sizeOfImageAtPath(imageURI)
        val parentLayout = image_container as ConstraintLayout
        segments.forEach {
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
                (coords[0].toInt() - (width / 2).toInt())
            )
            set.connect(
                segmentView.id,
                ConstraintSet.TOP,
                parentLayout.id,
                ConstraintSet.TOP,
                (coords[1].toInt() - (height / 2).toInt())
            )
            set.applyTo(parentLayout)
            segmentView.setMode(SegmentViewMode.NORMAL)
        }

    }

}