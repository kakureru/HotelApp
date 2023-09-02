package com.example.hotelapp.presentation.recyclerview.adapter

interface DelegateItem {
    fun content(): Any
    fun id(): Int
    fun compareToOther(other: DelegateItem): Boolean
}