package com.kekyazilim.bookshelf.util.activitytransitionprocess

import android.transition.Transition

interface TransitionCallback {

    fun onTransitionEnd(transition: Transition?) {
        //Silence is golden
    }

    fun onTransitionResume(transition: Transition?) {
        //Silence is golden
    }

    fun onTransitionPause(transition: Transition?) {
        //Silence is golden
    }

    fun onTransitionCancel(transition: Transition?) {
        //Silence is golden
    }

    fun onTransitionStart(transition: Transition?) {
        //Silence is golden
    }

}