package com.example.core.recyclerview.decorator

import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.RectF
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.Interpolator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CirclePagerIndicatorDecoration() : RecyclerView.ItemDecoration() {

    private val colorActive = Color.BLACK
    private val colorDotsBg = Color.WHITE
    private val mIndicatorHeight = (DP * 32).toInt()
    private val mIndicatorStrokeWidth = DP * 4
    private val mIndicatorItemLength = DP * 3
    private val mIndicatorItemPadding = DP * 8
    private val mInterpolator: Interpolator = AccelerateDecelerateInterpolator()
    private val mPaint = Paint().apply {
        strokeWidth = mIndicatorStrokeWidth
        style = Paint.Style.STROKE
        isAntiAlias = true
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
        val itemCount = parent.adapter?.itemCount ?: 0

        // center horizontally, calculate width and subtract half from center
        val totalLength = mIndicatorItemLength * itemCount
        val paddingBetweenItems = Math.max(0, itemCount - 1) * mIndicatorItemPadding
        val indicatorTotalWidth = totalLength + paddingBetweenItems
        val indicatorStartX = (parent.width - indicatorTotalWidth) / 2f

        // center vertically in the allotted space
        val indicatorPosY = parent.height - mIndicatorHeight /2f * 3

        drawDotsBg(c, indicatorStartX, indicatorPosY, itemCount)

        // find active page (which should be highlighted)
        val layoutManager = parent.layoutManager as LinearLayoutManager?
        val activePosition = layoutManager!!.findFirstVisibleItemPosition()
        if (activePosition == RecyclerView.NO_POSITION) {
            return
        }

        drawInactiveIndicators(c, indicatorStartX, indicatorPosY, itemCount, activePosition)

        // find offset of active page (if the user is scrolling)
        val activeChild = layoutManager.findViewByPosition(activePosition)
        val left = activeChild!!.left
        val width = activeChild.width
        val right = activeChild.right

        // on swipe the active item will be positioned from [-width, 0]
        // interpolate offset for smooth animation
        val progress = mInterpolator.getInterpolation(left * -1 / width.toFloat())
        drawHighlights(c, indicatorStartX, indicatorPosY, activePosition, progress)
    }

    private fun drawInactiveIndicators(
        c: Canvas,
        indicatorStartX: Float,
        indicatorPosY: Float,
        itemCount: Int,
        activePosition: Int
    ) {
        mPaint.color = colorActive

        // width of item indicator including padding
        val itemWidth = mIndicatorItemLength + mIndicatorItemPadding
        var start = indicatorStartX
        for (i in 0 until itemCount) {
            mPaint.alpha = getAlpha(activePosition, i)
            c.drawCircle(start, indicatorPosY, mIndicatorItemLength / 2f, mPaint)
            start += itemWidth
        }
    }

    private fun getAlpha(activePosition: Int, inactivePosition: Int): Int {
        return if (activePosition == inactivePosition) 0
        else 255 / (kotlin.math.abs(activePosition - inactivePosition) * 2)
    }


    private fun drawHighlights(
        c: Canvas, indicatorStartX: Float, indicatorPosY: Float,
        highlightPosition: Int, progress: Float
    ) {
        mPaint.color = colorActive

        // width of item indicator including padding
        val itemWidth = mIndicatorItemLength + mIndicatorItemPadding
        if (progress == 0f) {
            // no swipe, draw a normal indicator
            val highlightStart = indicatorStartX + itemWidth * highlightPosition
            c.drawCircle(highlightStart, indicatorPosY, mIndicatorItemLength / 2f, mPaint)
        } else {
            val highlightStart = indicatorStartX + itemWidth * highlightPosition
            // calculate partial highlight
            val partialLength = mIndicatorItemLength * progress + mIndicatorItemPadding * progress
            c.drawCircle(
                highlightStart + partialLength,
                indicatorPosY,
                mIndicatorItemLength / 2f,
                mPaint
            )
        }
    }

    private fun drawDotsBg(
        c: Canvas,
        indicatorStartX: Float,
        indicatorPosY: Float,
        itemCount: Int
    ) {

        val itemWidth = mIndicatorItemLength + mIndicatorItemPadding
        val rect = RectF(
            indicatorStartX - itemWidth,
            indicatorPosY - itemWidth * 2 / 3,
            indicatorStartX + itemCount * itemWidth,
            indicatorPosY + itemWidth * 2 / 3
        )
        val paint = Paint().apply {
            color = colorDotsBg
            style = Paint.Style.FILL
        }
        val radius = DP * 5f
        c.drawRoundRect(rect, radius, radius, paint)
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.bottom = mIndicatorHeight
    }

    companion object {
        private val DP = Resources.getSystem().displayMetrics.density
    }
}