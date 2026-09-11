package com.example.loginroomapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val tvBienvenida: TextView = findViewById(R.id.tvBienvenida)
        val nombreUsuario = intent.getStringExtra("NOMBRE_USUARIO") ?: "Usuario"

        tvBienvenida.text = "Bienvenido, $nombreUsuario"
    }
}