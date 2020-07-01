package com.example.myapplication

import java.util.*

/**
 * Created by Keerti on 01-07-2020.
 */

fun main(){

    val reader = Scanner(System.`in`)
    print("Enter the number :")

    when(reader.nextInt()){
        0->print("Given day is sunday")
        1-> print("Given day is monday")
        2->print("Given day is tuesday")
        3-> print("Given day is wednesday")
        4-> print("Given day is thursday")
        5-> print("Given day is friday")
        6-> print("Given day is saturday")
        else -> print("Not a valid number")
    }
}