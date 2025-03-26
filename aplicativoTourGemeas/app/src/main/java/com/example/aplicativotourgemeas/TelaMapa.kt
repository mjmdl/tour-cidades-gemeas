package com.example.aplicativotourgemeas

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.aplicativotourgemeas.databinding.ActivityLoginBinding
import com.example.aplicativotourgemeas.databinding.ActivityTelaMapaBinding
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker

class TelaMapa : AppCompatActivity() {
    private lateinit var telaMapaAct: ActivityTelaMapaBinding

    private lateinit var map: MapView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        telaMapaAct = ActivityTelaMapaBinding.inflate(layoutInflater)

        enableEdgeToEdge()
        setContentView(telaMapaAct.root)

        Configuration.getInstance().load(applicationContext, getSharedPreferences("osmdroid", MODE_PRIVATE))

        map = telaMapaAct.mapView;
        map.setTileSource(TileSourceFactory.MAPNIK)
        map.setMultiTouchControls(true)

        val startPoint = GeoPoint(-26.242206917356413, -51.091648716745766)
        map.controller.setZoom(15.0)
        map.controller.setCenter(startPoint)

        val marker = Marker(map)
        marker.position = startPoint
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
        marker.title = "Você está aqui"
        map.overlays.add(marker)

        telaMapaAct.btnPerfil.setOnClickListener() {
            val intent = Intent(this, TelaPerfil::class.java)
            startActivity(intent)
        }

        telaMapaAct.btnMaps.setOnClickListener() {
            val intent = Intent(this, TelaMapa::class.java)
            startActivity(intent)
        }

        telaMapaAct.btnEnigmas.setOnClickListener() {
            val intent = Intent(this, TelaEnigmas::class.java)
            startActivity(intent)
        }
    }
}