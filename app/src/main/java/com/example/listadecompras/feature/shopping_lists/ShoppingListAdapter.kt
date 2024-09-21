package com.example.listadecompras.feature.shopping_lists

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.listadecompras.databinding.ListOfListBinding
import com.example.listadecompras.presentation.ShoppingListOfList

class ShoppingListAdapter(
    private val items: List<ShoppingListOfList>,
    private val onClick: (ShoppingListOfList) -> Unit
) : RecyclerView.Adapter<ShoppingListAdapter.ShoppingListViewHolder>() {

    inner class ShoppingListViewHolder(val binding: ListOfListBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingListViewHolder {
        val binding = ListOfListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ShoppingListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ShoppingListViewHolder, position: Int) {
        val item = items[position]
        with(holder.binding) {
            itemImage.setImageResource(item.image)
            itemTitle.text = item.name

            root.setOnClickListener {
                onClick(item)
            }
        }
    }

    override fun getItemCount(): Int = items.size
}
