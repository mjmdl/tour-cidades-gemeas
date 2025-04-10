package com.example.tourgemeas

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.tabs.TabLayout

class EnigmaActivity : AppCompatActivity() {
    private lateinit var tabLayout: TabLayout
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_enigma)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        findViewById<android.widget.LinearLayout>(R.id.enigma1).setOnClickListener {
            val intent = Intent(this, DicasEnigmasActivity::class.java)
            startActivity(intent)
        }

        tabLayout = findViewById(R.id.tabLayout)
        tabLayout.addTab(tabLayout.newTab().setText("Mapa"))
        tabLayout.addTab(tabLayout.newTab().setText("Enigma"))
        
        tabLayout.selectTab(tabLayout.getTabAt(1))

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                when (tab.position) {
                    0 -> {
                        val intent = Intent(this@EnigmaActivity, TelaPrincipalActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                    1 -> {

                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }
    
    override fun onResume() {
        super.onResume()
        
        if (::tabLayout.isInitialized) {
            tabLayout.selectTab(tabLayout.getTabAt(1))
        }
    }
}