package br.com.alura.orgs.ui.activity

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import br.com.alura.orgs.databinding.ActivityFormularioProdutoBinding
import br.com.alura.orgs.ui.configuration.AppDatabase
import br.com.alura.orgs.ui.constant.EntityConstants
import br.com.alura.orgs.ui.dao.ProdutoItemDAO
import br.com.alura.orgs.ui.dialog.FormularioImagemDialog
import br.com.alura.orgs.ui.extension.loadExternalImage
import br.com.alura.orgs.ui.model.ProdutoItem

class FormularioProdutoActivity : AppCompatActivity() {

    private val binding: ActivityFormularioProdutoBinding by lazy {
        ActivityFormularioProdutoBinding.inflate(layoutInflater)
    }

    private val produtoItemDAO: ProdutoItemDAO by lazy {
        AppDatabase.getInstance(this).produtoItemDao()
    }

    private var produtoItemId: Long = 0L

    private var url: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        produtoItemId = intent.getLongExtra(EntityConstants.PRODUTO_ITEM_KEY, 0L)

        binding.formularioProdutoImagem.setOnClickListener {
            FormularioImagemDialog(this).show(url) {
                url = it
                binding.formularioProdutoImagem.loadExternalImage(url)
            }
        }

        binding.activityFormularioProdutoBotaoSalvar.setOnClickListener {
            val newProdutoItem = ProdutoItem(
                id = produtoItemId,
                nome = binding.formularioProdutoNome.text.toString(),
                descricao = binding.formularioProdutoDescricao.text.toString(),
                valor = binding.formularioProdutoValor.text.toString().toDoubleOrNull() ?: 0.0,
                urlImagem = url
            )

            produtoItemDAO.save(newProdutoItem)

            finish()
        }

        setContentView(binding.root)
    }

    override fun onResume() {
        produtoItemDAO.findById(produtoItemId)?.let {
            fillFormFields(it)
        }

        super.onResume()
    }

    private fun fillFormFields(produtoItem: ProdutoItem) {
        url = produtoItem.urlImagem

        binding.apply {
            formularioProdutoNome.setText(produtoItem.nome)
            formularioProdutoDescricao.setText(produtoItem.descricao)
            formularioProdutoValor.setText(produtoItem.valor.toString())
            formularioProdutoImagem.loadExternalImage(produtoItem.urlImagem)
        }
    }
}