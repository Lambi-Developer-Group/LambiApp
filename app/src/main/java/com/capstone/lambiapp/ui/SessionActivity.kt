package com.capstone.lambiapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.lambiapp.R
import com.capstone.lambiapp.data.database.SessionManager
import com.capstone.lambiapp.databinding.ActivityCameraViewBinding
import com.capstone.lambiapp.databinding.ActivityCameraXactivityBinding
import com.capstone.lambiapp.databinding.ActivitySessionBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

    class SessionActivity : AppCompatActivity(), SessionAdapter.OnItemClickListener {

        private lateinit var sessionViewModel: GetSessionViewModel
        private lateinit var addSessionViewModel: SessionViewModel
        private lateinit var sessionAdapter: SessionAdapter
        private lateinit var binding: ActivitySessionBinding
        private lateinit var gso: GoogleSignInOptions
        private lateinit var gsc: GoogleSignInClient
        private lateinit var sharedViewModel: SharedViewModel
        private lateinit var sessionManager: SessionManager
        private val handler = Handler(Looper.getMainLooper())

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivitySessionBinding.inflate(layoutInflater)
            setContentView(binding.root)
            gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()
            gsc = GoogleSignIn.getClient(this, gso)
            sessionAdapter = SessionAdapter(emptyList(), this)
            binding.rvSession.adapter = sessionAdapter
            binding.rvSession.layoutManager = LinearLayoutManager(this)
            sessionViewModel = ViewModelProvider(this).get(GetSessionViewModel::class.java)
            observeViewModel()
            sessionManager = SessionManager(this)
            addSessionViewModel = ViewModelProvider(this).get(SessionViewModel::class.java)
            sharedViewModel = ViewModelProvider(this).get(SharedViewModel::class.java)
            binding.addSession.setOnClickListener {
                observeAddViewModel()
                delayedMethod()



            }
            binding.imageViewBack.setOnClickListener{
                goSignOut()
                showLoading()
            }

            binding.textViewBack.setOnClickListener {
                goSignOut()
                showLoading()
            }

            binding.tvName.setOnClickListener {
                startActivity(Intent(this@SessionActivity,HomeActivity::class.java))
            }
        }
        private fun delayedMethod() {
            handler.postDelayed({
                // Your method logic goes here
                observeViewModel()
            }, 800) // 2000 milliseconds (2 seconds) delay, change it as needed
        }

        private fun observeViewModel() {
            val account: GoogleSignInAccount? = GoogleSignIn.getLastSignedInAccount(this)
            sessionViewModel.sessionList.observe(this, { sessions ->

                if(sessions != null){
                    sessionAdapter.submitList(sessions)
                    sessionAdapter.notifyDataSetChanged()
                }
                else{
                    goSignOut()
                }

            })

            sessionViewModel.loading.observe(this, { isLoading ->
                if (isLoading == true) {
                    showLoading()

                } else {
                    hideLoading()
                }
            })

            sessionViewModel.getSession(account?.idToken.toString(), applicationContext)
            Log.d("TOKEn", account?.idToken.toString())
        }

        private fun observeAddViewModel() {
            val account: GoogleSignInAccount? = GoogleSignIn.getLastSignedInAccount(this)
            addSessionViewModel.getSession(account?.idToken.toString(), this)
        }


        override fun onItemClick(sessionID: String) {
            sessionManager.saveSessionId(sessionID)
            val intent = Intent(this@SessionActivity, HomeActivity::class.java)
            intent.putExtra("SESSION_ID", sessionID)
            startActivity(intent)
            Log.d("SESSIONID FIX",sessionID)
        }
        private fun showLoading() {
            binding.progressBar.visibility= View.VISIBLE

        }
        private fun hideLoading() {
            binding.progressBar.visibility = View.GONE
        }
        private fun goSignOut() {
            gsc.signOut().addOnSuccessListener {
                startActivity(Intent(this,LoginActivity::class.java))
                showLoading()
                finish()
            }

        }

        override fun onBackPressed() {
            finish()
            super.onBackPressed()
        }
    }