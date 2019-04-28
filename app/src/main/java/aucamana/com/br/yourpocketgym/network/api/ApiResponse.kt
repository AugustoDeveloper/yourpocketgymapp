package aucamana.com.br.yourpocketgym.network.api

interface ApiResponse<T> {
    fun success(response: T)
}