package lclark.mapplication;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.BaseColumns;

/**
 * Created by larspmayrand on 4/3/16.
 */
public class User implements BaseColumns, Parcelable {

    private String name;
    private int idNumber;

    public static final String COLUMN_NAME = "name";
    public static final String TABLE_NAME = "users";
    public static final String COLUMN_USERID = "user_ID";

    public static final String CREATE_TABLE = "CREATE TABLE" + TABLE_NAME + " ( " +
            _ID + " TEXT PRIMARY KEY, " +
            COLUMN_NAME + " TEXT, ";

    protected User(Parcel source) {

    }

    protected GithubUser(Parcel in) {
        login = in.readString();
        id = in.readInt();
        avatar_url = in.readString();
        gravatar_id = in.readString();
        url = in.readString();
        html_url = in.readString();
        followers_url = in.readString();
        following_url = in.readString();
        gists_url = in.readString();
        starred_url = in.readString();
        subscriptions_url = in.readString();
        organizations_url = in.readString();
        repos_url = in.readString();
        events_url = in.readString();
        received_events_url = in.readString();
        type = in.readString();
        site_admin = in.readByte() != 0;
    }

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
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, name);
        return contentValues;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(idNumber);
    }

//    public static final Creator<GithubUser> CREATOR = new Creator<GithubUser>() {
//        @Override
//        public GithubUser createFromParcel(Parcel in) {
//            return new GithubUser(in);
//        }
//
//        @Override
//        public GithubUser[] newArray(int size) {
//            return new GithubUser[size];
//        }
//    };

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[0];
        }
    }

}
