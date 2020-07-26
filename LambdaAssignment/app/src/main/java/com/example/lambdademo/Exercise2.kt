package com.example.lambdademo

/**
 * Created by Keerti on 26-07-2020.
 */

fun main(){

    val grade = {totalnumber:Int->
        when(totalnumber){
            in 0..35 ->println("fail")
            in 36..60 ->println("Passed")
            in 61..100->println("Passed with distinction")
        }
    }
    grade(40)

}