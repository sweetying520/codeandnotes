package com.dream.permission.request;

import java.util.List;

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2023/4/19
 */
public interface ChainTask {

    ExplainScope getExplainScope();

    ForwardScope getForwardScope();

    void request();

    void requestAgain(List<String> permissions);

    void finish();
}
