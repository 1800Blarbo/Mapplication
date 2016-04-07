package lclark.mapplication;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by larspmayrand on 4/1/16.
 */
public class MainActivity extends AppCompatActivity implements AddPinDialogFragment.PinCreatedListener, LoginFragment.SQLiteListener {

    private UserSQLiteHelper mUserSQLiteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.activity_main_framelayout, new LoginFragment());
                transaction.commit();
        mUserSQLiteHelper = UserSQLiteHelper.getInstance(getApplicationContext());
    }

    @Override
    public void onUserCreated(User user) {
        Log.d(getClass().getSimpleName(), "Created -- " + user.toString());
        mUserSQLiteHelper.insertUser(user);
        //mUserSQLiteHelper.getCSClassForStudents();
    }

    @Override
    public boolean userExists(String user) {
        return mUserSQLiteHelper.getAllUsernames().contains(user);
    }

    @Override
    public void onPinCreated(Pin pin) {
        Log.d(getClass().getSimpleName(), "Created -- " + pin.toString());
        mUserSQLiteHelper.insertPin(pin);
    }

}
