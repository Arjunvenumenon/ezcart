package com.example.ezcart.presentation.cart.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ezcart.data.db.entities.Cart
import com.example.ezcart.databinding.CartItemRowBinding
import java.util.*

class CartAdapter(
    var list: MutableList<Cart>,
    val context: Context,
    private val action: (String) -> Unit
): RecyclerView.Adapter<CartAdapter.ViewHolder>(), View.OnClickListener {

    private val descPrefix = " - "

    class ViewHolder(val binding: CartItemRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: CartItemRowBinding =
            CartItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val cartItem = list[position]

        holder.binding.itemNameTextView.text = cartItem.item_name
        holder.binding.itemDescription.text = "$descPrefix ${cartItem.desc}"
        holder.binding.itemAmount.text = cartItem.price.toString()

        val imageName = cartItem.item_name.lowercase(Locale.getDefault())
            .replace("\\s".toRegex(), "")
        val resources: Resources = context.resources
        val resourceId: Int = resources.getIdentifier(
            imageName, "drawable",
            context.packageName
        )

        holder.binding.productImage.setImageResource(resourceId)
        holder.binding.deleteIcon.tag = position
        holder.binding.deleteIcon.setOnClickListener(this)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onClick(v: View?) {
        val tag: Int = v?.tag as Int
        action(list[tag].item_name)
    }
}