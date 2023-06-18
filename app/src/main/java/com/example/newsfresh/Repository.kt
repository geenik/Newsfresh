package com.example.newsfresh

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.newsfresh.models.news
import retrofit2.Response

class Repository(private val newsservice: newsapi) {
   private val  newsdata=MutableLiveData<news>()
   lateinit var resultnews:Response<news>
    val Newsdata:LiveData<news>
          get()  = newsdata
   suspend fun getnews(string: String){
       when(string){
           "All" ->resultnews = newsservice.getNews()
           "Sports"-> resultnews=newsservice.getsportsNews()
           "Business"->resultnews=newsservice.getbusinessNews()
           "Entertainment"->resultnews=newsservice.getentertainmentNews()
           "General"->resultnews=newsservice.getgeneralNews()
           "Health"->resultnews=newsservice.gethealthNews()
           "Science"->resultnews=newsservice.getscienceNews()
           "Technology"->resultnews=newsservice.gettechnologyNews()
       }
       if(resultnews?.body() != null){
        newsdata.postValue(resultnews.body())
       }
   }

}