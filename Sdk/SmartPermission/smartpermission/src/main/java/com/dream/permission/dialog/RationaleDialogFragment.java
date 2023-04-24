package com.dream.permission.dialog;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.List;

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2023/4/24
 */
public abstract class RationaleDialogFragment extends DialogFragment {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null){
            dismiss();
        }
    }

    public abstract @NonNull View getPositiveButton();

    public abstract @Nullable View getNegativeButton();

    public abstract @NonNull List<String> getPermissionsToRequest();
}
