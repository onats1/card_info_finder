package com.onats.cardinfofinder.business.interactors

interface DataMapper<K, T> {

    fun mapObject(data: K): T
}