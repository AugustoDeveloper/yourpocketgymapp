package aucamana.com.br.yourpocketgym.activities

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.*
import aucamana.com.br.yourpocketgym.R
import aucamana.com.br.yourpocketgym.models.Token
import aucamana.com.br.yourpocketgym.network.api.ApiClient
import aucamana.com.br.yourpocketgym.network.api.ApiResponse
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    private val PERMISSIONS_REQUEST: Int = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setupPermission()

        val editUsername = findViewById<EditText>(R.id.editText_username)
        val editPassword = findViewById<EditText>(R.id.edittext_password)
        val progressBar = findViewById<ProgressBar>(R.id.progressbar_login)
        val buttonLogin = findViewById<Button>(R.id.button_login)
        val textviewSignup = findViewById<TextView>(R.id.textview_signup)
        val intentMainActivity = Intent(applicationContext, MainActivity::class.java)
        val intentSignupActivity = Intent(applicationContext, SignupActivity::class.java)

        val textviewQuestion = findViewById<TextView>(R.id.textview_question)

        textviewQuestion.setOnClickListener {
            showRegisterActivity(it, intentSignupActivity)
        }

        textviewSignup.setOnClickListener {
            showRegisterActivity(it, intentSignupActivity)
        }

        buttonLogin.setOnClickListener {
            val username = editUsername.text.toString()
            val password = editPassword.text.toString()

            buttonLogin.isEnabled = false
            startLoading(progressBar)


            ApiClient().authenticate(username, password, object : ApiResponse<Token> {
                override fun fail() {
                    Toast.makeText(applicationContext, getString(R.string.login_failed_phrase), Toast.LENGTH_SHORT).show()
                    buttonLogin.isEnabled = true
                    stopLoading(progressBar)
                }

                override fun error(throwable: Throwable) {
                    Toast.makeText(applicationContext,getString(R.string.something_wrong_phrase), Toast.LENGTH_SHORT).show()
                    buttonLogin.isEnabled = true
                    stopLoading(progressBar)
                }

                override fun success(response: Token) {
                    Toast.makeText(applicationContext, getString(R.string.login_succeded_phrase), Toast.LENGTH_SHORT).show()
                    buttonLogin.isEnabled = true
                    stopLoading(progressBar)

                    startActivity(intentMainActivity)
                    finish()
                }
            })
        }
    }

    private fun showRegisterActivity(v: View, intent: Intent) {
        startActivity(intent)
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    }

    private fun startLoading(progressBar: ProgressBar) {
        progressBar.isIndeterminate = true
        progressBar.visibility = View.VISIBLE
    }

    private fun stopLoading(progressBar: ProgressBar) {
        progressBar.isIndeterminate = false
        progressBar.visibility = View.GONE
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