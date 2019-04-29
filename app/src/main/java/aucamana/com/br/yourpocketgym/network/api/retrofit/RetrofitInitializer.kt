package aucamana.com.br.yourpocketgym.network.api.retrofit

import aucamana.com.br.yourpocketgym.network.api.services.LoginService
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

class RetrofitInitializer {
    private val retrofit =
        Retrofit.Builder()
            .baseUrl("https://pocket-gym.herokuapp.com/api/")
            .addConverterFactory(JacksonConverterFactory.create())
            .build()

    fun autheticationService() : LoginService = retrofit.create(LoginService::class.java)
}