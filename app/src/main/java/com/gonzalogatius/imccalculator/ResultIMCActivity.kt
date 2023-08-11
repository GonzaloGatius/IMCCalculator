package com.gonzalogatius.imccalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.OnBackPressedDispatcher
import androidx.core.content.ContextCompat
import com.gonzalogatius.imccalculator.MainActivity.Companion.IMC_KEY

class ResultIMCActivity : AppCompatActivity() {

    private lateinit var tvResult:TextView
    private lateinit var tvIMC:TextView
    private lateinit var tvDescription:TextView
    private lateinit var btnReCalculate:Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_imcactivity)
        val result = intent.extras?.getDouble(IMC_KEY) ?: -1.0
        initComponents()
        initUI(result)
        initListeners()
    }

    private fun initListeners() {
        btnReCalculate.setOnClickListener { onBackPressed() }
        //ver que onda, aristi usa onBackPressed() pero está deprecado (aunque todavia funciona) por esta funcion OnBackPressedDispatcher.
    }

    private fun initUI(result: Double) {
        tvIMC.text = result.toString()
    when(result){
        in 0.00..18.50 ->{ //Bajo peso
            tvResult.text = getString(R.string.title_bajo_peso)
            tvResult.setTextColor(ContextCompat.getColor(this, R.color.peso_bajo))
            tvDescription.text = getString(R.string.description_bajo_peso)
        }
        in 18.51..24.99 ->{ //Normal
            tvResult.text = getString(R.string.title_peso_normal)
            tvResult.setTextColor(ContextCompat.getColor(this, R.color.peso_normal))
            tvDescription.text = getString(R.string.description_normal)
        }
        in 25.00..29.99 ->{ //Sobrepeso
            tvResult.text = getString(R.string.title_sobrepeso)
            tvResult.setTextColor(ContextCompat.getColor(this, R.color.sobrepeso))
            tvDescription.text = getString(R.string.description_sobrepeso)
        }
        in 30.00..99.00 ->{ //Obesidad
            tvResult.text = getString(R.string.title_obesidad)
            tvResult.setTextColor(ContextCompat.getColor(this, R.color.obesidad))
            tvDescription.text = getString(R.string.description_obesidad)
        }
        else -> {//error
            tvIMC.text = getString(R.string.error)  //esta es la manera de llamar a un string personalizado desde el .kt, distinto es en xml
            tvResult.text = getString(R.string.error)
            tvDescription.text = getString(R.string.error)
        }
    }
    }

    private fun initComponents() {
        tvResult = findViewById(R.id.tvResult)
        tvIMC = findViewById(R.id.tvIMC)
        tvDescription = findViewById(R.id.tvDescription)
        btnReCalculate = findViewById(R.id.btnReCalculate)
    }
}