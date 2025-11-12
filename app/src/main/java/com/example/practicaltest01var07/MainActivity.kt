package com.example.practicaltest01var07

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val toggleButton = ButtonClickListener()

        val buttons = listOf(
            R.id.random
        )

        for (id in buttons) {
            findViewById<View>(id).setOnClickListener(toggleButton)
        }
    }

    inner class ButtonClickListener : android.view.View.OnClickListener {
        override fun onClick(v: View?) {
            if (v == null) return

            if(v.id == R.id.random) {
                var left_1 = findViewById<EditText>(R.id.left_1)
                var left_2 = findViewById<EditText>(R.id.left_2)
                var right_1 = findViewById<EditText>(R.id.right_1)
                var right_2 = findViewById<EditText>(R.id.right_2)
                var random_num = (0..10).random()

                if(left_1.text.toString() == "") {
                    left_1.setText(random_num.toString())
                } else if(right_1.text.toString() == "") {
                    right_1.setText(random_num.toString())
                } else if(left_2.text.toString() == "") {
                    left_2.setText(random_num.toString())
                } else if(right_2.text.toString() == "") {
                    right_2.setText(random_num.toString())
                }
            }
        }
    }
}