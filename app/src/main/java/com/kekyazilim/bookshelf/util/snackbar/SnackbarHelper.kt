package com.kekyazilim.bookshelf.util.snackbar

import android.content.Context

import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import com.google.android.material.snackbar.Snackbar
import com.kekyazilim.bookshelf.android.R

class SnackbarHelper(context: Context) {

    private val SNACKBAR_MARGIN = 20
    private val SNACKBAR_ELEVATION = 6f
    private val mContext: Context =context


    /**
     * This method shows default snackbar with long duration
     *
     * @param view
     * @param message
     * @author ismailgungor
     */
    fun showBasicSnackbar(view: View, message: String) {
        val snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG)
        initializeView(snackbar)
        snackbar.show()
    }

    /**
     * This method initialize snackbar view in order to set background, margin and elevation for given snackbar as parameter
     *
     * @param snackbar
     * @author ismailgungor
     */
    private fun initializeView(snackbar: Snackbar) {
        addMargins(snackbar)
        setRoundBordersBg(snackbar)
        ViewCompat.setElevation(snackbar.view, SNACKBAR_ELEVATION)
    }

    /**
     * This method set margin for given snackbar as parameter
     *
     * @param snackbar
     * @author ismailgungor
     */
    private fun addMargins(snackbar: Snackbar) {
        val params = snackbar.view.layoutParams as ViewGroup.MarginLayoutParams
        params.setMargins(SNACKBAR_MARGIN, SNACKBAR_MARGIN, SNACKBAR_MARGIN, SNACKBAR_MARGIN)
        snackbar.view.layoutParams = params
    }

    /**
     * This method set background for given snackbar as parameter
     *
     * @param snackbar
     * @author ismailgungor
     */
    private fun setRoundBordersBg(snackbar: Snackbar) {
        snackbar.view.background = ContextCompat.getDrawable(mContext, R.drawable.default_snackbar_bg)

    }


}