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
import com.example.countcuboidvolume.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    companion object{
        private const val STATE_RESULT = "state_result"
    }

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.count.setOnClickListener(this)

        if(savedInstanceState != null) {
            val resultVolume = savedInstanceState.getString(STATE_RESULT)
            binding.result.text = resultVolume
        } /// for saving the result if the app layer changed to landscape or portrait
    }

    override fun onClick(view: View?) {
        val widthValue = binding.width.text.toString().trim()
        val lengthValue = binding.length.text.toString()
        val highValue = binding.high.text.toString()

            var emptyField = false
            if(widthValue.isEmpty()){
                emptyField = true
                binding.width.error = "This form cannot be empty"
            }
            if(lengthValue.isEmpty()){
                emptyField = true
                binding.length.error = "This form cannot be empty"
            }
            if(highValue.isEmpty()){
                emptyField = true
                binding.high.error = "This form cannot be empty"
            }

            if(!emptyField){
                val cuboidVolume = widthValue.toDouble() * lengthValue.toDouble() * highValue.toDouble()
                binding.result.text = cuboidVolume.toString()
            }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(STATE_RESULT,binding.result.text.toString())
    } /// for saving the result if the app layer changed to landscape or portrait
}