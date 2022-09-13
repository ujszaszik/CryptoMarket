package com.swissborg.cryptomarket.extension

import android.content.Context

const val DRAWABLE_DEFINITION_TYPE = "drawable"

fun Context.getDrawableIdByName(name: String): Int {
    return resources.getIdentifier(name, DRAWABLE_DEFINITION_TYPE, packageName)
}