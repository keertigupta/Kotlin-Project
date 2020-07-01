package com.example.example

fun main(){

    val a:Int = 99
    val b = 10

    fun setDetails(age:Int,gender:String ,location:String="Default Location"){
        println("Age is = $age")
        println("Location is = $location")
        println("Gender is = $gender")
    }

   /* setDetails(10,"M","Bangalore")
    setDetails(20,"F")
    setDetails(age = 10,gender = "M",location = "Kerala")
*/
    fun isEven(num:Int) = num %2 ==0

    val number =100
  //  println("Number  $number is  ${if(isEven(number)) "Even" else "Odd" }")

   /* fun getDetail() = {
        println("keerti")
       // println("Bangalore")
    }
*/
  /*  val temp = println("Test is")
    println(temp)*/

   /* print("My name is ${getDetail()}")*/
   /* if (a<10){
        print("One digit")
    }else if(a < 100){
        print("Two digit")
    }else{
        print("Too big")
    }*/

   /* if (a in 0..9){
        print("One digit")
    }else if(a in 10 ..99){
        print("Two digit")
    }else{
        print("Too big")
    }*/

  /*  when(a){
        in 0..9 -> print("one digit")
        in 10..99 ->print("two digit")

    }*/


/*
    println("First value is $a")
    println("Second Value is $b")
    print("Poduct of  $a and  $b is = ${a*b}")*/
    /*when(a){
        0->print("value is zero")
        *//*else->{
            println("Value is not Zero")
        }*//*
    }

    print("Number is ${if(a in 0..10) "one digit"  else "two digit"}")
    val c = a?.plus(8)?:0
*/
    //for(i in 1..10) println("value - $i")

}