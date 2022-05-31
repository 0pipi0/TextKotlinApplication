package com.martian.architecture.lifecycles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.martian.architecture.R
import com.martian.architecture.databinding.ActivityLivecyclesBinding
import com.martian.architecture.lifecycles.function.PlayManager
import com.martian.architecture.lifecycles.viewmodel.MViewModel

class LifecyclesActivity : AppCompatActivity() {
    private lateinit var playManager: PlayManager
    private lateinit var viewModel: MViewModel
    private lateinit var binding: ActivityLivecyclesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLivecyclesBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_livecycles)
        viewModel = ViewModelProvider(this).get(MViewModel::class.java)
        playManager = PlayManager();
        getLifecycle().addObserver(playManager)
    }
}