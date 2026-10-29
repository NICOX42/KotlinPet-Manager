package com.example.kotlinpetmanager.navigation

sealed class ItemsMenu(
    val ruta: String,
    val titulo: String
) {
    object pacientes : ItemsMenu(
        ruta = "pacientes",
        titulo = "Pacientes"
    )
}
