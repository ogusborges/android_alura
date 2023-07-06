package br.com.alura.orgs.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.alura.orgs.R
import br.com.alura.orgs.ui.model.ProdutoItem

class ListaProdutosAdapter(
    private val context: Context,
    produtos: List<ProdutoItem>,
): RecyclerView.Adapter<ListaProdutosAdapter.ViewHolder>() {
    private var listaProdutos: MutableList<ProdutoItem> = produtos.toMutableList()

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        fun bind(produtoItem: ProdutoItem) {
            val nomeTextView = itemView.findViewById<TextView>(R.id.activity_produto_item_nome)
            val descricaoTextView = itemView.findViewById<TextView>(R.id.activity_produto_item_descricao)
            val valorTextView = itemView.findViewById<TextView>(R.id.activity_produto_item_valor)

            nomeTextView.text = produtoItem.nome
            descricaoTextView.text = produtoItem.descricao
            valorTextView.text = produtoItem.valor.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.produto_item, parent, false)
        )
    }

    override fun getItemCount(): Int = listaProdutos.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val produto = listaProdutos[position]
        holder.bind(produto)
    }

    fun updateAll(newListaProdutos: List<ProdutoItem>) {
        listaProdutos = newListaProdutos.toMutableList()
        notifyDataSetChanged()
    }
}