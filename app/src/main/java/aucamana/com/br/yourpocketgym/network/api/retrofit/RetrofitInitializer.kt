package aucamana.com.br.yourpocketgym.network.api.retrofit

import aucamana.com.br.yourpocketgym.network.api.services.LoginService
import aucamana.com.br.yourpocketgym.network.api.services.UserService
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitInitializer {
    private val retrofit =
        Retrofit.Builder()
            .baseUrl("https://pocket-gym.herokuapp.com/api/")
            .addConverterFactory(JacksonConverterFactory.create())
            .build()

    fun<TModel, TModelError: Any> convertToError(ofClass: Class<TModelError>, response: Response<TModel>) : TModelError? {
        val converter: Converter<ResponseBody, TModelError> = retrofit.responseBodyConverter<TModelError>(ofClass, arrayOfNulls<Annotation>(0))
        return try {
            val errorBody = response.errorBody()
            if (errorBody != null) {
                return converter.convert(errorBody)
            }
            return null

        } catch (e: Exception) {
            null
        }
    }

    inline fun<TModel, reified TModelError: Any> convertToError(response: Response<TModel>) : TModelError? {
        return this.convertToError(TModelError::class.java, response)
    }

    fun autheticationService() : LoginService = retrofit.create(LoginService::class.java)
    fun userService() : UserService = retrofit.create(UserService::class.java)
}