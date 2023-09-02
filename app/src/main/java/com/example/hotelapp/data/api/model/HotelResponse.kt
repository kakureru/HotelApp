package com.example.hotelapp.data.api.model

import com.google.gson.annotations.SerializedName

class HotelResponse(
    @SerializedName("id") val id: Int? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("adress") val address: String? = null,
    @SerializedName("minimal_price") val minimalPrice: Int? = null,
    @SerializedName("price_for_it") val priceForIt: String? = null,
    @SerializedName("rating") val rating: Int? = null,
    @SerializedName("rating_name") val ratingName: String? = null,
    @SerializedName("image_urls") val imageUrls: List<String>? = null,
    @SerializedName("about_the_hotel") val aboutTheHotel: HotelDescription? = null,
) {
    class HotelDescription(
        @SerializedName("description") val description: String? = null,
        @SerializedName("peculiarities") val peculiarities: List<String>? = null,
    )
}