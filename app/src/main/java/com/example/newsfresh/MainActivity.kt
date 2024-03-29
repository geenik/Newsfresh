package com.example.newsfresh

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.core.view.GravityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.newsfresh.databinding.ActivityMainBinding
import com.example.newsfresh.models.Article


class MainActivity : AppCompatActivity(), newsitemclick {
     lateinit var Bind:ActivityMainBinding
     lateinit var  madapter:NewsListAdapter
     lateinit var viewmodel:mainviewmodel
     lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        Bind= ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(Bind.root)
        //viewmodal
        val newsservice=retrofithelper.getinstace().create(newsapi::class.java)
        val repository=Repository(newsservice)
        viewmodel=ViewModelProvider(this,Myviewmodalfactory(repository)).get(mainviewmodel::class.java)
        fetch()
        val recyclerView=findViewById<RecyclerView>(R.id.recycleview)
        recyclerView.layoutManager=LinearLayoutManager(this)
        (viewmodel).newsdata.observe(this, {
            madapter.updatednews(it.articles as ArrayList<Article>)
        })
        madapter= NewsListAdapter(this)
        recyclerView.adapter=madapter

        //navigation view

        toggle= ActionBarDrawerToggle(this,Bind.root,R.string.open,R.string.close)
        Bind.root.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        Bind.navview
            .setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.All->viewmodel.fetchnews("All")
                R.id.Sports->viewmodel.fetchnews("Sports")
                R.id.Business->viewmodel.fetchnews("Business")
                R.id.Entertainment->viewmodel.fetchnews("Entertainment")
                R.id.General->viewmodel.fetchnews("General")
                R.id.Health->viewmodel.fetchnews("Health")
                R.id.Science->viewmodel.fetchnews("Science")
                R.id.Technology->viewmodel.fetchnews("Technology")
            }
            Bind.root.closeDrawer(GravityCompat.START)
                recyclerView.scrollToPosition(0)
            true
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item))return true
        return super.onOptionsItemSelected(item)
    }
//    private fun fetchdata(){
//       val url="https://newsapi.org/v2/top-headlines?country=in&apiKey=b237dc50292c4b6ba8eeccd0f71ce213"
//       val queue=Volley.newRequestQueue(this)
//        val jsonObjectRequest = object:JsonObjectRequest(
//            Request.Method.GET, url, null,
//             { response ->
//            val jsonobjectarray=response.getJSONArray("articles")
//               val newsarray= arrayListOf<News>()
//                for(i in 0 until jsonobjectarray.length()){
//                    val newsobj=jsonobjectarray.getJSONObject(i)
//                    val news=News(
//                        newsobj.getString("title"),
//                        newsobj.getString("author"),
//                        newsobj.getString("url"),
//                        newsobj.getString("urlToImage")
//                    )
//                    newsarray.add(news)
//                }
//
//               // madapter.updatednews(newsarray)
//            },
//            Response.ErrorListener { error ->
//                 println(error.message)
//            }
//        )
//        {
//         override fun getHeaders(): Map<String, String> {
//            val headers = HashMap<String, String>()
//            headers["User-Agent"] = "Mozilla/5.0"
//            return headers
//        }
//        }
//       queue.add(jsonObjectRequest)
//    }

    override fun onitemclick(item: Article) {
        val url=item.url
        var intent=Intent(this,web_view::class.java)
        intent.putExtra("key",url)
        startActivity(intent)
    }
    fun fetch(string: String="All"){
        viewmodel.fetchnews(string)
    }
}