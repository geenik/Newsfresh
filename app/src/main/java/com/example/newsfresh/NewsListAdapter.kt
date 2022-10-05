package com.example.newsfresh

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsfresh.models.Article
import android.view.animation.AlphaAnimation
import androidx.core.net.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class NewsListAdapter(private val listener: newsitemclick):RecyclerView.Adapter<Newsviewholder>() {
    private val items:ArrayList<Article> =ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Newsviewholder {
          val view =LayoutInflater.from(parent.context).inflate(R.layout.item_news,parent,false)
          val viewholder=Newsviewholder(view)
        view.setOnClickListener{
            listener.onitemclick(items[viewholder.adapterPosition])
        }
          return viewholder

    }

    override fun onBindViewHolder(holder: Newsviewholder, position: Int) {
        var rawdate=items[position].publishedAt.replace("T"," ")
          rawdate=rawdate.replace("Z","")
               val currentitem=items[position]
        val datetime= parseDateToddMMyyyy(rawdate)
        holder.titleview.text=currentitem.title
               holder.Date.text=datetime
              holder.source.text=currentitem.source.name;
               Glide.with(holder.itemView.context).load(currentitem.urlToImage).into(holder.image)
               setFadeAnimation(holder.itemView);
    }
    fun updatednews(updatedNews:ArrayList<Article>){
        items.clear()
        items.addAll(updatedNews)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return items.size

    }
    private fun setFadeAnimation(view: View) {
        val anim = AlphaAnimation(0.0f, 1.0f)
        anim.duration = 1000
        view.startAnimation(anim)
    }
    fun parseDateToddMMyyyy(time: String?): String? {
        val inputPattern = "yyyy-MM-dd HH:mm:ss"
        val outputPattern = "dd-MMM-yyyy h:mm a"
        val inputFormat = SimpleDateFormat(inputPattern)
        val outputFormat = SimpleDateFormat(outputPattern)
        var date: Date? = null
        var str: String? = null
        try {
            date = inputFormat.parse(time)
            str = outputFormat.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        str=str?.removeRange(6,11)
        return str
    }
}
class Newsviewholder(itemview:View):RecyclerView.ViewHolder(itemview){
    val titleview=itemview.findViewById<TextView>(R.id.title)
    val image=itemview.findViewById<ImageView>(R.id.image)
    val  Date=itemview.findViewById<TextView>(R.id.Date)
    val source=itemview.findViewById<TextView>(R.id.Source)
}
interface newsitemclick{
    fun onitemclick(item: Article)
}
