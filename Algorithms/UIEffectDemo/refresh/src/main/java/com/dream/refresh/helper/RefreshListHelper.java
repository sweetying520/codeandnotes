package com.dream.refresh.helper;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;


/**
 * function: 刷新列表（recyclerView绑定）帮助类
 *
 * @author zy
 * @since 2022/7/20
 */
public class RefreshListHelper {

    /**
     * 刷新控件
     */
    SmartRefreshLayout smartRefreshLayout;

    /**
     * 填充在刷新控件里的recyclerView
     */
    RecyclerView recyclerView;

    /**
     * recyclerView绑定的Adapter
     */
    BaseQuickAdapter<?, ? extends BaseViewHolder> adapter;

    /**
     * recyclerView绑定的LayoutManager
     */
    RecyclerView.LayoutManager layoutManager;

    /**
     * item之间的装饰
     */
    List<RecyclerView.ItemDecoration> itemDecorations;

    /**
     * 开始间距
     */
    int startSpace;

    /**
     * 结尾间距
     */
    int endSpace;

    /**
     * 创建刷新控件帮助类：
     * 由于默认条件就是由该帮助模块来监听刷新，因此切记无需监听SmartRefreshLayout的刷新回调即无需做以下监听
     * {@link com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener}
     * {@link com.scwang.smartrefresh.layout.listener.OnRefreshListener}
     * {@link com.scwang.smartrefresh.layout.listener.OnLoadMoreListener}
     */
    public static RefreshListHelper with(SmartRefreshLayout smartRefreshLayout) {
        return new RefreshListHelper(smartRefreshLayout);
    }

    private RefreshListHelper(SmartRefreshLayout smartRefreshLayout) {
        this.smartRefreshLayout = smartRefreshLayout;
    }

    /**
     * 填充RecyclerView，非必填项可省略
     * 1、若在布局layout文件中SmartRefreshLayout存在直接子view为RecyclerView类型（非直接子view请手动填写该方法），
     * 将使用该直接子view即RecyclerView为该项
     * 2、若未填充该项将会自动new一个RecyclerView，且RecyclerView的宽高为
     * [{@link android.view.ViewGroup.LayoutParams#MATCH_PARENT}, {@link android.view.ViewGroup.LayoutParams#MATCH_PARENT}]
     */
    public RefreshListHelper recyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
        return this;
    }

    /**
     * 刷新控件中RecyclerView的LayoutManager，非必填项
     * 若未填值，将默认使用{@link LinearLayoutManager#LinearLayoutManager}构造方法，即纵向布局
     */
    public RefreshListHelper layoutManager(RecyclerView.LayoutManager layoutManager) {
        this.layoutManager = layoutManager;
        return this;
    }

    /**
     * 设置RecyclerView的装饰对象，如果增加了该装饰，那么对于{@link #startSpace(int)}、{@link #endSpace(int)}无效
     */
    public RefreshListHelper addItemDecoration(RecyclerView.ItemDecoration itemDecoration) {
        if (itemDecoration != null) {
            if (itemDecorations == null) {
                itemDecorations = new ArrayList<>();
            }
            itemDecorations.add(itemDecoration);
        }
        return this;
    }

    /**
     * 设置开始间距，只支持{@link LinearLayoutManager}，与{@link #addItemDecoration}互斥
     * 如果布局为横向布局，则为left间距
     * 如果布局为纵向布局，则为top间距
     *
     * @param startSpace 开始间距，数值为px
     */
    public RefreshListHelper startSpace(int startSpace) {
        this.startSpace = startSpace;
        return this;
    }

    /**
     * 设置开始间距，只支持{@link LinearLayoutManager}，与{@link #addItemDecoration}互斥
     * 如果布局为横向布局，则为right间距
     * 如果布局为纵向布局，则为bottom间距
     *
     * @param endSpace 结尾间距，数值为px
     */
    public RefreshListHelper endSpace(int endSpace) {
        this.endSpace = endSpace;
        return this;
    }

    /**
     * 最后步：绑定Adapter，必填项，切记最后一步调用
     *
     * @param adapter 若RecyclerView已经setAdapter，并且该adapter类型为{@link BaseQuickAdapter}，该帮助类会匹配到已经set的adapter做逻辑处理，
     *                若无则会主动使用该参数绑定到RecyclerView
     */
    public <T> RecyclerViewHelper<T> bindAdapter(BaseQuickAdapter<T, ? extends BaseViewHolder> adapter) {
        if (adapter == null) {
            throw new NullPointerException("Sorry, the adapter must not be null.");
        }
        this.adapter = adapter;
        return new RecyclerViewHelper<>(this);
    }
}
