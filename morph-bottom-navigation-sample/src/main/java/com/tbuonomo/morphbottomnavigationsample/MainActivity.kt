package com.tbuonomo.morphbottomnavigationsample

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
  private var numVisibleChildren = 5


  companion object {
    private const val MAX_MENU_ITEMS = 5
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    initMenuItemsVisibility()

    drawDebugButton.setOnClickListener {
      val enable = !drawDebugButton.isActivated
      bottomNavigationView.drawDebug = enable
      drawDebugButton.isActivated = enable
      drawDebugButton.setBackgroundColor(if (enable) ContextCompat.getColor(this, R.color.colorPrimary) else Color.GRAY)
    }

    addItemButton.setOnClickListener {
      if (numVisibleChildren < MAX_MENU_ITEMS) {
        bottomNavigationView.menu.getItem(numVisibleChildren).isVisible = true
        numVisibleChildren++
      }
    }

    removeItemButton.setOnClickListener {
      if (numVisibleChildren > 1) {
        numVisibleChildren--
        bottomNavigationView.menu.getItem(numVisibleChildren).isVisible = false
      }
    }

    setUpSeekBars()
  }

  private fun initMenuItemsVisibility() {
    bottomNavigationView.morphItemRadius = 56.0F
    bottomNavigationView.morphCornerRadius = 14.0F
    bottomNavigationView.morphVerticalOffset = 46.0F
    for (i in 0 until bottomNavigationView.menu.size()) {
      bottomNavigationView.menu.getItem(i).isVisible = i < numVisibleChildren
    }
  }

  private fun setUpSeekBars() {
    morphItemRadiusValue.text = getString(R.string.value_dp, pxToDp(bottomNavigationView.morphItemRadius).toInt())
    morphCornerRadiusValue.text = getString(R.string.value_dp, pxToDp(bottomNavigationView.morphCornerRadius).toInt())
    morphVerticalOffsetValue.text = getString(R.string.value_dp, pxToDp(bottomNavigationView.morphVerticalOffset).toInt())

    val minItemRadius = dpToPx(8f)
    val maxItemRadius = dpToPx(128f)
    morphItemRadiusSeekBar.max = (maxItemRadius - minItemRadius).toInt()
    morphItemRadiusSeekBar.progress = (bottomNavigationView.morphItemRadius - minItemRadius).toInt()

    morphItemRadiusSeekBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
      override fun onProgressChanged(seekbar: SeekBar?, progress: Int, p2: Boolean) {
        val morphItemRadius = progress + minItemRadius
        Log.d("CHECK DATA ItemRadius", "=> $morphItemRadius")
        bottomNavigationView.morphItemRadius = 56.0F
        morphItemRadiusValue.text = getString(R.string.value_dp, pxToDp(morphItemRadius).toInt())
      }

      override fun onStartTrackingTouch(p0: SeekBar?) {
      }

      override fun onStopTrackingTouch(p0: SeekBar?) {
      }
    })

    val minCornerRadius = dpToPx(8f)
    val maxCornerRadius = dpToPx(128f)
    morphCornerRadiusSeekBar.max = (maxCornerRadius - minCornerRadius).toInt()
    morphCornerRadiusSeekBar.progress = (bottomNavigationView.morphCornerRadius - minCornerRadius).toInt()


    morphCornerRadiusSeekBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
      override fun onProgressChanged(seekbar: SeekBar?, progress: Int, p2: Boolean) {
        val morphCornerRadius = progress + minCornerRadius
        Log.d("CHECK DATA CORRADIUS", "=> $morphCornerRadius")
        bottomNavigationView.morphCornerRadius = 14.0F
        morphCornerRadiusValue.text = getString(R.string.value_dp, pxToDp(morphCornerRadius).toInt())
      }

      override fun onStartTrackingTouch(p0: SeekBar?) {
      }

      override fun onStopTrackingTouch(p0: SeekBar?) {
      }
    })

    val minVerticalOffset = dpToPx(2f)
    val maxVerticalOffset = dpToPx(32f)
    morphVerticalOffsetSeekBar.max = (maxVerticalOffset - minVerticalOffset).toInt()
    morphVerticalOffsetSeekBar.progress = (bottomNavigationView.morphVerticalOffset - minVerticalOffset).toInt()

    morphVerticalOffsetSeekBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
      override fun onProgressChanged(seekbar: SeekBar?, progress: Int, p2: Boolean) {
        val morphVerticalOffset = progress + minVerticalOffset
        Log.d("CHECK DATA Vertical", "=> $morphVerticalOffset")
        bottomNavigationView.morphVerticalOffset = 46.0F
        morphVerticalOffsetValue.text = getString(R.string.value_dp, pxToDp(morphVerticalOffset).toInt())
      }

      override fun onStartTrackingTouch(p0: SeekBar?) {
      }

      override fun onStopTrackingTouch(p0: SeekBar?) {
      }
    })
  }

  fun dpToPx(dp: Float): Float = resources.displayMetrics.density * dp
  fun pxToDp(px: Float): Float = px / resources.displayMetrics.density
}
