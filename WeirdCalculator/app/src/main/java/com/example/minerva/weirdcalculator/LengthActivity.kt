package com.example.minerva.weirdcalculator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_length.*

val LengthNames = arrayOf(
    "Inch",
    "Yard",
    "Centimeter",
    "Meter",
    "Great Wall of China",
    "Cockroach",
    "Mile",
    "Shaquille O'Neal",
    "Small Intestine",
    "Giraffe's Neck",
    "Marathon",
    "Human Tongue",
    "Oregon Trail"
)
val LengthMeter = arrayOf(
    .0254,
    .9144018,
    .01,
    1.0,
    6400012.8,
    .03,
    1609.347,
    2.159,
    5.9436118,
    1.778,
    42164.897,
    .1016,
    3218694.437
)

class LengthActivity : AppCompatActivity() {

    lateinit var lengthLeft: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_length)
        lengthLeft = editText1

        // Set up ListView with String Adapter
        val adapter1 = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, LengthNames)
        listViewWeight1.adapter = adapter1
        val adapter2 = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, LengthNames)
        listViewWeight2.adapter = adapter2
        // On ListView Click, update textview and convert appropriate edittext
        listViewWeight1.setOnItemClickListener { _, _, position, _ ->
            textViewL.text = LengthNames[position]
            val editL = lengthLeft.text.toString()
            convertRightEditText(textViewL.text.toString(), textViewR.text.toString(), editL)
        }
        listViewWeight2.setOnItemClickListener { _, _, position, _ ->
            textViewR.text = LengthNames[position]
            val editL = lengthLeft.text.toString()
            convertRightEditText(textViewL.text.toString(), textViewR.text.toString(), editL)
        }

        lengthLeft.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(c: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(c: CharSequence, i: Int, i1: Int, i2: Int) {
                if (c.isNotEmpty()) {
                    val left = lengthLeft.text.toString()
                    if(notEmpty(left)){
                        convertRightEditText(textViewL.text.toString(), textViewR.text.toString(), left)
                    }
                }
            }
            override fun afterTextChanged(editable: Editable) {}
        })
    }

    fun convertRightEditText(textL: String, textR: String, editL: String){
        val leftMult = editL.toDouble() * LengthMeter[LengthNames.indexOf(textL)]
        val rightMult = LengthMeter[LengthNames.indexOf(textR)]
        val result = leftMult / rightMult
        textViewWeightR.text = result.toString()
    }
    fun notEmpty(T: String): Boolean {
        return T != ""
    }
}
