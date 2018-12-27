package com.lxinet.jeesns.core.model;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.List;

public class Page<T>
        implements Serializable {
    private List<T> list;
    private int pageNo = 1;
    private int pageSize = 20;
    private int totalPage;
    private int totalCount;
    private boolean isFirstPage = false;
    private boolean isLastPage = false;
    private HttpServletRequest request;

    public Page(int pageNo, int pageSize) {
        setPageNo(pageNo);
        setPageSize(pageSize);
    }

    public Page(List<T> list, int pageNo, int pageSize, int totalCount) {
        setList(list);
        setPageNo(pageNo);
        setPageSize(pageSize);
        setTotalCount(totalCount);
    }

    public Page(HttpServletRequest request) {
        this.request = request;
        String no = request.getParameter("pageNo");
        try {
            setPageNo(Integer.parseInt(no));
            if (getPageNo() < 1) {
                setPageNo(1);
            }
        } catch (Exception e) {
            setPageNo(1);
        }
        String size = request.getParameter("pageSize");
        try {
            setPageSize(Integer.parseInt(size));
        } catch (Exception e) {
            setPageSize(20);
        }
    }

    public int getPageNo() {
        return this.pageNo;
    }

    public void setPageNo(int pageNo) {
        if (pageNo < 1) {
            pageNo = 1;
        }
        if (pageNo == 1) {
            this.isFirstPage = true;
        } else {
            this.isFirstPage = false;
        }
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        return this.totalPage;
    }

    public void setTotalPage() {
        this.totalPage = ((this.totalCount - 1) / this.pageSize + 1);
        if (getPageNo() == getTotalPage()) {
            this.isLastPage = true;
        } else {
            this.isLastPage = false;
        }
    }

    public int getTotalCount() {
        return this.totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        setTotalPage();
    }

    public boolean isFirstPage() {
        return this.pageNo == 1;
    }

    public boolean isLastPage() {
        return this.pageNo == this.totalPage;
    }

    public int getStartRow() {
        return (this.pageNo - 1) * this.pageSize;
    }

    public List<T> getList() {
        return this.list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
