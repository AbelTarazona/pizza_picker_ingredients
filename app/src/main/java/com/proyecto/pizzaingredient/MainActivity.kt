package com.proyecto.pizzaingredient

import android.content.ClipData
import android.os.Bundle
import android.view.DragEvent
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import com.proyecto.pizzaingredient.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    private val adapter = SizeAdapter(getSizes()) { onClickSize(it) }
    private val adapterIngredient = IngredientAdapter(getIngredients())

    private lateinit var aniRotateClk: Animation

    var defaultScale = getSizes()[1].scale

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        aniRotateClk = AnimationUtils.loadAnimation(this, R.anim.rotation)

        binding.rvSizes.adapter = adapter
        adapter.setDefault()

        binding.rvIngredients.adapter = adapterIngredient
        binding.imageView5.setOnDragListener(onDragIngredienteListener)
    }

    private fun onClickSize(size: Size) {

        binding.imageView5.also {
            it.startAnimation(aniRotateClk)
            it.scaleX = size.scale
            it.scaleY = size.scale
            defaultScale = size.scale
        }

        binding.ingredients.also {
            it.scaleX = size.scale
            it.scaleY = size.scale
        }

        binding.textView9.text = "$ ${size.price}"

    }

    private val onDragIngredienteListener = View.OnDragListener { view, dragEvent ->
        (view as? ImageView)?.let {
            when (dragEvent.action) {
                // Once the drag event has started, we elevate all the views that are listening.
                // In our case, that's two of the areas.
                DragEvent.ACTION_DRAG_STARTED -> {
                    return@OnDragListener true
                }
                // Once the drag gesture enters a certain area, we want to elevate it even more.
                DragEvent.ACTION_DRAG_ENTERED -> {
                    val currentValX = defaultScale
                    val currentValY = defaultScale

                    binding.imageView5.also {
                        it.scaleX = currentValX + 0.1f
                        it.scaleY = currentValY + 0.1f
                    }

                    return@OnDragListener true
                }
                // No need to handle this for our use case.
                DragEvent.ACTION_DRAG_LOCATION -> {
                    return@OnDragListener true
                }
                // Once the drag gesture exits the area, we lower the elevation down to the previous one.
                DragEvent.ACTION_DRAG_EXITED -> {
                    binding.imageView5.also {
                        it.scaleX = defaultScale
                        it.scaleY = defaultScale
                    }
                    return@OnDragListener true
                }
                // Once the color is dropped on the area, we want to paint it in that color.
                DragEvent.ACTION_DROP -> {
                    val item: ClipData.Item = dragEvent.clipData.getItemAt(0)
                    val imagePosition = item.text.toString().toInt()
                    generateRandomIngrendiente(ingredientsList[imagePosition])
                    return@OnDragListener true
                }
                // Once the drag has ended, revert card views to the default elevation.
                DragEvent.ACTION_DRAG_ENDED -> {
                    binding.imageView5.also {
                        it.scaleX = defaultScale
                        it.scaleY = defaultScale
                    }
                    return@OnDragListener true
                }
                else -> return@OnDragListener false
            }
        }
        false
    }


    private fun generateRandomIngrendiente(@DrawableRes image: Int) {
        for (i in 0..5) {

            val lp = RelativeLayout.LayoutParams(90, 90)

            val imageView = ImageView(this) // initialize ImageView

            imageView.layoutParams = lp
            imageView.setImageResource(image)

            val location = IntArray(2)
            binding.ingredients.getLocationInWindow(location)


            imageView.animate().x((0..binding.ingredients.width).random().toFloat())
                .y((0..binding.ingredients.height).random().toFloat())
                .setDuration(4).start()

            binding.ingredients.addView(imageView)
        }
    }
}