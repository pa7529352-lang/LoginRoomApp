package com.example.loginroomapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var etUsuario: EditText
    private lateinit var etContrasena: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etUsuario = findViewById(R.id.etUsuario)
        etContrasena = findViewById(R.id.etContrasena)
        val btnIngresar: Button = findViewById(R.id.btnIngresar)
        val tvRegistrarse: TextView = findViewById(R.id.tvRegistrarse)

        btnIngresar.setOnClickListener {
            validarLogin()
        }

        tvRegistrarse.setOnClickListener {
            val intent = Intent(this, RegistroActivity::class.java)
            startActivity(intent)
        }
    }

    private fun validarLogin() {
        val usuario = etUsuario.text.toString().trim()
        val contrasena = etContrasena.text.toString().trim()

        if (usuario.isEmpty() || contrasena.isEmpty()) {
            Toast.makeText(this, "Completá usuario y contraseña", Toast.LENGTH_SHORT).show()
            return
        }

        lifecycleScope.launch {
            val db = AppDatabase.getDatabase(applicationContext)
            val usuarioEncontrado = db.usuarioDao().login(usuario, contrasena)

            if (usuarioEncontrado != null) {
                val intent = Intent(this@MainActivity, DashboardActivity::class.java)
                intent.putExtra("NOMBRE_USUARIO", usuarioEncontrado.nombre)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this@MainActivity, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}