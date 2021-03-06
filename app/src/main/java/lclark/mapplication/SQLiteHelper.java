package lclark.mapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by larspmayrand on 4/3/16.
 */
public class SQLiteHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "roster.db";
    private static SQLiteHelper sInstance;

    private SQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    /**
     * Returns an instance of StudentSqliteHelper
     *
     * @param context : Must be Application Context
     * @return instance of StudentSqliteHelper
     */
    public static SQLiteHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new SQLiteHelper(context.getApplicationContext(), DB_NAME, null, 1);
        }
        return sInstance;
    }

//    public void initialize() {
//        SQLiteDatabase database = getWritableDatabase();
//        database.beginTransaction();
//
//        database.setTransactionSuccessful();
//        database.endTransaction();
//    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Pin.CREATE_TABLE); // CRASHING
        db.execSQL(User.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + User.TABLE_NAME);
        db.execSQL("DROP TABLE " + Pin.TABLE_NAME);
        onCreate(db);
    }

    public String getCursorString(Cursor cursor, String columnName) {
        return cursor.getString(cursor.getColumnIndex(columnName));
    }

    public double getCursorDouble(Cursor cursor, String columnName) {
        return cursor.getDouble(cursor.getColumnIndex(columnName));
    }

    public int getCursorInt(Cursor cursor, String columnName) {
        return cursor.getInt(cursor.getColumnIndex(columnName));
    }

    public ArrayList<Pin> getPinsForUser(int userID) {

        String sql = " SELECT " +
                Pin.TABLE_NAME + ".*" +
                " FROM " + Pin.TABLE_NAME +
                " WHERE " + Pin.COL_USERID + " = ?";

        Log.d("getPinsForUsers", sql);

        ArrayList<Pin> pins = new ArrayList<>();
        Cursor cursor = getReadableDatabase().rawQuery(sql, new String[]{String.valueOf(userID)});

        if (cursor.moveToFirst()) {
            do {
                int id = getCursorInt(cursor, Pin._ID);
                double lat = getCursorDouble(cursor, Pin.COL_LNG);
                double lng = getCursorDouble(cursor, Pin.COL_LNG);
                String title = getCursorString(cursor, Pin.COL_TITLE);
                String snippet = getCursorString(cursor, Pin.COL_SNIPPET);
                pins.add(new Pin(id, lat, lng, title, snippet, userID));
            } while (cursor.moveToNext());
        }
        cursor.close();

        return pins;
    }

    public ArrayList<String> getAllUsernames() {

        ArrayList<String> users = new ArrayList<>();
        Cursor cursor = getReadableDatabase().rawQuery(" SELECT * FROM " + User.TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                users.add(getCursorString(cursor, User.COLUMN_NAME));
            } while (cursor.moveToNext());
        }

        cursor.close();
        return users;
    }

    public ArrayList<Pin> getAllPins() {

        ArrayList<Pin> pins = new ArrayList<>();
        Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM " + Pin.TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                int id = getCursorInt(cursor, Pin._ID);
                double lat = getCursorDouble(cursor, Pin.COL_LNG);
                double lng = getCursorDouble(cursor, Pin.COL_LNG);
                String title = getCursorString(cursor, Pin.COL_TITLE);
                String snippet = getCursorString(cursor, Pin.COL_SNIPPET);
                int userID = getCursorInt(cursor, Pin.COL_USERID);
                pins.add(new Pin(id, lat, lng, title, snippet, userID));
            } while (cursor.moveToNext());
        }

        cursor.close();
        return pins;
    }

    public void insertUser(User user) {
        getWritableDatabase().insert(User.TABLE_NAME, null, user.getContentValues());
    }

    public void insertPin(Pin pin) {
        getWritableDatabase().insert(Pin.TABLE_NAME, null, pin.getContentValues());
    }

}