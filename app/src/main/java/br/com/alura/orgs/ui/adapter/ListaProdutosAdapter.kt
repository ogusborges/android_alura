package br.com.alura.orgs.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.alura.orgs.R
import br.com.alura.orgs.databinding.ProdutoItemBinding
import br.com.alura.orgs.ui.model.ProdutoItem

class ListaProdutosAdapter(
    private val context: Context,
    produtos: List<ProdutoItem>,
): RecyclerView.Adapter<ListaProdutosAdapter.ViewHolder>() {
    private var listaProdutos: MutableList<ProdutoItem> = produtos.toMutableList()

    class ViewHolder(private val binding: ProdutoItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(produtoItem: ProdutoItem) {
            binding.activityProdutoItemNome.text = produtoItem.nome
            binding.activityProdutoItemDescricao.text = produtoItem.descricao
            binding.activityProdutoItemValor.text = produtoItem.valor.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ProdutoItemBinding.inflate(LayoutInflater.from(context), parent, false)

        return ViewHolder(binding)
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