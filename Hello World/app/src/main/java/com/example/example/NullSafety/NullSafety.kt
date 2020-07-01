package com.journaldev.NullSafety

fun main(args: Array<String>) {


    var str: String = "JournalDev Kotlin Archives"
    //str= null //compilation error

    //var a : String = null

    var a: String? = null
    var newStr: String? = "Kotlin Null Safety"
    newStr = null

    println(a?.length)

    a = "Hi"
    println(a?.length)


    /*a = null
    println(a!!.length)

    a = "Hi"
    print(a!!.length)*/


    var b: String = "2"
    var x: Int? = b as? Int

    println(x)

    var array1: Array<Any?> = arrayOf("1", "2", "3")
    var array2: Array<Any?> = arrayOf("1", "2", "3", null)

    var newArray = array2.filterNotNull()
    println(newArray.size)
    var i: Int? = array1[0] as? Int
    println(i)
    var s: String? = array1[0] as? String
    println(s)


    var newString: String? = "JournalDev.com"

    println(newString?.length)
    newString = null
    println(newString?.length ?: "-1")

    var c = "Hello"

    newString = " Kotlin from Android"
    newString?.also { println("Logging the value: $it") }




    newString = null
    newString?.let { println("The string value is: $it") }


}