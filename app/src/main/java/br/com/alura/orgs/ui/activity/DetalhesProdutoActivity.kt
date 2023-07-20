package br.com.alura.orgs.ui.activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import br.com.alura.orgs.R
import br.com.alura.orgs.databinding.ActivityDetalhesProdutoBinding
import br.com.alura.orgs.ui.configuration.AppDatabase
import br.com.alura.orgs.ui.constant.EntityConstants
import br.com.alura.orgs.ui.extension.loadExternalImage
import br.com.alura.orgs.ui.model.ProdutoItem
import br.com.alura.orgs.ui.util.TextFormatUtil
import kotlin.properties.Delegates

class DetalhesProdutoActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityDetalhesProdutoBinding.inflate(layoutInflater)
    }

    private val produtoItemDAO by lazy {
        AppDatabase.getInstance(this).produtoItemDao()
    }

    private var produtoItemId: Long = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        produtoItemId = intent.getLongExtra(EntityConstants.PRODUTO_ITEM_KEY, 0L)

        if(produtoItemId == 0L) {
            finish()
        }

        setContentView(binding.root)
    }

    override fun onResume() {
        produtoItemDAO.findById(produtoItemId)?.let {
            fillProductDetailFields(it)
        }

        super.onResume()
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