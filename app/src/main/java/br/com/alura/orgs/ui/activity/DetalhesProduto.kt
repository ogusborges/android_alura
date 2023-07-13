package br.com.alura.orgs.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.alura.orgs.R
import br.com.alura.orgs.databinding.ActivityDetalhesProdutoBinding

class DetalhesProduto : AppCompatActivity() {
    private val binding by lazy {
        ActivityDetalhesProdutoBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}