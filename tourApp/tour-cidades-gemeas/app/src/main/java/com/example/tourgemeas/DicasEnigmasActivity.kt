package com.example.tourgemeas

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.tabs.TabLayout
import com.example.tourgemeas.databinding.ActivityDicasEnigmasBinding

class DicasEnigmasActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDicasEnigmasBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        binding = ActivityDicasEnigmasBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.inicarAventura.setOnClickListener {
            val intent = Intent(this, EnigmaDetalhadoActivity::class.java)
            startActivity(intent)
        }

        binding.outraDica.setOnClickListener {
            // Implementar lógica para mostrar outra dica
        }

        binding.menuHamburguer.setOnClickListener {
            val intent = Intent(this, PerfilActivity::class.java)
            startActivity(intent)
        }

        val tabLayout = binding.tabLayout
        tabLayout.addTab(tabLayout.newTab().setText("Mapa"))
        tabLayout.addTab(tabLayout.newTab().setText("Enigma"))

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                when (tab.position) {
                    0 -> {
                        val intent = Intent(this@DicasEnigmasActivity, TelaPrincipalActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                    1 -> {
                        val intent = Intent(this@DicasEnigmasActivity, EnigmaActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }
}
