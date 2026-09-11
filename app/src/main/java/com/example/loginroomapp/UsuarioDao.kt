package com.example.loginroomapp

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UsuarioDao {

    @Insert
    suspend fun insertarUsuario(usuario: Usuario)

    @Query("SELECT * FROM usuarios WHERE usuario = :user AND contrasena = :pass LIMIT 1")
    suspend fun login(user: String, pass: String): Usuario?

    @Query("SELECT * FROM usuarios WHERE usuario = :user LIMIT 1")
    suspend fun buscarPorUsuario(user: String): Usuario?
}