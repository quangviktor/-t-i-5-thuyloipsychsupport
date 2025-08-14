package com.example.bai15;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "MyStudentDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "sinhvien";
    private static final String COLUMN_MALOP = "malop";
    private static final String COLUMN_TENLOP = "tenlop";
    private static final String COLUMN_SISO = "siso";

    public MyDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_MALOP + " TEXT PRIMARY KEY, " +
                COLUMN_TENLOP + " TEXT, " +
                COLUMN_SISO + " INTEGER)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Các phương thức INSERT, UPDATE, DELETE, QUERY sẽ được viết trong MainActivity
    // hoặc bạn có thể viết ở đây để quản lý code tốt hơn.
}
