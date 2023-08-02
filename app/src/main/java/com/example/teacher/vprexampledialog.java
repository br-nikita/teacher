package com.example.teacher;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class vprexampledialog extends DialogFragment {

    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Важное сообщение! Прочитай внимательно!");
        builder.setMessage("В заданиях 2-6 вводи ответы с маленькой буквы. В задании 7 первое слово пиши с большой буквы," +
                " в конце предложения не забудь поставить точку. ");
        builder.setPositiveButton("ОК", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        ((MainActivity) getActivity()).okClicked();
                    }
                });
        builder.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                ((MainActivity) getActivity()).cancelClicked();
            }
        });
        return builder.create();
    }

}
