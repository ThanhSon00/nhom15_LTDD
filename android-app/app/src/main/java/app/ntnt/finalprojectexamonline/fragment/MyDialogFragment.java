package app.ntnt.finalprojectexamonline.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import app.ntnt.finalprojectexamonline.R;

public class MyDialogFragment extends DialogFragment implements UploadImageFragment.OnImageUploadedListener{
    UploadImageFragment uploadImageFragment;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // inflate the dialog layout XML file
        //View rootView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_add_subject, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View dialogView = inflater.inflate(R.layout.dialog_add_subject, null);
        uploadImageFragment = UploadImageFragment.getInstance();
        getChildFragmentManager().beginTransaction()
                .replace(R.id.img_dialog_addImage, uploadImageFragment)
                .commit();

        builder.setView(dialogView);
//                .setTitle(R.string.add_subject_dialog_title)
//                .setPositiveButton(R.string.add_subject_dialog_add_button, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        // Handle add button click
//                    }
//                })
//                .setNegativeButton(R.string.add_subject_dialog_cancel_button, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        // Handle cancel button click
//                    }
//                });

        return builder.create();
    }

    @Override
    public void onImageUploaded(Uri uri) {

    }
}
