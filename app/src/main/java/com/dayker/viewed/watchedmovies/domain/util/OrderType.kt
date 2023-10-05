package com.dayker.viewed.watchedmovies.domain.util

sealed class OrderType {
    object Ascending : OrderType()
    object Descending : OrderType()
}