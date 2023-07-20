package br.com.alura.orgs.ui.configuration

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.alura.orgs.ui.dao.ProdutoItemDAO
import br.com.alura.orgs.ui.model.ProdutoItem

@Database(entities = [ProdutoItem::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun produtoItemDao(): ProdutoItemDAO

    companion object {
        @Volatile private var database: AppDatabase? = null
        private val name = "app_database"
        fun getInstance(context: Context): AppDatabase = database ?:
            Room.databaseBuilder(context, AppDatabase::class.java, name).build()
                .also { database = it }

    }
}

