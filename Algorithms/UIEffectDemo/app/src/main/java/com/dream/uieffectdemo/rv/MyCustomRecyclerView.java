package com.dream.uieffectdemo.rv;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * function: 自定义 RecyclerView
 * 1、适配器：将 UI 和控件加载滑动进行解耦
 * 2、怎么做到不显示的 Item 不加载到内存中？：
 *
 * @author zy
 * @since 2022/7/29
 */
public class MyCustomRecyclerView extends ViewGroup {

    private boolean needRelayout;
    //缓存存在屏幕上的 View
    private List<View> viewList;
    private Adapter adapter;
    private int rowCount;
    private int[] heights;

    public MyCustomRecyclerView(Context context) {
        super(context);
        init();
    }

    public MyCustomRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyCustomRecyclerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        needRelayout = true;
        viewList = new ArrayList<>();
        heights = new int[]{};
    }

    public void setAdapter(Adapter adapter){
        this.adapter = adapter;
        needRelayout = true;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean change, int i, int i1, int i2, int i3) {
        if(needRelayout || change){
            //重新摆放子控件
            needRelayout = false;
            //初始化
            viewList.clear();
            //移除所有子 View
            removeAllViews();
            if(adapter != null){
                rowCount = adapter.getCount();
                //依赖这个方法测量 item 的高度
                for (int fi = 0; fi < rowCount; fi++) {
                    heights[fi] += adapter.getHeight(fi);
                }
            }

        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }



    interface Adapter{
        View onCreateViewHolder(int position, View convertView, ViewGroup parent);
        void onBindViewHolder(int position, View convertView, ViewGroup parent);
        int getItemViewType(int row);
        int getCount();
        int getHeight(int index);
    }

}
