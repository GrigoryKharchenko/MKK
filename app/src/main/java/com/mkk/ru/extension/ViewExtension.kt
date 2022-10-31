package com.mkk.ru.extension

import android.view.View

private var lastTimeClicked = 0L
private val currentTimeMillis = System.currentTimeMillis()

fun View.safeOnClickListener(delay: Long = 1000L, onClick: (View) -> Unit) {
    setOnClickListener {
        if (currentTimeMillis > lastTimeClicked + delay) {
            onClick.invoke(this)
            lastTimeClicked = currentTimeMillis
        }
    }
}
