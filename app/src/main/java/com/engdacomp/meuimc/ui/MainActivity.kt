package com.engdacomp.meuimc.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.Toast
import androidx.room.Room
import com.engdacomp.meuimc.databinding.ActivityMainBinding
import com.engdacomp.meuimc.db.MyDataBase
import com.engdacomp.meuimc.db.model.Imc
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var db: MyDataBase
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        db = Room.databaseBuilder(applicationContext, MyDataBase::class.java, "meusIMCs").build()

        binding.calcButton.setOnClickListener{
            if (binding.pesoInput.text.toString() == ""){
                Toast.makeText(this, "Insira um peso válido.", Toast.LENGTH_SHORT).show();
            } else if (binding.alturaInput.text.toString() == ""){
                Toast.makeText(this, "Insira uma altura válida.", Toast.LENGTH_SHORT).show();
            }else {
                var indice = calcularImc(binding.pesoInput.text.toString(), binding.alturaInput.text.toString())
                var grauObs: String

                grauObs = when {
                    indice < 16f -> {
                        "Magreza grave."
                    }
                    indice in 16f..17f -> {
                        "Magreza moderada."
                    }
                    indice in 17f..18.5f -> {
                        "Magreza leve."
                    }
                    indice in 18.5f..25f -> {
                        "Saudável"
                    }
                    indice in 25f..30f -> {
                        "Sobrepeso."
                    }
                    indice in 30f..35f -> {
                        "Obsidade grau I."
                    }
                    indice in 35f..40f -> {
                        "Obsidade grau II."
                    }
                    else -> {
                        "Obsidade grau III."
                    }
                }

                binding.resultadoText.text = ("Seu IMC: " + indice.roundDecimal(2) + "kg/m². \n"
                        + "Seu grau de obsidade: " + grauObs)

                var indiceString = indice.roundDecimal(2) + "kg/m²"

                val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
                var currentDate: String = sdf.format(Date())

                val imc = Imc(0,
                    indiceString,
                    currentDate,
                    grauObs)

                Thread(Runnable {
                    db.imcDAO().insertAll(imc)
                }).start()

                Toast.makeText(this, "IMC salvo.", Toast.LENGTH_LONG).show()
            }
        }

        binding.listButton.setOnClickListener{
            var intent = Intent(applicationContext, ListaActivity::class.java)
            startActivity(intent)
        }
    }

    fun Float.roundDecimal(digit: Int) = "%.${digit}f".format(this)

    private fun calcularImc(peso: String, altura: String): Float {
        var _peso = peso.toFloat()
        var _altura = altura.toFloat()
        var _imc: Float

        _imc = _peso / (_altura * _altura)

        return _imc
    }
}