package com.jessimori.appclinicamedicasaludplus

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.jessimori.appclinicamedicasaludplus.model.Cliente
import com.jessimori.appclinicamedicasaludplus.ui.screens.PedidosView
import com.jessimori.appclinicamedicasaludplus.ui.theme.AppClinicaMedicaSaludplusTheme

class PedidosActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val idCliente = intent.getStringExtra(EXTRA_ID_CLIENTE).orEmpty()
        val nombreCliente = intent.getStringExtra(EXTRA_NOMBRE_CLIENTE).orEmpty()
        val empresaCliente = intent.getStringExtra(EXTRA_EMPRESA_CLIENTE).orEmpty()

        setContent {
            AppClinicaMedicaSaludplusTheme {
                PedidosView(
                    idCliente = idCliente,
                    clienteNombre = nombreCliente,
                    clienteEmpresa = empresaCliente,
                    onBackClick = { finish() }
                )
            }
        }
    }

    companion object {
        private const val EXTRA_ID_CLIENTE = "extra_id_cliente"
        private const val EXTRA_NOMBRE_CLIENTE = "extra_nombre_cliente"
        private const val EXTRA_EMPRESA_CLIENTE = "extra_empresa_cliente"

        fun start(context: Context, cliente: Cliente) {
            val intent = Intent(context, PedidosActivity::class.java).apply {
                putExtra(EXTRA_ID_CLIENTE, cliente.idCliente)
                putExtra(EXTRA_NOMBRE_CLIENTE, cliente.nombres)
                putExtra(EXTRA_EMPRESA_CLIENTE, cliente.empresa)
            }
            context.startActivity(intent)
        }
    }
}

