package com.example.helloconstraintlayout

/**
 * Created by Keerti on 22-07-2020.
 */

fun main(){

    var address: Address?
    address = Address("keerti",30)
    //println("Address is $address")

    var add = {a:Int,b:Int,c:Int ->a+b+c}
    println( " Sum  = ${add(4,6,5)}")

}

