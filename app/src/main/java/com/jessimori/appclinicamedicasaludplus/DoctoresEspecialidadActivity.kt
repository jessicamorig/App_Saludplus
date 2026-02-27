package com.jessimori.appclinicamedicasaludplus

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.jessimori.appclinicamedicasaludplus.model.Especialidad
import com.jessimori.appclinicamedicasaludplus.ui.screens.DoctoresEspecialidadView
import com.jessimori.appclinicamedicasaludplus.ui.theme.AppClinicaMedicaSaludplusTheme

/**
 * Activity DETALLE: Doctores filtrados por Especialidad
 * Punto 3 de la evaluación - consulta maestro-detalle
 * Recibe la especialidad seleccionada y muestra sus doctores vía GET
 */
class DoctoresEspecialidadActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val idEspecialidad = intent.getStringExtra(EXTRA_ID_ESPECIALIDAD).orEmpty()
        val nombreEspecialidad = intent.getStringExtra(EXTRA_NOMBRE_ESPECIALIDAD).orEmpty()
        val descripcionEspecialidad = intent.getStringExtra(EXTRA_DESCRIPCION_ESPECIALIDAD).orEmpty()
        val areaEspecialidad = intent.getStringExtra(EXTRA_AREA_ESPECIALIDAD).orEmpty()

        val especialidad = Especialidad(
            idEspecialidad = idEspecialidad,
            nombre = nombreEspecialidad,
            descripcion = descripcionEspecialidad,
            area = areaEspecialidad
        )

        setContent {
            AppClinicaMedicaSaludplusTheme {
                DoctoresEspecialidadView(
                    especialidad = especialidad,
                    onBackClick = { finish() }
                )
            }
        }
    }

    companion object {
        private const val EXTRA_ID_ESPECIALIDAD = "extra_id_especialidad"
        private const val EXTRA_NOMBRE_ESPECIALIDAD = "extra_nombre_especialidad"
        private const val EXTRA_DESCRIPCION_ESPECIALIDAD = "extra_descripcion_especialidad"
        private const val EXTRA_AREA_ESPECIALIDAD = "extra_area_especialidad"

        fun start(context: Context, especialidad: Especialidad) {
            val intent = Intent(context, DoctoresEspecialidadActivity::class.java).apply {
                putExtra(EXTRA_ID_ESPECIALIDAD, especialidad.idEspecialidad)
                putExtra(EXTRA_NOMBRE_ESPECIALIDAD, especialidad.nombre)
                putExtra(EXTRA_DESCRIPCION_ESPECIALIDAD, especialidad.descripcion)
                putExtra(EXTRA_AREA_ESPECIALIDAD, especialidad.area)
            }
            context.startActivity(intent)
        }
    }
}
