package com.jessimori.appclinicamedicasaludplus

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.jessimori.appclinicamedicasaludplus.model.Cliente
import com.jessimori.appclinicamedicasaludplus.ui.screens.ClientesView
import com.jessimori.appclinicamedicasaludplus.ui.theme.AppClinicaMedicaSaludplusTheme

class ClientesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppClinicaMedicaSaludplusTheme {
                ClientesView(
                    onClienteClick = { cliente ->
                        PedidosActivity.start(
                            context = this,
                            cliente = cliente
                        )
                    },
                    onBackClick = { finish() }
                )
            }
        }
    }
}

