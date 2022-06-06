package com.example.gadgeontest.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Noushad N on 06-06-2022.
 */
data class User(
    @SerializedName("page")
    var page: Int? = null,
    @SerializedName("per_page")
    var per_page: Int? = null,
    @SerializedName("total")
    var total: Int? = null,
    @SerializedName("total_pages")
    var totalPages: Int? = null,
    @SerializedName("data")
    var data: List<Data>? = null,

)
