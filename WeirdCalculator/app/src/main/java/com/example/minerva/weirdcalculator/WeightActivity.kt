package com.example.minerva.weirdcalculator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_weight.*

data class Weight(val name: String, val pound: Double)

val weightNames = arrayOf(
    "Pound",
    "Kilogram",
    "Male Polar Bear",
    "Blue Whale",
    "Spider Monkey",
    "American Female",
    "Human Eyeball",
    "Newborn Baby",
    "African Elephant",
    "Gallon of Water",
    "Keg of Beer",
    "Tom Cruise",
    "Hockey Puck",
    "Sheet of Paper",
    "Chicken",
    "Average Dump",
    "Airbus A380"
)
val weightPounds = arrayOf(
    1.0,
    2.2046,
    990.0,
    350000.0,
    14.1095,
    162.9,
    .0625,
    7.5,
    12125.4244,
    8.33,
    170.0,
    200.0,
    .375,
    .0099,
    4.4092,
    .4409,
    610239.542
)

class WeightActivity : AppCompatActivity() {

    lateinit var weightLeft: EditText
    lateinit var weightRight: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weight)
        weightLeft = editText1
        weightRight = editText2

        // Set up ListView with String Adapter
        val adapter1 = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, weightNames)
        listViewWeight1.adapter = adapter1
        val adapter2 = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, weightNames)
        listViewWeight2.adapter = adapter2
        // On ListView Click, update textview and convert appropriate edittext
        listViewWeight1.setOnItemClickListener { _, _, position, _ ->
            textViewL.text = weightNames[position]
            convertRightEditText(textViewL, textViewR, weightLeft, weightRight)
        }
        listViewWeight2.setOnItemClickListener { _, _, position, _ ->
            textViewR.text = weightNames[position]
            convertLeftEditText(textViewL, textViewR, weightLeft, weightRight)
        }

        weightLeft.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(c: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(c: CharSequence, i: Int, i1: Int, i2: Int) {
                if (c.isNotEmpty()) {
                    val left = weightLeft.text.toString()
                    val right = weightRight.text.toString()
                    convertRightEditText(textViewL, textViewR, weightLeft, weightRight)
                }
            }
            override fun afterTextChanged(editable: Editable) {}
        })
        weightRight.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(c: Editable?) {}
            override fun beforeTextChanged(c: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(c: CharSequence, start: Int, before: Int, count: Int) {
                if (c.isNotEmpty()){
                    val left = weightLeft.text.toString()
                    val right = weightRight.text.toString()
                    convert(left,right)
                }
            }
        })
    }
    fun convert(left: String, right: String){
        val l = left.toDouble()
        val r = right.toDouble()
        val t1 = textViewL.toString()
        val t2 = textViewR.toString()

    }
    fun convertRightEditText(tvL: TextView, tvR: TextView, etL: EditText, etR: EditText){
        val leftMult = etL.toString().toDouble() * weightPounds[weightNames.indexOf(tvL.toString())]
        val rightMult = etR.toString().toDouble() * weightPounds[weightNames.indexOf(tvR.toString())]
        val result = leftMult / rightMult
        weightRight.setText(result.toString())
    }
    fun convertLeftEditText(tvL: TextView, tvR: TextView, etL: EditText, etR: EditText){
        val leftMult = etL.toString().toDouble() * weightPounds[weightNames.indexOf(tvL.toString())]
        val rightMult = etR.toString().toDouble() * weightPounds[weightNames.indexOf(tvR.toString())]
        val result = leftMult / rightMult
        weightRight.setText(result.toString())
    }
}
