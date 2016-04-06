package lclark.mapplication;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by larspmayrand on 4/5/16.
 */
public class AddPinDialogFragment extends DialogFragment {

    @Bind(R.id.fragment_title_edit_text)
    EditText mTitle;

    @Bind(R.id.fragment_description_edit_text)
    EditText mDescription;

    private UserCreatedListener mListener;

    public interface UserCreatedListener {
        void onUserCreated(User user);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mListener = (UserCreatedListener) getActivity();
        View rootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_dialog, null);
        ButterKnife.bind(this, rootView);

        AlertDialog dialog = new AlertDialog.Builder(getActivity())
                .setView(rootView)
                .setTitle(getActivity().getString(R.string.new_user))
                .setPositiveButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String name = mDescription.getText().toString().trim();
//                                int id = mRadioGroup.getCheckedRadioButtonId();
//                                switch (id) {
//                                    case R.id.fragment_add_student_freshman:
//                                        year = Student.FRESHMAN;
//                                        break;
//                                    case R.id.fragment_add_student_sophomore:
//                                        year = Student.SOPHOMORE;
//                                        break;
//                                    case R.id.fragment_add_student_junior:
//                                        year = Student.JUNIOR;
//                                        break;
//                                    case R.id.fragment_add_student_senior:
//                                        year = Student.SENIOR;
//                                        break;
//                                }

                                int id = 27; // ???
                                User user = new User(id, name);
                                mListener.onUserCreated(user);

                            }
                        })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .create();

        ButterKnife.bind(this, rootView);

        return dialog;
    }

}
