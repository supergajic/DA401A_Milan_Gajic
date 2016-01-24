package se.dropmedia.milan.assignment_4;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;

/**
 * Created by MAH on 2016-01-20.
 */
public class TreasureDialog extends DialogFragment implements DialogInterface.OnClickListener
{

    private int mTreasure;
    private OnOptionSelected mListener;

    public TreasureDialog()
    {

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.mListener = (OnOptionSelected) activity;
        } catch (final ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnOptionSelectedr");
        }
    }

    public static interface OnOptionSelected
    {
        public abstract void onComplete(int option);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        mTreasure = getArguments().getInt("treasure");

        switch (mTreasure) {
            case 0:
                AlertDialog.Builder question_1 = new AlertDialog
                        .Builder(getActivity())
                        .setTitle(R.string.question_1)
                        .setItems(R.array.answer_1, this);
                return question_1.create();

            case 1:
                AlertDialog.Builder question_2 = new AlertDialog
                        .Builder(getActivity())
                        .setTitle(R.string.question_2)
                        .setItems(R.array.answer_2, this);
                return question_2.create();

            case 2:
                AlertDialog.Builder question_3 = new AlertDialog
                        .Builder(getActivity())
                        .setTitle(R.string.question_3)
                        .setItems(R.array.answer_3, this);
                return question_3.create();

            case 3:
                AlertDialog.Builder question_4 = new AlertDialog
                        .Builder(getActivity())
                        .setTitle(R.string.question_4)
                        .setItems(R.array.answer_4, this);
                return question_4.create();
        }
        return null;
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        this.mListener.onComplete(which);
    }
}
