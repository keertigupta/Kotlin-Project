package com.example.lambdademo

/**
 * Created by Keerti on 26-07-2020.
 */

fun main(){

    val sum = {a:Int,b:Int,c:Int->
                                    a+b+c }
    val average = {firstNo:Int,secondNo:Int,someFunc:(Int,Int,Int)->Int ->
        ( firstNo+secondNo+someFunc(2,2,2))/3
    }

    println("Average is ${average(3,3,sum)}")
}