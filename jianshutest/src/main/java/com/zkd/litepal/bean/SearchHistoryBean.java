package com.zkd.litepal.bean;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

/**
* 搜索历史表 ，表名 SearchHistoryBean
* @author Alva
* create by 2017/3/2 12:27
*/
public class SearchHistoryBean extends DataSupport implements Serializable {


    private String searchContent ;
    private String searchTime;

    public SearchHistoryBean(String searchContent, String searchTime) {
        this.searchContent = searchContent;
        this.searchTime = searchTime;
    }

    public String getSearchContent() {
        return searchContent;
    }

    public void setSearchContent(String searchContent) {
        this.searchContent = searchContent;
    }

    public String getSearchTime() {
        return searchTime;
    }

    public void setSearchTime(String searchTime) {
        this.searchTime = searchTime;
    }
}
