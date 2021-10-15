package com.josivaniomarinho.desafiotargetwork.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.josivaniomarinho.desafiotargetwork.ConnectInternet
import com.josivaniomarinho.desafiotargetwork.R
import com.josivaniomarinho.desafiotargetwork.adapter.FeedAdapter
import com.josivaniomarinho.desafiotargetwork.domain.Service.RSSService
import com.josivaniomarinho.desafiotargetwork.domain.model.Enclosure
import com.josivaniomarinho.desafiotargetwork.domain.model.Feed
import com.josivaniomarinho.desafiotargetwork.domain.model.Item
import com.josivaniomarinho.desafiotargetwork.domain.model.RSSObject
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toobar.*
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        supportActionBar?.title = getString(R.string.toobar_title)

        val layoutManager = LinearLayoutManager(baseContext, LinearLayoutManager.VERTICAL, false)
        rv_feed.layoutManager = layoutManager

        taskRSS()

        refresh.setOnRefreshListener {
            taskRSS()
        }
    }

    private fun taskRSS(){

        // Verifica a conexão. Se estiver OK ele carrega a lista
        var internetIsOk = ConnectInternet.idNetworkAvaiable(applicationContext)

        if (internetIsOk) {
            thread {

                val rss = RSSService.getRSS()
                Log.d("rssRetrofit", "taskRSS: ${rss.items.toString()}")

                runOnUiThread{
                    rv_feed.adapter = FeedAdapter(rss, baseContext)
                    refresh.isRefreshing = false
                }
            }
        } else {
            // Se não estiver conectado, é mostrado uma mensagem
            txt_connected.text = "Por favor, verifique sua conexão."
        }
    }
}