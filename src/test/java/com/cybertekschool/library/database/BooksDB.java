package com.cybertekschool.library.database;

import com.cybertekschool.library.utils.db.DBUtils;

import java.util.Map;

public class BooksDB {

    public Map<String, Object > getBookInfoFromDB(String bookName){
        String sql = "SELECT b.isbn, b.year, b.author, bc.name, b.description\n" +
                "FROM books b\n" +
                "JOIN book_categories bc\n" +
                "ON b.book_category_id = bc.id\n" +
                "WHERE b.name = '" + bookName + "';";
        return DBUtils.getRowMap(sql);
    }
}
