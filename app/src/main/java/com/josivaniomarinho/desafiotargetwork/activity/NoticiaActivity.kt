package com.josivaniomarinho.desafiotargetwork.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.Toolbar
import com.josivaniomarinho.desafiotargetwork.R
import com.josivaniomarinho.desafiotargetwork.domain.model.Item
import com.josivaniomarinho.desafiotargetwork.extensions.loadUrl
import kotlinx.android.synthetic.main.activity_noticia.*
import kotlinx.android.synthetic.main.toobar.*

class NoticiaActivity : AppCompatActivity() {

    private var item: Item? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_noticia)

        setupToobar()

        item = intent.getParcelableExtra("item")

        bindingViews()
    }

    private fun setupToobar() {
        setSupportActionBar(toobar2 as Toolbar?)
        supportActionBar?.title = "Notícia"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun bindingViews() {
        text_data.text = item?.pubDate
        txt_title_noticia.text = item?.title

        var stg = item?.description
        var separador = "<br>"
        val description = stg?.split(separador)

        txt_subtitle_noticia.text = when(description?.size) {
            1 -> description?.get(0)
            else -> description?.get(1)
        }

        var thumb: String? = item?.thumbnail
        image_noticia.loadUrl(thumb ?: "")
    }
}