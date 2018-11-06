package com.example.minerva.weirdcalculator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_weight.*

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weight)
        weightLeft = editText1
        textViewL.text = weightNames[0]
        textViewR.text = weightNames[0]

        // Set up ListView with String Adapter
        val adapter1 = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, weightNames)
        listViewWeight1.adapter = adapter1
        val adapter2 = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, weightNames)
        listViewWeight2.adapter = adapter2
        // On ListView Click, update textview and convert appropriate edittext
        listViewWeight1.setOnItemClickListener { _, _, position, _ ->
            textViewL.text = weightNames[position]
            val editL = weightLeft.text.toString()
            convertRightEditText(textViewL.text.toString(), textViewR.text.toString(), editL)
        }
        listViewWeight2.setOnItemClickListener { _, _, position, _ ->
            textViewR.text = weightNames[position]
            val editL = weightLeft.text.toString()
            convertRightEditText(textViewL.text.toString(), textViewR.text.toString(), editL)
        }

        weightLeft.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(c: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(c: CharSequence, i: Int, i1: Int, i2: Int) {
                if (c.isNotEmpty()) {
                    val left = weightLeft.text.toString()
                    if(notEmpty(left)){
                        convertRightEditText(textViewL.text.toString(), textViewR.text.toString(), left)
                    }
                }
            }
            override fun afterTextChanged(editable: Editable) {}
        })
    }

    fun convertRightEditText(textL: String, textR: String, editL: String){
        val leftMult = editL.toDouble() * weightPounds[weightNames.indexOf(textL)]
        val rightMult = weightPounds[weightNames.indexOf(textR)]
        val result = leftMult / rightMult
        textviewVolR.text = result.toString()
    }
    fun notEmpty(T: String): Boolean {
        return T != ""
    }
}
