package co.yap.master_detailapplication.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job

internal interface CoroutineViewModel {
    val viewModelJob: Job
    val viewModelScope: CoroutineScope
    fun cancelAllJobs()
    fun launch(block: suspend () -> Unit)
}