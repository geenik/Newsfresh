package com.example.newsfresh

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class Myviewmodalfactory(private val repository: Repository):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return mainviewmodel(repository) as T
    }
}