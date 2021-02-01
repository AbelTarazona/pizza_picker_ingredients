package com.proyecto.pizzaingredient

import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.proyecto.pizzaingredient.databinding.ItemSizePizzaBinding

/**
 * Created by AbelTarazona on 16/01/2021
 */
class SizeAdapter(
    private val list: List<Size>,
    val onClick: (Size) -> Unit
) : RecyclerView.Adapter<SizeAdapter.Holder>() {

    var selectedItemPos = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = parent.inflate(R.layout.item_size_pizza)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        if (position == selectedItemPos) {
            holder.selected()
        } else {
            holder.notSelected()
        }


        val item = list[position]
        holder.bind(item)

        holder.itemView.setOnClickListener {
            selectedItemPos = position
            notifyDataSetChanged()
            onClick(item)
        }

    }

    override fun getItemCount(): Int = list.size

    fun setDefault() {
        selectedItemPos = 1
    }

    class Holder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding = ItemSizePizzaBinding.bind(view)

        fun bind(item: Size) {
            with(binding) {
                textView10.text = item.size
            }
        }

        fun selected() {
            binding.view.visibility = View.VISIBLE
            binding.textView10.setTextColor(
                ContextCompat.getColor(
                    itemView.context,
                    android.R.color.holo_red_dark
                )
            )
        }

        fun notSelected() {
            binding.view.visibility = View.INVISIBLE
            binding.textView10.setTextColor(ContextCompat.getColor(itemView.context, R.color.grey))
        }

    }


}