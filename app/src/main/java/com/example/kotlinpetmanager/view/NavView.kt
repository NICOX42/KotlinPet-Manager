package com.example.kotlinpetmanager.view

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kotlinpetmanager.viewmodel.PacienteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavView() {

    val pacienteViewModel: PacienteViewModel = viewModel()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("KotlinPet Manager 🐾")
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor =
                        MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor =
                        MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { innerPadding ->

        PantallaPacientesView(
            viewModel = pacienteViewModel,
            modifier = Modifier,
            contentPadding = innerPadding
        )
    }
}
