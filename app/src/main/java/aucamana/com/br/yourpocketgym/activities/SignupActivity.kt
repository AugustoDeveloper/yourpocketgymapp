package aucamana.com.br.yourpocketgym.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import aucamana.com.br.yourpocketgym.R

class SignupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    }
}
