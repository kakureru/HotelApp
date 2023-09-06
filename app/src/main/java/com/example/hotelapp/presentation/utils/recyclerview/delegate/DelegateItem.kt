package com.example.hotelapp.presentation.utils.recyclerview.delegate

interface DelegateItem {
    fun content(): Any
    fun id(): Int
    fun compareToOther(other: DelegateItem): Boolean
}