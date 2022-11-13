package com.dream.lib.generator;


import com.dream.lib.constants.DimenTypes;
import com.dream.lib.utils.MakeUtils;

import java.io.File;


public class DimenGenerator {

    /**
     * 设计稿尺寸(将自己设计师的设计稿的宽度填入)
     */
    private static final int DESIGN_WIDTH = 540;

    /**
     * 设计稿的高度  （将自己设计师的设计稿的高度填入）
     */
    private static final int DESIGN_HEIGHT = 667;

    public static void main(String[] args) {
        int smallest = DESIGN_WIDTH>DESIGN_HEIGHT? DESIGN_HEIGHT:DESIGN_WIDTH;  //     求得最小宽度
        DimenTypes[] values = DimenTypes.values();
        for (DimenTypes value : values) {
            File file = new File("");
            MakeUtils.makeAll(smallest, value, file.getAbsolutePath());
        }

    }

}
