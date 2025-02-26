package com.kekyazilim.bookshelf.bookactivity

import android.animation.Animator
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.kekyazilim.bookshelf.app.AppModule
import com.kekyazilim.bookshelf.databaseprocess.AppRoomDatabaseModule
import com.kekyazilim.bookshelf.databaseprocess.table.CategoryTable
import com.kekyazilim.bookshelf.util.activitytransitionprocess.ActivityTransitionModule
import com.kekyazilim.bookshelf.util.animationprocess.AnimationCallback
import com.kekyazilim.bookshelf.util.animationprocess.AnimationModule
import com.kekyazilim.bookshelf.util.animationprocess.CircularRevealAnimationHelper
import com.kekyazilim.bookshelf.util.categoryprocess.CategoryProcessModule
import com.kekyazilim.bookshelf.util.categorytaglistview.CategoryTagListViewHelper
import com.kekyazilim.bookshelf.util.customdialog.customcategorylistdialog.CategoryListDialogHelper
import com.kekyazilim.bookshelf.util.windowprocess.WindowModule
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.github.clans.fab.FloatingActionButton
import com.kekyazilim.bookshelf.android.R
import com.kekyazilim.bookshelf.util.bookprocess.BookProcessModule
import com.kekyazilim.bookshelf.util.categorybookjoinprocess.CategoryBookJoinModule
import com.kekyazilim.bookshelf.util.snackbar.SnackbarHelper


class BookActivity : AppCompatActivity(), BookActivityContract.View {

    lateinit var myView: LinearLayout
    lateinit var mFab: FloatingActionButton
    lateinit var mainWrapper: RelativeLayout
    lateinit var tagLayout: LinearLayout
    lateinit var ivAddCategory: AppCompatImageView
    lateinit var etName: AppCompatEditText
    lateinit var etAuthor: AppCompatEditText
    lateinit var etPublishYear: AppCompatEditText
    lateinit var etPrice: AppCompatEditText
    lateinit var ratingBar: RatingBar
    lateinit var ivBack: AppCompatImageView
    lateinit var ivSave: AppCompatImageView
    lateinit var tvToolbarTitle: AppCompatTextView

    private var screenHeight = 0
    private var screenWidth = 0

    @Inject
    lateinit var mPresenter: BookActivityPresenter

    @Inject
    lateinit var mCircularAnimationHelper: CircularRevealAnimationHelper

    @Inject
    lateinit var mCategoryListDialogHelper: CategoryListDialogHelper

    @Inject
    lateinit var mCategoryTagListViewHelper: CategoryTagListViewHelper

