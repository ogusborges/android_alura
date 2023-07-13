package br.com.alura.orgs.ui.util

import java.text.NumberFormat
import java.util.Locale

class TextFormatUtil {
    companion object {
        fun formatarMoeda(valor: Double): String {
            val currencyFormatter = NumberFormat.getCurrencyInstance(
                Locale("pt", "br")
            )

            return currencyFormatter.format(valor)
        }
    }
}