package com.dayker.viewed.watchedmovies.common.domain.util

sealed class OrderType {
    object Ascending : OrderType()
    object Descending : OrderType()
}