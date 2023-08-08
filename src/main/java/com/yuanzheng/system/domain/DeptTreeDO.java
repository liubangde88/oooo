package com.yuanzheng.system.domain;

import java.io.Serializable;
import java.util.Date;


/**
 * @author wang
 * @email 1992lcg@163.com
 * @date 2021-06-03 15:28:21
 */
public class DeptTreeDO implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Long id;
    //
    private Long deptId;
    //
    private Long parentId;
    //
    private Date createTime;

    /**
     * 获取：
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置：
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取：
     */
    public Long getDeptId() {
        return deptId;
    }

    /**
     * 设置：
     */
    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    /**
     * 获取：
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 设置：
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取：
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置：
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
