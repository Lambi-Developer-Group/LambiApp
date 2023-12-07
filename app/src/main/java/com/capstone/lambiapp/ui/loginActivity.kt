package com.capstone.lambiapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.capstone.lambiapp.R
import com.capstone.lambiapp.databinding.ActivityLoginBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task

class loginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var gso:GoogleSignInOptions
    private lateinit var gsc:GoogleSignInClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Thread.sleep(3000)
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
                val idToken = account1?.idToken // This is the Google ID token
                Toast.makeText(this,idToken,Toast.LENGTH_SHORT).show()
                goToHome()
            }
            catch(e:java.lang.Exception)
            {
                Toast.makeText(this,e.message,Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun goToHome(){
        val intent  = Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()

    }
}