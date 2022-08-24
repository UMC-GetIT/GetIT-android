package com.getit.getit.data

import java.io.Serializable

data class NameImagePriceProduct(
    var id: String? = "",
    var name: String? = "",
    var price: String? = "",
    var coverImg: String? = ""
) : Serializable
