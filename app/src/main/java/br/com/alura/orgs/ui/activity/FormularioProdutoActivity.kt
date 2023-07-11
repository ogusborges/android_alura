package br.com.alura.orgs.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import br.com.alura.orgs.R
import br.com.alura.orgs.databinding.ActivityFormularioProdutoBinding
import br.com.alura.orgs.ui.dao.ProdutoItemDAO
import br.com.alura.orgs.ui.model.ProdutoItem

class FormularioProdutoActivity : AppCompatActivity() {

    private val binding: ActivityFormularioProdutoBinding by lazy {
        ActivityFormularioProdutoBinding.inflate(layoutInflater)
    }

    private val produtoItemDAO: ProdutoItemDAO by lazy {
        ProdutoItemDAO()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.activityFormularioProdutoBotaoSalvar.setOnClickListener {
            val newProdutoItem = ProdutoItem(
                nome = binding.formularioProdutoNome.text.toString(),
                descricao = binding.formularioProdutoDescricao.text.toString(),
                valor = binding.formularioProdutoValor.text.toString().toDoubleOrNull() ?: 0.0
            )

            produtoItemDAO.add(newProdutoItem)

            Log.i("FormularioProduto", "onCreate: Produto criado $newProdutoItem")

            finish()
        }

        setContentView(binding.root)
    }
}