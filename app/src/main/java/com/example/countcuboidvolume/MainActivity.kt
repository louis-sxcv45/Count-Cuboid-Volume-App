package com.example.countcuboidvolume

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var width : EditText
    private lateinit var length : EditText
    private lateinit var high : EditText
    private lateinit var result : TextView
    private lateinit var count : Button

    companion object{
        private const val STATE_RESULT = "state_result"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        width = findViewById(R.id.width)
        length = findViewById(R.id.length)
        high = findViewById(R.id.high)
        result = findViewById(R.id.result)
        count = findViewById(R.id.count)
        count.setOnClickListener(this)

        if(savedInstanceState != null) {
            val resultVolume = savedInstanceState.getString(STATE_RESULT)
            result.text = resultVolume
        } /// for saving the result if the app layer changed to landscape or portrait
    }

    override fun onClick(view: View?) {
        val widthValue = width.text.toString().trim()
        val lengthValue = length.text.toString()
        val highValue = high.text.toString()

            var emptyField = false
            if(widthValue.isEmpty()){
                emptyField = true
                width.error = "This form cannot be empty"
            }
            if(lengthValue.isEmpty()){
                emptyField = true
                length.error = "This form cannot be empty"
            }
            if(highValue.isEmpty()){
                emptyField = true
                high.error = "This form cannot be empty"
            }

            if(!emptyField){
                val cuboidVolume = widthValue.toDouble() * lengthValue.toDouble() * highValue.toDouble()
                result.text = cuboidVolume.toString()
            }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(STATE_RESULT,result.text.toString())
    } /// for saving the result if the app layer changed to landscape or portrait
}