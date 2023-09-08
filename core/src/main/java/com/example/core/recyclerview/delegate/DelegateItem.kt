package com.example.core.recyclerview.delegate

interface DelegateItem {
    fun content(): Any
    fun id(): Int
    fun compareToOther(other: DelegateItem): Boolean
}