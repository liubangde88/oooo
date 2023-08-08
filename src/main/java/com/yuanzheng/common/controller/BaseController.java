package com.yuanzheng.common.controller;

import com.yuanzheng.common.utils.ShiroUtils;
import com.yuanzheng.system.domain.RoleDO;
import com.yuanzheng.system.domain.UserDO;
import com.yuanzheng.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BaseController {

    @Autowired
    private RoleService roleService;

    public UserDO getUser() {
        return ShiroUtils.getUser();
    }

    public Long getUserId() {
        return getUser().getUserId();
    }

    public String getUsername() {
        return getUser().getUsername();
    }

    public Boolean hasRoleCode(String roleCode) {
        List<RoleDO> rolels = roleService.list(getUserId());
        Boolean result = false;
        if (null != rolels && rolels.size() > 0) {
            for (RoleDO role : rolels) {
                if (role.getRoleName().equals(roleCode) && role.getRoleSign().equals("true")) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }
}