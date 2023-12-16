package com.capstone.lambiapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.capstone.lambiapp.databinding.FragmentGalleryBinding


class GalleryFragment : Fragment() {


    private var _binding: FragmentGalleryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        return binding.root

        binding.rvBajuGallery.apply {
            layoutManager = GridLayoutManager(context,2)
            setHasFixedSize(true)

        }


    }


}