package aucamana.com.br.yourpocketgym.models

import com.fasterxml.jackson.annotation.JsonProperty

data class Token (
    var created: String = "",
    var expiration: String = "",
    @JsonProperty("access_token")
    var accessToken: String = ""
)