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

    private PinCreatedListener mListener;

    public interface PinCreatedListener {
        void onPinCreated(User user);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mListener = (PinCreatedListener) getActivity();
        View rootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_dialog, null);
        ButterKnife.bind(this, rootView);

        AlertDialog dialog = new AlertDialog.Builder(getActivity())
                .setView(rootView)
                .setTitle(getActivity().getString(R.string.new_pin))
                .setPositiveButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String title = mTitle.getText().toString().trim();
                                String description = mDescription.getText().toString().trim();
                                Pin pin = new Pin(title, description, 50, 50);
                                mListener.onPinCreated(pin);

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
