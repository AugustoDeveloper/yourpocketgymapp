package aucamana.com.br.yourpocketgym.network.api.services

import aucamana.com.br.yourpocketgym.models.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST


interface UserService {
    @POST("user")
    fun register(@Body user: User) : Call<User>
}