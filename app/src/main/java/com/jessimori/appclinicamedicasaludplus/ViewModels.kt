package com.jessimori.appclinicamedicasaludplus.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jessimori.appclinicamedicasaludplus.model.Cliente
import com.jessimori.appclinicamedicasaludplus.model.Especialidad
import com.jessimori.appclinicamedicasaludplus.model.Pedido
import com.jessimori.appclinicamedicasaludplus.network.CustomRetrofitClient
import com.jessimori.appclinicamedicasaludplus.network.Doctor
import com.jessimori.appclinicamedicasaludplus.network.RetrofitClient
import com.google.gson.JsonElement
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

// ─────────────────────────────────────────────────────────
// CLIENTES - Lista principal (servicios.campus.pe)
// ─────────────────────────────────────────────────────────

sealed class ClientesUiState {
    object Loading : ClientesUiState()
    data class Success(val clientes: List<Cliente>) : ClientesUiState()
    data class Error(val message: String) : ClientesUiState()
}

class ClientesViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<ClientesUiState>(ClientesUiState.Loading)
    val uiState: StateFlow<ClientesUiState> = _uiState.asStateFlow()

    init { cargarClientes() }

    fun cargarClientes() {
        viewModelScope.launch {
            _uiState.value = ClientesUiState.Loading
            try {
                val clientes = RetrofitClient.apiService.getClientes()
                _uiState.value = ClientesUiState.Success(clientes)
            } catch (e: Exception) {
                _uiState.value = ClientesUiState.Error(e.message ?: "Error al cargar clientes")
            }
        }
    }

    fun retry() { cargarClientes() }
}

// ─────────────────────────────────────────────────────────
// PEDIDOS - Detalle de cliente (servicios.campus.pe)
// ─────────────────────────────────────────────────────────

sealed class PedidosUiState {
    object Loading : PedidosUiState()
    data class Success(val pedidos: List<Pedido>, val cliente: Cliente) : PedidosUiState()
    data class Error(val message: String) : PedidosUiState()
}

class PedidosViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<PedidosUiState>(PedidosUiState.Loading)
    val uiState: StateFlow<PedidosUiState> = _uiState.asStateFlow()

    fun cargarPedidos(idCliente: String, cliente: Cliente) {
        viewModelScope.launch {
            _uiState.value = PedidosUiState.Loading
            try {
                val pedidos = RetrofitClient.apiService.getPedidosCliente(idCliente)
                _uiState.value = PedidosUiState.Success(pedidos, cliente)
            } catch (e: Exception) {
                _uiState.value = PedidosUiState.Error(e.message ?: "Error al cargar pedidos")
            }
        }
    }

    fun retry(idCliente: String, cliente: Cliente) { cargarPedidos(idCliente, cliente) }
}

// ─────────────────────────────────────────────────────────
// DOCTORES - Lista simple (XAMPP propio)
// ─────────────────────────────────────────────────────────

sealed class DoctoresUiState {
    object Loading : DoctoresUiState()
    data class Success(val doctores: List<Doctor>) : DoctoresUiState()
    data class Error(val message: String) : DoctoresUiState()
}

class DoctoresViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<DoctoresUiState>(DoctoresUiState.Loading)
    val uiState: StateFlow<DoctoresUiState> = _uiState.asStateFlow()

    init { cargarDoctores() }

    fun cargarDoctores() {
        viewModelScope.launch {
            _uiState.value = DoctoresUiState.Loading
            try {
                val doctores = CustomRetrofitClient.customApiService.getDoctores()
                _uiState.value = DoctoresUiState.Success(doctores)
            } catch (e: Exception) {
                _uiState.value = DoctoresUiState.Error(e.message ?: "Error al cargar doctores")
            }
        }
    }

    fun retry() { cargarDoctores() }
}

// ─────────────────────────────────────────────────────────
// ESPECIALIDADES - Maestro (XAMPP propio) - Punto 2
// ─────────────────────────────────────────────────────────

sealed class EspecialidadesUiState {
    object Loading : EspecialidadesUiState()
    data class Success(val especialidades: List<Especialidad>) : EspecialidadesUiState()
    data class Error(val message: String) : EspecialidadesUiState()
}

class EspecialidadesViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<EspecialidadesUiState>(EspecialidadesUiState.Loading)
    val uiState: StateFlow<EspecialidadesUiState> = _uiState.asStateFlow()

    init { cargarEspecialidades() }

    fun cargarEspecialidades() {
        viewModelScope.launch {
            _uiState.value = EspecialidadesUiState.Loading
            try {
                val especialidades = CustomRetrofitClient.customApiService.getEspecialidades()
                _uiState.value = EspecialidadesUiState.Success(especialidades)
            } catch (e: Exception) {
                _uiState.value = EspecialidadesUiState.Error(
                    e.message ?: "Error al cargar especialidades"
                )
            }
        }
    }

    fun retry() { cargarEspecialidades() }
}

// ─────────────────────────────────────────────────────────
// DOCTORES POR ESPECIALIDAD - Detalle (XAMPP propio) - Punto 2
// Usa parámetro GET: doctoresxespecialidad.php?idespecialidad=X
// ─────────────────────────────────────────────────────────

sealed class DoctoresEspecialidadUiState {
    object Loading : DoctoresEspecialidadUiState()
    data class Success(
        val doctores: List<Doctor>,
        val especialidad: Especialidad
    ) : DoctoresEspecialidadUiState()
    data class Error(val message: String) : DoctoresEspecialidadUiState()
}

class DoctoresEspecialidadViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<DoctoresEspecialidadUiState>(DoctoresEspecialidadUiState.Loading)
    val uiState: StateFlow<DoctoresEspecialidadUiState> = _uiState.asStateFlow()

    fun cargarDoctores(idespecialidad: String, especialidad: Especialidad) {
        viewModelScope.launch {
            _uiState.value = DoctoresEspecialidadUiState.Loading
            try {
                val doctores = CustomRetrofitClient.customApiService.getDoctoresByEspecialidad(idespecialidad)
                _uiState.value = DoctoresEspecialidadUiState.Success(doctores, especialidad)
            } catch (e: Exception) {
                _uiState.value = DoctoresEspecialidadUiState.Error(
                    e.message ?: "Error al cargar doctores"
                )
            }
        }
    }

    fun retry(idespecialidad: String, especialidad: Especialidad) {
        cargarDoctores(idespecialidad, especialidad)
    }
}

// ─────────────────────────────────────────────────────────
// LOGIN - Punto 3: login.php con DataStore y AlertDialog
// ─────────────────────────────────────────────────────────

sealed class LoginUiState {
    object Idle : LoginUiState()
    object Loading : LoginUiState()
    data class Success(val usuario: String) : LoginUiState()
    data class Error(val message: String) : LoginUiState()
}

class LoginViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<LoginUiState>(LoginUiState.Idle)
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun login(usuario: String, password: String) {
        viewModelScope.launch {
            _uiState.value = LoginUiState.Loading
            try {
                val response = CustomRetrofitClient.customApiService.login(usuario, password)
                val isSuccess: Boolean
                val nombreUsuario: String

                when {
                    // Servidor devuelve número: 1 = correcto, 0 = incorrecto
                    response.isJsonPrimitive && response.asJsonPrimitive.isNumber -> {
                        isSuccess = response.asInt == 1
                        nombreUsuario = usuario
                    }
                    // Servidor devuelve objeto: {"respuesta":"correcto","usuario":"..."}
                    response.isJsonObject -> {
                        val obj = response.asJsonObject
                        val respuesta = obj.get("respuesta")?.asString ?: ""
                        isSuccess = respuesta == "correcto"
                        nombreUsuario = obj.get("usuario")?.asString ?: usuario
                    }
                    // Servidor devuelve string: "correcto" o "incorrecto"
                    response.isJsonPrimitive && response.asJsonPrimitive.isString -> {
                        isSuccess = response.asString == "correcto"
                        nombreUsuario = usuario
                    }
                    else -> {
                        isSuccess = false
                        nombreUsuario = usuario
                    }
                }

                if (isSuccess) {
                    _uiState.value = LoginUiState.Success(nombreUsuario)
                } else {
                    _uiState.value = LoginUiState.Error("Usuario o contraseña incorrectos")
                }
            } catch (e: Exception) {
                _uiState.value = LoginUiState.Error(e.message ?: "Error de conexión")
            }
        }
    }

    fun resetState() {
        _uiState.value = LoginUiState.Idle
    }
}
