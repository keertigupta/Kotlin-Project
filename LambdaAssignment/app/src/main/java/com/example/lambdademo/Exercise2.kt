package com.example.lambdademo

/**
 * Created by Keerti on 26-07-2020.
 */

fun main(){

    val grade = {totalnumber:Int->
        when(totalnumber){
            in 0..35 ->"fail"
            in 36..60 ->"Passed"
            in 61..100->"Passed with distinction"
            else ->"Less than 0 or more than 100"
        }
    }
        println("Grade is :- ${grade(40)}")

}