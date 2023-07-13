package br.com.alura.orgs.ui.dialog

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import br.com.alura.orgs.databinding.ActivityFormularioImagemBinding
import br.com.alura.orgs.ui.extension.loadExternalImage

class FormularioImagemDialog(private val context: Context) {
    private val inflater by lazy {
        LayoutInflater.from(context)
    }

    private val binding by lazy {
        ActivityFormularioImagemBinding.inflate(inflater)
    }

    fun show(defaultImageUrl: String? = null, success: (imagemUrl: String?) -> Unit) {
        binding.apply {
            defaultImageUrl?.let {
                formularioImagemUrl.setText(defaultImageUrl)
                formularioImagemImagem.loadExternalImage(defaultImageUrl)
            }

            formularioImagemBotaoCarregar.setOnClickListener {
                val imagemUrl = formularioImagemUrl.text.toString()

                formularioImagemImagem.loadExternalImage(imagemUrl)
            }

            AlertDialog.Builder(context)
                .setView(binding.root)
                .setPositiveButton("Confirmar") { _, _ ->
                    val imagemUrl = formularioImagemUrl.text.toString()
                    success(imagemUrl)
                }
                .setNegativeButton("Cancelar") { _, _ ->

                }
                .show()
        }
    }
}