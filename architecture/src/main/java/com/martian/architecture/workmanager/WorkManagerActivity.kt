package com.martian.architecture.workmanager

import android.os.Build
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.work.WorkInfo
import com.martian.architecture.databinding.ActivityWorkManagerBinding
import com.martian.architecture.workmanager.viewmodel.WorkManagerViewModel
import com.martian.architecture.workmanager.work.ProgressWorker

class WorkManagerActivity : AppCompatActivity() {
    private val TAG: String = "WorkManagerActivity"
    private lateinit var viewModel: WorkManagerViewModel
    private lateinit var binding: ActivityWorkManagerBinding
    private var inputData: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWorkManagerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(WorkManagerViewModel::class.java)
        viewModel.init(this)
        binding.textViewWork.movementMethod = ScrollingMovementMethod.getInstance()

    }

    fun workRequestFrom(view: View) {
        viewModel.workRequestOne().observe(this) { it ->
            binding.textViewWork.text = it?.toString()
        }
    }

    fun workRequestBuild(view: View) {
        viewModel.workRequestBuild().observe(this) { it ->
            binding.textViewWork.text = getResult(it)
        }
    }

    fun workRequestExpedited(view: View) {
        viewModel.workRequestExpedited().observe(this) { it ->
            binding.textViewWork.text = getResult(it)
        }
    }

    fun workRequestPeriodic(view: View) {
        viewModel.workRequestPeriodic().observe(this) { it ->
            binding.textViewWork.text = getResult(it)
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun workRequestConstraints(view: View) {
        viewModel.workRequestConstraints().observe(this) { it ->
            binding.textViewWork.text = getResult(it)
        }
    }

    fun workRequestInitialDelay(view: View) {
        viewModel.workRequestInitialDelay().observe(this) { it ->
            binding.textViewWork.text = getResult(it)
        }
    }

    fun workRequestBackoffCriteria(view: View) {
        viewModel.workRequestBackoffCriteria().observe(this) { it ->
            binding.textViewWork.text = getResult(it)
        }
    }

    fun workRequestInputMerger(view: View) {
        viewModel.workRequestInputMerger(inputData++).observe(this) { it ->
            binding.textViewWork.text = getResult(it)
        }
    }

    fun workQuery(view: View) {
        binding.textViewWork.text = getResult(viewModel.workQuery().get())
    }

    fun cancelWork(view: View) {
        viewModel.cancelWork()
        binding.textViewWork.text =""
    }

    fun progressWorker(view: View) {
        viewModel.progressWorker().observe(this) { it ->
            it?.forEach {workInfo->
                when(workInfo.state){
                    WorkInfo.State.ENQUEUED ->{
                        binding.button10.text = "ProgressWorker start"
                        binding.textViewWork.text = "ProgressWorker start"
                    }
                    WorkInfo.State.RUNNING ->{
                        val value = workInfo.progress.getInt(ProgressWorker.Progress, 0)
                        Log.i(TAG,"progressWorker-value: ${value}")
                        binding.button10.text = "ProgressWorker ${value}"
                        binding.textViewWork.text = "ProgressWorker ${workInfo.progress}"
                    }
                    WorkInfo.State.SUCCEEDED ->{
                        binding.button10.text = "ProgressWorker complete"
                        binding.textViewWork.text = "ProgressWorker complete"
                    }
                    else ->{}
                }
            }
        }
    }

    fun linkWork(view: View) {
        viewModel.linkWork().observe(this) { it ->
            binding.textViewWork.text = getResult(it)
        }
    }
    fun remoteProcessWorker(view: View) {
        viewModel.remoteProcessWorker().observe(this) { it ->
            binding.textViewWork.text = getResult(it)
        }
    }




    fun getResult(list: List<WorkInfo>?): String {
        var content = StringBuffer()
        list?.forEach { workInfo ->
            workInfo.let {
                content.append(it.tags).append(" ").append(it.outputData).append("\n")
            }
        }
        return content.toString()
    }
}