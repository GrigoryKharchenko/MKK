package com.mkk.ru.extension

import androidx.fragment.app.Fragment
import androidx.fragment.app.add
import androidx.fragment.app.commit

inline fun <reified F : Fragment> Fragment.addFragment(containerId: Int) {
    parentFragmentManager.commit {
        setReorderingAllowed(true)
        add<F>(containerId)
    }
}

inline fun <reified F : Fragment> Fragment.addFragment(containerId: Int, tag: String) {
    parentFragmentManager.commit {
        setReorderingAllowed(true)
        addToBackStack(tag)
        add<F>(containerId)
    }
}
