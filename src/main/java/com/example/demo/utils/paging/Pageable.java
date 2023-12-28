package com.example.demo.utils.paging;

import com.example.demo.utils.sort.Sorter;

public interface Pageable {
    Integer getPage();
    Integer getOffset();
    Integer getLimit();
    Sorter getSorter();
}
