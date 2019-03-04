package com.imokhonko.rest.filters;

import org.springframework.web.bind.annotation.RequestParam;

public class CustomerFilter {

    private int offset = 0;
    private int limit = 1000;

    public CustomerFilter() {
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
