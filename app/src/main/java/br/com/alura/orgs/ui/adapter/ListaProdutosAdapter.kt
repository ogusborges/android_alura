package br.com.alura.orgs.ui.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import br.com.alura.orgs.R
import br.com.alura.orgs.databinding.ProdutoItemBinding
import br.com.alura.orgs.ui.activity.DetalhesProdutoActivity
import br.com.alura.orgs.ui.activity.FormularioProdutoActivity
import br.com.alura.orgs.ui.constant.EntityConstants
import br.com.alura.orgs.ui.extension.loadExternalImage
import br.com.alura.orgs.ui.model.ProdutoItem
import br.com.alura.orgs.ui.util.TextFormatUtil.Companion.formatarMoeda
import kotlinx.coroutines.MainScope
import java.text.NumberFormat
import java.util.Locale

class ListaProdutosAdapter(
    private val context: Context,
    produtos: List<ProdutoItem>,
): RecyclerView.Adapter<ListaProdutosAdapter.ViewHolder>() {
    private var listaProdutos: MutableList<ProdutoItem> = produtos.toMutableList()

    inner class ViewHolder(private val binding: ProdutoItemBinding): RecyclerView.ViewHolder(binding.root) {
        private val menu by lazy {
            PopupMenu(context, binding.root)
        }

        fun bind(produtoItem: ProdutoItem) {
            binding.apply {
                activityProdutoItemNome.text = produtoItem.nome
                activityProdutoItemDescricao.text = produtoItem.descricao
                activityProdutoItemValor.text = formatarMoeda(produtoItem.valor)
                activityProdutoItemImagem.loadExternalImage(produtoItem.urlImagem)
            }
            Log.i("recyclerView", "$produtoItem")
            menu.setOnMenuItemClickListener {
                when(it.itemId) {
                    R.id.card_produto_item_editar -> {
                        context.startActivity(
                            Intent(context, FormularioProdutoActivity::class.java)
                                .putExtra(EntityConstants.PRODUTO_ITEM_KEY, produtoItem.id)
                        )
                    }

                    R.id.card_produto_item_remover -> {

                    }

                    else -> {}
                }

                true
            }

            if(menu.menu.size() == 0) {
                menu.inflate(R.menu.menu_card_produto_item)
            }

            binding.root.setOnLongClickListener {
                menu.show()
                true
            }

            binding.root.setOnClickListener {
                context.startActivity(
                    Intent(context, DetalhesProdutoActivity::class.java)
                        .putExtra(EntityConstants.PRODUTO_ITEM_KEY, produtoItem.id)
                )
            }
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