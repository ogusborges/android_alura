package br.com.alura.orgs.ui.activity

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.alura.orgs.databinding.ActivityDetalhesProdutoBinding
import br.com.alura.orgs.ui.extension.loadExternalImage
import br.com.alura.orgs.ui.model.ProdutoItem
import br.com.alura.orgs.ui.util.TextFormatUtil

class DetalhesProdutoActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityDetalhesProdutoBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val produtoItem = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("produto", ProdutoItem::class.java)
        } else {
            intent.getParcelableExtra("produto")
        }

        if(produtoItem == null) {
            finish()
            return
        }

        binding.apply {
            detalhesProdutoImagem.loadExternalImage(produtoItem.urlImagem)
            detalhesProdutoNome.text = produtoItem.nome
            detalhesProdutoDescricao.text = produtoItem.descricao
            detalhesProdutoValor.text = TextFormatUtil.formatarMoeda(produtoItem.valor)
        }

        setContentView(binding.root)
    }
}