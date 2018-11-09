package com.example.minerva.weirdcalculator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_time.*

val TimeNames = arrayOf(
    "Minute",
    "Hour",
    "Microwaveable Mac & Cheese",
    "Average Wait at DMV without Appointment",
    "CPSC 411 Class",
    "Flight to Hawaii",
    "Les Miserables the Movie",
    "Average Time for Cement to Set",
    "Average Time to Fly to the Moon",
    "Average Time for Plastic to Decompose",
    "Lifespan of a Bumblebee",
    "Cook a 30 Pound Turkey",
    "Life Expectancy for an American"
)
val TimeMeter = arrayOf(
    1.0,
    60.0,
    3.5,
    96.0,
    75.0,
    355.0,
    60.6666667,
    48.0,
    72.0,
    3942000.0,
    672.0,
    7.5,
    617580.0
)

class TimeActivity : AppCompatActivity() {

    lateinit var timeLeft: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time)
        timeLeft = editText1
        textViewL.text = TimeNames[0]
        textViewR.text = TimeNames[0]

        // Set up ListView with String Adapter
        val adapter1 = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, TimeNames)
        listViewWeight1.adapter = adapter1
        val adapter2 = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, TimeNames)
        listViewWeight2.adapter = adapter2
        // On ListView Click, update textview and convert appropriate edittext
        listViewWeight1.setOnItemClickListener { _, _, position, _ ->
            textViewL.text = TimeNames[position]
            val editL = timeLeft.text.toString()
            convertRightEditText(textViewL.text.toString(), textViewR.text.toString(), editL)
        }
        listViewWeight2.setOnItemClickListener { _, _, position, _ ->
            textViewR.text = TimeNames[position]
            val editL = timeLeft.text.toString()
            convertRightEditText(textViewL.text.toString(), textViewR.text.toString(), editL)
        }

        timeLeft.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(c: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(c: CharSequence, i: Int, i1: Int, i2: Int) {
                if (c.isNotEmpty()) {
                    val left = timeLeft.text.toString()
                    if(notEmpty(left)){
                        convertRightEditText(textViewL.text.toString(), textViewR.text.toString(), left)
                    }
                }
            }
            override fun afterTextChanged(editable: Editable) {}
        })
    }

    fun convertRightEditText(textL: String, textR: String, editL: String){
        val leftMult = editL.toDouble() * TimeMeter[TimeNames.indexOf(textL)]
        val rightMult = TimeMeter[TimeNames.indexOf(textR)]
        val result = leftMult / rightMult
        textviewVolR.text = result.toString()
    }
    fun notEmpty(T: String): Boolean {
        return T != ""
    }
}
