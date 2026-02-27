package com.jessimori.appclinicamedicasaludplus

//https://www.behance.net/gallery/241327955/Healthcare-Mobile-App-UIUX-Design/modules/1390878193

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jessimori.appclinicamedicasaludplus.ui.theme.AppClinicaMedicaSaludplusTheme
import com.jessimori.appclinicamedicasaludplus.ui.theme.saturacion_oxigeno


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppClinicaMedicaSaludplusTheme {
                Box(modifier = Modifier.fillMaxSize()) {

                    // Imagen al fondo - ocupa el 80% de la pantalla con borde redondeado
                    Image(
                        painter = painterResource(R.drawable.logopulmon),
                        contentDescription = stringResource(R.string.texto_logo),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth(0.9f)
                            .fillMaxHeight(0.8f)
                            .align(Alignment.Center)
                            .clip(RoundedCornerShape(24.dp))
                            .border(
                                width = 3.dp,
                                color = MaterialTheme.colorScheme.primary,
                                shape = RoundedCornerShape(24.dp)
                            )
                            .alpha(0.85f)
                    )

                    // Contenido sobre la imagen
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 24.dp, vertical = 40.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        // Título arriba
                        Text(
                            text = stringResource(R.string.frase),
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.primary
                        )

                        // Botones abajo - todos del mismo ancho
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Button(
                                onClick = {
                                    startActivity(Intent(this@MainActivity, saturacion_oxigeno::class.java))
                                },
                                modifier = Modifier.fillMaxWidth(0.8f)
                            ) {
                                Text(stringResource(R.string.saturacion_oxigeno))
                            }

                            Button(
                                onClick = {
                                    startActivity(Intent(this@MainActivity, ClientesActivity::class.java))
                                },
                                modifier = Modifier.fillMaxWidth(0.8f)
                            ) {
                                Text("Clientes")
                            }

                            Button(
                                onClick = {
                                    startActivity(Intent(this@MainActivity, DoctoresActivity::class.java))
                                },
                                modifier = Modifier.fillMaxWidth(0.8f)
                            ) {
                                Text("Doctores")
                            }

                            // Punto 2: Maestro-detalle Especialidades -> Doctores
                            Button(
                                onClick = {
                                    startActivity(Intent(this@MainActivity, EspecialidadesActivity::class.java))
                                },
                                modifier = Modifier.fillMaxWidth(0.8f)
                            ) {
                                Text("Especialidades")
                            }
                        }
                    }
                }
            }
        }
    }
}
