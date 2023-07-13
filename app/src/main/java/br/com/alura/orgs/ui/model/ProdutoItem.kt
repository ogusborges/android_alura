package br.com.alura.orgs.ui.model

import android.os.Parcel
import android.os.Parcelable

class ProdutoItem(
    val nome: String,
    val descricao: String,
    val valor: Double,
    val urlImagem: String? = null,
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readDouble(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nome)
        parcel.writeString(descricao)
        parcel.writeDouble(valor)
        parcel.writeString(urlImagem)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ProdutoItem> {
        override fun createFromParcel(parcel: Parcel): ProdutoItem {
            return ProdutoItem(parcel)
        }

        override fun newArray(size: Int): Array<ProdutoItem?> {
            return arrayOfNulls(size)
        }
    }
}
