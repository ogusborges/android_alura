package br.com.alura.orgs.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.alura.orgs.ui.model.ProdutoItem

class ListaProdutosAdapter(
    private val context: Context,
    private val listaProdutos: ProdutoItem,
): RecyclerView.Adapter<ListaProdutosAdapter.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}