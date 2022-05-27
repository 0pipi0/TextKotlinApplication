package com.martian.architecture.databinding.util

import android.view.View
import androidx.databinding.BindingAdapter

/**
 * Create by：Martian
 * on 2022/5/27
 */
object BindingAdapters {

    @BindingAdapter("goneUnless")
    @JvmStatic fun goneUnless(view: View,showAge:Boolean){
        view.visibility = if(showAge)View.VISIBLE else View.GONE
    }

}