package lclark.mapplication;

import android.content.ContentValues;
import android.provider.BaseColumns;

/**
 * Created by larspmayrand on 4/3/16.
 */
public class Pin implements BaseColumns {

    public static final String COL_LNG = "Longitude";
    public static final String COL_LAT = "Latitude";
    public static final String COL_TITLE = "Pin_Title";
    public static final String COL_SNIPPET = "Pin_Snippet";
    public static final String TABLE_NAME = "Pins";
    public static final String COL_USERID = "User_ID";

    private String mTitle, mSnippet;
    private int mID, mUserID;
    private double mLNG, mLAT;

    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ( " +
            _ID + " TEXT PRIMARY KEY, " +
            COL_LAT + " REAL, " +
            COL_LNG + " REAL, " +
            COL_TITLE + " TEXT, " +
            COL_SNIPPET + " TEXT, " + COL_USERID + " TEXT )";

//    "CREATE TABLE " + Student.TABLE_NAME + " ( " +
//    Student._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
//    Student.COL_NAME + " TEXT, " +
//    Student.COL_YEAR + " TEXT, " +
//    Student.COL_NET_WORTH + " BIGINT )"

    public Pin(int id, double lat, double lng, String title, String snippet, int userID) {
        mID = id;
        mLNG = lng;
        mLAT = lat;
        mTitle = title;
        mSnippet = snippet;
        mUserID = userID;
    }

    public Pin(String mTitle, String mSnippet, double mLAT, double mLNG) {
        this.mTitle = mTitle;
        this.mSnippet = mSnippet;
        this.mLAT = mLAT;
        this.mLNG = mLNG;
    }

    public int getmID() {
        return mID;
    }

    public double getmLNG() {
        return mLNG;
    }

    public double getmLAT() {
        return mLAT;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmSnippet() {
        return mSnippet;
    }

    public int getmUserID() {
        return mUserID;
    }

    @Override
    public String toString() {
        return mID + ":" + mLAT + ":" + mLNG + ":" + mTitle + ":" + mSnippet;
    }

    public ContentValues getContentValues() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_LAT, mLAT);
        contentValues.put(COL_LNG, mLNG);
        contentValues.put(COL_TITLE, mTitle);
        contentValues.put(COL_SNIPPET, mSnippet);
        contentValues.put(COL_USERID, mUserID);
        return contentValues;
    }

}
