package lclark.mapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by larspmayrand on 4/3/16.
 */
public class UserSQLiteHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "roster.db";
    private static UserSQLiteHelper sInstance;


    private UserSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    /**
     * Returns an instance of StudentSqliteHelper
     *
     * @param context : Must be Application Context
     * @return instance of StudentSqliteHelper
     */
    public static UserSQLiteHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new UserSQLiteHelper(context.getApplicationContext(), DB_NAME, null, 1);
        }
        return sInstance;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Pin.CREATE_TABLE);
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


    public long getCursorLong(Cursor cursor, String columnName) {
        return cursor.getLong(cursor.getColumnIndex(columnName));
    }


    public int getCursorInt(Cursor cursor, String columnName) {
        return cursor.getInt(cursor.getColumnIndex(columnName));
    }


    public ArrayList<User> getAllUsers() {

        ArrayList<User> users = new ArrayList<>();
        Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM " + User.TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                int id = getCursorInt(cursor, User._ID);
                String name = getCursorString(cursor, User.COLUMN_NAME);
                users.add(new User(id, name));
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
                String lat = getCursorString(cursor, Pin.COL_LNG);
                String lng = getCursorString(cursor, Pin.COL_LNG);
                String title = getCursorString(cursor, Pin.COL_TITLE);
                String snippet = getCursorString(cursor, Pin.COL_SNIPPET);
                pins.add(new Pin(id, lng, lat, title, snippet));
            } while (cursor.moveToNext());
        }

        cursor.close();
        return pins;
    }


    public void insertUser(User user) {
        getWritableDatabase().insert(User.TABLE_NAME, null, user.getContentValues());
    }
}