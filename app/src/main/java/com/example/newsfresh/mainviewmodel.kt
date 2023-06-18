package com.example.newsfresh

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.newsfresh.models.news
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class mainviewmodel(private val repository:Repository):ViewModel() {
    lateinit var newsdata:LiveData<news>
    fun fetchnews(string: String){
        GlobalScope.launch(IO) {
            repository.getnews(string)
        }
        newsdata=repository.Newsdata
    }

}