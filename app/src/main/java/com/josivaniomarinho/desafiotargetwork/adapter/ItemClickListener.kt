package com.josivaniomarinho.desafiotargetwork.adapter

import android.view.View

interface ItemClickListener {

    fun onClick(view: View?, position: Int, isLongCLick: Boolean)
}