package com.martian.architecture.navigation.ui.dsl.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.martian.architecture.R
import com.martian.architecture.databinding.FragmentDslDetailBinding
import com.martian.architecture.databinding.FragmentDslItemBinding
import com.martian.architecture.navigation.ui.dsl.data.nav_graph

class DslDetailFragment : Fragment() {

    companion object {
        fun newInstance() = DslDetailFragment()
    }

    private lateinit var binding: FragmentDslDetailBinding
    private lateinit var viewModel: DslDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDslDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DslDetailViewModel::class.java)
        binding.dslDetailContent.text = arguments?.getString(nav_graph.args.plant_id)

    }

}