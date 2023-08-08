package com.yuanzheng.system.domain;

import java.io.Serializable;


/**
 * @author wang
 * @email 1992lcg@163.com
 * @date 2021-06-11 18:22:04
 */
public class AreaDO implements Serializable {
    private static final long serialVersionUID = 1L;

    //区划代码
    private Long id;
    //名称
    private String name;
    //父级区划代码
    private Long pid;
    //省/直辖市代码
    private Long provinceCode;
    //市代码
    private Long cityCode;
    //区/县代码
    private Long areaCode;
    //街道/镇代码
    private Long streetCode;
    //社区/乡村代码
    private Long committeeCode;
    //城乡分类代码
    private Long committeeType;
    //排序
    private Integer sort;
    //级别: 1-省/直辖市, 2-市, 3-区/县/地级市, 4-街道/镇, 5-社区/乡村
    private Integer level;

    /**
     * 获取：区划代码
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置：区划代码
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取：名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置：名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取：父级区划代码
     */
    public Long getPid() {
        return pid;
    }

    /**
     * 设置：父级区划代码
     */
    public void setPid(Long pid) {
        this.pid = pid;
    }

    /**
     * 获取：省/直辖市代码
     */
    public Long getProvinceCode() {
        return provinceCode;
    }

    /**
     * 设置：省/直辖市代码
     */
    public void setProvinceCode(Long provinceCode) {
        this.provinceCode = provinceCode;
    }

    /**
     * 获取：市代码
     */
    public Long getCityCode() {
        return cityCode;
    }

    /**
     * 设置：市代码
     */
    public void setCityCode(Long cityCode) {
        this.cityCode = cityCode;
    }

    /**
     * 获取：区/县代码
     */
    public Long getAreaCode() {
        return areaCode;
    }

    /**
     * 设置：区/县代码
     */
    public void setAreaCode(Long areaCode) {
        this.areaCode = areaCode;
    }

    /**
     * 获取：街道/镇代码
     */
    public Long getStreetCode() {
        return streetCode;
    }

    /**
     * 设置：街道/镇代码
     */
    public void setStreetCode(Long streetCode) {
        this.streetCode = streetCode;
    }

    /**
     * 获取：社区/乡村代码
     */
    public Long getCommitteeCode() {
        return committeeCode;
    }

    /**
     * 设置：社区/乡村代码
     */
    public void setCommitteeCode(Long committeeCode) {
        this.committeeCode = committeeCode;
    }

    /**
     * 获取：城乡分类代码
     */
    public Long getCommitteeType() {
        return committeeType;
    }

    /**
     * 设置：城乡分类代码
     */
    public void setCommitteeType(Long committeeType) {
        this.committeeType = committeeType;
    }

    /**
     * 获取：排序
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 设置：排序
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * 获取：级别: 1-省/直辖市, 2-市, 3-区/县/地级市, 4-街道/镇, 5-社区/乡村
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * 设置：级别: 1-省/直辖市, 2-市, 3-区/县/地级市, 4-街道/镇, 5-社区/乡村
     */
    public void setLevel(Integer level) {
        this.level = level;
    }
}
