package com.journaldev.Variables

fun main(args: Array<String>) {



    var b = 10
    var a: Int = 1
    var c = a++ // 0
    println(a)
    a.inc()
    println(a)

    c = --a //1

    c.inv()
    println(c)


    val l = 23L
    var f = 1.56F
    var d = 1.55
    var d1 = 1.55e10

    var x= a + l
    val i: Int = x.toInt()

    val creditCardNumber = 1234_5678_9012_3456L
    //print(creditCardNumber)



    var newChar = c + 1
    //print(newChar)


    val character : Char = '\u0041'

    var characterA = 'C'
    var characterB = 'E'
    var characterC = 'C'

    if(characterA>characterB || characterA>characterC)
    {
        println("CharacterA isn't the smallest")
    }


    var equalityChecker = (characterA  == characterC)
    println(equalityChecker)


}