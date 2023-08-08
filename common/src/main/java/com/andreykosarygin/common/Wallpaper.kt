package com.andreykosarygin.common

import android.graphics.Bitmap
import java.io.Serializable

data class Wallpaper(
    val id: Long,
    val name: String,
    val price: Int,
    val imageResource: Bitmap
) : Serializable