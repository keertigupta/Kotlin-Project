package com.journaldev.Strings

fun main(args: Array<String>) {

    var str  = "Hello "
    str += "Kotlin Strings"
    println(str.length)
    var s = String()

    s = "Hello KOTLIN Strings"
    println(s.compareTo(str))
    println(s.compareTo(str,true))


    var len = str.length
    var newStr = "Length of str is ${str.length}"

    var escapedDollar = "Amount is \$5.50"
    println(escapedDollar)


    str = "Kotlin"
    var rawString = """Hi How you're Doing $str
        |I'm doing fine.
        |I owe you $5.50""".trimMargin("|")
    println(rawString)

    var student = Student("Anupam", 23)
    println(student)


    var a = "Hello"
    var b = "Hello again"
    var c = "Hello"
    var d1 = "Hel"
    var d2 ="lo"
    var d = d1 + d2

    println(a===c)
    println(a==c)
    println(a===b)
    println(a==b)
    println(a===d)
    println(a==d)


}

class Student(var name: String, var age: Int)
{

    override fun toString(): String {
        return "Student name is $name and age is $age"
    }
}