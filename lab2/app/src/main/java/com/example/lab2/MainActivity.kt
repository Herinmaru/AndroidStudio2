package com.example.lab2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import java.lang.Math.sqrt

class MainActivity : AppCompatActivity() {

    private lateinit var editTextA: EditText
    private lateinit var editTextB: EditText
    private lateinit var editTextC: EditText
    private lateinit var button: Button
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextA = findViewById(R.id.editTextA)
        editTextB = findViewById(R.id.editTextB)
        editTextC = findViewById(R.id.editTextC)
        button = findViewById(R.id.button)
        textView = findViewById(R.id.textView)

        button.setOnClickListener {
            val a = editTextA.text.toString().toDoubleOrNull()
            val b = editTextB.text.toString().toDoubleOrNull()
            val c = editTextC.text.toString().toDoubleOrNull()

            if(a != null && b != null && c != null){
                if(a == 0.0){
                    textView.text = "Ошибка ввода коэффициента (Деление на ноль)"
                } else {
                    val result = quadraticEquation(a, b, c)
                    showResultDialog(result)
                }
            } else {
                textView.text = "Ошибка ввода коэффициента (Некорректный ввод)"
            }
        }
    }

    private fun quadraticEquation(a: Double, b: Double, c: Double): List<Double> {
        val discriminant = b * b - 4 * a * c
        val result = mutableListOf<Double>()

        if (discriminant >= 0) {
            val x1 = (-b + sqrt(discriminant)) / (2 * a)
            val x2 = (-b - sqrt(discriminant)) / (2 * a)
            result.add(x1)
            result.add(x2)
        }

        return result
    }

    private fun showResultDialog(result: List<Double>) {
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle("Результат")
        if (result.isEmpty()) {
            alertDialogBuilder.setMessage("Нет корней")
        } else if (result.size == 1) {
            alertDialogBuilder.setMessage("X1 = ${result[0]}")
        } else {
            alertDialogBuilder.setMessage("X1 = ${result[0]}, X2 = ${result[1]}")
        }
        alertDialogBuilder.setPositiveButton("OK", null)
        alertDialogBuilder.show()
    }
}