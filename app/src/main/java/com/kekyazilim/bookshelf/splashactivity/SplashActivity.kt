package com.kekyazilim.bookshelf.splashactivity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kekyazilim.bookshelf.android.R
import com.kekyazilim.bookshelf.app.AppModule
import com.kekyazilim.bookshelf.databaseprocess.AppRoomDatabaseModule
import com.kekyazilim.bookshelf.util.categoryprocess.CategoryProcessModule
import com.kekyazilim.bookshelf.util.intentprocess.IntentModule
import javax.inject.Inject

class SplashActivity : AppCompatActivity(), SplashActivityContract.View {

    @Inject
    lateinit var mPresenter: SplashActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        DaggerSplashActivityComponent.builder()
            .appModule(AppModule(this))
            .appRoomDatabaseModule(AppRoomDatabaseModule())
            .categoryProcessModule(CategoryProcessModule())
            .intentModule(IntentModule(this))
            .build().inject(this)

        this.mPresenter.setView(this)
        this.mPresenter.created()

    }

    override fun finishActivity() {
        finish()
    }

    override fun showErrorMessage() {
        Toast.makeText(this, getString(R.string.unknown_error), Toast.LENGTH_LONG).show()
    }
}