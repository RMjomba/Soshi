package com.reginaldateya.soshi

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class Register : AppCompatActivity() {

    private lateinit var etRegisterEmailAddress : EditText
    private lateinit var etPassword : EditText
    private lateinit var tvLogin : TextView
    private lateinit var btnNext : Button
    private lateinit var auth : FirebaseAuth


    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        etRegisterEmailAddress = findViewById(R.id.etRegisterEmailAddress)
        etPassword = findViewById(R.id.etPassword)

        auth = FirebaseAuth.getInstance()


        tvLogin = findViewById(R.id.tvLogin)
        tvLogin.setOnClickListener {
            startActivity(Intent(this@Register, Login::class.java))
            finish()
        }


        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Register"

        btnNext = findViewById(R.id.btnNext)
        btnNext.setOnClickListener {
            doLogin()
        }
    }

    private fun doLogin() {
        if (etRegisterEmailAddress.text.toString().isEmpty()) {
            etRegisterEmailAddress.error = "Please enter email address"
            etRegisterEmailAddress.requestFocus()
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(etRegisterEmailAddress.text.toString()).matches()) {
            etRegisterEmailAddress.error = "Please enter a valid email address"
            etRegisterEmailAddress.requestFocus()
            return
        }

        if (etPassword.text.toString().isEmpty()) {
            etPassword.error = "Please enter password"
            etPassword.requestFocus()
            return
        }

        auth.createUserWithEmailAndPassword(etRegisterEmailAddress.text.toString(), etPassword.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                    val user = auth.currentUser
                    user?.sendEmailVerification()
                        ?.addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                startActivity(Intent(this@Register, Login::class.java))
                                finish()

                            }
                        }

                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(baseContext, "Registration failed. Try again after some time.",
                        Toast.LENGTH_SHORT).show()

                }
            }
    }


    override fun onOptionsItemSelected(item : MenuItem) : Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}