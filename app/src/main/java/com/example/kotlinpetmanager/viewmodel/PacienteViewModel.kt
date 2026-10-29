package com.example.kotlinpetmanager.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.kotlinpetmanager.model.Paciente

data class FormularioPaciente(
    val nombre: String = "",
    val especie: String = "",
    val edad: String = "",
    val contactoEmergencia: String = "",
    val esUrgencia: Boolean = false
)

class PacienteViewModel : ViewModel() {

    var pacientes by mutableStateOf<List<Paciente>>(emptyList())
        private set

    var formulario by mutableStateOf(FormularioPaciente())
        private set

    var mensajeError by mutableStateOf<String?>(null)
        private set

    fun actualizarNombre(valor: String) {
        formulario = formulario.copy(nombre = valor)
        mensajeError = null
    }

    fun actualizarEspecie(valor: String) {
        formulario = formulario.copy(especie = valor)
        mensajeError = null
    }

    fun actualizarEdad(valor: String) {
        if (valor.all { it.isDigit() } && valor.length <= 3) {
            formulario = formulario.copy(edad = valor)
            mensajeError = null
        }
    }

    fun actualizarContacto(valor: String) {
        formulario = formulario.copy(contactoEmergencia = valor)
        mensajeError = null
    }

    fun actualizarUrgencia(valor: Boolean) {
        formulario = formulario.copy(esUrgencia = valor)
    }

    fun agregarPaciente(): Boolean {
        val datos = formulario
        val nombre = datos.nombre.trim()
        val especie = datos.especie.trim()
        val contacto = datos.contactoEmergencia.trim()
        val edad = datos.edad.toIntOrNull()

        mensajeError = when {
            nombre.isBlank() ->
                "Ingresa el nombre del paciente."

            especie.isBlank() ->
                "Ingresa la especie o raza."

            edad == null || edad !in 0..100 ->
                "La edad debe ser un número entre 0 y 100."

            contacto.isBlank() ->
                "Ingresa un contacto de emergencia."

            else -> null
        }

        if (mensajeError != null) return false

        val nuevoPaciente = Paciente(
            id = java.util.UUID.randomUUID().toString(),
            nombre = nombre,
            especie = especie,
            edad = edad!!,
            contactoEmergencia = contacto,
            esUrgencia = datos.esUrgencia
        )

        pacientes = pacientes + nuevoPaciente
        limpiarFormulario()

        return true
    }

    fun limpiarFormulario() {
        formulario = FormularioPaciente()
        mensajeError = null
    }

    fun eliminarPaciente(id: String) {
        pacientes = pacientes.filterNot { it.id == id }
    }
}
