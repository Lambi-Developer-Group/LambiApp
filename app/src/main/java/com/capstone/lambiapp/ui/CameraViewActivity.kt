package com.capstone.lambiapp.ui

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.capstone.lambiapp.databinding.ActivityCameraViewBinding
import java.io.File
import java.text.SimpleDateFormat
import java.util.Locale


class CameraViewActivity : AppCompatActivity() {


    private lateinit var binding: ActivityCameraViewBinding
    private var getFile: File? = null
    private val timeStamp: String = SimpleDateFormat(
        "dd-MMM-yyyy",
        Locale.US
    ).format(System.currentTimeMillis())

    companion object {
        const val CAMERA_X_RESULT = 200
        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
        private const val REQUEST_CODE_PERMISSIONS = 10
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (!allPermissionsGranted()) {
            Toast.makeText(
                this,
                "Tidak mendapatkan Izin untuk memulai Kamera",
                Toast.LENGTH_LONG
            ).show()
            finish()
        }
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(this, it) == PackageManager.PERMISSION_GRANTED
    }

    private fun permissionCamera() {
        if (!allPermissionsGranted()) {
            ActivityCompat.requestPermissions(
                this,
                REQUIRED_PERMISSIONS,
                REQUEST_CODE_PERMISSIONS
            )
            Toast.makeText(
                this, "tidak mendapat permission", Toast.LENGTH_LONG
            ).show()

        } else {
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCameraViewBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        permissionCamera()

        binding.btnTakePhoto.setOnClickListener {
            goToCamera()
        }
    }

    //    CameraX Stuff
    private fun goToCamera() {
        val intent = Intent(this, CameraXActivity::class.java)
        launcherIntentCameraX.launch(intent)
    }

    private val launcherIntentCameraX = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == 200) {
            val myFile = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                it.data?.getSerializableExtra("picture", File::class.java)
            } else {
                @Suppress("DEPRECATION")
                it.data?.getSerializableExtra("picture")
            } as File
            val isBackCamera = it.data?.getBooleanExtra("isBackCamera", true) as Boolean


            myFile.let { file ->
                Utils().rotateFile(file, isBackCamera)
                getFile = file
//                imgURI = uriToFile()//
                binding.previewCaptureCamera.setImageBitmap(BitmapFactory.decodeFile(getFile?.path))
            }

        }


    }
}