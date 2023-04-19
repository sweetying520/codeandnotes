package com.dream.permission.callback;

import androidx.annotation.NonNull;

import java.util.List;

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2023/4/19
 */
public interface RequestCallback {

    void onResult(boolean allGranted, @NonNull List<String> grantedList,@NonNull List<String> deniedList);
}
