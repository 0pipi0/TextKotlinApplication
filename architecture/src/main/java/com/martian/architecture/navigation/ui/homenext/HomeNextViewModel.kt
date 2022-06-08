package com.martian.architecture.navigation.ui.homenext

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.martian.architecture.navigation.data.Person

class HomeNextViewModel : ViewModel() {

    var pserson : MutableLiveData<Person> = MutableLiveData()

    override fun onCleared() {
        super.onCleared()
    }
}