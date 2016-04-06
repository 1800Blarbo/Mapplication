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

    private String mTitle, mSnippet;
    private double mLNG, mLAT;
    private int mID;

    public static final String CREATE_TABLE = "CREATE_TABLE" + TABLE_NAME + " ( " +
            _ID + " TEXT PRIMARY KEY, " +
            COL_LNG + " TEXT, " +
            COL_LAT + " TEXT, " +
            COL_TITLE + " TEXT, " +
            COL_SNIPPET + " TEXT )";

    public Pin(int id, double lat, double lng, String title, String snippet) {
        mID = id;
        mLAT = lat;
        mLNG = lng;
        mTitle = title;
        mSnippet = snippet;
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
        return contentValues;
    }
}
