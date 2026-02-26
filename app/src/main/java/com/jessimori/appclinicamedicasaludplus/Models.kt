package com.jessimori.appclinicamedicasaludplus.model

import com.google.gson.annotations.SerializedName

/**
 * Modelo de datos para Cliente
 * Representa la información de un cliente del sistema
 */
data class Cliente(
    @SerializedName("idcliente")
    val idCliente: String,

    @SerializedName("empresa")
    val empresa: String,

    @SerializedName("nombres")
    val nombres: String,

    @SerializedName("correo")
    val correo: String,

    @SerializedName("ciudad")
    val ciudad: String,

    // Campos adicionales que vienen en el servicio pero no se mostrarán
    @SerializedName("pais")
    val pais: String? = null,

    @SerializedName("telefono")
    val telefono: String? = null
)

/**
 * Modelo de datos para Pedido
 * Representa la información de un pedido realizado por un cliente
 */
data class Pedido(
    @SerializedName("idpedido")
    val idPedido: String,

    @SerializedName("fecha")
    val fecha: String,

    @SerializedName("producto")
    val producto: String,

    @SerializedName("precio")
    val precio: String,

    @SerializedName("cantidad")
    val cantidad: String,

    @SerializedName("total")
    val total: String,

    @SerializedName("estado")
    val estado: String? = null
)


