package com.kekyazilim.bookshelf.util.intentprocess

import android.os.Bundle
import java.io.Serializable

class BundleArgumentsHelper {

    private var mArguments: Bundle? = null

    fun setArguments(arguments: Bundle?) {
        this.mArguments = arguments
    }

    fun getSerializableModel(key: String): Serializable? {
        return mArguments?.getSerializable(key)
    }


    fun hasKey(key: String): Boolean {

        mArguments?.let {
            return it.containsKey(key)
        } ?: return false
    }

    fun getString(key: String): String? {
        return mArguments?.getString(key)

    }

    fun getInt(key: String, defaultValue: Int): Int {
        mArguments?.let {
            return it.getInt(key, defaultValue)
        } ?: return defaultValue
    }


}