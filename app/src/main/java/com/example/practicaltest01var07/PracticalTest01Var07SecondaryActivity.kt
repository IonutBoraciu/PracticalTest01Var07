package com.example.practicaltest01var07

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class PracticalTest01Var07SecondaryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_practical_test01_var07_secondary)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val left_1 = findViewById<TextView>(R.id.left_1_2)
        val right_1 = findViewById<TextView>(R.id.right_1_2)
        val left_2 = findViewById<TextView>(R.id.left_2_2)
        val right_2 = findViewById<TextView>(R.id.right_2_2)

        val recv_left_1 = intent.getStringExtra("left_1")
        val recv_right_1 = intent.getStringExtra("right_1")
        val recv_left_2 = intent.getStringExtra("left_2")
        val recv_right_2 = intent.getStringExtra("right_2")

        if(recv_left_1 != "")
            left_1.hint = intent.getStringExtra("left_1")
        if(recv_right_1 != "")
            right_1.hint = intent.getStringExtra("right_1")
        if(recv_right_2 != "")
            right_2.hint = intent.getStringExtra("right_2")
        if(recv_left_2 != "")
            left_2.hint = intent.getStringExtra("left_2")

        var sum_act = findViewById<View>(R.id.sum).setOnClickListener { v ->
            var sum = left_1.hint.toString().toInt() + right_1.hint.toString().toInt()
            sum += left_2.hint.toString().toInt() + right_2.hint.toString().toInt()
            Toast.makeText(v.context, "SUM WAS PRESSED $sum", Toast.LENGTH_SHORT).show()
            intent.putExtra("sum", sum)
            val i = Intent()
            i.putExtra("sum", sum)
            setResult(0, i)
            finish()
        }
        var prod_act = findViewById<View>(R.id.product).setOnClickListener { v ->
            var prod = left_1.hint.toString().toInt() * right_1.hint.toString().toInt()
            prod *= left_2.hint.toString().toInt() * right_2.hint.toString().toInt()
            Toast.makeText(v.context, "PROD WAS PRESSED $prod", Toast.LENGTH_SHORT).show()
            finish()
        }

    }
}