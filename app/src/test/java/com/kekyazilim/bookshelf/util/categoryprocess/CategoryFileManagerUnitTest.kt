package com.kekyazilim.bookshelf.util.categoryprocess

import com.kekyazilim.bookshelf.util.fakes.FakeBookCategoryList
import org.assertj.core.api.Assertions
import org.junit.After
import org.junit.Before
import org.junit.Test

class CategoryFileManagerUnitTest {

    private var mCategoryTypeConverter: CategoryTypeConverter? = null
    private var mFakeBookCategoryList: FakeBookCategoryList? = null

    @Before
    fun setUp() {
        this.mCategoryTypeConverter = CategoryTypeConverter()
        this.mFakeBookCategoryList = FakeBookCategoryList()
    }

    @Test
    fun getConvertedCategoryListFromJsonArray_ShouldReturnTrue() {
        //Given
        val jsonArray = this.mFakeBookCategoryList?.provideBookListAsJsonArray()

        //When
        val actual = this.mCategoryTypeConverter?.getConvertedCategoryListFromJsonArray(jsonArray!!)

        //Then
        Assertions.assertThat(actual)
            .isNotNull
            .isNotEmpty
            .hasSize(70)

        Assertions.assertThat(actual?.get(1)?.name)
            .isEqualTo("Polisiye  Kitapları")

    }

    @After
    fun tearDown() {
        this.mFakeBookCategoryList = null
        this.mCategoryTypeConverter = null
    }

}