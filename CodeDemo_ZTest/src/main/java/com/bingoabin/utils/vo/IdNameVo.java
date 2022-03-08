package com.bingoabin.utils.vo;

import java.util.Objects;

/**
 * @author HanJunsheng
 * @date 2021/7/16
 * @copyright sankuai.com
 */
public class IdNameVo {
    private Integer id;
    private String name;

    public IdNameVo() {
        super();
    }

    public IdNameVo(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

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

    @Override
    public String toString() {
        return "IdNameVo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IdNameVo idNameVo = (IdNameVo) o;
        return Objects.equals(id, idNameVo.id) &&
                Objects.equals(name, idNameVo.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
