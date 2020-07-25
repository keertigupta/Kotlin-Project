package com.example.example

/**
 * Created by Keerti on 12-07-2020.
 */
abstract class Shape {

/*
   fun printDetails(){
        println("Width is $width")
       println("Height is $height")
       println("Length is $lenght")
    }*/

    abstract  fun calculateArea()
    abstract fun calculteVolume()
}

class Triangle : Shape() {
    val height = 10
    val base = 6
    override fun calculateArea() {

        val area = .5*base*height
        println(" Area is : $area")

    }

    override fun calculteVolume() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}

class Cube: Shape(){

    val a = 4
    override fun calculateArea() {


    }

    override fun calculteVolume() {
        val volume  =  a*a*a
        println("Cube Volume is $volume")
    }

}

fun main(){
    val triangle =  Triangle()
    triangle.calculateArea()
    val cube = Cube()
    cube.calculteVolume()

}