package com.kekyazilim.bookshelf.util.animationprocess

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Build
import android.view.View
import android.view.ViewAnimationUtils

/**
 * Responsible to manage circular reveal animation process
 *
 * @since 1.0
 * @author ismailgungor
 */
class CircularRevealAnimationHelper {


    /**
     * This method create a new circular reveal animation
     *
     * @param view The View will be clipped to the animating circle.
     * @param centerX The x coordinate of the center of the animating circle, relative to view.
     * @param centerY The y coordinate of the center of the animating circle, relative to view.
     * @param startRadius The starting radius of the animating circle.
     * @param endRadius The ending radius of the animating circle.
     * @param animationCallback Notify parent class about animation process
     * @author ismailgungor
     * @since 1.0
     */
    fun createCircularRevealAnimation(
        view: View, centerX: Int, centerY: Int, startRadius: Float,
        finalRadius: Float, duration: Int?, animationCallback: AnimationCallback
    ) {
        var anim: Animator? = null
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            anim = ViewAnimationUtils.createCircularReveal(view, centerX, centerY, startRadius, finalRadius)
        }

        anim?.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)
                animationCallback.onAnimationEnd(animation)
            }
        })
        duration?.let {
            anim?.duration = it.toLong()
        }

        anim?.start()

    }

}