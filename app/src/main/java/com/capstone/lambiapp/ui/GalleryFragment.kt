package com.capstone.lambiapp.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.GridLayoutManager
import com.capstone.lambiapp.data.database.AppDatabase
import com.capstone.lambiapp.data.database.Photo
import com.capstone.lambiapp.data.database.SessionManager
import com.capstone.lambiapp.databinding.FragmentGalleryBinding
import kotlin.math.log


class GalleryFragment : Fragment(), PhotoAdapter.OnItemClickListener {

    private var _binding: FragmentGalleryBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: GalleryViewModel
    private lateinit var photoAdapter: PhotoAdapter
    private lateinit var recommendationsIdViewModel: RecommendationsIdViewModel
    private lateinit var recommendationImageViewModel: RecommendationImageViewModel
    private lateinit var sessionManager: SessionManager
    private lateinit var progressBar : ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(GalleryViewModel::class.java)
        photoAdapter = PhotoAdapter(this)
        recommendationsIdViewModel = ViewModelProvider(this).get(RecommendationsIdViewModel::class.java)
        recommendationImageViewModel = ViewModelProvider(this).get(RecommendationImageViewModel::class.java)
        sessionManager = SessionManager(requireContext())

        val sessionId = sessionManager.getSessionId()
        binding.rvBajuGallery.apply {
            layoutManager = GridLayoutManager(context, 2)
            setHasFixedSize(true)
            adapter = photoAdapter
        }

        observePhotos()
        binding.btnRecommendations.setOnClickListener{
            showLoading()
            if (sessionId != null) {
                recommendationsIdViewModel.getRecommendationsId(sessionId,requireContext())

            }
            observeRecomendationId()

        }


    }

    private fun observePhotos() {
        viewModel.allPhotos.observe(viewLifecycleOwner, { photos ->
            if (photos != null) {
                photos.forEach {
                    Log.d("Database", "Image Path from DB: ${it.imagePath}")
                }
                photoAdapter.submitList(photos)
            }
        })
    }
    private fun observeRecomendationId(){
        recommendationsIdViewModel.recommendationId.observe(viewLifecycleOwner,{recommendationId ->
            if (recommendationId != null) {
                sessionManager.saveRecommendationId(recommendationId)
                startActivity(Intent(requireActivity(),HomeActivity::class.java))
                hideLoading()
            }
            else{

            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(photo: Photo) {
        viewModel.deletePhoto(photo)
        Toast.makeText(requireContext(), "Clicked on item with ID: ${photo.id}", Toast.LENGTH_SHORT).show()
    }
    private fun showLoading() {
        binding.progressBar.visibility= View.VISIBLE

    }
    private fun hideLoading() {
        binding.progressBar.visibility= View.GONE

    }

}