package com.example.credrdummy.activity.franchise

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.credrdummy.R
import com.example.credrdummy.activity.FranchiseVehicleStatusActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_start.setOnClickListener{
            val intent = Intent(this,
                FranchiseVehicleStatusActivity::class.java)
            startActivity(intent)
        }
    }
}
