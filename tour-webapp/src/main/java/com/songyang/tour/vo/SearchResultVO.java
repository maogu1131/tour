package com.songyang.tour.vo;/**
 * Created by lenovo on 2017/10/29.
 */

import java.io.Serializable;
import java.util.List;

/**
 * 搜索结果模型VO
 *
 * @author
 * @create 2017-10-29 12:24
 **/
public class SearchResultVO implements Serializable {

    private List<SearchVO>  searchList;

    public List<SearchVO> getSearchList() {
        return searchList;
    }

    public void setSearchList(List<SearchVO> searchList) {
        this.searchList = searchList;
    }
}
