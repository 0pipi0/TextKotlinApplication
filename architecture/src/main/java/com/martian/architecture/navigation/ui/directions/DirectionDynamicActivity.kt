package com.martian.architecture.navigation.ui.directions

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.TransitionInflater
import androidx.core.content.ContentProviderCompat.requireContext
import com.martian.architecture.R
import com.martian.architecture.databinding.ActivityDirectionDynamicBinding

class DirectionDynamicActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDirectionDynamicBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        sharedElementEnterTransition = TransitionInflater.from(this).inflateTransition(R.transition.shared_home)
        binding = ActivityDirectionDynamicBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initData()
    }

    private fun initData() {
        var uri = intent.data
        if (uri != null) {
            var name = uri.getQueryParameter("userName")
            var age = uri.getQueryParameter("userAge")
            binding.textDynamic.text = getString(R.string.direction_dynamic_content,name,age)
        }
    }
}