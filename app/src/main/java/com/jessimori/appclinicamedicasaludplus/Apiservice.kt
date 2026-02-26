package com.jessimori.appclinicamedicasaludplus.network

import com.google.gson.annotations.SerializedName
import com.jessimori.appclinicamedicasaludplus.model.Cliente
import com.jessimori.appclinicamedicasaludplus.model.Pedido
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

/**
 * Interface del API Service
 * Define los endpoints que se consumirán
 */
interface ApiService {

    /**
     * Obtiene la lista de todos los clientes
     */
    @GET("clientes.php")
    suspend fun getClientes(): List<Cliente>

    /**
     * Obtiene los pedidos de un cliente específico
     * @param idCliente ID del cliente
     */
    @GET("pedidoscliente.php")
    suspend fun getPedidosCliente(
        @Query("idcliente") idCliente: String
    ): List<Pedido>
}

/**
 * Objeto singleton para manejar las instancias de Retrofit
 */
object RetrofitClient {

    private const val BASE_URL = "https://servicios.campus.pe/"

    /**
     * Configuración del interceptor de logging para debugging
     */
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    /**
     * Cliente HTTP con configuraciones personalizadas
     */
    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    /**
     * Instancia de Retrofit para servicios.campus.pe
     */
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    /**
     * Servicio de API principal
     */
    val apiService: ApiService = retrofit.create(ApiService::class.java)
}

/**
 * Interface del API Service para tu servicio web personalizado
 */
interface CustomApiService {

    // Aquí agregarás el endpoint de tu servicio web personalizado
    // Ejemplo para un servicio de doctores/médicos:
    @GET("doctores.php")  // Cambia esto según tu servicio
    suspend fun getDoctores(): List<Doctor>
}

/**
 * Modelo de ejemplo para tu servicio personalizado (Doctores)
 * Ajusta según tu servicio web
 */
data class Doctor(
    @SerializedName("iddoctor")
    val idDoctor: String,

    @SerializedName("nombres")
    val nombres: String,

    @SerializedName("apellidos")
    val apellidos: String,

    @SerializedName("especialidad")
    val especialidad: String,

    @SerializedName("telefono")
    val telefono: String,

    @SerializedName("correo")
    val correo: String,

    @SerializedName("horario")
    val horario: String? = null,

    @SerializedName("consultorio")
    val consultorio: String? = null
)

/**
 * Cliente Retrofit para tu servicio web personalizado
 */
object CustomRetrofitClient {

    // URL base de tu servicio en XAMPP
    // Para emulador de Android Studio, "10.0.2.2" apunta al localhost de tu PC.
    private const val BASE_URL = "http://10.0.2.2/appClinica/"

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val customApiService: CustomApiService = retrofit.create(CustomApiService::class.java)
}




