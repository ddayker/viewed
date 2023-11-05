package com.dayker.viewed.watched.common.domain.util

sealed class MoviesOrder(val orderType: OrderType) {
    class Rating(orderType: OrderType) : MoviesOrder(orderType)
    class Viewing(orderType: OrderType) : MoviesOrder(orderType)
    class Release(orderType: OrderType) : MoviesOrder(orderType)

    fun copy(orderType: OrderType): MoviesOrder {
        return when (this) {
            is Rating -> Rating(orderType)
            is Viewing -> Viewing(orderType)
            is Release -> Release(orderType)
        }
    }
}