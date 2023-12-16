package com.capstone.lambiapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Observer
import com.capstone.lambiapp.R
import com.capstone.lambiapp.data.request.LoginRequest
import com.capstone.lambiapp.data.response.TokenResponse
import com.capstone.lambiapp.data.retrofit.ApiConfig
import com.capstone.lambiapp.databinding.ActivityLoginBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class loginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var gso:GoogleSignInOptions
    private lateinit var gsc:GoogleSignInClient
    private lateinit var idToken: String
    private val viewModel: LoginViewModel by viewModels()
//    private val loginViewModel: LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Thread.sleep(2000)
        installSplashScreen()
        binding  = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        gso =  GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.web_client_id))
            .requestEmail()
            .build()
        gsc = GoogleSignIn.getClient(this,gso)

        val account : GoogleSignInAccount?= GoogleSignIn
            .getLastSignedInAccount(this)


        if (account!=null){
            goToHome()
        }
    viewModel.loginResult.observe(this, Observer { loginSuccess ->
        if (loginSuccess) {
            Toast.makeText(this, "Success Login", Toast.LENGTH_SHORT).show()
            goToHome()
        }
    })

//    viewModel.loading.observe(this, Observer { isLoading ->
//        // Show/hide loading indicator based on isLoading value
//        if (isLoading) {
//            showLoading()
//
//        } else {
//
//        }
//    })

        binding.signInButton.setOnClickListener{
            goToSign()


        }
//        observeViewModel()


    }



    private fun goToSign() {
        val signInIntent = gsc.signInIntent
        startActivityForResult(signInIntent,1000)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1000){
            val task: Task<GoogleSignInAccount> = GoogleSignIn
                .getSignedInAccountFromIntent(data)

            try {
                val account1 = task.getResult(ApiException::class.java)
                idToken = account1?.idToken.toString()// This is the Google ID token
                Toast.makeText(this,idToken,Toast.LENGTH_SHORT).show()
                if (idToken != null) {
                    // Send the Google ID token to the server using the ViewModel
//                    loginViewModel.sendGoogleSignInToken(this, LoginRequest(idToken))
                    Log.d("TOKEN",idToken.toString())
                    viewModel.getToken(idToken, this)
//                    getToken()



                } else {
                    Toast.makeText(this, "Failed to get Google ID token", Toast.LENGTH_SHORT).show()
                }


            }
            catch(e:java.lang.Exception)
            {
                Toast.makeText(this,e.message,Toast.LENGTH_SHORT).show()
            }
        }
    }
//    private fun observeViewModel() {
//        loginViewModel.LoginResponse.observe(this, Observer { response ->
//            // Handle the login response appropriately
//            if (response != null) {
//                Log.d("hehe",response.message.toString())
//                if (response.message == "success to login"){
//                    goToHome()
//                }
//            } else {
//                // Handle login failure
//                Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show()
//            }
//        })
//    }
    private fun getToken(){
        val client = ApiConfig.getApiService(this).login(LoginRequest(idToken))
    client.enqueue(object : Callback<TokenResponse>{
        override fun onResponse(
            call: Call<TokenResponse>,
            response: Response<TokenResponse>
        ) {
            if (response.isSuccessful){
                val body = response.body()
                Log.d(TAG, "onResponse: $body")
                if (body != null){
                    goToHome()
                    Toast.makeText(this@loginActivity, "Success Login", Toast.LENGTH_SHORT).show()
                }
            }
        }

        override fun onFailure(call: Call<TokenResponse>, t: Throwable) {
            Toast.makeText(this@loginActivity, t.message, Toast.LENGTH_SHORT).show()
            Log.e(TAG, "onFailure: ${t.message}")
        }

    })
    }

    companion object{
        private const val TAG = "RESPONSE"
    }



        private fun goToHome(){
        val intent  = Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()

    }
//    private fun showLoading(){
//        binding.progressBar.visibility= View.VISIBLE
//    }
}