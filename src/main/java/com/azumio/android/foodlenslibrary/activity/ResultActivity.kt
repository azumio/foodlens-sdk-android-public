package com.azumio.android.foodlenslibrary.activity

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.azumio.android.foodlenslibrary.R
import com.azumio.android.foodlenslibrary.fragment.ResultFragment
import com.azumio.android.foodlenslibrary.utils.ArgusIconMap
import com.azumio.android.foodlenslibrary.views.CenteredCustomFontView

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.foodlens_activity_result)

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

          intent.getStringExtra(IMAGE_FILE_KEY)?.let {

              val meal = intent.getStringExtra(MEAL_KEY)
              val uri = Uri.parse(it)
              val resultFragment = ResultFragment.newInstance(uri, meal)
              fragmentTransaction.replace(R.id.fragment_container, resultFragment)
              fragmentTransaction.commitAllowingStateLoss()

          }

        initBackArrow()

    }

    private fun initBackArrow() {
        val arrow: CenteredCustomFontView = findViewById(R.id.back_arrow)
        val iconPath = this.getString(R.string.foodlens_font_path_material_design_set)
        arrow.setFontPath(iconPath)
        arrow.setText(ArgusIconMap.getInstance()[ArgusIconMap.ARROW_LEFT])
        arrow.setOnClickListener { view: View? -> onBackPressed() }
    }

    companion object {
        private const val TAG = "ResultActivity"
        const val IMAGE_FILE_KEY = "IMAGE_FILE"
        const val MEAL_KEY = "MEAL_KEY"
    }
}

