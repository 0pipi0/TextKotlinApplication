package com.martian.architecture.livedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.arch.core.util.Function
import androidx.lifecycle.*
import androidx.lifecycle.Transformations.switchMap
import com.martian.architecture.R
import com.martian.architecture.databinding.ActivityLiveDataBinding
import com.martian.architecture.livedata.viewmodel.MViewModel

class LiveDataActivity : AppCompatActivity() {
    private lateinit var viewModel: MViewModel
    private lateinit var binding: ActivityLiveDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLiveDataBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(MViewModel::class.java)
        binding.lifecycleOwner = this
        binding.mViewModel = viewModel

        viewModel.count.observe(this, Observer {
            println("change:$it")
        })
        switchLiveData()
        mediatorLiveData()
    }

    fun switchLiveData(){
//        viewModel.liveData1.observe(this, Observer {
//            println("switchLiveData-liveData1:$it")
//        })
//        viewModel.liveData2.observe(this, Observer {
//            println("switchLiveData-liveData2:$it")
//        })

        switchMap(viewModel.switchLiveData){
            println("switchMap:$it")
            if(it){
               viewModel.liveData1
            }else{
               viewModel.liveData2
            }
        }.observe(this, Observer {
            println("switchLiveData-switchLiveData:$it")
        })

    }

    fun mediatorLiveData(){
        viewModel.mediatorLiveData.addSource(viewModel.liveData3) {
            println("mediatorLiveData-liveData3:$it")
        }
        viewModel.mediatorLiveData.addSource(viewModel.liveData4){
            println("mediatorLiveData-liveData4:$it")
        }
        viewModel.mediatorLiveData.observe(this, Observer {
            println("mediatorLiveData-mediatorLiveData:$it")
        })

    }
}