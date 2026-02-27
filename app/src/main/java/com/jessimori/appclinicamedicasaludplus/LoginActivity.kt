package com.jessimori.appclinicamedicasaludplus

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.jessimori.appclinicamedicasaludplus.ui.screens.LoginView
import com.jessimori.appclinicamedicasaludplus.ui.theme.AppClinicaMedicaSaludplusTheme

// Instancia única del DataStore para toda la app
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_prefs")

class LoginActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppClinicaMedicaSaludplusTheme {
                LoginView(
                    dataStore = dataStore,
                    onLoginSuccess = {
                        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                        finish()
                    }
                )
            }
        }
    }
}
