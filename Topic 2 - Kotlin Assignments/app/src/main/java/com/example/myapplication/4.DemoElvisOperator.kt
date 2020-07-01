package com.example.myapplication

/**
 * Created by Keerti on 01-07-2020.
 */

fun main(){
    var counter:Int? = null

    for(i in 0..5) {
         counter = counter?.plus(1) ?: 0

    }
    println("Value of counter is $counter")

}