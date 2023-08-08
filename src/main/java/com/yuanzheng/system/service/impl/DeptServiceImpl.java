package com.yuanzheng.system.service.impl;

import com.yuanzheng.common.domain.Tree;
import com.yuanzheng.common.utils.BuildTree;
import com.yuanzheng.common.utils.ShiroUtils;
import com.yuanzheng.system.dao.DeptDao;
import com.yuanzheng.system.dao.DeptTreeDao;
import com.yuanzheng.system.domain.DeptDO;
import com.yuanzheng.system.domain.DeptTreeDO;
import com.yuanzheng.system.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class DeptServiceImpl implements DeptService {


    @Autowired
    private DeptDao sysDeptMapper;

    @Autowired
    private DeptTreeDao deptTreeDao;

    @Override
    public DeptDO get(Long deptId) {
        return sysDeptMapper.get(deptId);
    }

    @Override
    public List<DeptDO> list(Map<String, Object> map) {
        return sysDeptMapper.list(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return sysDeptMapper.count(map);
    }

    @Override
    public int save(DeptDO sysDept) {
        sysDeptMapper.save(sysDept);
        DeptTreeDO t = new DeptTreeDO();
        t.setDeptId(sysDept.getDeptId());
        t.setParentId(sysDept.getDeptId());
        t.setCreateTime(new Date());
        deptTreeDao.save(t);
        getParentDept(sysDept.getDeptId(), sysDept);
        return sysDept.getDeptId().intValue();
    }

    public void getParentDept(Long deptId, DeptDO dept) {
        if (null == dept) {
            return;
        }
        DeptDO parent = sysDeptMapper.get(dept.getParentId());
        if (null == parent) {
            return;
        }
        DeptTreeDO tree = new DeptTreeDO();
        tree.setDeptId(deptId);
        tree.setParentId(parent.getDeptId());
        tree.setCreateTime(new Date());
        deptTreeDao.save(tree);
        getParentDept(deptId, parent);
    }


    @Override
    public int update(DeptDO sysDept) {
        return sysDeptMapper.update(sysDept);
    }

    @Override
    public int remove(Long deptId) {
        deptTreeDao.remove(deptId);
        return sysDeptMapper.remove(deptId);
    }

    @Override
    public int batchRemove(Long[] deptIds) {
        return sysDeptMapper.batchRemove(deptIds);
    }

    @Override
    public Tree<DeptDO> getTree() {
        Long currentDeptId = 0L;
        List<Tree<DeptDO>> trees = new ArrayList<Tree<DeptDO>>();
        Map<String, Object> query = new HashMap<>(16);
        if (0 != ShiroUtils.getUser().getDeptId()) {
            currentDeptId = ShiroUtils.getUser().getDeptId();
            query.put("deptParentId", currentDeptId);
        }
        List<DeptDO> sysDepts = sysDeptMapper.list(query);
        for (DeptDO sysDept : sysDepts) {
            Tree<DeptDO> tree = new Tree<DeptDO>();
            tree.setId(sysDept.getDeptId().toString());
            tree.setParentId(sysDept.getParentId().toString());
            tree.setText(sysDept.getName());
            Map<String, Object> state = new HashMap<>(16);
            state.put("opened", true);
            tree.setState(state);
            trees.add(tree);
        }
        // 默认顶级菜单为０，根据数据库实际情况调整
        Tree<DeptDO> root = new Tree<DeptDO>();
        trees = BuildTree.buildByDept(ShiroUtils.getUser().getDeptId(), trees);
        if (0 == ShiroUtils.getUser().getDeptId()) {
            root.setId("0");
            root.setParentId("");
            root.setHasParent(false);
            root.setChildren(true);
            root.setChecked(true);
            root.setChildren(trees);
            root.setText("香港雅太医疗美容医院");
            Map<String, Object> state = new HashMap<>(16);
            state.put("opened", true);
            root.setState(state);
        } else {
            DeptDO dep = sysDeptMapper.get(currentDeptId);
            root.setId(dep.getDeptId() + "");
            root.setParentId("");
            root.setHasParent(false);
            root.setChildren(true);
            root.setChecked(true);
            root.setChildren(trees);
            root.setText(dep.getName());
            Map<String, Object> state = new HashMap<>(16);
            state.put("opened", true);
            root.setState(state);
        }
        return root;
    }

    @Override
    public boolean checkDeptHasUser(Long deptId) {
        // TODO Auto-generated method stub
        //查询部门以及此部门的下级部门
        int result = sysDeptMapper.getDeptUserNumber(deptId);
        return result == 0 ? true : false;
    }

    @Override
    public List<Long> listChildrenIds(Long parentId) {
        List<DeptDO> deptDOS = list(null);
        return treeMenuList(deptDOS, parentId);
    }

    List<Long> treeMenuList(List<DeptDO> menuList, long pid) {
        List<Long> childIds = new ArrayList<>();
        for (DeptDO mu : menuList) {
            //遍历出父id等于参数的id，add进子节点集合
            if (mu.getParentId() == pid) {
                //递归遍历下一级
                treeMenuList(menuList, mu.getDeptId());
                childIds.add(mu.getDeptId());
            }
        }
        return childIds;
    }

}
