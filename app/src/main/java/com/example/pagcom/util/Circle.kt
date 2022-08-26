package com.example.pagcom.util

import android.content.Context
import android.graphics.drawable.Drawable
import com.example.pagcom.R
import android.graphics.drawable.GradientDrawable
import android.util.Log
import com.example.pagcom.util.Circle
import java.util.*

object Circle {
    fun getRandomCircle(context: Context, position: Int): Drawable {
        val drawable = context.resources.getDrawable(R.drawable.round)
        val shapeDrawable = drawable as GradientDrawable
        val colors = context.resources.getIntArray(R.array.colors)
        val times = times(position)
        Log.e("TAG", "times $times position$position")
        shapeDrawable.setColor(colors[position - times * 15])
        return shapeDrawable
    }

    fun getColor(context: Context): Int {
        val colors = context.resources.getIntArray(R.array.colors)
        val random = Random()
        return colors[random.nextInt(colors.size - 1)]
    }

    operator fun times(number: Int): Int {
        var number = number
        if (number == 0) {
            return 0
        }
        var i = 0
        while (true) {
            if (number < 15) {
                return if (i == 0) 0 else i
            }
            number = number - 15
            i++
        }
    }
}