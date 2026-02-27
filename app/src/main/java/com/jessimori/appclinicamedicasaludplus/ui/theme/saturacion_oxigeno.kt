package com.jessimori.appclinicamedicasaludplus.ui.theme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.LocalHospital
import androidx.compose.material.icons.filled.MonitorHeart
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Modelo de datos para cada tarjeta de métrica
data class MetricaCard(
    val titulo: String,
    val valor: String,
    val unidad: String,
    val icon: ImageVector,
    val iconColor: Color,
    val bgColor: Color
)

class saturacion_oxigeno : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppClinicaMedicaSaludplusTheme {
                SaturacionScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SaturacionScreen() {
    // Lista de tarjetas de salud
    val metricas = listOf(
        MetricaCard(
            titulo = "Blood Status",
            valor = "98",
            unidad = "%",
            icon = Icons.Filled.Favorite,
            iconColor = Color(0xFFE53935),
            bgColor = Color(0xFFFFEBEE)
        ),
        MetricaCard(
            titulo = "Blood Count",
            valor = "4.5",
            unidad = "M/µL",
            icon = Icons.Filled.LocalHospital,
            iconColor = Color(0xFF43A047),
            bgColor = Color(0xFFE8F5E9)
        ),
        MetricaCard(
            titulo = "Heart Rate",
            valor = "72",
            unidad = "bpm",
            icon = Icons.Filled.MonitorHeart,
            iconColor = Color(0xFF1E88E5),
            bgColor = Color(0xFFE3F2FD)
        ),
        MetricaCard(
            titulo = "Glucose Level",
            valor = "95",
            unidad = "mg/dL",
            icon = Icons.Filled.WaterDrop,
            iconColor = Color(0xFF8E24AA),
            bgColor = Color(0xFFF3E5F5)
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Health Metrics",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Spacer(modifier = Modifier.height(8.dp))

            // Fila 1: Blood Status + Blood Count
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                MetricaCardItem(
                    metrica = metricas[0],
                    modifier = Modifier.weight(1f)
                )
                MetricaCardItem(
                    metrica = metricas[1],
                    modifier = Modifier.weight(1f)
                )
            }

            // Fila 2: Heart Rate + Glucose Level
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                MetricaCardItem(
                    metrica = metricas[2],
                    modifier = Modifier.weight(1f)
                )
                MetricaCardItem(
                    metrica = metricas[3],
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
fun MetricaCardItem(metrica: MetricaCard, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.aspectRatio(1f),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Icono circular en la parte superior
            Box(
                modifier = Modifier
                    .size(52.dp)
                    .clip(CircleShape)
                    .background(metrica.bgColor),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = metrica.icon,
                    contentDescription = metrica.titulo,
                    tint = metrica.iconColor,
                    modifier = Modifier.size(28.dp)
                )
            }

            // Fila "Now" + valor
            Column {
                Text(
                    text = "Now",
                    fontSize = 12.sp,
                    color = Color.Gray,
                    fontWeight = FontWeight.Normal
                )
                Row(
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = metrica.valor,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1A1A2E)
                    )
                    Text(
                        text = metrica.unidad,
                        fontSize = 13.sp,
                        color = Color.Gray,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                }
            }

            // Título de la métrica en la parte inferior
            Text(
                text = metrica.titulo,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF444444)
            )
        }
    }
}
