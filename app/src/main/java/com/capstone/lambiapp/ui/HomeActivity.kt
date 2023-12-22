package com.capstone.lambiapp.ui

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.capstone.lambiapp.R
import com.capstone.lambiapp.databinding.ActivityHomeBinding
import com.capstone.lambiapp.ui.HomeFragment
import com.capstone.lambiapp.ui.ProfileFragment
import com.capstone.lambiapp.ui.TutorialFragment
import java.io.File

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private val homeFragment: Fragment = HomeFragment()
    private val fm: FragmentManager = supportFragmentManager
//    val sharedViewModel: SharedViewModel by viewModels()
//    val sessionID = sharedViewModel.sessionID.value


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityHomeBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding?.root)

        supportActionBar?.hide()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setupNavbar()
        binding.btnCamera.setOnClickListener {
            if (checkCameraPermission()) {
                startCameraActivity()
            } else {
                requestCameraPermission()
            }

        }
    }
    private fun startCameraActivity() {
        startActivityForResult(
            Intent(this@HomeActivity, CameraXActivity::class.java),
            CAMERA_X_REQUEST_CODE
        )
    }

    private fun setupNavbar() {
        fm.beginTransaction().add(R.id.fragment_container, homeFragment).show(homeFragment).commit()
        binding.bottomBarNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home_button -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, HomeFragment())
                        .commit()
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.gallery_button -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, GalleryFragment())
                        .commit()
//                    Navigation.createNavigateOnClickListener(R.id.action_homeFragment_to_scanFragment)
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.tutor_button -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, TutorialFragment())
                        .commit()
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.profil_button -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, ProfileFragment())
                        .commit()
                    return@setOnNavigationItemSelectedListener true
                }

                else -> return@setOnNavigationItemSelectedListener false
            }
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CAMERA_X_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val pictureFile = data?.getSerializableExtra("picture") as? File
            val isBackCamera = data?.getBooleanExtra("isBackCamera", true) ?: true

            if (pictureFile != null) {
                val cameraViewIntent = Intent(this, CameraViewActivity::class.java)
                cameraViewIntent.putExtra("picture", pictureFile)
                cameraViewIntent.putExtra("isBackCamera", isBackCamera)

                startActivity(cameraViewIntent)
            } else {
                Toast.makeText(this, "Failed to capture image", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun checkCameraPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestCameraPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.CAMERA),
            CAMERA_PERMISSION_REQUEST_CODE
        )
    }

    // ... (rest of your code)

    companion object {
        const val CAMERA_X_REQUEST_CODE = 300
        const val CAMERA_PERMISSION_REQUEST_CODE = 301
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            CAMERA_PERMISSION_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startCameraActivity()
                } else {
                    Toast.makeText(this, "Camera permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}