package com.mkk.ru.extension

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

private var lastTimeClicked = 0L

fun View.safeOnClickListener(delay: Long = 1000L, onClick: (View) -> Unit) {
    setOnClickListener {
        val currentTimeMillis = System.currentTimeMillis()
        if (currentTimeMillis > lastTimeClicked + delay) {
            onClick.invoke(this)
            lastTimeClicked = currentTimeMillis
        }
    }
}

fun ViewGroup.inflate(layoutId: Int): View {
    return LayoutInflater.from(context).inflate(layoutId, this, false)
}
