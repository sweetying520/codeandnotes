package com.dream.jetpackdemo.livedata;

import androidx.lifecycle.MutableLiveData;

import java.util.HashMap;
import java.util.Map;

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2022/7/3
 */
public class LiveDataBus {

    private final Map<String, MutableLiveData<Object>> bus;

    private LiveDataBus(){
        bus = new HashMap<>();
    }

    public static LiveDataBus getInstance(){
        return Holder.LIVE_DATA_BUS;
    }

    private static final class Holder{
        @SuppressWarnings("InstantiationOfUtilityClass")
        private static final LiveDataBus LIVE_DATA_BUS = new LiveDataBus();
    }

    public synchronized <T> MutableLiveData<T> with(String key,Class<T> type,boolean sticky){
        if(!bus.containsKey(key)){
            if(sticky){
                bus.put(key, new MutableLiveData<>());
            }else {
                bus.put(key,new NoStickyMutableLiveData<>());
            }
        }
        return (MutableLiveData<T>) bus.get(key);
    }
}
