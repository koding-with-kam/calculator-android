package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    // Contains info on last button pressed
    var lastNumeric = false
    var lastDot = false

    private val tvInput: TextView = findViewById<TextView>(R.id.tvInput)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onDigit(view: View) {
        this.lastNumeric = true
        this.tvInput.append((view as Button).text)
    }

    fun onClear(view: View) {
        this.tvInput.text = ""
        this.lastNumeric = false
        this.lastDot = false
    }

    fun onDecimalPoint(view: View) {
        // If last input was a number and not a dot
        if (this.lastNumeric && !this.lastDot) {
            this.tvInput.append(".")
            this.lastNumeric = false
            this.lastDot = true
        }
    }

    fun onOperator(view: View) {
        if (this.lastNumeric && !isOperatorAdded(this.tvInput.text.toString())) {
            this.tvInput.append((view as Button).text)
            this.lastNumeric = false
            this.lastDot = false
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