package com.capstone.lambiapp.ui

import android.Manifest
import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.capstone.lambiapp.data.database.AppDatabase
import com.capstone.lambiapp.data.database.SessionManager
import com.capstone.lambiapp.data.response.ImageResponse
import com.capstone.lambiapp.data.retrofit.ApiConfig
import com.capstone.lambiapp.data.retrofit.ApiService
import com.capstone.lambiapp.databinding.ActivityCameraViewBinding
import com.capstone.lambiapp.ui.HomeActivity.Companion.CAMERA_X_REQUEST_CODE
import com.nvt.color.ColorPickerDialog
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Locale


class CameraViewActivity : AppCompatActivity() {
    private lateinit var database: AppDatabase
    private lateinit var binding: ActivityCameraViewBinding
    private lateinit var getFile: File
    private var isBackCamera: Boolean = true
    private lateinit var apiService: ApiService
    private lateinit var sessionManager: SessionManager
    private val timeStamp: String = SimpleDateFormat(
        "dd-MMM-yyyy_HH-mm-ss",
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
        sessionManager = SessionManager(this)
        Log.d("SESSION ID", sessionManager.getSessionId().toString())
        database = AppDatabase.getDatabase(application)
        apiService = ApiConfig.getApiService(this)
        binding.btnColorpicker.setOnClickListener {
            val colorPicker = ColorPickerDialog(
                this,
                Color.BLACK,
                true,
                object : ColorPickerDialog.OnColorPickerListener {
                    override fun onCancel(dialog: ColorPickerDialog?) {
                    }

                    override fun onOk(dialog: ColorPickerDialog?, colorPicker: Int) {

                        binding.previewSelectedColor.setBackgroundColor(colorPicker)
                        updateColorViews(colorPicker)
                    }
                })
            colorPicker.show()
        }
        binding.btnUploadImage.setOnClickListener {
            saveToGallery()
            uploadImageToServer()
            showLoading()
            finish()

        }
        retrieveIntentExtras()
        displayPhoto()
    }
    private fun updateColorViews(selectedColor: Int) {

        val hexColor = String.format("%06X", 0xFFFFFF and selectedColor)
        sessionManager.saveColor(hexColor)
        binding.tvColorPicker.text = "$hexColor"
    }


    private fun goToCamera() {
        val intent = Intent(this, CameraXActivity::class.java)
        launcherIntentCameraX.launch(intent)
    }
    private fun retrieveIntentExtras() {
        val intent = intent


        getFile = intent.getSerializableExtra("picture") as File


        isBackCamera = intent.getBooleanExtra("isBackCamera", true)
    }

    private fun displayPhoto() {
        val rotatedBitmap = Utils().rotateBitmap(getFile, isBackCamera)
        binding.previewCaptureCamera.setImageBitmap(rotatedBitmap)
    }

    private val launcherIntentCameraX = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == RESULT_OK) {
            val myFile = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                it.data?.getSerializableExtra("picture", File::class.java)
            } else {
                @Suppress("DEPRECATION")
                it.data?.getSerializableExtra("picture")
            } as File
            val isBackCamera = it.data?.getBooleanExtra("isBackCamera", true) as Boolean

            myFile.let { file ->
                Utils().rotateBitmap(file, isBackCamera)
                getFile = file
                binding.previewCaptureCamera.setImageBitmap(BitmapFactory.decodeFile(getFile?.path))
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CAMERA_X_REQUEST_CODE && resultCode == RESULT_OK) {
            // Retrieve the file from the result intent
            val pictureFile = data?.getSerializableExtra("picture") as? File
            val isBackCamera = data?.getBooleanExtra("isBackCamera", true) ?: true

            // Check if the file is not null
            if (pictureFile != null) {
                // Update the displayed photo in the ImageView
                val rotatedBitmap = Utils().rotateBitmap(pictureFile, isBackCamera)
                binding.previewCaptureCamera.setImageBitmap(rotatedBitmap)
            } else {
                Toast.makeText(this, "Failed to capture image", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun saveToGallery() {
        val contentValues = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, "Image_$timeStamp.jpg")
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
        }

        val resolver = contentResolver
        val imageUri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

        try {
            resolver.openOutputStream(imageUri ?: return)?.use { outputStream ->
                val bitmap = (binding.previewCaptureCamera.drawable as BitmapDrawable).bitmap
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
            }

            Toast.makeText(this, "Image saved to gallery", Toast.LENGTH_SHORT).show()
        } catch (e: IOException) {
            e.printStackTrace()
            Toast.makeText(this, "Failed to save image to gallery", Toast.LENGTH_SHORT).show()
        }

    }
    private fun uploadImageToServer() {
        val color = sessionManager.getColor() ?: ""
        val sessionID = sessionManager.getSessionId() ?: ""
        Log.d("SESI ID",sessionID)
        val file = getFile
        Log.d("ISI FILE", file.toString())

        val reducedAndRotatedFile = Utils().reduceFileImage(file)
        val rotatedBitmap = Utils().rotateBitmap(reducedAndRotatedFile, isBackCamera)

        val rotatedFile = Utils().saveBitmapToFile(rotatedBitmap, reducedAndRotatedFile)

        val requestFile = reducedAndRotatedFile.asRequestBody("image/jpeg".toMediaType())
        val body = MultipartBody.Part.createFormData("file", rotatedFile.name, requestFile)
        Log.d("BODY REQUEST",body.toString())


        Log.d("UploadImage", "SessionID: $sessionID, Color: $color")
        val call = ApiConfig.getApiService(this)
            .uploadImage(body, color, sessionID)


        call.enqueue(object : Callback<ImageResponse> {
            override fun onResponse(call: Call<ImageResponse>, response: Response<ImageResponse>) {
                if (response.isSuccessful) {
                    val imageResponse = response.body()
                    if (imageResponse != null){
                        startActivity(Intent(this@CameraViewActivity,HomeActivity::class.java))
                    }
                    showToast("Image uploaded successfully. Image URL")
                } else {
                    showToast("Failed to upload image. Server returned error.")

                }
            }

            override fun onFailure(call: Call<ImageResponse>, t: Throwable) {
                showToast("Network request failed. Please check your internet connection.")
            }
        })
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
    private fun showLoading() {
        binding.progressBar.visibility= View.VISIBLE

    }


}