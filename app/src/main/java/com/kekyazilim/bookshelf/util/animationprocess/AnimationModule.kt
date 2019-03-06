package com.kekyazilim.bookshelf.util.animationprocess

import dagger.Module
import dagger.Provides

@Module
class AnimationModule {

    @Provides
    fun provideCircularRevealAnimationHelper(): CircularRevealAnimationHelper {
        return CircularRevealAnimationHelper()
    }
}