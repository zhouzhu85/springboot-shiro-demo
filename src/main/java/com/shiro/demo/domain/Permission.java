package com.shiro.demo.domain;

/**
 * @author zhouzhu
 * @Description
 * @create 2020-04-29 15:08
 */
public class Permission {
    private Integer id;

    private String name;
    private String url;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
