package com.example.example.interfaceDemo

/**
 * Created by Keerti on 12-07-2020.
 */
abstract class Shape{
    abstract fun printColor()
}

interface Area {
    fun calculateArea()
}

interface Volume{
    fun caclulateVolume()
}

class Cube : Shape(), Volume{

    val a = 8
    val color = "White Cube"
    override fun caclulateVolume() {
        println("Volume is ${a*a*a}")
    }

    override fun printColor() {
        println("Color of cube is $color")
    }
}

class Triangle :Shape(),Area{

    val base = 5
    val height = 10
    val color ="Blue"

    override fun printColor() {
        println("Color of Triangle is $color")
    }

    override fun calculateArea() {
        println("Area of Triangle is ${.5*base*height}")
    }

}
fun main(){
    val t1 = Triangle()
    //t1.printColor()
   // t1.calculateArea()


    val c1 = Cube()
  //  c1.printColor()
   // c1.caclulateVolume()

    val s1 = object  :Shape(){
        override fun printColor() {
            println("inside printColor")
        }

    }
    s1.printColor()

    val a1 =  object :Area{
        override fun calculateArea() {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

    }
}