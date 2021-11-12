package com.example.ezcart.presentation.home_screen.adapter

import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ezcart.data.model.ProductDetails
import com.example.ezcart.databinding.ItemListRowBinding
import java.util.*


class ProductListAdapter (
    var list: MutableList<ProductDetails>,
    val context: Context,
    private val action: (ProductDetails) -> Unit
): RecyclerView.Adapter<ProductListAdapter.ViewHolder>(), View.OnClickListener {


    class ViewHolder(val binding: ItemListRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemListRowBinding =
            ItemListRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]

        holder.binding.itemNameTextView.text = item.item_name
        holder.binding.itemDescription.text = item.desc
        holder.binding.itemAmount.text = item.price.toString()

        val imageName = item.item_name.lowercase(Locale.getDefault())
            .replace("\\s".toRegex(), "")
        val resources: Resources = context.resources
        val resourceId: Int = resources.getIdentifier(
           imageName, "drawable",
            context.packageName
        )

        holder.binding.productImage.setImageResource(resourceId)
        holder.binding.baseLayout.tag = position

        holder.binding.baseLayout.setOnClickListener(this)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onClick(v: View?) {
        val tag: Int = v?.tag as Int
        action(list[tag])
    }

}