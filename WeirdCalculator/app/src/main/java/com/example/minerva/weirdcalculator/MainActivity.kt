package com.example.minerva.weirdcalculator

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*

val conversions = arrayOf(
    "Weight",
    "Length",
    "Volume",
    "Time"
)
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, conversions)
        listView.adapter = adapter

        listView.setOnItemClickListener { _, _, position, _ ->
            when (conversions[position]){
                "Weight" -> {
                    val i = Intent(this,WeightActivity::class.java)
                    startActivity(i)
                }
                "Length" -> {
                    val i = Intent(this,LengthActivity::class.java)
                    startActivity(i)
                }
                "Volume" -> {
                    val i = Intent(this,VolumeActivity::class.java)
                    startActivity(i)
                }
                "Time" -> {
                    val i = Intent(this,TimeActivity::class.java)
                            startActivity(i)
                }
            }
        }
    }
}
