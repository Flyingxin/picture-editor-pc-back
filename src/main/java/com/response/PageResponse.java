package com.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 分页查询结果
 * @author ccyx
 * date 2025/02/09 11:38:00
 */
@Data
public class PageResponse<T> {

    private int pageNum;

    private int pageSize;

    private int total;

    private Collection<T> data;

    private static <T> PageResponse<T> empty(int pageNum, int pageSize, int total){
        PageResponse<T> pageResponse = new PageResponse<T>();
        pageResponse.setPageNum(pageNum);
        pageResponse.setPageSize(pageSize);
        pageResponse.setTotal(total);
        pageResponse.setData(new ArrayList<T>());

        return pageResponse;
    }

    private static  <T> PageResponse<T> result(int pageNum, int pageSize, int total, Collection<T> data){
        PageResponse<T> pageResponse = new PageResponse<T>();
        pageResponse.setPageNum(pageNum);
        pageResponse.setPageSize(pageSize);
        pageResponse.setTotal(total);
        pageResponse.setData(data);

        return pageResponse;

    }
}
