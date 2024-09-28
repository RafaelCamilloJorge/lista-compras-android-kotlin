package com.example.listadecompras.feature.shopping_items

import ShoppingItem
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.listadecompras.databinding.ListOfItemBinding
import com.example.listadecompras.presentation.ShoppingListOfList

class ShoppingItemAdapter(
    private val items: List<ShoppingItem>,
    private val onClick: (ShoppingItem) -> Unit
) : RecyclerView.Adapter<ShoppingItemAdapter.ShoppingItemViewHolder>() {

    private var shoppingListOfItem = items.toMutableList()

    inner class ShoppingItemViewHolder(val binding: ListOfItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingItemViewHolder {
        val binding = ListOfItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ShoppingItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ShoppingItemViewHolder, position: Int) {
        val item = shoppingListOfItem[position]
        with(holder.binding) {
            itemName.text = item.name
            itemQuatity.text = item.quantity.toString() + " " + item.unity.getName()
            itemImage.setImageResource(item.image)

            root.setOnClickListener {
                onClick(item)
            }

        }
    }

    override fun getItemCount(): Int = shoppingListOfItem.size

    fun updateList(newList: List<ShoppingItem>) {
        shoppingListOfItem.clear()
        shoppingListOfItem.addAll(newList.sortedBy { it.name.lowercase() })
        notifyDataSetChanged()
    }
}
