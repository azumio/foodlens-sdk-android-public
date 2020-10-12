package com.azumio.android.foodlenslibrary.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.SurfaceRequest
import androidx.fragment.app.FragmentContainer
import androidx.fragment.app.FragmentContainerView
import com.azumio.android.foodlenslibrary.FoodLens
import com.azumio.android.foodlenslibrary.R
import kotlinx.android.synthetic.main.activity_camera.*
import java.io.File
import java.lang.Exception

class CameraActivity : AppCompatActivity() {

    private lateinit var container: FragmentContainerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)
        container = fragment_container
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

        /** Use external media if it is available, our app's file directory otherwise */
        fun getOutputDirectory(context: Context): File {
            val appContext = context.applicationContext
            val mediaDir = context.externalMediaDirs.firstOrNull()?.let {
                File(it, appContext.resources.getString(R.string.library_name)).apply { mkdirs() } }
            return if (mediaDir != null && mediaDir.exists())
                mediaDir else appContext.filesDir
        }
    }


}