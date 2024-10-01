package com.example.listadecompras.feature.shopping_lists

import android.content.Context
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.listadecompras.databinding.ListOfListBinding
import com.example.listadecompras.presentation.ShoppingListOfList
import java.io.File

class ShoppingListAdapter(
    private val items: List<ShoppingListOfList>,
    private val onClick: (ShoppingListOfList) -> Unit,
    private val onLongClick: (ShoppingListOfList) -> Unit
) : RecyclerView.Adapter<ShoppingListAdapter.ShoppingListViewHolder>() {

    private var shoppingListOfList = items.toMutableList()


    inner class ShoppingListViewHolder(val binding: ListOfListBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingListViewHolder {
        val binding = ListOfListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ShoppingListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ShoppingListViewHolder, position: Int) {
        val item = shoppingListOfList[position]
        with(holder.binding) {
            if (item.image != null) {
                loadImageWithGlideIfExist(
                    item.image!!,
                    holder.binding.root.context,
                    holder.binding.itemImage
                )
            } else {
                Glide.with(holder.binding.root.context).clear(this.itemImage)
            }
            itemTitle.text = item.name

            root.setOnClickListener {
                onClick(item)
            }
            root.setOnLongClickListener {
                onLongClick(item)
                true
            }
        }
    }

    override fun getItemCount(): Int = shoppingListOfList.size

    fun updateList(newList: List<ShoppingListOfList>) {
        shoppingListOfList.clear()
        shoppingListOfList.addAll(newList.sortedBy { it.name.lowercase() })
        notifyDataSetChanged()
    }

    private fun loadImageWithGlideIfExist(image: String, context: Context, imageView: ImageView) {
        Glide.with(context)
            .load(image)
            .centerCrop()
            .placeholder(android.R.drawable.ic_menu_report_image)
            .into(imageView)
    }
}