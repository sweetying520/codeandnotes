package com.dream.permission.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2023/4/20
 */
public abstract class RationaleDialog extends Dialog {


    public RationaleDialog(@NonNull Context context) {
        super(context);
    }

    public RationaleDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected RationaleDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public abstract @NonNull View getPositiveButton();

    public abstract @Nullable View getNegativeButton();

    public abstract @NonNull List<String> getPermissionToRequest();

}
