package com.martian.architecture.paging

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.martian.architecture.databinding.ActivityPagingBinding
import com.martian.architecture.paging.adapter.PageAdapter
import com.martian.architecture.paging.adapter.PagingAdapter
import com.martian.architecture.paging.viewmodel.PagingViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class PagingActivity : AppCompatActivity() {
    private lateinit var viewModel: PagingViewModel
    private lateinit var binding: ActivityPagingBinding
    private lateinit var adapter: PageAdapter
    private lateinit var adapterPaging: PagingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPagingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(PagingViewModel::class.java)
//        initView()
        initViewPaging()
    }



    private fun initView() {
        adapter = PageAdapter()
        binding.rvPaging.adapter = adapter
        binding.rvPaging.layoutManager = LinearLayoutManager(this)
        viewModel.personList?.observe(this, Observer {
            adapter.submitList(it)
        })
    }

    private fun initViewPaging() {
        adapterPaging = PagingAdapter()
        binding.rvPaging.adapter = adapterPaging
        binding.rvPaging.layoutManager = LinearLayoutManager(this)
        lifecycleScope.launch {
            viewModel.flow.collectLatest {
                adapterPaging.submitData(it)
            }
        }
    }
}