package com.kekyazilim.bookshelf.databaseprocess

object DatabaseQueries {

    // Query for Category
    const val DELETE_ALL_CATEGORIES = "DELETE FROM categories"
    const val GET_ALL_CATEGORIES = "SELECT * FROM categories"
    const val GET_CATEGORY_COUNT = "SELECT COUNT(*) FROM categories"
    const val GET_CATEGORIES_FOR_BOOK = "SELECT * FROM categories " +
            "INNER JOIN category_book_join " +
            "ON categories.categoryId=category_book_join.categoryId " +
            "WHERE category_book_join.bookId=:bookId"

    // Query for Book
    const val GET_ALL_BOOKS = "SELECT * FROM books"
    const val GET_BOOKS_FOR_CATEGORY = "SELECT * FROM books " +
            "INNER JOIN category_book_join " +
            "ON books.bookId = category_book_join.bookId " +
            "WHERE category_book_join.categoryId=:categoryId"

    // Query for Category Book Join
    const val DELETE_ITEM_BY_BOOK_ID = "delete from category_book_join where bookId = :bookId"



}


