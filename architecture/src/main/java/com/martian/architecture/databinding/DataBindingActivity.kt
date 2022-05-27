package com.martian.architecture.databinding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.martian.architecture.R
import com.martian.architecture.databinding.viewmodel.MViewModel

class DataBindingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDataBindingBinding
    private lateinit var mViewModel: MViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding)
        setContentView(binding.root)
        mViewModel = ViewModelProvider(this).get(MViewModel::class.java)
        mViewModel.initPerson()
        binding.mViewModel = mViewModel
    }
}