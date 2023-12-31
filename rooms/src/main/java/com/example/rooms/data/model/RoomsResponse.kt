package com.example.rooms.data.model

import com.google.gson.annotations.SerializedName

class RoomsResponse(
    @SerializedName("rooms") val rooms: List<RoomDto>
) {
    class RoomDto(
        @SerializedName("id") val id: Int? = null,
        @SerializedName("name") val name: String? = null,
        @SerializedName("price") val price: Int? = null,
        @SerializedName("price_per") val pricePer: String? = null,
        @SerializedName("peculiarities") val peculiarities: List<String>? = null,
        @SerializedName("image_urls") val imageUrls: List<String>? = null,
    )
}