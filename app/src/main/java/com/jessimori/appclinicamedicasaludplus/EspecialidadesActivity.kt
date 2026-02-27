package com.jessimori.appclinicamedicasaludplus

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.jessimori.appclinicamedicasaludplus.ui.screens.EspecialidadesView
import com.jessimori.appclinicamedicasaludplus.ui.theme.AppClinicaMedicaSaludplusTheme

class EspecialidadesActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppClinicaMedicaSaludplusTheme {
                EspecialidadesView(
                    onEspecialidadClick = { especialidad ->
                        DoctoresEspecialidadActivity.start(this, especialidad)
                    },
                    onBackClick = { finish() }
                )
            }
        }
    }
}
