package br.com.alura.orgs.ui.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import br.com.alura.orgs.ui.model.ProdutoItem
import kotlinx.coroutines.flow.Flow

@Dao
interface ProdutoItemDAO {
    @Query("SELECT * FROM produto_item")
    fun findAll(): Flow<List<ProdutoItem>>

    @Query("SELECT * FROM produto_item WHERE id = :id")
    fun findById(id: Long): ProdutoItem?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(vararg produtoItens: ProdutoItem)

    @Delete
    fun delete(vararg produtoItens: ProdutoItem)

    @Update
    fun update(vararg produtoItens: ProdutoItem)
}