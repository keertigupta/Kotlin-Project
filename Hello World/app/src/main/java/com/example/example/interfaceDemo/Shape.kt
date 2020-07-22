package com.example.example.interfaceDemo

/**
 * Created by Keerti on 12-07-2020.
 */
abstract class Shape{
    abstract fun printColor()
}

interface CalculateArea {
    fun calculateArea()
}

interface CaculateVolume{
    fun caclulateVolume()
}

class Cube : Shape(), CaculateVolume{

    val a = 8
    val color = "White Cube"
    override fun caclulateVolume() {
        print("Volume is ${a*a*a}")
    }

    override fun printColor() {
        println("Color of cube is $color")
    }
    
   
}