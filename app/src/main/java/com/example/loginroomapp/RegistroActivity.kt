package com.example.loginroomapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class RegistroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        val etNombre: EditText = findViewById(R.id.etNombreRegistro)
        val etUsuario: EditText = findViewById(R.id.etUsuarioRegistro)
        val etContrasena: EditText = findViewById(R.id.etContrasenaRegistro)
        val btnRegistrar: Button = findViewById(R.id.btnRegistrar)

        btnRegistrar.setOnClickListener {
            val nombre = etNombre.text.toString().trim()
            val usuario = etUsuario.text.toString().trim()
            val contrasena = etContrasena.text.toString().trim()

            if (nombre.isEmpty() || usuario.isEmpty() || contrasena.isEmpty()) {
                Toast.makeText(this, "Completá todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            lifecycleScope.launch {
                val db = AppDatabase.getDatabase(applicationContext)

                val existente = db.usuarioDao().buscarPorUsuario(usuario)
                if (existente != null) {
                    Toast.makeText(this@RegistroActivity, "Ese usuario ya existe", Toast.LENGTH_SHORT).show()
                    return@launch
                }

                val nuevoUsuario = Usuario(usuario = usuario, contrasena = contrasena, nombre = nombre)
                db.usuarioDao().insertarUsuario(nuevoUsuario)

                Toast.makeText(this@RegistroActivity, "Cuenta creada. Ya puedes iniciar sesión", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
}