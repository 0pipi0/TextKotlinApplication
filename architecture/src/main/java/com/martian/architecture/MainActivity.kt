package com.martian.architecture

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.martian.architecture.databinding.ActivityMainBinding
import com.martian.architecture.databinding.DataBindingActivity
import com.martian.architecture.livedata.LiveDataActivity
import com.martian.architecture.viewmodel.ViewModelActivity
import com.martian.architecture.viewmodel.ViewModelFragmentActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
//    private lateinit var bindingContentScrolling: ContentScrollingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(findViewById(R.id.toolbar))
        binding.toolbarLayout.title = title
        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        initListener()
    }

    private fun initListener() {
//        bindingContentScrolling = ContentScrollingBinding.inflate(layoutInflater)
//        bindingContentScrolling.dataBinding
    }

    fun viewModelActivity(view: View){
//        Toast.makeText(this,"view model",Toast.LENGTH_SHORT).show()
        startActivity(Intent(this,ViewModelActivity::class.java))
    }
    fun viewModelFragment(view: View){
//        Toast.makeText(this,"view model",Toast.LENGTH_SHORT).show()
        startActivity(Intent(this,ViewModelFragmentActivity::class.java))
    }

    fun dataBinding(view: View) {
//        Toast.makeText(this,"data binding",Toast.LENGTH_SHORT).show()
        startActivity(Intent(this,DataBindingActivity::class.java))
    }
    fun liveData(view: View) {
//        Toast.makeText(this,"data binding",Toast.LENGTH_SHORT).show()
        startActivity(Intent(this, LiveDataActivity::class.java))
    }



}