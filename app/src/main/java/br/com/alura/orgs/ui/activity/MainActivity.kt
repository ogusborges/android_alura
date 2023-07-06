package br.com.alura.orgs.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.alura.orgs.R
import br.com.alura.orgs.ui.adapter.ListaProdutosAdapter
import br.com.alura.orgs.ui.dao.ProdutoItemDAO
import br.com.alura.orgs.ui.model.ProdutoItem
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity: AppCompatActivity(R.layout.activity_main) {

    private lateinit var listaProdutosRecyclerView: RecyclerView
    private lateinit var criarProdutoFloatingActionButton: FloatingActionButton
    private lateinit var produtoItemDAO: ProdutoItemDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        listaProdutosRecyclerView = findViewById(R.id.listaProdutos)

        criarProdutoFloatingActionButton = findViewById(R.id.criar_produto_floating_action_button)

        criarProdutoFloatingActionButton.setOnClickListener {
            startActivity(
                Intent(this, FormularioProdutoActivity::class.java)
            )
        }

        produtoItemDAO = ProdutoItemDAO()

        listaProdutosRecyclerView.layoutManager = LinearLayoutManager(
            this,
            RecyclerView.VERTICAL,
            false
        )
    }

    override fun onResume() {
        super.onResume()

        listaProdutosRecyclerView.adapter = ListaProdutosAdapter(
            this,
            produtoItemDAO.findAll()
        )
    }
}