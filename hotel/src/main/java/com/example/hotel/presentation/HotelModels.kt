package com.example.hotel.presentation

import com.example.core.toFormattedPrice
import com.example.hotel.domain.Hotel
import com.example.hotel.presentation.feature.FeatureItem

data class HotelUiState(
    val imageUrls: List<String> = emptyList(),
    val rating: String = "",
    val name: String = "",
    val address: String = "",
    val minimalPrice: String = "",
    val priceForIt: String = "",
    val peculiarities: List<String> = emptyList(),
    val description: String = "",
    val features: List<FeatureItem> = emptyList(),
)

fun Hotel.toUiState(features: List<FeatureItem>) = HotelUiState(
    imageUrls = imageUrls,
    rating = "$rating $ratingName",
    name = name,
    address = address,
    minimalPrice = minimalPrice.toFormattedPrice(),
    priceForIt = priceForIt,
    peculiarities = peculiarities,
    description = description,
    features = features,
)