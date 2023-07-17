package br.com.alura.orgs.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.room.Room
import br.com.alura.orgs.R
import br.com.alura.orgs.databinding.ActivityFormularioProdutoBinding
import br.com.alura.orgs.ui.configuration.AppDatabase
import br.com.alura.orgs.ui.dao.ProdutoItemDAO
import br.com.alura.orgs.ui.dialog.FormularioImagemDialog
import br.com.alura.orgs.ui.extension.loadExternalImage
import br.com.alura.orgs.ui.model.ProdutoItem

class FormularioProdutoActivity : AppCompatActivity() {

    private val binding: ActivityFormularioProdutoBinding by lazy {
        ActivityFormularioProdutoBinding.inflate(layoutInflater)
    }

    private val database by lazy {
        Room.databaseBuilder(
            this,
            AppDatabase::class.java,
            "app_database"
        ).allowMainThreadQueries()
        .build()
    }

    private val produtoItemDAO: ProdutoItemDAO by lazy {
        database.produtoItemDao()
    }

    private var url: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.formularioProdutoImagem.setOnClickListener {
            FormularioImagemDialog(this).show(url) {
                url = it
                binding.formularioProdutoImagem.loadExternalImage(url)
            }
        }

        binding.activityFormularioProdutoBotaoSalvar.setOnClickListener {
            val newProdutoItem = ProdutoItem(
                nome = binding.formularioProdutoNome.text.toString(),
                descricao = binding.formularioProdutoDescricao.text.toString(),
                valor = binding.formularioProdutoValor.text.toString().toDoubleOrNull() ?: 0.0,
                urlImagem = url
            )

            produtoItemDAO.insertAll(newProdutoItem)

            finish()
        }

        setContentView(binding.root)
    }
}