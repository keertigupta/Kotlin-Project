package com.example.myapplication

/**
 * Created by Keerti on 01-07-2020.
 */

fun main(){
    val dummyStringList = mutableListOf<String>("Gita","Sita","Teena","Kamla")

    for((index,element) in  dummyStringList.withIndex()){
        println("Index = $index & element = $element")
    }
}