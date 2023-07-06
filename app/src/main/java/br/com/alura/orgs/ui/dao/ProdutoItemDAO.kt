package br.com.alura.orgs.ui.dao

import br.com.alura.orgs.ui.model.ProdutoItem

class ProdutoItemDAO {
    companion object {
        private val listaProdutos: MutableList<ProdutoItem> = mutableListOf()
    }

    fun findAll(): List<ProdutoItem> = listaProdutos.toList()

    fun add(produtoItem: ProdutoItem) {
        listaProdutos.add(produtoItem)
    }
}