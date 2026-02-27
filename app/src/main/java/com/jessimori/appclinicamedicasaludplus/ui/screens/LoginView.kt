package com.jessimori.appclinicamedicasaludplus.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jessimori.appclinicamedicasaludplus.viewmodel.LoginUiState
import com.jessimori.appclinicamedicasaludplus.viewmodel.LoginViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

// Clave para guardar el usuario en DataStore
val KEY_USUARIO = stringPreferencesKey("usuario")

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginView(
    dataStore: DataStore<Preferences>,
    onLoginSuccess: () -> Unit,
    viewModel: LoginViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    var usuario by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var showErrorDialog by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    // Verificar DataStore UNA SOLA VEZ al iniciar: si hay sesión guardada, navegar directo
    LaunchedEffect(Unit) {
        val savedUser = dataStore.data.map { prefs -> prefs[KEY_USUARIO] }.first()
        if (savedUser != null) {
            onLoginSuccess()
        }
    }

    // Manejar resultado del login API
    LaunchedEffect(uiState) {
        when (val state = uiState) {
            is LoginUiState.Success -> {
                // Guardar sesión en DataStore y luego navegar (una sola vez)
                dataStore.edit { prefs ->
                    prefs[KEY_USUARIO] = state.usuario
                }
                onLoginSuccess()
            }
            is LoginUiState.Error -> {
                errorMessage = state.message
                showErrorDialog = true
            }
            else -> {}
        }
    }

    // AlertDialog para mostrar errores de inicio de sesión
    if (showErrorDialog) {
        AlertDialog(
            onDismissRequest = {
                showErrorDialog = false
                viewModel.resetState()
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.Error,
                    contentDescription = "Error",
                    tint = MaterialTheme.colorScheme.error
                )
            },
            title = {
                Text(
                    text = "Error de inicio de sesión",
                    fontWeight = FontWeight.Bold
                )
            },
            text = { Text(text = errorMessage) },
            confirmButton = {
                TextButton(
                    onClick = {
                        showErrorDialog = false
                        viewModel.resetState()
                    }
                ) {
                    Text("Aceptar")
                }
            }
        )
    }

    Scaffold { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Ícono de la clínica
                    Icon(
                        imageVector = Icons.Default.LocalHospital,
                        contentDescription = "Clínica",
                        modifier = Modifier.size(64.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )

                    Text(
                        text = "Clínica Médica Saludplus",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )

                    Text(
                        text = "Iniciar Sesión",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    // Campo usuario
                    OutlinedTextField(
                        value = usuario,
                        onValueChange = { usuario = it },
                        label = { Text("Usuario") },
                        leadingIcon = {
                            Icon(Icons.Default.Person, contentDescription = "Usuario")
                        },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                    )

                    // Campo contraseña
                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text("Contraseña") },
                        leadingIcon = {
                            Icon(Icons.Default.Lock, contentDescription = "Contraseña")
                        },
                        trailingIcon = {
                            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                Icon(
                                    imageVector = if (passwordVisible)
                                        Icons.Default.VisibilityOff
                                    else
                                        Icons.Default.Visibility,
                                    contentDescription = if (passwordVisible) "Ocultar" else "Mostrar"
                                )
                            }
                        },
                        visualTransformation = if (passwordVisible)
                            VisualTransformation.None
                        else
                            PasswordVisualTransformation(),
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                    )

                    // Botón de login
                    Button(
                        onClick = {
                            if (usuario.isNotBlank() && password.isNotBlank()) {
                                viewModel.login(usuario, password)
                            } else {
                                errorMessage = "Por favor ingrese usuario y contraseña"
                                showErrorDialog = true
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        enabled = uiState !is LoginUiState.Loading
                    ) {
                        if (uiState is LoginUiState.Loading) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(20.dp),
                                color = MaterialTheme.colorScheme.onPrimary,
                                strokeWidth = 2.dp
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Iniciando sesión...")
                        } else {
                            Icon(Icons.Default.Login, contentDescription = "Ingresar")
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Ingresar", fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }
    }
}
