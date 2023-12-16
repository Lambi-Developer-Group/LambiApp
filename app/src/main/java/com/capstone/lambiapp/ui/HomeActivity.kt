package com.capstone.lambiapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.capstone.lambiapp.R
import com.capstone.lambiapp.databinding.ActivityHomeBinding
import com.capstone.lambiapp.ui.HomeFragment
import com.capstone.lambiapp.ui.ProfileFragment
import com.capstone.lambiapp.ui.TutorialFragment

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private val homeFragment: Fragment = HomeFragment()
    private val fm: FragmentManager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityHomeBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding?.root)

        supportActionBar?.hide()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setupNavbar()
        binding.btnCamera.setOnClickListener {
            startActivity(Intent(this@HomeActivity, CameraViewActivity::class.java))
        }
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


}