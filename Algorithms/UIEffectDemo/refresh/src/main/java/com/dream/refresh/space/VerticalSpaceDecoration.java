package com.dream.refresh.space;

/**
 * function: 垂直布局间距
 *
 * @author zy
 * @since 2022/7/20
 */
public class VerticalSpaceDecoration extends SpaceItemDecoration {

    public VerticalSpaceDecoration(int top, int bottom) {
        super(new VerticalSpaceBuilder().top(top).bottom(bottom));
    }

    public VerticalSpaceDecoration(VerticalSpaceDecoration.Builder builder) {
        super(builder);
    }

    public static class VerticalSpaceBuilder extends SpaceItemDecoration.Builder<VerticalSpaceBuilder> {

        public VerticalSpaceDecoration build() {
            return new VerticalSpaceDecoration(this);
        }
    }

}
