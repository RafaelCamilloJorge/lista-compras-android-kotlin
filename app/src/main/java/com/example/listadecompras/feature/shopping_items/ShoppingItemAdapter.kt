package com.example.listadecompras.feature.shopping_items

import ShoppingItem
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.listadecompras.databinding.ListOfItemBinding

class ShoppingItemAdapter(
    private val items: List<ShoppingItem>,
    private val onClick: (ShoppingItem) -> Unit
) : RecyclerView.Adapter<ShoppingItemAdapter.ShoppingItemViewHolder>() {

    inner class ShoppingItemViewHolder(val binding: ListOfItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingItemViewHolder {
        val binding = ListOfItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ShoppingItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ShoppingItemViewHolder, position: Int) {
        val item = items[position]
        with(holder.binding) {
            itemName.text = item.name
            itemQuatity.text = item.quantity.toString()
            itemImage.setImageResource(item.image)

            root.setOnClickListener {
                onClick(item)
            }

        }
    }

    override fun getItemCount(): Int = items.size
}
