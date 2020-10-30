package com.yw.springbootdemo.beans;

/**
 * @author yangwei
 * @date 2020-01-09 16:17
 */
public class UserVo {
    private Integer pageIdx = 1;
    private Integer pageSize = 10;

    public Integer getPageIdx() {
        return pageIdx;
    }

    public void setPageIdx(Integer pageIdx) {
        this.pageIdx = pageIdx;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
