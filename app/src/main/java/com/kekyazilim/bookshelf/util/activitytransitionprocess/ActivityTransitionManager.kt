package com.kekyazilim.bookshelf.util.activitytransitionprocess

import android.os.Build
import android.transition.Transition
import androidx.appcompat.app.AppCompatActivity


class ActivityTransitionManager(activity: AppCompatActivity) {

    private var mActivity = activity
    private var mTransitionListener: Transition.TransitionListener? = null
    private var mSharedElementEnterTransition: Transition? = null
    private var mTransitionCallback: TransitionCallback? = null

    init {
        initTransitionListener()
    }

    fun setEnterTransitionNull() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
           mActivity.window.enterTransition = null
        }
    }

    fun setSharedElementEnterTransition(transitionCallback: TransitionCallback) {
        this.mTransitionCallback = transitionCallback

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mSharedElementEnterTransition = mActivity.window.sharedElementEnterTransition
            mTransitionListener?.let {
                mSharedElementEnterTransition?.addListener(it)
            }
        }
    }

    fun removeTransitionListenerFromSharedElementEnterTransition() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            this.mSharedElementEnterTransition?.removeListener(this.mTransitionListener)
        }
    }

    private fun initTransitionListener() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

            this.mTransitionListener = object : Transition.TransitionListener {
                override fun onTransitionEnd(transition: Transition?) {
                    mTransitionCallback?.onTransitionEnd(transition)
                }

                override fun onTransitionResume(transition: Transition?) {
                    mTransitionCallback?.onTransitionResume(transition)
                }

                override fun onTransitionPause(transition: Transition?) {
                    mTransitionCallback?.onTransitionPause(transition)
                }

                override fun onTransitionCancel(transition: Transition?) {
                    mTransitionCallback?.onTransitionCancel(transition)
                }

                override fun onTransitionStart(transition: Transition?) {
                    mTransitionCallback?.onTransitionStart(transition)
                }

            }
        }
    }

}