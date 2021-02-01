package com.proyecto.pizzaingredient

import android.content.ClipData
import android.content.ClipDescription
import android.os.Build
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.proyecto.pizzaingredient.databinding.ItemIngredientPizzaBinding

/**
 * Created by AbelTarazona on 16/01/2021
 */
class IngredientAdapter(
    val list: List<Ingredient>
) : RecyclerView.Adapter<IngredientAdapter.Holder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = parent.inflate(R.layout.item_ingredient_pizza)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = list[position]
        holder.bind(item)

        if (item.isSelected) {
            holder.selected()
        } else {
            holder.notSelected()
        }

        holder.itemView.setOnClickListener {
            item.isSelected = !item.isSelected
            notifyDataSetChanged()
        }

        holder.itemView.tag = position.toString()
        holder.itemView.setOnTouchListener(onTouchListener)


    }

    private val onTouchListener = View.OnTouchListener { view, event ->
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                (view as? ConstraintLayout)?.let {
                    val item = ClipData.Item(it.tag as? CharSequence)

                    val dragData = ClipData(
                        it.tag as? CharSequence,
                        arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN),
                        item
                    )
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        it.startDragAndDrop(dragData, View.DragShadowBuilder(it), null, 0)
                    } else {
                        it.startDrag(dragData, View.DragShadowBuilder(it), null, 0)
                    }
                }
                true
            }
            else -> false
        }
        view.performClick()
    }

    override fun getItemCount(): Int = list.size


    class Holder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding = ItemIngredientPizzaBinding.bind(view)

        fun bind(item: Ingredient) {
            with(binding) {
                imageView4.also {
                    it.tag = adapterPosition.toString()
                    it.setImageDrawable(ContextCompat.getDrawable(itemView.context, item.img))
                    //it.setOnTouchListener(onTouchListener)
                }
            }
        }

        fun selected() {
            binding.root.background =
                ContextCompat.getDrawable(itemView.context, R.drawable.circle_pizza_item_selected)
        }

        fun notSelected() {
            binding.root.background =
                ContextCompat.getDrawable(itemView.context, R.drawable.circle_pizza_item)
        }

        private val onTouchListener = View.OnTouchListener { view, event ->
            when (event?.action) {
                MotionEvent.ACTION_DOWN -> {
                    (view as? ImageView)?.let {
                        val item = ClipData.Item(it.tag as? CharSequence)

                        val dragData = ClipData(
                            it.tag as? CharSequence,
                            arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN),
                            item
                        )
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            it.startDragAndDrop(dragData, View.DragShadowBuilder(it), null, 0)
                        } else {
                            it.startDrag(dragData, View.DragShadowBuilder(it), null, 0)
                        }
                    }
                    true
                }
                else -> false
            }
            view.performClick()
        }

        private val onLongIngredienteClickListener = View.OnClickListener { view: View ->
            (view as? ImageView)?.let {
                val item = ClipData.Item(it.tag as? CharSequence)

                val dragData = ClipData(
                    it.tag as? CharSequence,
                    arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN),
                    item
                )
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    it.startDragAndDrop(dragData, View.DragShadowBuilder(it), null, 0)
                } else {
                    it.startDrag(dragData, View.DragShadowBuilder(it), null, 0)
                }


            }
        }

    }


}