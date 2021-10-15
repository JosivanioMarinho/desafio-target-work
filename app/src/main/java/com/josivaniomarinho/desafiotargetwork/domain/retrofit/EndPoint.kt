package com.josivaniomarinho.desafiotargetwork.domain.retrofit

import com.josivaniomarinho.desafiotargetwork.domain.model.RSSObject
import retrofit2.Call
import retrofit2.http.GET

interface EndPoint {

    @GET("api.json?rss_url=HTTPS%3A%2F%2Fg1.globo.com%2Frss%2Fg1%2Fcarros%2F")
    fun getRSS(): Call<RSSObject>
}