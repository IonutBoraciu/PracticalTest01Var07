package com.example.practicaltest01var07

import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    var sum = 8
    private lateinit var startedServiceBroadcastReceiver: StartedServiceBroadcastReceiver
    private lateinit var startedServiceIntentFilter: IntentFilter


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

        var left_1 = findViewById<EditText>(R.id.left_1)
        var left_2 = findViewById<EditText>(R.id.left_2)
        var right_1 = findViewById<EditText>(R.id.right_1)
        var right_2 = findViewById<EditText>(R.id.right_2)

        var navigate = findViewById<View>(R.id.second_act).setOnClickListener {
            val intent = Intent(this@MainActivity, PracticalTest01Var07SecondaryActivity::class.java)
            intent.putExtra("left_1", left_1.text.toString())
            intent.putExtra("left_2", left_2.text.toString())
            intent.putExtra("right_1", right_1.text.toString())
            intent.putExtra("right_2", right_2.text.toString())
            startActivity(intent)

//            sum = intent.getIntExtra("sum", 0)
//            Log.d("[SUM]", "$sum")
        }
        val intent = Intent().apply {
            component = ComponentName(
                "com.example.practicaltest01var07",
                "com.example.practicaltest01var07.PracticalTest01Var07Service"
            )
        }

        // De la Android Oreo (Android 8) în sus se folosește startForegroundService
        startForegroundService(intent)
        startedServiceBroadcastReceiver = StartedServiceBroadcastReceiver()
        // TODO: exercise 8b - create an instance of an IntentFilter
        // with all available actions contained within the broadcast intents sent by the service
        startedServiceIntentFilter = IntentFilter().apply {
            addAction("[C is much better]")
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

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("sum", sum)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        sum = savedInstanceState.getInt("sum")
        Log.d("[SUM IS]", "$sum")
    }

    override fun onResume() {
        super.onResume()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            registerReceiver(startedServiceBroadcastReceiver, startedServiceIntentFilter, Context.RECEIVER_EXPORTED)
        } else {
            @Suppress("DEPRECATION")
            registerReceiver(startedServiceBroadcastReceiver, startedServiceIntentFilter)
        }
    }

    override fun onPause() {
        if (::startedServiceBroadcastReceiver.isInitialized) {
            unregisterReceiver(startedServiceBroadcastReceiver)
        }

        super.onPause()
    }
    class StartedServiceBroadcastReceiver() : BroadcastReceiver()  {

        override fun onReceive(context: Context?, intent: Intent?) {
            val action = intent?.action
            val left_1 = intent?.getIntExtra("left_1", 0)
            val right_1 = intent?.getIntExtra("right_1", 0)
            val left_2 = intent?.getIntExtra("left_2", 0)
            val right_2 = intent?.getIntExtra("right_2", 0)


            Log.d("[RECEIVED]",  "$action WITH: left_1: $left_1, right_1: $right_1, left_2: $left_2, right_2: $right_2")
            val startedServiceActivityIntent = Intent(context, MainActivity::class.java)
            startedServiceActivityIntent.putExtra("left_1", left_1)
            startedServiceActivityIntent.putExtra("right_1", right_1)
            startedServiceActivityIntent.putExtra("left_2", left_2)
            startedServiceActivityIntent.putExtra("right_2", right_2)
            startedServiceActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_SINGLE_TOP)
            context?.startActivity(startedServiceActivityIntent)
        }

    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        val left_1 = intent.getIntExtra("left_1", 0)
        val left_2 = intent.getIntExtra("left_2", 0)
        val right_1 = intent.getIntExtra("right_1", 0)
        val right_2 = intent.getIntExtra("right_2", 0)

        var left_1_text = findViewById<EditText>(R.id.left_1)
        var left_2_text = findViewById<EditText>(R.id.left_2)
        var right_1_text = findViewById<EditText>(R.id.right_1)
        var right_2_text = findViewById<EditText>(R.id.right_2)

        left_1_text.setText(left_1.toString())
        left_2_text.setText(left_2.toString())
        right_1_text.setText(right_1.toString())
        right_2_text.setText(right_2.toString())

        Log.d("[TESTING INTENT]", "$left_1")
    }


}