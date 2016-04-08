package lclark.mapplication;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by larspmayrand on 4/1/16.
 */
public class MainActivity extends AppCompatActivity implements AddPinDialogFragment.PinCreatedListener, LoginFragment.SQLiteListener, MapsFragment.DialogCallbackListener {

    private SQLiteHelper mSQLiteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.activity_main_framelayout, new LoginFragment());
                transaction.commit();
        mSQLiteHelper = SQLiteHelper.getInstance(getApplicationContext());
    }

    @Override
    public void onUserCreated(User user) {
        Log.d(getClass().getSimpleName(), "Created -- " + user.toString());
        mSQLiteHelper.insertUser(user);
    }

    @Override
    public boolean userExists(String user) {
        return mSQLiteHelper.getAllUsernames().contains(user);
    }

    @Override
    public void onPinCreated(Pin pin) {
        Log.d(getClass().getSimpleName(), "Created -- " + pin.toString());
        mSQLiteHelper.insertPin(pin);
    }

    @Override
    public Pin makePin(String title, String description, LatLng point) {
        return new Pin(title, description,  point.latitude, point.longitude);
    }

}
