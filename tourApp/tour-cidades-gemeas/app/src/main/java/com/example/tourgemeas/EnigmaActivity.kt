package com.example.tourgemeas

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.tabs.TabLayout

class EnigmaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_enigma)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)


        tabLayout.addTab(tabLayout.newTab().setText("Perfil"))
        tabLayout.addTab(tabLayout.newTab().setText("Mapa"))
        tabLayout.addTab(tabLayout.newTab().setText("Enigma"))
        tabLayout.addTab(tabLayout.newTab().setText("Classificação"))


        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                // abas navegacao
                when (tab.position) {//*inicia navegacao */ }
                    0 -> { }

                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }
}