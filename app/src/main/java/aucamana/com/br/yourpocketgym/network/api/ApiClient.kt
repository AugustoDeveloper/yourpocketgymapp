package aucamana.com.br.yourpocketgym.network.api

import aucamana.com.br.yourpocketgym.models.DefaultModelError
import aucamana.com.br.yourpocketgym.models.Login
import aucamana.com.br.yourpocketgym.models.Token
import aucamana.com.br.yourpocketgym.models.User
import aucamana.com.br.yourpocketgym.network.api.retrofit.RetrofitInitializer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiClient {
    fun authenticate(username: String, password: String, apiResponse: ApiResponse<Token>) {
        val login = Login(username, password)
        val retrofit = RetrofitInitializer()
        val auth = retrofit.autheticationService()
        val call = auth.authenticate(login)

        call.enqueue(object: Callback<Token?> {
            override fun onFailure(call: Call<Token?>, t: Throwable) {
                apiResponse.error(t)
            }

            override fun onResponse(call: Call<Token?>, response: Response<Token?>) {
                if (response?.isSuccessful) {
                    response?.body()?.let {
                        val token: Token = it
                        apiResponse.success(token)
                    }
                } else {
                    if (response?.code() >= 400)
                    {
                        val modelError = retrofit.convertToError<Token?, DefaultModelError>(response);
                        if(modelError != null) {
                            apiResponse.fail(modelError.reason)
                            return
                        }
                    }

                    apiResponse.fail()
                }
            }
        })
    }

    fun register(user: User, apiResponse: ApiResponse<User>) {
        val retrofit = RetrofitInitializer()
        val userService = retrofit.userService()
        val call = userService.register(user)

        call.enqueue(object : Callback<User?> {
            override fun onFailure(call: Call<User?>, t: Throwable) {
                apiResponse.error(t)

            }

            override fun onResponse(call: Call<User?>, response: Response<User?>) {
                if (response?.isSuccessful) {
                    response?.body()?.let {
                        val user: User = it
                        apiResponse.success(user)
                    }
                } else {
                    if (response?.code() >= 400)
                    {
                        val modelError = retrofit.convertToError<User?, DefaultModelError>(response);
                        if(modelError != null) {
                            apiResponse.fail(modelError.reason)
                            return
                        }
                    }
                    apiResponse.fail()
                }
            }
        })
    }
}