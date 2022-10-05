package com.example.newsfresh
import com.example.newsfresh.models.news
import retrofit2.http.GET
import retrofit2.http.Headers

interface newsapi {
    @GET("top-headlines?country=in&apiKey=b237dc50292c4b6ba8eeccd0f71ce213")
    suspend fun getNews():retrofit2.Response<news>

    @GET("top-headlines?country=in&category=Sports&apiKey=b237dc50292c4b6ba8eeccd0f71ce213")
    suspend fun getsportsNews():retrofit2.Response<news>

    @GET("top-headlines?country=in&category=business&apiKey=b237dc50292c4b6ba8eeccd0f71ce213")
    suspend fun getbusinessNews():retrofit2.Response<news>

    @GET("top-headlines?country=in&category=entertainment&apiKey=b237dc50292c4b6ba8eeccd0f71ce213")
    suspend fun getentertainmentNews():retrofit2.Response<news>

    @GET("top-headlines?country=in&category=general&apiKey=b237dc50292c4b6ba8eeccd0f71ce213")
    suspend fun getgeneralNews():retrofit2.Response<news>

    @GET("top-headlines?country=in&category=health&apiKey=b237dc50292c4b6ba8eeccd0f71ce213")
    suspend fun gethealthNews():retrofit2.Response<news>

    @GET("top-headlines?country=in&category=science&apiKey=b237dc50292c4b6ba8eeccd0f71ce213")
    suspend fun getscienceNews():retrofit2.Response<news>

    @GET("top-headlines?country=in&category=technology&apiKey=b237dc50292c4b6ba8eeccd0f71ce213")
    suspend fun gettechnologyNews():retrofit2.Response<news>

}
