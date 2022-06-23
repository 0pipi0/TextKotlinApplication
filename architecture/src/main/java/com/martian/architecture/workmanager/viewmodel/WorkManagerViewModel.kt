package com.martian.architecture.workmanager.viewmodel

import android.content.ComponentName
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.work.*
import androidx.work.multiprocess.RemoteListenableWorker.ARGUMENT_CLASS_NAME
import androidx.work.multiprocess.RemoteListenableWorker.ARGUMENT_PACKAGE_NAME
import com.google.common.util.concurrent.ListenableFuture
import com.martian.architecture.workmanager.service.RemoteWorkService
import com.martian.architecture.workmanager.work.*
import java.util.concurrent.TimeUnit

/**
 * Create by：Martian
 * on 2022/6/9
 */
class WorkManagerViewModel : ViewModel() {

    private lateinit var context: Context
    private val TAG_1: String = "StringWork"
    private val TAG_2: String = "StringPeriodicWork"
    private val TAG_3: String = "StringConstraintsWork"
    private val TAG_4: String = "StringInitialDelayWork"
    private val ID_1: String = "StringWork1"
    private val ID_2: String = "StringWork2"
    private val ID_3: String = "StringWork3"
    private val ID_4: String = "ProgessWork"
    private val ID_5: String = "LinkWork"
    var string: MutableLiveData<String> = MutableLiveData<String>()

    fun init(context: Context) {
        this.context = context
    }

    fun workRequestOne(): LiveData<Operation.State> {
        val workRequest = OneTimeWorkRequest.from(WorkerOne::class.java)
        return WorkManager.getInstance(context).enqueue(workRequest).state
    }

    fun workRequestBuild(): LiveData<List<WorkInfo>> {
        val data = Data.Builder().putString("message","martian")
            .putAll(mapOf("data" to "WorkRequest Builder")).build()
        val workRequest = OneTimeWorkRequest.Builder(WorkerOne::class.java)
            .setInitialDelay(10,TimeUnit.SECONDS)
            .setInputData(data)
            .addTag(TAG_1).build()
        WorkManager.getInstance(context).enqueueUniqueWork(ID_1, ExistingWorkPolicy.REPLACE,workRequest)
        return WorkManager.getInstance(context).getWorkInfosForUniqueWorkLiveData(ID_1)
    }

