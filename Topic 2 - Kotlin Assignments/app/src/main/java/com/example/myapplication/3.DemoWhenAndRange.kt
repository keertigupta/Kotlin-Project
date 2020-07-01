package com.example.myapplication

import java.util.*

/**
 * Created by Keerti on 01-07-2020.
 */

fun main(){

    val reader = Scanner(System.`in`)
    print("Enter the age of participant : ")

    when(reader.nextInt()){
        in 1 ..11 -> print("Kids entry pass alloted")
        in 12..19 -> print("Teen entry pass alloted")
        in 20..64 -> print("Regular entry pass alloted")
        else ->print("Free entry pass alloted")
    }
}