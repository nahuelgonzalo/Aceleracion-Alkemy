package com.alkemy.java.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class PageUtil {

    @Autowired
    private HttpServletRequest servletRequest;

    public PageRequest pageRequest(Integer page) {
        return PageRequest.of(page - 1, 10);
    }

    public String getPreviousUrl(Integer page) {
        return servletRequest.getRequestURL().toString() + (page == 2 ? "" : "?page=" + (page - 1));
    }

    public String getNextUrl(Integer page) {
        return servletRequest.getRequestURL().toString() + "?page=" + (page + 1);
    }
}
