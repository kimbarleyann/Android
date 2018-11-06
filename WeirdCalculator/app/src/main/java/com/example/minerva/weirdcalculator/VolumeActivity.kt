package com.example.minerva.weirdcalculator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_volume.*

val Volumes = arrayOf (
    "Liter",
    "Cubic cm",
    "Cubic Meter",
    "Grand Canyon",
    "Keg of Beer",
    "Daily Human Urine Production",
    "Bathtub",
    "Can of Beer",
    "Olympic Pool",
    "Human Stomach",
    "Dump Truck"
)
val VolumeSizes = arrayOf(
    1.0,
    .001,
    1000.0,
    10420454558150000.0,
    58.674,
    1.5,
    136.275,
    .3549,
    2499329.83,
    4.0,
    12232.878
)
class VolumeActivity : AppCompatActivity() {

    lateinit var volumeLeft: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_volume)

        volumeLeft = editText1
        textViewL.text = Volumes[0]
        textViewR.text = Volumes[0]

        // Set up ListView with String Adapter
        val adapter1 = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, Volumes)
        listViewVol1.adapter = adapter1
        val adapter2 = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, Volumes)
        listViewVol2.adapter = adapter2
        // On ListView Click, update textview and convert appropriate edittext
        listViewVol1.setOnItemClickListener { _, _, position, _ ->
            textViewL.text = Volumes[position]
            val editL = volumeLeft.text.toString()
            convertRightEditText(textViewL.text.toString(), textViewR.text.toString(), editL)
        }
        listViewVol2.setOnItemClickListener { _, _, position, _ ->
            textViewR.text = Volumes[position]
            val editL = volumeLeft.text.toString()
            convertRightEditText(textViewL.text.toString(), textViewR.text.toString(), editL)
        }

        volumeLeft.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(c: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(c: CharSequence, i: Int, i1: Int, i2: Int) {
                if (c.isNotEmpty()) {
                    val left = volumeLeft.text.toString()
                    if(notEmpty(left)){
                        convertRightEditText(textViewL.text.toString(), textViewR.text.toString(), left)
                    }
                }
            }
            override fun afterTextChanged(editable: Editable) {}
        })
    }

    fun convertRightEditText(textL: String, textR: String, editL: String){
        val leftMult = editL.toDouble() * VolumeSizes[Volumes.indexOf(textL)]
        val rightMult = VolumeSizes[Volumes.indexOf(textR)]
        val result = leftMult / rightMult
        textviewVolR.text = result.toString()
    }
    fun notEmpty(T: String): Boolean {
        return T != ""
    }
}
