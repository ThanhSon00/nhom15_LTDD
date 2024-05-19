package com.iotstar.onlinetest.services.test;

import com.iotstar.onlinetest.common.paging.PagingRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class TestPaging extends PagingRequest {
    private int pageIndex, pageSize;

    @Override
    public int getPageNumber() {
        return super.getPageNumber();
    }

    @Override
    public int getPageSize() {
        return super.getPageSize();
    }

    @Override
    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    @Override
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Pageable pageable () {
        return PageRequest.of(getPageNumber(), getPageSize());
    }
}
