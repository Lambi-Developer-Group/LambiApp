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

class LoginActivity : AppCompatActivity() {
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

        viewModel.loading.observe(this, Observer { isLoading ->
            if (isLoading) {
                showLoading()

            } else {

            }
        })

        binding.signInButton.setOnClickListener{
            goToSign()


        }


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
                idToken = account1?.idToken.toString()
                if (idToken != null) {
                    viewModel.getToken(idToken, this)
//



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
                        Toast.makeText(this@LoginActivity, "Success Login", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onFailure(call: Call<TokenResponse>, t: Throwable) {
                Toast.makeText(this@LoginActivity, t.message, Toast.LENGTH_SHORT).show()
                Log.e(TAG, "onFailure: ${t.message}")
            }

        })
    }

    companion object{
        private const val TAG = "RESPONSE"
    }



    private fun goToHome(){
        val intent  = Intent(this,SessionActivity::class.java)
        startActivity(intent)
        finish()

    }
    private fun showLoading(){
        binding.progressBar.visibility= View.VISIBLE
    }
}