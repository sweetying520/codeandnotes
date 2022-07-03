package com.dream.jetpackdemo.livedata;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2022/7/3
 */
public class NoStickyMutableLiveData<T> extends MutableLiveData<T> {


    @Override
    public void observe(@NonNull LifecycleOwner owner, @NonNull Observer<? super T> observer) {
        super.observe(owner, observer);
        hook(observer);

    }

    private void hook(Observer<? super T> observer) {
        try {
            Class<LiveData> liveDataClass = LiveData.class;
            //获取 LiveData 的 mObservers map 属性
            Field mObserverField = liveDataClass.getDeclaredField("mObservers");
            mObserverField.setAccessible(true);

            //获取 map 中的 get 方法
            Object mObserverObject = mObserverField.get(this);
            Class<?> mObserverClass = mObserverObject.getClass();
            Method get = mObserverClass.getDeclaredMethod("get",Object.class);
            get.setAccessible(true);

            Object entryObject = get.invoke(mObserverObject, observer);
            //获取observerWrapper
            Object observerWrapper = null;
            if(entryObject instanceof Map.Entry){
                observerWrapper = ((Map.Entry) entryObject).getValue();
            }

            if(observerWrapper == null){
                throw new RuntimeException("observerWrapper is null");
            }

            //获取observerWrapper 中的 mLastVersion
            Class<?> superclass = observerWrapper.getClass().getSuperclass();
            Field mLastVersionFiled = superclass.getDeclaredField("mLastVersion");
            mLastVersionFiled.setAccessible(true);

            Field mVersionFiled = liveDataClass.getDeclaredField("mVersion");
            mVersionFiled.setAccessible(true);
            Object mVersionValue = mVersionFiled.get(this);

            mLastVersionFiled.set(observerWrapper,mVersionValue);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
