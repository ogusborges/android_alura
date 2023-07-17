package br.com.alura.orgs.ui.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.com.alura.orgs.ui.model.ProdutoItem
@Dao
interface ProdutoItemDAO {
    @Query("SELECT * FROM produto_item")
    fun findAll(): List<ProdutoItem>

    @Insert
    fun insertAll(vararg produtoItens: ProdutoItem)

    @Delete
    fun delete(vararg produtoItens: ProdutoItem)

    @Update
    fun update(vararg produtoItens: ProdutoItem)
}

//class ProdutoItemDAO {
//    companion object {
//        private val listaProdutos: MutableList<ProdutoItem> = mutableListOf()
//    }
//
//    fun findAll(): List<ProdutoItem> = listaProdutos.toList()
//
//    fun add(produtoItem: ProdutoItem) {
//        listaProdutos.add(produtoItem)
//    }
//}