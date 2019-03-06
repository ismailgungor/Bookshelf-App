package com.kekyazilim.bookshelf.util.animationprocess

import android.animation.Animator

interface AnimationCallback {

    fun onAnimationRepeat(animation: Animator?) {
    }

    fun onAnimationEnd(animation: Animator?) {
    }

    fun onAnimationCancel(animation: Animator?) {
    }

    fun onAnimationStart(animation: Animator?) {
    }

}