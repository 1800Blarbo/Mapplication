package lclark.mapplication;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_dialog, container, false);
        ButterKnife.bind(this, rootView);

        //getActivity().setTitle(getActivity().getString(R.string.fragment_search_title));

        return rootView;
    }

    public interface UserCreatedListener {
        void onUserCreated(User user);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        mListener = (UserCreatedListener) getActivity();

        View rootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_add_student, null);

        AlertDialog dialog = new AlertDialog.Builder(getActivity())
                .setView(rootView)
                .setTitle(getActivity().getString(R.string.new_student))
                .setPositiveButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String name = mNameEditText.getText().toString().trim();
                                String networth = mNetWorthEditText.getText().toString().trim();
                                String year = Student.FRESHMAN;
                                int id = mRadioGroup.getCheckedRadioButtonId();
                                switch (id) {
                                    case R.id.fragment_add_student_freshman:
                                        year = Student.FRESHMAN;
                                        break;
                                    case R.id.fragment_add_student_sophomore:
                                        year = Student.SOPHOMORE;
                                        break;
                                    case R.id.fragment_add_student_junior:
                                        year = Student.JUNIOR;
                                        break;
                                    case R.id.fragment_add_student_senior:
                                        year = Student.SENIOR;
                                        break;
                                }

                                Student student = new Student(name, year, Double.valueOf(networth));
                                mListener.onStudentCreated(student);

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
