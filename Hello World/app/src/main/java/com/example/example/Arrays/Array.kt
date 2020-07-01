package com.journaldev.Arrays

fun main(args: Array<String>) {

    var array1 = arrayOf(1,2,3,4)
    val array2 = arrayOf(1L,2L,3L,4L)
    val array3 = arrayOf<Long>(1,2,3,4)

    array3.get(0)
    array3[0]


    /*array1[1] = 6
    array1.set(1,6)*/

    val array = Array(6) {i-> "Hi "+i}

    for(element in array)
        println(element)

    val intArray = IntArray(6)

    for(element in intArray)
    {
        println(element)
    }


    array1 = array1.reversedArray()
    for(element in array1)
    {
        println(element)
    }


    println("AA")

    array1.reverse()
    for(element in array1)
    {
        println(element)
    }

    println("ABB")
    println(array1.sum())

    array1 = array1.plus(5)
    for(element in array1)
    {
        println(element)
    }
    println("ABB")

    var oldArray = Array(6, {i->i*10})

    println("Size is ${array1.size}")
    array1.fill(0,0,array1.size)
    for(element in array1)
    {
        println(element)
    }

    array1 = array1.plus(oldArray)

    for(element in array1)
    {
        println(element)
    }
}