package com.dream.asm.common.weaver;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author zhongch
 * @version 1.0
 * @since 2020/8/4
 */
public interface IWeaver {

    /**
     * Check a certain file is weavable
     *
     * @param filePath 路径
     * @return 是否可织入
     * @throws IOException 异常
     */
    boolean isWeavableClass(String filePath) throws IOException;

    /**
     * Weave single class to byte array
     *
     * @param inputStream 输入流
     * @return 字节流
     * @throws IOException 异常
     */
    byte[] weaveSingleClassToByteArray(InputStream inputStream) throws IOException;
}