    /**
     * 假如: targetSdkVersion < 31, 我 是否 只需调用 setForeground(), 而不需要调用 setExpedited()
     * 假如: targetSdkVersion >= 31, 我 是否 只需调用 setExpedited(), 而无需调用 setForeground()
     *
     * OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST，这会导致作业作为普通工作请求运行。上述代码段演示了此操作。
     * OutOfQuotaPolicy.DROP_WORK_REQUEST，这会在配额不足时导致请求取消。
     */
    fun workRequestExpedited(): LiveData<List<WorkInfo>> {
        val data = Data.Builder().putString("message","martian")
            .putAll(mapOf("data" to "WorkRequest setExpedited")).build()
        val workRequest = OneTimeWorkRequest.Builder(CoroutineWorkerOne::class.java)
            .setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST)
            .setInputData(data)
            .addTag(TAG_1).build()
        WorkManager.getInstance(context).enqueueUniqueWork(ID_1, ExistingWorkPolicy.REPLACE,workRequest)
        return WorkManager.getInstance(context).getWorkInfosForUniqueWorkLiveData(ID_1)
    }


    fun workRequestPeriodic(): LiveData<List<WorkInfo>> {
        val data = Data.Builder().putString("message","martian")
            .putAll(mapOf("data" to "WorkRequest Periodic")).build()
        val workRequest = PeriodicWorkRequest.Builder(WorkerOne::class.java,15,TimeUnit.MINUTES)
                //PeriodicWorkRequests cannot be expedited
//            .setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST)
            .setInputData(data)
            .addTag(TAG_2).build()
//        WorkManager.getInstance(context).enqueue(workRequest)
        WorkManager.getInstance(context).enqueueUniquePeriodicWork("WorkRequest Periodic",ExistingPeriodicWorkPolicy.REPLACE,workRequest)
        return WorkManager.getInstance(context).getWorkInfosForUniqueWorkLiveData("WorkRequest Periodic")
    }

    /**
     * NetworkType	约束运行工作所需的网络类型。例如 Wi-Fi (UNMETERED)。
     * BatteryNotLow	如果设置为 true，那么当设备处于“电量不足模式”时，工作不会运行。
     * RequiresCharging	如果设置为 true，那么工作只能在设备充电时运行。
     * DeviceIdle	如果设置为 true，则要求用户的设备必须处于空闲状态，才能运行工作。在运行批量操作时，此约束会非常有用；若是不用此约束，批量操作可能会降低用户设备上正在积极运行的其他应用的性能。
     * StorageNotLow	如果设置为 true，那么当用户设备上的存储空间不足时，工作不会运行。
     */
    @RequiresApi(Build.VERSION_CODES.M)
    fun workRequestConstraints(): LiveData<List<WorkInfo>> {
        val data = Data.Builder().putString("message","martian")
            .putAll(mapOf("data" to "WorkRequest Constraints")).build()

        val contraints = Constraints.Builder()
            .setRequiresCharging(true)
            .setRequiresDeviceIdle(true)
            .setRequiresStorageNotLow(true)
            .build()
        val workRequest = PeriodicWorkRequest.Builder(WorkerOne::class.java,15,TimeUnit.MINUTES)
            .setConstraints(contraints)
            //PeriodicWorkRequests cannot be expedited
//            .setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST)
            .setInputData(data)
            .addTag(TAG_3).build()
//        WorkManager.getInstance(context).enqueue(workRequest)
        WorkManager.getInstance(context).enqueueUniquePeriodicWork("WorkRequest Constraints",ExistingPeriodicWorkPolicy.REPLACE,workRequest)
//        return WorkManager.getInstance(context).getWorkInfosByTagLiveData(TAG_3)
        return WorkManager.getInstance(context).getWorkInfosForUniqueWorkLiveData("WorkRequest Constraints")
    }



    fun workRequestInitialDelay(): LiveData<List<WorkInfo>> {
        val data = Data.Builder().putString("message","martian")
            .putAll(mapOf("data" to "WorkRequest Constraints")).build()

        val workRequest = OneTimeWorkRequest.Builder(WorkerOne::class.java)
            .setInitialDelay(5,TimeUnit.SECONDS)
            //PeriodicWorkRequests cannot be expedited
//            .setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST)
            .setInputData(data).build()
        WorkManager.getInstance(context).enqueueUniqueWork(ID_2, ExistingWorkPolicy.REPLACE,workRequest)
        return WorkManager.getInstance(context).getWorkInfosForUniqueWorkLiveData(ID_2)
    }
    fun workRequestBackoffCriteria(): LiveData<List<WorkInfo>> {
        val data = Data.Builder().putString("message","martian")
            .putAll(mapOf("data" to "WorkRequest Constraints")).build()

        val workRequest = OneTimeWorkRequest.Builder(WorkerTwo::class.java)
            .setBackoffCriteria(BackoffPolicy.LINEAR,OneTimeWorkRequest.MIN_BACKOFF_MILLIS, TimeUnit.MILLISECONDS)
            //PeriodicWorkRequests cannot be expedited
//            .setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST)
//            .setInputData(data)
            .build()
        WorkManager.getInstance(context).enqueueUniqueWork(ID_3, ExistingWorkPolicy.REPLACE,workRequest)
        return WorkManager.getInstance(context).getWorkInfosForUniqueWorkLiveData(ID_3)
    }

    fun workRequestInputMerger(inputData:Int): LiveData<List<WorkInfo>> {

        val workRequestOne = OneTimeWorkRequest.Builder(WorkerThree::class.java)
            .addTag("WorkerThree")
//            .setInputData(dataOne)
//            .setInputMerger(OverwritingInputMerger::class)
            .build()

        val workRequestTwo = OneTimeWorkRequest.Builder(WorkerFour::class.java)
            .addTag("WorkerFour")
//            .setInputData(dataTwo)
//            .setInputMerger(OverwritingInputMerger::class)
            .build()
        val workRequest = OneTimeWorkRequest.Builder(WorkerFive::class.java)
            .addTag("WorkerFive")
            .setInputMerger(ArrayCreatingInputMerger::class)
            .build()

        //beginWith、then use listOf   be  different with WorkContinuation.combine
        //WorkContinuation.combine  has  more one worker  which is  CombineContinuationsWorker
        val continuationOne = WorkManager.getInstance(context).beginWith(workRequestOne)
        val continuationTwo = WorkManager.getInstance(context).beginWith(workRequestTwo)
//        val continuation = WorkContinuation.combine(listOf(continuationOne,continuationTwo)).then(workRequest)
        val continuation = WorkManager.getInstance(context).beginWith(listOf(workRequestOne,workRequestTwo)).then(workRequest)
        continuation.enqueue()
        return continuation.workInfosLiveData
    }

    fun workQuery(): ListenableFuture<List<WorkInfo>> {
        val workQuery = WorkQuery.Builder
            .fromUniqueWorkNames(listOf("WorkRequest Periodic","WorkRequest Constraints",ID_1,ID_2,ID_3))
            .addStates(listOf(WorkInfo.State.ENQUEUED,WorkInfo.State.SUCCEEDED))
            .build()
        return WorkManager.getInstance(context).getWorkInfos(workQuery)
    }


    fun cancelWork(){
//        WorkManager.getInstance(context).cancelUniqueWork("WorkRequest Periodic")
//        WorkManager.getInstance(context).cancelAllWorkByTag(TAG_3)
//        WorkManager.getInstance(context).cancelWorkById()
        WorkManager.getInstance(context).cancelAllWork()
    }

    fun progressWorker(): LiveData<List<WorkInfo>>{
        val workRequest = OneTimeWorkRequest.Builder(ProgressWorker::class.java).build()
        WorkManager.getInstance(context).enqueueUniqueWork(ID_4, ExistingWorkPolicy.REPLACE,workRequest)
        return WorkManager.getInstance(context).getWorkInfosForUniqueWorkLiveData(ID_4)
    }

    fun linkWork(): LiveData<List<WorkInfo>>{
        val progressWorker = OneTimeWorkRequest.Builder(ProgressWorker::class.java).build()
        val workerOne = OneTimeWorkRequest.from(WorkerOne::class.java)

        val data = Data.Builder().putString("message","martian")
            .putAll(mapOf("data" to "WorkRequest Builder")).build()

        val workerTwoData = OneTimeWorkRequest.Builder(WorkerTwo::class.java)
            .setInitialDelay(10,TimeUnit.SECONDS)
            .setInputData(data)
            .addTag(TAG_1).build()

        val workRequest = OneTimeWorkRequest.Builder(CoroutineWorkerOne::class.java)
            .setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST)
            .setInputData(Data.Builder().putString("message","martian").putAll(mapOf("data" to "WorkRequest setExpedited")).build())
            .build()

        WorkManager.getInstance(context).beginUniqueWork(ID_5, ExistingWorkPolicy.REPLACE,progressWorker)
            .then(listOf(workerOne,workerTwoData))
            .then(workRequest)
            .enqueue()

        return WorkManager.getInstance(context).getWorkInfosForUniqueWorkLiveData(ID_5)
    }
    fun remoteProcessWorker(): LiveData<List<WorkInfo>>{
//        val PACKAGE_NAME = "com.martian.architecture"
        val PACKAGE_NAME = context.packageName

        val serviceName = RemoteWorkService::class.java.name
        val componentName = ComponentName(PACKAGE_NAME, serviceName)

        val data: Data = Data.Builder()
            .putString(ARGUMENT_PACKAGE_NAME, componentName.packageName)
            .putString(ARGUMENT_CLASS_NAME, componentName.className)
            .putString("message","martian service")
            .build()

        var workRequest = OneTimeWorkRequest.Builder(RemoteCoroutineWorkerOne::class.java)
            .setInputData(data)
            .build()
        WorkManager.getInstance(context).enqueueUniqueWork("RemoteWorkService",ExistingWorkPolicy.REPLACE,workRequest)
        return WorkManager.getInstance(context).getWorkInfosForUniqueWorkLiveData("RemoteWorkService")
    }

    override fun onCleared() {
        super.onCleared()
    }

}