package com.kekyazilim.bookshelf.util.categoryprocess

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Before

import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.junit.Assert.*


@RunWith(AndroidJUnit4::class)
class CategoryFileManagerAndroidTest {

    private var mCategoryFileManager: CategoryFileManager? = null
    private var mCategoryTypeConverter: CategoryTypeConverter? = null
    var mContext: Context = ApplicationProvider.getApplicationContext()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        this.mCategoryFileManager = CategoryFileManager(mContext)
        this.mCategoryTypeConverter = CategoryTypeConverter()
    }

    @Test
    fun getConvertedCategoryListFromJsonArray_ShouldReturnTrue() {

        //Given
        val nameList = this.mCategoryFileManager?.loadStringCategoryNameArrayFromAsset()

        //When
        val actual = this.mCategoryTypeConverter?.getConvertedCategoryListFromJsonArray(nameList!!)

        //Then
        assert(actual?.isNotEmpty()!!)
        assert(actual[1].name == "Polisiye  Kitapları")
        assertEquals(70, actual.size)
    }

    @After
    fun tearDown() {
        this.mCategoryFileManager = null
        this.mCategoryTypeConverter = null
    }
}