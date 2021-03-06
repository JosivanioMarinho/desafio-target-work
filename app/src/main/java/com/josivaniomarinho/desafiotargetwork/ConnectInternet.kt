package com.josivaniomarinho.desafiotargetwork

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

object ConnectInternet {

    fun idNetworkAvaiable(context: Context?): Boolean {
        val connectivity = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networks = connectivity.allNetworks
        return networks
            .map { connectivity.getNetworkInfo(it) }
            .any { it?.state == NetworkInfo.State.CONNECTED }
    }
}