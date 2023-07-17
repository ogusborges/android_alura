package br.com.alura.orgs.ui.configuration

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.alura.orgs.ui.dao.ProdutoItemDAO
import br.com.alura.orgs.ui.model.ProdutoItem

@Database(entities = [ProdutoItem::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun produtoItemDao(): ProdutoItemDAO
}