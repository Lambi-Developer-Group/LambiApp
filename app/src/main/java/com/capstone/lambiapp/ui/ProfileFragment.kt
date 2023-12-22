package com.capstone.lambiapp.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.capstone.lambiapp.databinding.FragmentProfileBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

@Suppress("UNREACHABLE_CODE")
class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var gso: GoogleSignInOptions
    private lateinit var gsc: GoogleSignInClient
    private lateinit var viewModel: SessionViewModel
    private val handler = Handler(Looper.getMainLooper())
    private val logoutDelayMillis: Long = 3600000 // 1 hour in milliseconds

    private val logoutRunnable = Runnable {
        goSignOut()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)



        gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        gsc = GoogleSignIn.getClient(requireContext(), gso)
        viewModel = ViewModelProvider(this).get(SessionViewModel::class.java)

        val account: GoogleSignInAccount? = GoogleSignIn.getLastSignedInAccount(requireContext())

        if (account != null) {
            binding.tvNamaProfil.text = account.displayName
            binding.tvEmail.text = account.email
            Glide.with(requireContext()).load(account.photoUrl)
                .apply(RequestOptions.bitmapTransform(CircleCrop())).into(binding.ivProfile)
            Log.d("TOKEN New", account.idToken.toString())
        } else {
            goSignOut()
        }

        binding.btnLogout.setOnClickListener {
            goSignOut()
        }
        handler.postDelayed(logoutRunnable, logoutDelayMillis)
        binding.btnInfo.setOnClickListener {
            startActivity(Intent(requireActivity(),AboutActivity::class.java))

        }
        return binding.root


    }

    private fun goSignOut() {
        gsc.signOut().addOnSuccessListener {
            startActivity(Intent(requireContext(), LoginActivity::class.java))
            showLoading()
            requireActivity().finish()
        }

    }

    private fun showLoading() {
        binding.progressBar.visibility= View.VISIBLE

    }


}