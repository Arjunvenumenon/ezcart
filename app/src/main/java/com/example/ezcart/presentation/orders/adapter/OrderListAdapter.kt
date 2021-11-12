package com.example.ezcart.presentation.orders.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ezcart.data.model.OrderDisplayModel
import com.example.ezcart.databinding.OrderListRowBinding
import com.example.ezcart.util.DOLLAR_SYMBOL
import com.example.ezcart.util.getDisplayDateFormat
import java.util.*


class OrderListAdapter (
    var list: MutableList<OrderDisplayModel>,
    val context: Context,
): RecyclerView.Adapter<OrderListAdapter.ViewHolder>() {


    class ViewHolder(val binding: OrderListRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: OrderListRowBinding =
            OrderListRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]

        holder.binding.orderDate.text = getDisplayDateFormat(item.date)
        holder.binding.orderAmount.text = "$DOLLAR_SYMBOL ${item.amount}"

        val imageName = item.productName.lowercase(Locale.getDefault())
            .replace("\\s".toRegex(), "")
        val resources: Resources = context.resources
        val resourceId: Int = resources.getIdentifier(
            imageName, "drawable",
            context.packageName
        )

        holder.binding.productImage.setImageResource(resourceId)

    }

    override fun getItemCount(): Int {
        return list.size
    }

}