package com.example.listadecompras.feature.shopping_items

import ShoppingItem
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.listadecompras.R
import com.example.listadecompras.databinding.ListOfItemBinding


class ShoppingItemAdapter(
    private val items: List<ShoppingItem>,
    private val onLongClick: (ShoppingItem) -> Unit

) : RecyclerView.Adapter<ShoppingItemAdapter.ShoppingItemViewHolder>() {

    private var shoppingListOfItem = items.toMutableList()

    inner class ShoppingItemViewHolder(val binding: ListOfItemBinding) :
        RecyclerView.ViewHolder(binding.root)

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
            itemCheckbox.isChecked = item.marked

            if (item.marked) {
                root.setBackgroundColor(root.context.getColor(R.color.light_gray))
            } else {
                root.setBackgroundColor(root.context.getColor(android.R.color.transparent))
            }

            itemCheckbox.setOnClickListener {
                item.marked = itemCheckbox.isChecked

                updateList()
            }

            root.setOnLongClickListener {
                onLongClick(item)
                true
            }
        }
    }

    override fun getItemCount(): Int = shoppingListOfItem.size

    fun updateList() {
        shoppingListOfItem.clear()
        shoppingListOfItem.addAll(items.sortedWith(compareBy({ it.marked }, { it.name })))
        notifyDataSetChanged()
    }

    fun search(query: String) {
        shoppingListOfItem.clear()
        shoppingListOfItem.addAll(items.filter { it.name.startsWith(query, true) }
            .sortedBy { it.name })
        notifyDataSetChanged()
    }
}