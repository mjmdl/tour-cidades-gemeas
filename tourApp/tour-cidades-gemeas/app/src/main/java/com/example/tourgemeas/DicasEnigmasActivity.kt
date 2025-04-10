package com.example.tourgemeas

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.tourgemeas.databinding.ActivityDicasEnigmasBinding
import com.google.android.material.tabs.TabLayout

class DicasEnigmasActivity : AppCompatActivity() {

    private lateinit var telaEnigmaDetalhadoAct: ActivityDicasEnigmasBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        telaEnigmaDetalhadoAct = ActivityDicasEnigmasBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(telaEnigmaDetalhadoAct.root)

        telaEnigmaDetalhadoAct.inicarAventura.setOnClickListener {
            val intent = Intent(this, EnigmaDetalhadoActivity::class.java)
            startActivity(intent)
        }

        telaEnigmaDetalhadoAct.outraDica.setOnClickListener {

        }

        val tabLayout = telaEnigmaDetalhadoAct.tabLayout

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
