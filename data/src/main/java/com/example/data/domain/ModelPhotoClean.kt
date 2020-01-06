package com.example.data.domain

interface  MapperDomain<T: Any> {
    fun mapToDomainModel() : T
}

interface  MapperRoom<out T: Any>{
    fun mapToRoomModel() : T
}