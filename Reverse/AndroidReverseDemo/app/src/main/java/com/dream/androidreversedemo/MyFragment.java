package com.dream.androidreversedemo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2022/11/19
 */
@SuppressWarnings("FieldCanBeLocal")
public class MyFragment extends Fragment {

    private String toastTips = "toast in MyFragment";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_layout,container,false);
        methodWithGlobalVariable();
        methodWithLocalVariable();
        return rootView;
    }

    private void methodWithGlobalVariable() {
        Toast.makeText(getActivity(), toastTips, Toast.LENGTH_SHORT).show();
    }

    private void methodWithLocalVariable() {
        String logMessage = "log in MyFragment";
        logMessage = logMessage.toLowerCase();
        System.out.println(logMessage);
    }
}

