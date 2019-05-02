package aucamana.com.br.yourpocketgym.models

class User {
    var id: Int? = null
    var name: String = ""
    var username: String = ""
    var password: String? = ""
    var age: Int = 0

    constructor(name: String, username: String, password: String, age: Int) {
        this.name = name
        this.username = username
        this.password = password
        this.age = age
    }

    constructor() {
    }
}