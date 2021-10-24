package com.richarddewan.hiltmvvm.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.richarddewan.hiltmvvm.R
import com.richarddewan.hiltmvvm.databinding.FragmentAddBinding


/*
created by Richard Dewan 24/10/2021
*/

class AddFragment: Fragment(R.layout.fragment_add) {
    private var _binding: FragmentAddBinding? = null
    private  val binding: FragmentAddBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddBinding.inflate(inflater)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}