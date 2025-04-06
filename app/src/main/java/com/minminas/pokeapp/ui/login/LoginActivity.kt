package com.minminas.pokeapp.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.minminas.pokeapp.databinding.ActivityLoginBinding
import com.minminas.pokeapp.ui.list.ListActivity

class LoginActivity : ComponentActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            val user = binding.etUsername.text.toString()
            val pass = binding.etPassword.text.toString()

            if (user.isNotEmpty() && pass.isNotEmpty()) {
                val intent = Intent(this, ListActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}
