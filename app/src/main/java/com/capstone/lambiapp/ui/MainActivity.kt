package com.capstone.lambiapp.ui

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.service.autofill.TextValueSanitizer
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.capstone.lambiapp.R
import com.capstone.lambiapp.databinding.ActivityLoginBinding
import com.capstone.lambiapp.databinding.ActivityMainBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import org.michaelevans.colorart.library.ColorArt


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var gso: GoogleSignInOptions
    private lateinit var gsc: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding  = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        gso =  GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        gsc = GoogleSignIn.getClient(this,gso)

        val account : GoogleSignInAccount?= GoogleSignIn
            .getLastSignedInAccount(this)

        if (account!= null){
            binding.tvid.text = account?.idToken
            binding.tvmain.text = account.displayName
            Glide.with(applicationContext).load(account.photoUrl).apply(RequestOptions.bitmapTransform(
                CircleCrop())).into(binding.ivProfile)
            Log.d("TOKEN",account.idToken.toString())


        }
        else{
            goSignOut()
        }
        var bitmap = BitmapFactory.decodeResource(resources,R.drawable.loginlogo)
        var colorArt = ColorArt(bitmap)


        binding.buttonSignOut.setOnClickListener {
            goSignOut()
        }
        
    }

    private fun goSignOut() {
        gsc.signOut().addOnSuccessListener {
            startActivity(Intent(this,loginActivity::class.java))
//            showLoading()
            finish()
        }
    }
//    private fun showLoading(){
//        binding.progressBar.visibility= View.VISIBLE
//    }
}