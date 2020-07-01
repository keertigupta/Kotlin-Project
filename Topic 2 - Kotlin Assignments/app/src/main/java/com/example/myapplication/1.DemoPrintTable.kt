package com.example.myapplication

import java.util.*

/**
 * Created by Keerti on 01-07-2020.
 */

fun main(){

    val reader  = Scanner(System.`in`)
    print("Enter Number to Print Table :")

    val number = reader.nextInt()

    fun printTable(num:Int){
        println("Table of Number $num :")
        for(i in 1..10)
            println(num.times(i))
    }
    printTable(number)
}