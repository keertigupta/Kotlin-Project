package com.example.lambdademo

/**
 * Created by Keerti on 26-07-2020.
 */
fun main(){

    val sum  = {a:Float,b:Float,c:Float -> a+b+c}

    println("Sum Of Number is ${sum(3.5F,5.6F,6.0F)}")
}