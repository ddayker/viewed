package com.dayker.viewed.watched.common.domain.util

sealed class OrderType {
    object Ascending : OrderType()
    object Descending : OrderType()
}