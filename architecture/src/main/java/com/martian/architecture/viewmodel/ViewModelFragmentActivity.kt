package com.martian.architecture.viewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.martian.architecture.R
import com.martian.architecture.databinding.ActivityViewModel2Binding
import com.martian.architecture.viewmodel.ui.main.ViewModelFragment

class ViewModelFragmentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityViewModel2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewModel2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ViewModelFragment.newInstance())
                .commitNow()
        }
    }
}