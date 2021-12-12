package com.example.practicaandroidrecyclerview

import java.io.Serializable

class Contacto() : Serializable {
    lateinit var name: String
    var phoneNumber: Int = 0
    var imageUrl: Int = R.drawable.default_user_pfp
    var isFavourite: Boolean = false

    constructor(
        name: String?,
        phoneNumber: Int,
        imageUrl: Int?,
        isFavourite: Boolean?
    ) : this() {
        this.name = name ?: "$phoneNumber"
        this.phoneNumber = phoneNumber
        this.imageUrl = imageUrl ?: R.drawable.default_user_pfp
        this.isFavourite = isFavourite ?: false
    }
}