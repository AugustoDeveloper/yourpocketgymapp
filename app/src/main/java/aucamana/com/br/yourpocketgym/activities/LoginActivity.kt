package aucamana.com.br.yourpocketgym.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import aucamana.com.br.yourpocketgym.R
import aucamana.com.br.yourpocketgym.models.Token
import aucamana.com.br.yourpocketgym.network.api.ApiClient
import aucamana.com.br.yourpocketgym.network.api.ApiResponse

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val editUsername = findViewById<EditText>(R.id.editText_username);
        val editPassword = findViewById<EditText>(R.id.edittext_password);

        val buttonLogin = findViewById<Button>(R.id.button_login);
        buttonLogin.setOnClickListener(View.OnClickListener {
            val username = editUsername.text.toString()
            val password = editPassword.text.toString()

            ApiClient().authenticate(username, password, object : ApiResponse<Token> {
                override fun success(response: Token) {
                    Toast.makeText(this@LoginActivity, "Login succeded", Toast.LENGTH_SHORT)
                }
            })
        })
    }
}
