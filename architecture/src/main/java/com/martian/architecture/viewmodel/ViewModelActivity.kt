package com.martian.architecture.viewmodel

import android.os.Bundle
import android.widget.Button
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.widget.TextViewCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.martian.architecture.R
import com.martian.architecture.R.id.textView
import com.martian.architecture.databinding.ActivityViewModelBinding
import com.martian.architecture.viewmodel.models.OnTimeChangeListener
import com.martian.architecture.viewmodel.models.TimerViewModel

class ViewModelActivity : AppCompatActivity(), OnTimeChangeListener {
    private lateinit var binding: ActivityViewModelBinding
    private lateinit var timeViewModel:TimerViewModel
    private lateinit var textView: AppCompatTextView
    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewModelBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        initView()
        initViewModel()
    }

    private fun initView() {
        textView = binding.root.findViewById(R.id.textView)
        button = binding.root.findViewById(R.id.button)
        button.setOnClickListener {
            timeViewModel?.startTimer()
        }
    }

    private fun initViewModel() {
        timeViewModel = ViewModelProvider(this).get(TimerViewModel::class.java)
        timeViewModel.setOnTimeChangeListener(this)
    }

    override fun onTimeChange(seconds: Int) {
        runOnUiThread{
            textView.text = "$seconds 秒"
        }
    }
}