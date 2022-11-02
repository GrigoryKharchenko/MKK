package com.mkk.ru.extension

import android.view.View

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
