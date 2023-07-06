package br.com.alura.orgs.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.alura.orgs.R
import br.com.alura.orgs.ui.adapter.ListaProdutosAdapter
import br.com.alura.orgs.ui.model.ProdutoItem
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity: AppCompatActivity(R.layout.activity_main) {
    private lateinit var listaProdutosRecyclerView: RecyclerView
    private lateinit var criarProdutoFloatingActionButton: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        criarProdutoFloatingActionButton = findViewById(R.id.criar_produto_floating_action_button)

        criarProdutoFloatingActionButton.setOnClickListener {
            startActivity(
                Intent(this, FormularioProdutoActivity::class.java)
            )
        }

        listaProdutosRecyclerView = findViewById(R.id.listaProdutos)

        listaProdutosRecyclerView.adapter = ListaProdutosAdapter(
            this,
            listOf(
                ProdutoItem(nome = "Teste 1", descricao = "Teste 1", valor = 12.99),
                ProdutoItem(nome = "Teste 2", descricao = "Teste 2", valor = 22.99),
                ProdutoItem(nome = "Teste 3", descricao = "Teste 3", valor = 32.99),
            )
        )

        listaProdutosRecyclerView.layoutManager = LinearLayoutManager(
            this,
            RecyclerView.VERTICAL,
            false
        )
    }
}