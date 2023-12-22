package com.capstone.lambiapp.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.capstone.lambiapp.R
import com.capstone.lambiapp.data.database.SessionManager
import com.capstone.lambiapp.data.response.DataItem
import com.capstone.lambiapp.databinding.FragmentHomeBinding
import com.capstone.lambiapp.databinding.FragmentProfileBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

class HomeFragment : Fragment(), RecommendationImagesAdapter.OnItemClickListener {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var gso: GoogleSignInOptions
    private lateinit var gsc: GoogleSignInClient
    private lateinit var sessionManager: SessionManager
    private lateinit var recommendationImageViewModel: RecommendationImageViewModel
    private lateinit var recommendationAdapter: RecommendationImagesAdapter
    override fun onAttach(context: Context) {
        super.onAttach(context)
        sessionManager = SessionManager(requireContext())
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        gsc = GoogleSignIn.getClient(requireContext(), gso)
        val account: GoogleSignInAccount? = GoogleSignIn.getLastSignedInAccount(requireContext())

        if (account != null) {
            binding.tvName.text = account.displayName
            binding.tvTitleHome.text = "You Don't have Any History, Please Generate Recommendation First"
            Log.d("TOKEN New", account.idToken.toString())
        } else {

        }
        binding.backBtn.setOnClickListener{
            startActivity(Intent(requireActivity(),SessionActivity::class.java))
        }

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sessionManager = SessionManager(requireContext())
        recommendationImageViewModel = ViewModelProvider(this).get(RecommendationImageViewModel::class.java)
        val sessionId= sessionManager.getSessionId()
        val recommendationId = sessionManager.getRecommendationId()

        if (sessionId != null) {
            if (recommendationId != null) {
                recommendationImageViewModel.getRecommendationImages(sessionId,recommendationId,requireContext())
            }else{

            }
        }
        recommendationAdapter = RecommendationImagesAdapter(emptyList(), this)
        binding.recyclerViewRecommendation.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewRecommendation.adapter = recommendationAdapter
        binding.backBtn.setOnClickListener{
            startActivity(Intent(requireActivity(),SessionActivity::class.java))
        }

        observeRecommendationImages()
    }

    private fun observeRecommendationImages() {
        recommendationImageViewModel.recommendationImages.observe(viewLifecycleOwner, { recommendationImages ->
            if (recommendationImages != null){
                binding.tvTitleHome.text = "Your Recent History"
            }
            recommendationAdapter.submitList(recommendationImages?.data)
            recommendationAdapter.notifyDataSetChanged()
        })
    }

    override fun onItemClick(recommendation: DataItem?) {
        val intent = Intent(requireContext(), DetailActivity::class.java)
        intent.putExtra("recommendation", recommendation)
        startActivity(intent)
    }

}