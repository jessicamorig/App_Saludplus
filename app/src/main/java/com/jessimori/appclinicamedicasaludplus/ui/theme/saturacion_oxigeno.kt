package com.jessimori.appclinicamedicasaludplus.ui.theme

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jessimori.appclinicamedicasaludplus.R


private val Any.medium: Any
private val MaterialTheme.dimens: Any
private val MaterialTheme.medium: Any

class saturacion_oxigeno : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppClinicaMedicaSaludplusTheme {
                            Column() {
                                Box(contentAlignment = Alignment.BottomEnd) {
                                    Image(
                                        painterResource(R.drawable.cuadroblood),
                                        contentDescription = null,
                                        modifier = Modifier.height(400.dp),
                                        contentScale = ContentScale.Crop
                                    )

                                }
                                Column(modifier = Modifier.fillMaxHeight()
                                    .padding(MaterialTheme.dimens.medium),
                                    verticalArrangement = Arrangement.SpaceBetween) {
                                    Text(text = stringResource(R.string.saturacion_oxigeno))
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Button(onClick = {
                                            startActivity(Intent(this@saturacion_oxigeno::class.java))
                                        }) {
                                            Text(stringResource(R.string.saturacion_oxigeno))
                                        }
                                        Button(onClick = {
                                            startActivity(Intent(this@saturacion_oxigeno::class.java))
                                        }) {
                                            Text(stringResource(R.string.conteo_sanguineo))
                                        }
                                        Button(onClick = {
                                            startActivity(Intent(this@saturacion_oxigeno::class.java))
                                        }) {
                                            Text(stringResource(R.string.ritmo_cardiaco))
                                        }
                                        Button(onClick = {
                                            startActivity(Intent(this@saturacion_oxigeno::class.java))
                                        }) {
                                            Text(stringResource(R.string.nivel_glucosa))
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

