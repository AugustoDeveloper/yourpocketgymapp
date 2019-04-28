package aucamana.com.br.yourpocketgym.network.api

import aucamana.com.br.yourpocketgym.models.Login
import aucamana.com.br.yourpocketgym.models.Token
import aucamana.com.br.yourpocketgym.network.api.retrofit.RetrofitInitializer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiClient {
    fun authenticate(username: String, password: String, apiResponse: ApiResponse<Token>) {
        val login = Login(username, password)
        val auth = RetrofitInitializer().autheticationService()
        val call = auth.authenticate(login)

        call.enqueue(object: Callback<Token?> {
            override fun onFailure(call: Call<Token?>, t: Throwable) {

            }

            override fun onResponse(call: Call<Token?>, response: Response<Token?>) {
                if (response?.isSuccessful) {
                    response?.body()?.let {
                        val token: Token = it
                        apiResponse.success(token)
                    }
                }
            }
        })
    }
}