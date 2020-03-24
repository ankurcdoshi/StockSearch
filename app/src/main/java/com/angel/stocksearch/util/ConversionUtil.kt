package com.angel.stocksearch.util

import android.content.Context
import android.util.DisplayMetrics

class ConversionUtil {
    companion object {
        fun convertPixelsToDp(px: Float, context: Context): Float {
            return px / (context.resources.displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT)
        }
    }
}