package com.rocpjunior.miawzineos.model

data class Resultado(
    val `data`: List<Data>,
    val status: Int,
    val success: Boolean
)