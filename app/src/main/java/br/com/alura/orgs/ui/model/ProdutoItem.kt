package br.com.alura.orgs.ui.model

data class ProdutoItem(
    val nome: String,
    val descricao: String,
    val valor: Double,
    val urlImagem: String? = null,
)
