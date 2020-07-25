package com.example.example

/**
 * Created by Keerti on 10-07-2020.
 */
open class DLTest ( var name:String,private var type:String,private val noOfWheels:Int) {

       /* init {
            println("Name Of DL : $name")
            println(" Type is : $type")
            println("No of Whells : $noOfWheels")
        }*/

   open fun showDetails(){
        println("Name Of DL : $name")
        println("Type is : $type")
        println("No of Whells : $noOfWheels")
    }

}

class ThreeWheeler :DLTest("NameThreeWheeler","TypeThreeWheeler",3){


    override fun showDetails() {
       // super.showDetails()
        print("Inside Threewheeler")

    }

}

enum class elements(var noofelements:Int){
    Helium(1),
    Calcium(2),
    Hydrogen(5)
}

class Flower(val name:String,val color:String){
    init {
        //print("Hello")
    }
}

fun main(){

  // println("Helium is ${elements.Helium.noofelements}")
    val fl1 = Flower("Rose","Yellow")
    val fl2 = Flower("jesmine","White")
    val fl3 = Flower("Rose","Yellow")

    println(fl1.equals(fl3))


}