package com.ex.jpa_demo.jpa_demo.dto;

import lombok.Data;
import org.springframework.data.domain.Slice;

import java.util.List;

@Data
public class SliceResponse<T> {

    private List<T> data;
    private int currentPage;
    private boolean hasNext;
    private String nextPageURL;

    public SliceResponse(Slice<T> slice, String baseURL, double salaryAmount){
        this.data = slice.getContent();
        this.currentPage = slice.getNumber();
        this.hasNext = slice.hasNext();
        this.nextPageURL = slice.hasNext() ?
                baseURL+"/salary/"+salaryAmount+ "?page="+(slice.getNumber()+1)+"?size="+(slice.getSize())
                : null ;
    }

}
