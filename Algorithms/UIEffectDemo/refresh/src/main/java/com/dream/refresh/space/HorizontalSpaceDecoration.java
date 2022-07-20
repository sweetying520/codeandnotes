package com.dream.refresh.space;

/**
 * function: 水平布局间距
 *
 * @author zy
 * @since 2022/7/20
 */
public class HorizontalSpaceDecoration extends SpaceItemDecoration {

    public HorizontalSpaceDecoration(int left, int right) {
        super(new HorizontalSpaceBuilder().left(left).right(right));
    }

    public HorizontalSpaceDecoration(Builder builder) {
        super(builder);
    }

    public static class HorizontalSpaceBuilder extends SpaceItemDecoration.Builder<HorizontalSpaceBuilder> {

        public HorizontalSpaceDecoration build() {
            return new HorizontalSpaceDecoration(this);
        }
    }
}
