package com.dayker.viewed.domain.util

sealed class OrderType {
    object Ascending : OrderType()
    object Descending : OrderType()
}