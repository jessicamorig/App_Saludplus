package com.jessimori.appclinicamedicasaludplus.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jessimori.appclinicamedicasaludplus.model.Cliente
import com.jessimori.appclinicamedicasaludplus.model.Pedido
import com.jessimori.appclinicamedicasaludplus.network.CustomRetrofitClient
import com.jessimori.appclinicamedicasaludplus.network.Doctor
import com.jessimori.appclinicamedicasaludplus.network.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * Estado de UI para la pantalla de Clientes
 */
sealed class ClientesUiState {
    object Loading : ClientesUiState()
    data class Success(val clientes: List<Cliente>) : ClientesUiState()
    data class Error(val message: String) : ClientesUiState()
}

/**
 * ViewModel para la pantalla de Clientes
 */
class ClientesViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<ClientesUiState>(ClientesUiState.Loading)
    val uiState: StateFlow<ClientesUiState> = _uiState.asStateFlow()

    init {
        cargarClientes()
    }

    /**
     * Carga la lista de clientes desde el API
     */
    fun cargarClientes() {
        viewModelScope.launch {
            _uiState.value = ClientesUiState.Loading
            try {
                val clientes = RetrofitClient.apiService.getClientes()
                _uiState.value = ClientesUiState.Success(clientes)
            } catch (e: Exception) {
                _uiState.value = ClientesUiState.Error(
                    e.message ?: "Error al cargar clientes"
                )
            }
        }
    }

    /**
     * Reintentar la carga de clientes
     */
    fun retry() {
        cargarClientes()
    }
}

/**
 * Estado de UI para la pantalla de Pedidos
 */
sealed class PedidosUiState {
    object Loading : PedidosUiState()
    data class Success(val pedidos: List<Pedido>, val cliente: Cliente) : PedidosUiState()
    data class Error(val message: String) : PedidosUiState()
}

/**
 * ViewModel para la pantalla de Pedidos
 */
class PedidosViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<PedidosUiState>(PedidosUiState.Loading)
    val uiState: StateFlow<PedidosUiState> = _uiState.asStateFlow()

    /**
     * Carga los pedidos de un cliente específico
     * @param idCliente ID del cliente
     * @param cliente Información del cliente
     */
    fun cargarPedidos(idCliente: String, cliente: Cliente) {
        viewModelScope.launch {
            _uiState.value = PedidosUiState.Loading
            try {
                val pedidos = RetrofitClient.apiService.getPedidosCliente(idCliente)
                _uiState.value = PedidosUiState.Success(pedidos, cliente)
            } catch (e: Exception) {
                _uiState.value = PedidosUiState.Error(
                    e.message ?: "Error al cargar pedidos"
                )
            }
        }
    }

    /**
     * Reintentar la carga de pedidos
     */
    fun retry(idCliente: String, cliente: Cliente) {
        cargarPedidos(idCliente, cliente)
    }
}

/**
 * Estado de UI para la pantalla de Doctores (servicio web propio)
 */
sealed class DoctoresUiState {
    object Loading : DoctoresUiState()
    data class Success(val doctores: List<Doctor>) : DoctoresUiState()
    data class Error(val message: String) : DoctoresUiState()
}

/**
 * ViewModel para la pantalla de Doctores
 * Consume el servicio web personalizado configurado en CustomRetrofitClient
 */
class DoctoresViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<DoctoresUiState>(DoctoresUiState.Loading)
    val uiState: StateFlow<DoctoresUiState> = _uiState.asStateFlow()

    init {
        cargarDoctores()
    }

    fun cargarDoctores() {
        viewModelScope.launch {
            _uiState.value = DoctoresUiState.Loading
            try {
                val doctores = CustomRetrofitClient.customApiService.getDoctores()
                _uiState.value = DoctoresUiState.Success(doctores)
            } catch (e: Exception) {
                _uiState.value = DoctoresUiState.Error(
                    e.message ?: "Error al cargar doctores"
                )
            }
        }
    }

    fun retry() {
        cargarDoctores()
    }
}