    @Inject
    lateinit var mSnackbarHelper: SnackbarHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book)

        DaggerBookActivityComponent.builder()
            .appModule(AppModule(this))
            .animationModule(AnimationModule())
            .appRoomDatabaseModule(AppRoomDatabaseModule())
            .activityTransitionModule(ActivityTransitionModule(this))
            .windowModule(WindowModule(this))
            .categoryProcessModule(CategoryProcessModule())
            .categoryBookJoinModule(CategoryBookJoinModule())
            .bookProcessModule(BookProcessModule())
            .bookActivityModule(BookActivityModule())
            .build().inject(this)


        bindViews()
        initOnClicks()

        this.mPresenter.setView(this)
        this.mPresenter.created(intent)
    }

    override fun bindViews() {
        this.myView = findViewById(R.id.my_view)
        this.mFab = findViewById(R.id.fab_add_book)
        this.mainWrapper = findViewById(R.id.main_wrapper)
        this.tagLayout = findViewById(R.id.ll_categories)
        this.ivAddCategory = findViewById(R.id.iv_add_category)
        this.etName = findViewById(R.id.et_name)
        this.etAuthor = findViewById(R.id.et_author)
        this.etPublishYear = findViewById(R.id.et_publish_year)
        this.etPrice = findViewById(R.id.et_price)
        this.ratingBar = findViewById(R.id.rating_bar)
        this.ivBack = findViewById(R.id.iv_back)
        this.ivSave = findViewById(R.id.iv_save)
        this.tvToolbarTitle = findViewById(R.id.tv_toolbar_title)
    }

    private fun initOnClicks() {
        this.ivAddCategory.setOnClickListener {
            this.mPresenter.tagLayoutClicked()
        }

        this.ivBack.setOnClickListener {
            onBackPressed()
        }

        this.ivSave.setOnClickListener {
            this.mPresenter.saveClicked(
                etName,
                etAuthor,
                etPrice,
                etPublishYear,
                ratingBar,
                mCategoryTagListViewHelper.getChosenCategories()
            )
        }
    }

    override fun setToolbarTitleForEdit() {
        this.tvToolbarTitle.text = getString(R.string.edit_book)
    }

    override fun setNameArea(name: String) {
        this.etName.setText(name)
    }

    override fun setAuthorArea(author: String) {
        this.etAuthor.setText(author)
    }

    override fun setPublishYearArea(year: String) {
        this.etPublishYear.setText(year)
    }

    override fun setPriceArea(price: String) {
        this.etPrice.setText(price)
    }

    override fun setRateArea(rate: Float) {
        this.ratingBar.rating = rate
    }

    override fun onBackPressed() {
        this.mPresenter.backPressed()
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        this.mPresenter.windowFocusChanged(myView)
    }

    override fun setScreenHeight(height: Int) {
        this.screenHeight = height
    }

    override fun setScreenWidth(width: Int) {
        this.screenWidth = width
    }

    override fun showMainView() {
        this.myView.visibility = View.VISIBLE
    }

    override fun hideFabView() {
        this.mFab.visibility = View.GONE
    }

    override fun hideMainView() {
        this.myView.visibility = View.GONE
    }

    override fun showFabView() {
        this.mFab.visibility = View.VISIBLE
    }

    override fun startShowingCircularMainAnimation() {
        val finalRadius = Math.hypot(screenWidth.toDouble(), screenHeight.toDouble()).toFloat()
        this.mCircularAnimationHelper.createCircularRevealAnimation(myView,
            screenWidth / 2,
            screenHeight - (mFab.height / 2 + resources.getDimensionPixelOffset(R.dimen.fab_margin)),
            0f,
            finalRadius,
            400,
            object : AnimationCallback {
                override fun onAnimationEnd(animation: Animator?) {
                    mPresenter.removeTransitionListener()
                }
            })
    }

    override fun startHidingCircularFabAnimation() {
        val centerX = mFab.width / 2
        val centerY = mFab.height / 2
        val startRadius = Math.hypot(centerX.toDouble(), centerY.toDouble()).toFloat()
        this.mCircularAnimationHelper.createCircularRevealAnimation(mFab, centerX, centerY, startRadius, 0f,
            400, object : AnimationCallback {
                override fun onAnimationEnd(animation: Animator?) {
                    hideFabView()
                }
            })
    }

    override fun startHidingCircularMainAnimation() {
        val finalRadius = Math.hypot(screenWidth.toDouble(), screenHeight.toDouble()).toFloat()
        this.mCircularAnimationHelper.createCircularRevealAnimation(myView,
            screenWidth / 2,
            screenHeight - (mFab.height / 2 + resources.getDimensionPixelOffset(R.dimen.fab_margin)),
            finalRadius,
            0f,
            400,
            object : AnimationCallback {
                override fun onAnimationEnd(animation: Animator?) {
                    hideMainView()
                }

            })
    }

    override fun startShowingCircularFabAnimation() {
        showFabView()
        val centerX = mFab.width / 2
        val centerY = mFab.height / 2
        val finalRadius = Math.hypot(mFab.width.toDouble(), mFab.height.toDouble()).toFloat()
        this.mCircularAnimationHelper.createCircularRevealAnimation(mFab, centerX, centerY, 0f, finalRadius,
            400, object : AnimationCallback {
                override fun onAnimationEnd(animation: Animator?) {
                    supportFinishAfterTransition()
                }
            })
    }

    override fun showCategoryListDialog(
        clickEvent: PublishSubject<CategoryTable>,
        categoryList: ArrayList<CategoryTable>
    ) {
        this.mCategoryListDialogHelper.setCategoryList(categoryList)
            .setClickEvent(clickEvent)
            .prepareCustomDialog()
            .showCustomDialog()

    }

    override fun initCategoryTagView() {
        this.mCategoryTagListViewHelper.init(tagLayout, ivAddCategory)
    }

    override fun initCategoryTagViewWithList(list: List<CategoryTable>) {
        this.mCategoryTagListViewHelper.init(tagLayout, ivAddCategory, list as ArrayList<CategoryTable>)
    }

    override fun addCategoryTagView(it: CategoryTable) {
        this.mCategoryTagListViewHelper.addCategoryTag(it)
    }

    override fun hideDialog() {
        this.mCategoryListDialogHelper.dismissCustomDialog()
    }

    override fun showMissingNameMessage() {
        showSnackbar(getString(R.string.missing_name))
    }

    override fun showMissingAuthorMessage() {
        showSnackbar(getString(R.string.missing_author))
    }

    override fun showMissingPublishYearMessage() {
        showSnackbar(getString(R.string.missing_publish_year))
    }

    override fun showMissingPriceMessage() {
        showSnackbar(getString(R.string.missing_price))
    }

    override fun showMissingCategoryMessage() {
        showSnackbar(getString(R.string.missing_category))
    }

    private fun showSnackbar(message: String) {
        this.mSnackbarHelper.showBasicSnackbar(mainWrapper, message)
    }

    override fun finishBookActivity(intent: Intent) {
        setResult(Activity.RESULT_OK, intent)
    }
}