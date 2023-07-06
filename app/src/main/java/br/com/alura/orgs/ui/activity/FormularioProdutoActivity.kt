package br.com.alura.orgs.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import br.com.alura.orgs.R
import br.com.alura.orgs.ui.dao.ProdutoItemDAO
import br.com.alura.orgs.ui.model.ProdutoItem

class FormularioProdutoActivity : AppCompatActivity(R.layout.activity_formulario_produto) {

    private lateinit var nomeEditText: EditText
    private lateinit var descricaoEditText: EditText
    private lateinit var valorEditText: EditText
    private lateinit var salvarProdutoButton: Button
    private lateinit var produtoItemDAO: ProdutoItemDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        nomeEditText = findViewById(R.id.nome)
        descricaoEditText = findViewById(R.id.descricao)
        valorEditText = findViewById(R.id.valor)
        salvarProdutoButton = findViewById(R.id.botao_salvar)

        produtoItemDAO = ProdutoItemDAO()

        salvarProdutoButton.setOnClickListener {
            val newProdutoItem = ProdutoItem(
                nome = nomeEditText.text.toString(),
                descricao = descricaoEditText.text.toString(),
                valor = valorEditText.text.toString().toDoubleOrNull() ?: 0.0
            )

            produtoItemDAO.add(newProdutoItem)

            Log.i("FormularioProduto", "onCreate: Produto criado $newProdutoItem")

            finish()
        }
    }
}