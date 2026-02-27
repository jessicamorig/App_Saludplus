package com.jessimori.appclinicamedicasaludplus.network

import com.google.gson.JsonElement
import com.google.gson.annotations.SerializedName
import com.jessimori.appclinicamedicasaludplus.model.Cliente
import com.jessimori.appclinicamedicasaludplus.model.Especialidad
import com.jessimori.appclinicamedicasaludplus.model.Pedido
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

/**
 * Interface del API Service para servicios.campus.pe
 */
interface ApiService {

    @GET("clientes.php")
    suspend fun getClientes(): List<Cliente>

    @GET("pedidoscliente.php")
    suspend fun getPedidosCliente(
        @Query("idcliente") idCliente: String
    ): List<Pedido>

    /**
     * Login - Punto 3: inicio de sesión con login.php del profesor
     * Retorna JsonElement para soportar tanto número (1/0) como objeto JSON
     */
    @GET("login.php")
    suspend fun login(
        @Query("usuario") usuario: String,
        @Query("password") password: String
    ): JsonElement
}

/**
 * Singleton Retrofit para servicios.campus.pe
 */
object RetrofitClient {

    private const val BASE_URL = "https://servicios.campus.pe/"

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

    val apiService: ApiService = retrofit.create(ApiService::class.java)
}

/**
 * Interface del API Service para el servidor propio (XAMPP)
 * Punto 1: servicio web con parámetro GET
 * Punto 2: consulta maestro-detalle Especialidades -> Doctores
 */
interface CustomApiService {

    /**
     * Obtiene todas las especialidades médicas (MAESTRO)
     */
    @GET("especialidades.php")
    suspend fun getEspecialidades(): List<Especialidad>

    /**
     * Punto 1: Obtiene doctores por especialidad usando parámetro URL GET
     * Ejemplo: doctoresxespecialidad.php?idespecialidad=1
     */
    @GET("doctoresxespecialidad.php")
    suspend fun getDoctoresByEspecialidad(
        @Query("idespecialidad") idespecialidad: String
    ): List<Doctor>

    @GET("doctores.php")
    suspend fun getDoctores(): List<Doctor>

    /**
     * Login propio (XAMPP) - reemplaza al login.php del profesor
     * Retorna: {"respuesta":"correcto","usuario":"nombre"} o {"respuesta":"incorrecto"}
     */
    @GET("login.php")
    suspend fun login(
        @Query("usuario") usuario: String,
        @Query("password") password: String
    ): JsonElement
}

/**
 * Modelo Doctor para el servicio propio
 */
data class Doctor(
    @SerializedName("iddoctor")
    val idDoctor: String,

    @SerializedName("nombres")
    val nombres: String,

    @SerializedName("apellidos")
    val apellidos: String,

    @SerializedName("especialidad")
    val especialidad: String? = null,

    @SerializedName("idespecialidad")
    val idEspecialidad: String? = null,

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
 * Cliente Retrofit para el servidor propio AlwaysData
 */
object CustomRetrofitClient {

    private const val BASE_URL = "https://clinicasaludplus.alwaysdata.net/appClinica/"

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
