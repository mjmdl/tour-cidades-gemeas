package com.example.tourgemeas

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

import com.example.tourgemeas.databinding.ActivityCuidadoBinding

class CuidadoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCuidadoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCuidadoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.BtnCompreendo.setOnClickListener {
            val intent = android.content.Intent(this, TelaPrincipalActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}