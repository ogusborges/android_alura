package br.com.alura.orgs.ui.activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import br.com.alura.orgs.R
import br.com.alura.orgs.databinding.ActivityDetalhesProdutoBinding
import br.com.alura.orgs.ui.configuration.AppDatabase
import br.com.alura.orgs.ui.constant.EntityConstants
import br.com.alura.orgs.ui.extension.loadExternalImage
import br.com.alura.orgs.ui.model.ProdutoItem
import br.com.alura.orgs.ui.util.TextFormatUtil
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlin.properties.Delegates

class DetalhesProdutoActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityDetalhesProdutoBinding.inflate(layoutInflater)
    }

    private val produtoItemDAO by lazy {
        AppDatabase.getInstance(this).produtoItemDao()
    }

    private var produtoItemId: Long = 0L

    private var produtoItem: ProdutoItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        produtoItemId = intent.getLongExtra(EntityConstants.PRODUTO_ITEM_KEY, 0L)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                when(produtoItemId) {
                    0L -> finish()

                    else -> {
                        produtoItemDAO.findById(produtoItemId).collect {
                            produtoItem = it

                            produtoItem?.let {
                                fillProductDetailFields(it)
                            }
                        }
                    }
                }
            }
        }

        setContentView(binding.root)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_detalhes_produto, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.detalhes_produto_editar -> {
                startActivity(
                    Intent(this, FormularioProdutoActivity::class.java)
                        .putExtra(EntityConstants.PRODUTO_ITEM_KEY, produtoItemId)
                )
            }

            R.id.detalhes_produto_remover -> {
                lifecycleScope.launch {
                    produtoItem?.let {
                        produtoItemDAO.delete(it)
                    }

                    finish()
                }
            }

            else -> {}
        }

        return super.onOptionsItemSelected(item)
    }

    private fun fillProductDetailFields(produtoItem: ProdutoItem) {
        binding.apply {
            detalhesProdutoImagem.loadExternalImage(produtoItem.urlImagem)
            detalhesProdutoNome.text = produtoItem.nome
            detalhesProdutoDescricao.text = produtoItem.descricao
            detalhesProdutoValor.text = TextFormatUtil.formatarMoeda(produtoItem.valor)
        }
    }
}