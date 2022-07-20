package com.dream.refresh.space;

import android.graphics.Rect;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;


/**
 * function: recyclerView item间隔
 *
 * @author zy
 * @since 2022/7/20
 */
public class SpaceItemDecoration extends RecyclerView.ItemDecoration {

    private int left;

    private int top;

    private int right;

    private int bottom;

    private boolean mHideLastSpace;

    private SpaceProvider mSpaceProvider;

    protected SpaceItemDecoration(Builder builder) {
        this.left = builder.left;
        this.top = builder.top;
        this.right = builder.right;
        this.bottom = builder.bottom;
        this.mHideLastSpace = builder.hideLastSpace;
        this.mSpaceProvider = builder.spaceProvider;
    }


    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);
        int itemCount = parent.getAdapter().getItemCount();
        int lastOffset = getLastSpaceOffset(parent);
        if (mHideLastSpace && position >= itemCount - lastOffset) {
            // Don't set item offset for last space if mShowLastSpace = false
            return;
        }

        if (mSpaceProvider == null || !mSpaceProvider.setItemOffsets(outRect, position, parent)) {
            setItemOffsets(outRect, position, parent);
        }
    }

    /**
     * In the case mShowLastDivider = false,
     * Returns offset for how many views we don't have to draw a divider for,
     * for LinearLayoutManager it is as simple as not drawing the last child divider,
     * but for a GridLayoutManager it needs to take the span count for the last items into account
     * until we use the span count configured for the grid.
     *
     * @param parent RecyclerView
     * @return offset for how many views we don't have to draw a divider or 1 if its a
     * LinearLayoutManager
     */
    private int getLastSpaceOffset(RecyclerView parent) {
        if (parent.getLayoutManager() instanceof GridLayoutManager) {
            GridLayoutManager layoutManager = (GridLayoutManager) parent.getLayoutManager();
            GridLayoutManager.SpanSizeLookup spanSizeLookup = layoutManager.getSpanSizeLookup();
            int spanCount = layoutManager.getSpanCount();
            int itemCount = parent.getAdapter().getItemCount();
            for (int i = itemCount - 1; i >= 0; i--) {
                if (spanSizeLookup.getSpanIndex(i, spanCount) == 0) {
                    return itemCount - i;
                }
            }
        }

        return 1;
    }

    /**
     * 设置item间距
     */
    private void setItemOffsets(Rect outRect, int position, RecyclerView parent) {
        outRect.left = left;
        outRect.top = top;
        outRect.right = right;
        outRect.bottom = bottom;
    }

    /**
     * 间距提供器
     */
    public interface SpaceProvider {

        /**
         * 设置间距
         *
         * @param outRect  范围
         * @param position 第几项
         * @param parent   RecyclerView
         * @return true代表拦截间距的绘制，该方法来实现间距；false代表不拦截间距的绘制，继续让{@link SpaceItemDecoration}绘制间距
         */
        boolean setItemOffsets(Rect outRect, int position, RecyclerView parent);
    }

    /**
     * 显示item间隔创建者
     */
    protected static class Builder<T extends Builder> {

        private int left;

        private int top;

        private int right;

        private int bottom;

        private boolean hideLastSpace;

        private SpaceProvider spaceProvider;

        /**
         * 左间距
         *
         * @param left px
         */
        public T left(int left) {
            this.left = left;
            return (T) this;
        }

        /**
         * 上间距
         *
         * @param top px
         */
        public T top(int top) {
            this.top = top;
            return (T) this;
        }

        /**
         * 右间距
         *
         * @param right px
         */
        public T right(int right) {
            this.right = right;
            return (T) this;
        }

        /**
         * 底间距
         *
         * @param bottom px
         */
        public T bottom(int bottom) {
            this.bottom = bottom;
            return (T) this;
        }

        /**
         * 隐藏最后一列的间距
         */
        public T hideLastSpace() {
            this.hideLastSpace = true;
            return (T) this;
        }

        public T spaceProvider(SpaceProvider provider) {
            this.spaceProvider = provider;
            return (T) this;
        }
    }

    public static class SpaceBuilder extends Builder<SpaceBuilder> {

        public SpaceItemDecoration build() {
            return new SpaceItemDecoration(this);
        }
    }
}
