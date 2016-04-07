package lclark.mapplication;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by larspmayrand on 4/3/16.
 */
public class LoginFragment extends Fragment {

    public static final String ARG_USER = "LoginFragment.Username";

    @Bind(R.id.login_main_username_editText)
    EditText mEditText;

    @Bind(R.id.fragment_main_login_button)
    Button mLoginButton;

    @Bind(R.id.fragment_main_add_user_button)
    Button mAddUserButton;

    private SQLiteListener mListener;

    public interface SQLiteListener {
        void onUserCreated(User user);
        boolean userExists(String user);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_login_main, container, false);
        mListener = (SQLiteListener) getActivity();
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick(R.id.fragment_main_login_button)
    void searchForUser() {
        String user = mEditText.getText().toString().trim();
        // mUserSQLiteHelper.getAllUsers().toString().contains(user)
        /**
         * look thru the database find if the user is a thing or not
         */
        if (user.equals("")) {
            Snackbar snackbar = Snackbar.make(mLoginButton, getString(R.string.null_username_error), Snackbar.LENGTH_LONG);
            snackbar.show();
        } else if (!mListener.userExists(user)) {
            Snackbar snackbar = Snackbar
                    .make(mLoginButton, R.string.user_not_found_error, Snackbar.LENGTH_LONG)
                    .setAction("ADD USER", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Snackbar snackbar1 = Snackbar.make(mLoginButton, R.string.user_added, Snackbar.LENGTH_SHORT);
                            snackbar1.show();
                        }
                    });
            snackbar.show();
            User newUser = new User(user);
            mListener.onUserCreated(newUser);
            launchMap(newUser);
        } else {
            // find the existing user from database and pass it below
            launchMap(new User(user)); // of user
        }
    }

    @OnClick(R.id.fragment_main_add_user_button)
    void addUser() {
        String user = mEditText.getText().toString().trim();
        if (user.equals("")) {
            Snackbar snackbar = Snackbar.make(mLoginButton, getString(R.string.null_username_error), Snackbar.LENGTH_LONG);
            snackbar.show();
        } else if (mListener.userExists(user)) {
            Snackbar snackbar = Snackbar
                    .make(mLoginButton, R.string.user_not_found_error, Snackbar.LENGTH_LONG)
                    .setAction("LOGIN", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Snackbar snackbar1 = Snackbar.make(mLoginButton, R.string.user_added, Snackbar.LENGTH_SHORT);
                            snackbar1.show();
                            // new User("tomas"); // somehow pass tomas to MapsFragment
                            // add new username to SQL
                        }
                    });
            snackbar.show();
        } else {
            User newUser = new User(user);
            mListener.onUserCreated(newUser);
            launchMap(newUser);
        }
    }

    public void launchMap(User user) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.activity_main_framelayout, MapsFragment.newInstance(user));
        //transaction.addToBackStack(null);
        transaction.commit();
    }

}
