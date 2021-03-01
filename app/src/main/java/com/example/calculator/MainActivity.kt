package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {
    // Contains info on last button pressed
    var lastNumeric = false
    var lastDot = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onDigit(view: View) {
        this.lastNumeric = true
        val tvInput: TextView = findViewById<TextView>(R.id.tvInput)
        tvInput.append((view as Button).text)
    }

    fun onClear(view: View) {
        val tvInput: TextView = findViewById<TextView>(R.id.tvInput)
        tvInput.text = ""
        this.lastNumeric = false
        this.lastDot = false
    }

    fun onDecimalPoint(view: View) {
        val tvInput: TextView = findViewById<TextView>(R.id.tvInput)
        // If last input was a number and not a dot
        if (this.lastNumeric && !this.lastDot) {
            tvInput.append(".")
            this.lastNumeric = false
            this.lastDot = true
        }
    }

    fun onOperator(view: View) {
        val tvInput: TextView = findViewById<TextView>(R.id.tvInput)
        if (this.lastNumeric && !isOperatorAdded(tvInput.text.toString())) {
            tvInput.append((view as Button).text)
            this.lastNumeric = false
            this.lastDot = false
        }
    }

    fun onEqual(view: View) {
        val tvInput: TextView = findViewById<TextView>(R.id.tvInput)

        if (this.lastNumeric) {

            var tvValue = tvInput.text.toString()
            var prefix = ""
            try {
                // Handle negative case
                if (tvValue.startsWith("-")) {
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }
                // Subtraction
               if (tvValue.contains("-")) {
                   val splitValue = tvValue.split("-")
                   var one = splitValue[0]
                   val two = splitValue[1]

                   if (!prefix.isEmpty()) {
                       one = prefix + one
                   }
                   tvInput.text = (one.toDouble() - two.toDouble()).toString()
               }
               // Addition
               else if (tvValue.contains("+")) {
                   val splitValue = tvValue.split("+")
                   var one = splitValue[0]
                   val two = splitValue[1]

                   if (!prefix.isEmpty()) {
                       one = prefix + one
                   }

                   tvInput.text = (one.toDouble() + two.toDouble()).toString()
               }
               // Multiplication
               else if (tvValue.contains("*")) {
                   val splitValue = tvValue.split("*")
                   var one = splitValue[0]
                   val two = splitValue[1]

                   if (!prefix.isEmpty()) {
                       one = prefix + one
                   }

                   tvInput.text = (one.toDouble() * two.toDouble()).toString()
               }
                // Division
                else if (tvValue.contains("/")) {
                   val splitValue = tvValue.split("/")
                   var one = splitValue[0]
                   val two = splitValue[1]

                   if (!prefix.isEmpty()) {
                       one = prefix + one
                   }

                   // If trying to divide by zero
                   if (two.toDouble() == 0.0) {
                       val toast = Toast.makeText(this, "Cant Divide By Zero!!!", Toast.LENGTH_LONG)
                       toast.show()
                   } else {
                       tvInput.text = (one.toDouble() / two.toDouble()).toString()
                   }

               }
            } catch (e: ArithmeticException) {
                e.printStackTrace()
            }
        }
    }

    private fun isOperatorAdded(value: String): Boolean {
        return if (value.startsWith("-")) {
            false
        } else {
            value.contains("/") ||
                    value.contains("*") ||
                    value.contains("+") ||
                    value.contains("-")

        }
    }
}