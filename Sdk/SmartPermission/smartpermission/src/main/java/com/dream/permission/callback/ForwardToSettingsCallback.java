package com.dream.permission.callback;

import androidx.annotation.NonNull;

import com.dream.permission.request.ForwardScope;

import java.util.List;

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2023/4/19
 */
public interface ForwardToSettingsCallback {

    void onForwardToSettings(@NonNull ForwardScope scope, @NonNull List<String> deniedList);
}
