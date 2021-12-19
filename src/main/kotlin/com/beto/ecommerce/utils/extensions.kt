package com.beto.ecommerce.utils

fun <E> MutableSet<E>.update(element:E): Boolean{
    return this.remove(element) && this.add(element)
}