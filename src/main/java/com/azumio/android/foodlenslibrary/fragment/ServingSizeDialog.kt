package com.azumio.android.foodlenslibrary.fragment

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.TextView.OnEditorActionListener
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.azumio.android.foodlenslibrary.R
import com.azumio.android.foodlenslibrary.adapter.ServingSizeHorizontalAdapter
import com.azumio.android.foodlenslibrary.model.SegmentResponse
import com.azumio.android.foodlenslibrary.utils.CaloriesManager
import kotlinx.android.synthetic.main.foodlens_fragment_serving_size_dialog.*

class ServingSizeDialog constructor() : DialogFragment() {
     lateinit var listener: ServingSizeDialogListener

    interface ServingSizeDialogListener {
        fun onSelect(serving:SegmentResponse.FoodItem.ServingSize,numberOfServings: Double)
    }

    private lateinit var adapter: ServingSizeHorizontalAdapter
    private  var numberOfServings: Double = CaloriesManager.NUMBER_OF_SERVINGS
    private  lateinit var servingSizes: List<SegmentResponse.FoodItem.ServingSize>
    private  lateinit var currentServing: SegmentResponse.FoodItem.ServingSize

    constructor(numberOfServings: Double,servingSizes:List<SegmentResponse.FoodItem.ServingSize>,selectedServing:SegmentResponse.FoodItem.ServingSize) :this() {
       this.numberOfServings = numberOfServings
        this.servingSizes = servingSizes
        this.currentServing = selectedServing
    }


    private fun setupUI() {

        adapter =
           ServingSizeHorizontalAdapter(this.servingSizes,serving_recycler_view,currentServing)

        serving_recycler_view.layoutManager =
            LinearLayoutManager(serving_recycler_view.context, RecyclerView.HORIZONTAL, false)

        serving_recycler_view.adapter = adapter
        if (editText_number_of_serving != null) {
            editText_number_of_serving.setOnEditorActionListener(OnEditorActionListener { v: TextView?, actionId: Int, event: KeyEvent? ->
                if (event != null && event.keyCode == KeyEvent.KEYCODE_ENTER || actionId == EditorInfo.IME_ACTION_DONE) {
                    editText_number_of_serving.clearFocus()



                }
                false
            })
            editText_number_of_serving.setText(numberOfServings.toString())
        }

        done_button.setOnClickListener {
            currentServing = adapter.mCurrentServing
            listener.onSelect(currentServing, editText_number_of_serving.text.toString().toDouble())
            dismiss()
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.foodlens_fragment_serving_size_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       setupUI()
       // setupClickListeners(view)
    }

    companion object {
        fun newInstance(numberOfServings: Double,servingSizes:List<SegmentResponse.FoodItem.ServingSize>,selectedServing:SegmentResponse.FoodItem.ServingSize):ServingSizeDialog {
            return  ServingSizeDialog(numberOfServings,servingSizes,selectedServing)
        }
        private const val TAG = "ResultFragment"
    }
}