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

    private lateinit var produtoItemDAO: ProdutoItemDAO
    private lateinit var listaProdutosAdapter: ListaProdutosAdapter
    private lateinit var listaProdutosRecyclerView: RecyclerView
    private lateinit var criarProdutoFloatingActionButton: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        produtoItemDAO = ProdutoItemDAO()

        listaProdutosAdapter = ListaProdutosAdapter(this, produtoItemDAO.findAll())

        listaProdutosRecyclerView = findViewById(R.id.activity_main_lista_produtos)

        listaProdutosRecyclerView.apply {
            adapter = listaProdutosAdapter
            layoutManager = LinearLayoutManager(
                this@MainActivity,
                RecyclerView.VERTICAL,
                false
            )
        }

        criarProdutoFloatingActionButton = findViewById(R.id.activity_main_criar_produto_floating_action_button)

        criarProdutoFloatingActionButton.setOnClickListener {
            startActivity(
                Intent(this, FormularioProdutoActivity::class.java)
            )
        }
    }

    override fun onResume() {
        super.onResume()

        listaProdutosAdapter.updateAll(
            produtoItemDAO.findAll()
        )
    }
}