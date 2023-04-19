package com.dream.permission.callback;

import androidx.annotation.NonNull;

import com.dream.permission.request.ExplainScope;
import com.dream.permission.request.ExplainScope;

import java.util.List;

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2023/4/19
 */
public interface ExplainReasonCallbackWithBeforeParam {

    void onExplainReason(@NonNull ExplainScope scope, @NonNull List<String> deniedList, boolean beforeRequest);

}
