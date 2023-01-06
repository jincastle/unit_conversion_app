package com.project.unit_conversion_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import com.project.unit_conversion_app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    var cmTom = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val outputTextView = binding.outputTextView
        val outputUnitTextView = binding.outputUnitTextView
        val inputEditText = binding.inputEditText
        val inputUnitTextView = binding.inputUnitTextView
        val swapImageButton = binding.swapImageButton

        var inputNumber : Int = 0


        // addTextChangedListener 문자열이 변경이 되면 알려주는거
        // text로 받은걸 int로 바꿈
        // isNullOrEmpty : text값이 null일때
        inputEditText.addTextChangedListener{ text ->
//            inputNumber = text.toString().toInt()
            inputNumber = if(text.isNullOrEmpty()){
                0
            }
            else {
                text.toString().toInt()
            }

            if(cmTom){
                outputTextView.text = inputNumber.times(0.01).toString()
            }else{
                outputTextView.text = inputNumber.times(100).toString()
            }
        }
        swapImageButton.setOnClickListener{
            // coTom을 반전 시킴 그래서 false가 됨
            cmTom = cmTom.not()
            if(cmTom){
                inputUnitTextView.text = "cm"
                outputUnitTextView.text = "m"
                outputTextView.text = inputNumber.times(0.01).toString()
            }else{
                inputUnitTextView.text = "m"
                outputUnitTextView.text = "cm"
                outputTextView.text = inputNumber.times(100).toString()
            }
        }
    }
    //  저장하고
    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean("cmTom", cmTom)
        super.onSaveInstanceState(outState)
    }
    // 복원
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        cmTom = savedInstanceState.getBoolean("cmTom")
        binding.inputUnitTextView.text = if(cmTom) "cm" else "m"
        binding.outputUnitTextView.text = if(cmTom) "m" else "cm"
        super.onRestoreInstanceState(savedInstanceState) // 번들값을 가지고 있음
    }
}

