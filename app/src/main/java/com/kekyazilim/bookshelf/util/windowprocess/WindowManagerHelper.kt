package com.kekyazilim.bookshelf.util.windowprocess

import android.graphics.Color
import android.os.Build
import android.util.DisplayMetrics
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity


/**
 * Responsible to manage window process
 *
 * @author ismailgungor
 * @since 1.0
 */
class WindowManagerHelper(activity: AppCompatActivity) {

    private var mActivity = activity

    /**
     * This method sets the status bar with a transparent background according to SDK version of device
     *
     * @author ismailgungor
     * @since 1.0
     */
    fun setTransparentStatusBar() {
        if (Build.VERSION.SDK_INT in 19..20) {
            setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true)
        }
        if (Build.VERSION.SDK_INT >= 19) {
            mActivity.window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false)
            mActivity.window.statusBarColor = Color.TRANSPARENT
        }

    }

    private fun setWindowFlag(bits: Int, on: Boolean) {
        val win = mActivity.window
        val winParams = win.attributes
        if (on) {
            winParams.flags = winParams.flags or bits
        } else {
            winParams.flags = winParams.flags and bits.inv()
        }
        win.attributes = winParams
    }

    /**
     * This method returns width of screen (not main layout width)
     *
     * @author ismailgungot
     * @since 1.0
     * @return screen width as Int
     */
    fun getScreenWidth(): Int {
        val displayMetrics = DisplayMetrics()
        mActivity.windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.widthPixels
    }

    /**
     * This method returns height of screen (not main layout height)
     *
     * @author ismailgungot
     * @since 1.0
     * @return screen height as Int
     */
    fun getScreenHeight(): Int {
        val displayMetrics = DisplayMetrics()
        mActivity.windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.heightPixels
    }

}