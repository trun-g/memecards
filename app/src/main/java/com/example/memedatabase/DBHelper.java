package com.example.memedatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final String SQL_CREATE_CARDS =
            "CREATE TABLE " + DBContract.CardsSchema.TABLE_NAME + " (" +
                    DBContract.CardsSchema.COLUMN_NAME_NAME + " TEXT PRIMARY KEY," +
                    DBContract.CardsSchema.COLUMN_NAME_DESCRIPTION + " TEXT," +
                    DBContract.CardsSchema.COLUMN_NAME_IMGPATH + " TEXT," +
                    DBContract.CardsSchema.COLUMN_NAME_UPVOTES + " INTEGER," +
                    DBContract.CardsSchema.COLUMN_NAME_LOCKED + " INTEGER)";

    private static final String SQL_DELETE_CARDS =
            "DROP TABLE IF EXISTS " + DBContract.CardsSchema.TABLE_NAME;

    private static final String SQL_CREATE_BATTLEDECK =
            "CREATE TABLE " + DBContract.CardsSchema.TABLE_NAME + " (" +
                    DBContract.BattleDeckSchema.COLUMN_NAME_NAME + " TEXT PRIMARY KEY)";

    private static final String SQL_DELETE_BATTLEDECK =
            "DROP TABLE IF EXISTS " + DBContract.BattleDeckSchema.TABLE_NAME;

    private static final String SQL_CREATE_PLAYERSTATS =
            "CREATE TABLE " + DBContract.PlayerStatsSchema.TABLE_NAME + " (" +
                    DBContract.PlayerStatsSchema.COLUMN_NAME_CASH + " INTEGER PRIMARY KEY)";

    private static final String SQL_DELETE_PLAYERSTATS =
            "DROP TABLE IF EXISTS " + DBContract.PlayerStatsSchema.TABLE_NAME;

    public DBHelper(Context context) {
        super(context, DBContract.DB_NAME, null, DBContract.DB_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_CARDS);
        db.execSQL(SQL_CREATE_BATTLEDECK);
        db.execSQL(SQL_CREATE_PLAYERSTATS);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // just discard the data and start over if database is upgraded
        // should be good enough for now
        db.execSQL(SQL_DELETE_CARDS);
        db.execSQL(SQL_DELETE_BATTLEDECK);
        db.execSQL(SQL_DELETE_PLAYERSTATS);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

}