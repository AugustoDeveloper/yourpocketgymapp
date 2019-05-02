package aucamana.com.br.yourpocketgym.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import aucamana.com.br.yourpocketgym.R
import aucamana.com.br.yourpocketgym.models.User
import aucamana.com.br.yourpocketgym.network.api.ApiClient
import aucamana.com.br.yourpocketgym.network.api.ApiResponse

class SignupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val buttonRegister = findViewById<Button>(R.id.button_register)
        val edittextName = findViewById<EditText>(R.id.edittext_name)
        val edittextUserName = findViewById<EditText>(R.id.edittext_username)
        val edittextPassword = findViewById<EditText>(R.id.edittext_password)
        val edittextAge = findViewById<EditText>(R.id.edittext_age)
        val progressBarRegister = findViewById<ProgressBar>(R.id.progressbar_register)


        buttonRegister.setOnClickListener {

            buttonRegister.isEnabled = false
            startLoading(progressBarRegister)

            val username = edittextUserName.text.toString()
            val password = edittextPassword.text.toString()
            val age = edittextAge.text.toString().toInt()
            val name = edittextName.text.toString()

            ApiClient().register(User(name, username, password, age), object: ApiResponse<User>{
                override fun fail(reason: String) {
                    Toast.makeText(applicationContext, reason, Toast.LENGTH_SHORT).show()
                    buttonRegister.isEnabled = true
                    stopLoading(progressBarRegister)
                }

                override fun success(response: User) {
                    stopLoading(progressBarRegister)
                    Toast.makeText(applicationContext, "Registration succeded", Toast.LENGTH_SHORT).show()
                    buttonRegister.isEnabled = true

                    finish()
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
                }

                override fun error(throwable: Throwable) {
                    Toast.makeText(applicationContext, "Oops! Something is wrong...", Toast.LENGTH_SHORT).show()

                    buttonRegister.isEnabled = true
                    stopLoading(progressBarRegister)
                }

                override fun fail() {
                    Toast.makeText(applicationContext, "Registration failed", Toast.LENGTH_SHORT).show()
                    buttonRegister.isEnabled = true
                    stopLoading(progressBarRegister)
                }

            })
        }
    }

    private fun startLoading(progressBar: ProgressBar) {
        progressBar.isIndeterminate = true
        progressBar.visibility = View.VISIBLE
    }

    private fun stopLoading(progressBar: ProgressBar) {
        progressBar.isIndeterminate = false
        progressBar.visibility = View.GONE
    }


    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    }
}
