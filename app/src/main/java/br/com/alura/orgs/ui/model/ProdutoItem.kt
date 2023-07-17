package br.com.alura.orgs.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class ProdutoItem(
    val nome: String,
    val descricao: String,
    val valor: Double,
    val urlImagem: String? = null,
): Parcelable
