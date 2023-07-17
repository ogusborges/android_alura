package br.com.alura.orgs.ui.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "produto_item")
data class ProdutoItem(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val nome: String,
    val descricao: String,
    val valor: Double,
    @ColumnInfo(name = "url_imagem") val urlImagem: String? = null,
): Parcelable
