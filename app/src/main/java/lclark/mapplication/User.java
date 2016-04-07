package lclark.mapplication;

import android.content.ContentValues;
import android.provider.BaseColumns;

/**
 * Created by larspmayrand on 4/3/16.
 */
public class User implements BaseColumns {

    private String name;
    private int idNumber;

    public static final String COLUMN_NAME = "name";
    public static final String TABLE_NAME = "users";
    public static final String COLUMN_USERID = "user_ID";

    public static final String CREATE_TABLE = "CREATE TABLE" + TABLE_NAME + " ( " +
            _ID + " TEXT PRIMARY KEY, " +
            COLUMN_NAME + " TEXT, ";

    public String getName() {
        return name;
    }

    public int getIdNumber() {
        return idNumber;
    }

    public User(int idNumber, String name) {
        this.idNumber = idNumber;
        this.name = name;
    }

    public User(String name) {
        this.name = name;
    }

    public ContentValues getContentValues() {
        android.content.ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, name);
        return contentValues;
    }

}
