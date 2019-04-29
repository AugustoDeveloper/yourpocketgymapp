package aucamana.com.br.yourpocketgym.activities

import android.Manifest
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Debug
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import aucamana.com.br.yourpocketgym.R
import aucamana.com.br.yourpocketgym.models.Token
import aucamana.com.br.yourpocketgym.network.api.ApiClient
import aucamana.com.br.yourpocketgym.network.api.ApiResponse
import java.util.concurrent.ThreadLocalRandom

class LoginActivity : AppCompatActivity() {
    private val PERMISSIONS_REQUEST: Int = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setupPermission()

        val editUsername = findViewById<EditText>(R.id.editText_username)
        val editPassword = findViewById<EditText>(R.id.edittext_password)


        val buttonLogin = findViewById<Button>(R.id.button_login)
        buttonLogin.setOnClickListener {
            val username = editUsername.text.toString()
            val password = editPassword.text.toString()

            ApiClient().authenticate(username, password, object : ApiResponse<Token> {
                override fun fail() {
                    Toast.makeText(applicationContext, "Login Failed", Toast.LENGTH_SHORT).show()
                }

                override fun error(throwable: Throwable) {
                    Toast.makeText(applicationContext,"Oops, something is wrong...", Toast.LENGTH_SHORT).show()
                }

                override fun success(response: Token) {
                    Toast.makeText(applicationContext, "Login succeded", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    private fun setupPermission() {
        var permission = ContextCompat.checkSelfPermission(this,
            Manifest.permission.INTERNET)

        if (permission != PackageManager.PERMISSION_GRANTED) {
            requestPermission(Manifest.permission.INTERNET)
        }

        permission = ContextCompat.checkSelfPermission(this,
            Manifest.permission.ACCESS_NETWORK_STATE)

        if (permission != PackageManager.PERMISSION_GRANTED) {
            requestPermission(Manifest.permission.ACCESS_NETWORK_STATE)
        }

    }

    private fun requestPermission(permissionName: String) {
        ActivityCompat.requestPermissions(this, arrayOf(permissionName), PERMISSIONS_REQUEST)
    }
}