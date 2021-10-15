package com.josivaniomarinho.desafiotargetwork.domain.Service

import com.josivaniomarinho.desafiotargetwork.domain.model.RSSObject
import com.josivaniomarinho.desafiotargetwork.domain.retrofit.EndPoint
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RSSService {

    private const val BASE_URL = "https://api.rss2json.com/v1/"
    private var service: EndPoint

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(EndPoint::class.java)
    }

    fun getRSS(): RSSObject {
        val call = service.getRSS()
        val rss = call.execute().body()
        return rss!!
    }

}