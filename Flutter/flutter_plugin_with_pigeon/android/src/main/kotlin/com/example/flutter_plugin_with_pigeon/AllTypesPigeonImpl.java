package com.example.flutter_plugin_with_pigeon;

import androidx.annotation.NonNull;

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2023/4/14
 */
public class AllTypesPigeonImpl implements AllTypesPigeon.HostEverything{

    @NonNull
    @Override
    public AllTypesPigeon.Everything giveMeEverything() {
        AllTypesPigeon.Everything everything = new AllTypesPigeon.Everything();
        everything.setAString("原生返给 Flutter 的字符串");
        everything.setABool(false);
        everything.setAInt(11L);
        return everything;
    }

    @NonNull
    @Override
    public AllTypesPigeon.Everything echo(AllTypesPigeon.Everything everything) {
        everything.setAString("原生交换给 Flutter 的字符串");
        everything.setABool(true);
        everything.setAInt(12L);
        return everything;
    }
}
