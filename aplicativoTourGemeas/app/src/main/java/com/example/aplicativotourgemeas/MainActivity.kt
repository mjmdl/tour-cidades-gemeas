package com.example.aplicativotourgemeas

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.aplicativotourgemeas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mainAct: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainAct = ActivityMainBinding.inflate(layoutInflater)

        enableEdgeToEdge()
        setContentView(mainAct.root)

    }
}