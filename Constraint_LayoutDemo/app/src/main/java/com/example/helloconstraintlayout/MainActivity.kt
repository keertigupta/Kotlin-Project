package com.example.helloconstraintlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


class MainActivity : AppCompatActivity() {

    private lateinit var address: Address
  //  private lateinit val name:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.test_constraint)

        println("Address is $address")
    }
}
