package com.dicoding.myapp_superheroes

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Superheroes(
    val name: String,
    val description: String,
    val photo: Int,
    val keterangan: String
) : Parcelable
