package com.example.myapplication

/**
 * Created by Keerti on 01-07-2020.
 */

fun main(){
    val arrayString = arrayOf("Keerti","Bharti","Kamla")

    for((index,element) in arrayString.withIndex()){
        println("Index  = $index & element = $element")
    }
    println()
}