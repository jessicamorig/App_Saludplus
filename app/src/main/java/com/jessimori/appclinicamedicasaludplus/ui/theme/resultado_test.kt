package com.jessimori.appclinicamedicasaludplus.ui.theme

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jessimori.appclinicamedicasaludplus.R


class resultado_test : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppClinicaMedicaSaludplusTheme {
                Column(modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = stringResource(R.string.resultado_test),
                        style = MaterialTheme.typography.titleLarge)
                    //style = TextStyle(fontSize = 24.sp,fontWeight = FontWeight.Bold)
                }
                Column (modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center){
                    Image(painterResource(R.drawable.presionarterial),
                        contentDescription = stringResource(R.string.texto_logo),
                        modifier = Modifier.height(240.dp))
                    Text(text = stringResource(R.string.saturacion_oxigeno),
                        style = MaterialTheme.typography.displayLarge)
                    //style = TextStyle(fontSize = 72.sp, fontWeight = FontWeight.Bold))
                    Button(onClick = {
                        startActivity(Intent(this@resultado_test, saturacion_oxigeno::class.java))
                    }) {
                        Text(stringResource(R.string.saturacion_oxigeno))
                    }
                }
            }
        }
    }
}


