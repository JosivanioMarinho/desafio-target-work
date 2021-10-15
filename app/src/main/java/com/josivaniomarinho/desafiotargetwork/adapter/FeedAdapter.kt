package com.josivaniomarinho.desafiotargetwork.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.josivaniomarinho.desafiotargetwork.R
import com.josivaniomarinho.desafiotargetwork.activity.NoticiaActivity
import com.josivaniomarinho.desafiotargetwork.domain.model.RSSObject
import com.josivaniomarinho.desafiotargetwork.extensions.loadUrl
import java.util.zip.Inflater

class FeedAdapter(
    private val rssObject: RSSObject,
    private val mContext: Context
): RecyclerView.Adapter<FeedViewlHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewlHolder {
        val itemView = LayoutInflater.from(mContext).inflate(R.layout.item_noticia, parent, false)
        return FeedViewlHolder(itemView)
    }

    override fun onBindViewHolder(holder: FeedViewlHolder, position: Int) {
        var item = rssObject.items[position]

        holder.textTitle.text = item.title

        var stg = item.description
        var separador = "<br>"
        val description = stg.split(separador)

        holder.textSubtitle.text = when(description.size) {
            1 -> description[0]
            else -> description[1]
        }
        holder.imageFeed.loadUrl(item.thumbnail)

        holder.setItemClickListener(object : ItemClickListener {
            override fun onClick(view: View?, position: Int, isLongCLick: Boolean) {
                if (!isLongCLick) {
                    val intent = Intent(mContext, NoticiaActivity::class.java)
                    intent.putExtra("item", item)
                    mContext.startActivity(intent)
                    //Toast.makeText(mContext, "Clicou no item $position", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    override fun getItemCount(): Int = rssObject.items.size
}

class FeedViewlHolder(itemView: View):
    RecyclerView.ViewHolder(itemView),
    View.OnClickListener,
    View.OnLongClickListener
{
    var textTitle: TextView
    var textSubtitle: TextView
    var imageFeed: ImageView
    var progress: ProgressBar

    private var itemClickListener: ItemClickListener? = null

    init {
        imageFeed = itemView.findViewById(R.id.image_feed)
        textTitle = itemView.findViewById(R.id.txt_title_feed)
        textSubtitle = itemView.findViewById(R.id.txt_subtitle_feed)
        progress = itemView.findViewById(R.id.progress)

        itemView.setOnClickListener(this)
        itemView.setOnClickListener(this)
    }

    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    override fun onClick(v: View?) {
        itemClickListener!!.onClick(v, adapterPosition, false)
    }

    override fun onLongClick(v: View?): Boolean {
        itemClickListener!!.onClick(v, adapterPosition, true)
        return true
    }

}