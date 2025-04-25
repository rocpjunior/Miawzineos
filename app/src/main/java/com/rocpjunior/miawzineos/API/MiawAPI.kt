package com.rocpjunior.miawzineos.API

import com.rocpjunior.miawzineos.model.Resultado
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MiawAPI {
    @GET("gallery/search/")
    suspend fun pesquisarImagensGatos(@Query("q") q: String ) : Response<Resultado>
}