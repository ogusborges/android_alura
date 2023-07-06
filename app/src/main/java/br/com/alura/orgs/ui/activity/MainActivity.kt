package br.com.alura.orgs.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.alura.orgs.R
import br.com.alura.orgs.databinding.ActivityMainBinding
import br.com.alura.orgs.ui.adapter.ListaProdutosAdapter
import br.com.alura.orgs.ui.dao.ProdutoItemDAO
import br.com.alura.orgs.ui.model.ProdutoItem
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity: AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val produtoItemDAO: ProdutoItemDAO by lazy {
        ProdutoItemDAO()
    }

    private val listaProdutosAdapter by lazy {
        ListaProdutosAdapter(this, listOf())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.activityMainListaProdutos.apply {
            adapter = listaProdutosAdapter

            layoutManager = LinearLayoutManager(
                this@MainActivity,
                RecyclerView.VERTICAL,
                false
            )
        }

        binding.activityMainCriarProdutoFloatingActionButton.setOnClickListener {
            startActivity(
                Intent(this, FormularioProdutoActivity::class.java)
            )
        }

        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()

        listaProdutosAdapter.updateAll(
            produtoItemDAO.findAll()
        )
    }
}