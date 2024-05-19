package com.iotstar.onlinetest.services.role;

import com.iotstar.onlinetest.common.paging.PagingRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class RolePaging extends PagingRequest {
    private int pageIndex, pageSize;
    @Override
    public int getPageNumber() {
        return pageIndex;
    }

    @Override
    public int getPageSize() {
        return pageSize;
    }

    @Override
    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    @Override
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Pageable pageable (){
        return PageRequest.of(getPageNumber(), getPageSize());
    }
}
