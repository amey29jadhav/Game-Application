package com.amey.gameapplication.utils

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.util.TypedValue
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

class SpacesItemDecoration(private val space: Int, color: Int, heightDp: Float, context: Context) :
    ItemDecoration() {
    private val mPaint: Paint = Paint()
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        //outRect.left = space;
        //outRect.right = space;
        outRect.bottom = space
        //outRect.top = space;
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        // we set the stroke width before, so as to correctly draw the line we have to offset by width / 2
        val offset = (mPaint.strokeWidth / 2).toInt()

        // this will iterate over every visible view
        for (i in 0 until parent.childCount) {
            // get the view
            val view = parent.getChildAt(i)
            val params =
                view.layoutParams as RecyclerView.LayoutParams

            // get the position
            val position = params.viewAdapterPosition

            // and finally draw the separator
            if (position < state.itemCount) {
                c.drawLine(
                    view.left.toFloat(),
                    view.bottom + offset.toFloat(),
                    view.right.toFloat(),
                    view.bottom + offset.toFloat(),
                    mPaint
                )
            }
        }
    }

    init {
        mPaint.color = color
        val thickness = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            heightDp, context.resources.displayMetrics
        )
        mPaint.strokeWidth = thickness
    }
}