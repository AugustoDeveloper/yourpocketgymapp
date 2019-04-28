package aucamana.com.br.yourpocketgym.network.api.services

import aucamana.com.br.yourpocketgym.models.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface LoginService {
    @POST("login/authenticate")
    fun authenticate(@Body login: Login) : Call<Token>
}