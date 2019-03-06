package com.kekyazilim.bookshelf.util.categoryprocess

import com.kekyazilim.bookshelf.databaseprocess.table.CategoryTable
import org.json.JSONArray

/**
 * Responsible to manage converting process for related category models
 *
 * @author ismailgungor
 * @since 1.0
 */
class CategoryTypeConverter {


    /**
     * This method converts name list, which includes info for category table, to category table list
     *
     * @param nameList
     * @return categoryTableList as ArrayList<CategoryTable>
     * @see CategoryTable
     */
    fun getConvertedCategoryListFromJsonArray(nameList: List<String>): ArrayList<CategoryTable> {

        val categoryTableList = ArrayList<CategoryTable>()
        for (name in nameList)
            categoryTableList.add(CategoryTable(name))

        return categoryTableList
    }

}