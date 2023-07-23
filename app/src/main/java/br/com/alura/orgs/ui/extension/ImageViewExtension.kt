package br.com.alura.orgs.ui.extension

import android.widget.ImageView
import br.com.alura.orgs.R
import coil.load

fun ImageView.loadExternalImage(imageUrl: String? = null) {
    load(imageUrl) {
        error(R.drawable.erro)
        fallback(R.drawable.erro)
        placeholder(R.drawable.placeholder)
    }
}