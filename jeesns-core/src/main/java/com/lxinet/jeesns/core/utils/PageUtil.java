package com.lxinet.jeesns.core.utils;

import com.lxinet.jeesns.core.model.Page;

public class PageUtil {
    private static ThreadLocal<Page> pageLocal = new ThreadLocal();

    public static void setPage(Page page) {
        pageLocal.set(page);
    }

    public static Page getPage() {
        return (Page) pageLocal.get();
    }
}
