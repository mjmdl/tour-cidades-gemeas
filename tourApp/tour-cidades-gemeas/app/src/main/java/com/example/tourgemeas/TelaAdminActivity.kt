package com.example.tourgemeas

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tourgemeas.databinding.ActivityTelaAdminBinding

class TelaAdminActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTelaAdminBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTelaAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}