package com.azumio.android.foodlenslibrary.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.azumio.android.foodlenslibrary.FoodLens
import com.azumio.android.foodlenslibrary.R
import java.io.File

class CameraActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.foodlens_activity_camera)
        FoodLens.renewTokenIfExpired()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == FoodLens.FOODLENS_IMAGE_ACTIVITY_RESULT_CODE && resultCode == Activity.RESULT_OK)
        {
            setResult(FoodLens.FOODLENS_CAMERA_ACTIVITY_RESULT_CODE,data)
            finish()
        }

    }

    companion object {

        private const val MEAL_ARGUMENT_KEY = "meal"

        fun startForResult(activity: Activity, meal: String, options: Bundle, requestCode: Int) {
            val intent = Intent(activity, CameraActivity::class.java)
            intent.putExtra(MEAL_ARGUMENT_KEY, meal)
            intent.putExtras(options)
            activity.startActivityForResult(intent, requestCode)
        }

        fun startForResult(fragment: Fragment, meal: String, options: Bundle, requestCode: Int) {
            val intent = Intent(fragment.context, CameraActivity::class.java)
            intent.putExtra(MEAL_ARGUMENT_KEY, meal)
            intent.putExtras(options)
            fragment.startActivityForResult(intent, requestCode)
        }

        /** Use external media if it is available, our app's file directory otherwise */
        fun getOutputDirectory(context: Context): File {
            val appContext = context.applicationContext
            val mediaDir = context.externalMediaDirs.firstOrNull()?.let {
                File(it, appContext.resources.getString(R.string.foodlens_library_name)).apply { mkdirs() } }
            return if (mediaDir != null && mediaDir.exists())
                mediaDir else appContext.filesDir
        }
    }


}