package com.kekyazilim.bookshelf.util.categoryprocess

import android.content.Context
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.io.IOException
import java.nio.charset.Charset


/**
 * Responsible to manage file process for category table
 *
 * @author ismailgungor
 * @since 1.0
 */
class CategoryFileManager(context: Context) {

    private val mContext = context

    companion object {
        private const val FILE_NAME = "book-types.json"
        private const val CHAR_SET = "UTF-8"
    }

    /**
     * This method creates a new json array from assets as json file
     *
     * @author ismailgungor
     * @since 1.0
     */
    fun loadStringCategoryNameArrayFromAsset(): List<String>? {

        return try {
            val inputStream = mContext.assets.open(FILE_NAME)

            val size = inputStream.available()
            val buffer = ByteArray(size)

            inputStream.read(buffer)
            inputStream.close()


            GsonBuilder().create()
                .fromJson<List<String>>(
                    String(buffer, Charset.forName(CHAR_SET)),
                    object : TypeToken<List<String>>() {}.type
                )

        } catch (ex: IOException) {
            ex.printStackTrace()
            null
        }


    }


}