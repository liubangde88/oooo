package com.yuanzheng.oa.service.impl;

import com.yuanzheng.common.service.DictService;
import com.yuanzheng.common.utils.DateUtils;
import com.yuanzheng.common.utils.PageUtils;
import com.yuanzheng.oa.dao.NotifyDao;
import com.yuanzheng.oa.dao.NotifyRecordDao;
import com.yuanzheng.oa.domain.NotifyDO;
import com.yuanzheng.oa.domain.NotifyDTO;
import com.yuanzheng.oa.service.NotifyService;
import com.yuanzheng.system.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class NotifyServiceImpl implements NotifyService {
    @Autowired
    private NotifyDao notifyDao;
    @Autowired
    private NotifyRecordDao recordDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private DictService dictService;


    @Override
    public NotifyDO get(Long id) {
        NotifyDO rDO = notifyDao.get(id);
        rDO.setType(dictService.getName("oa_notify_type", rDO.getType()));
        return rDO;
    }

    @Override
    public List<NotifyDO> list(Map<String, Object> map) {
        List<NotifyDO> notifys = notifyDao.list(map);
        for (NotifyDO notifyDO : notifys) {
            notifyDO.setType(dictService.getName("oa_notify_type", notifyDO.getType()));
        }
        return notifys;
    }

    @Override
    public int count(Map<String, Object> map) {
        return notifyDao.count(map);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int save(NotifyDO notify) {
        notify.setUpdateDate(new Date());
        notify.setStatus("0");
        notify.setDelFlag("0");
        int r = notifyDao.save(notify);
        return r;
    }

    @Override
    public int update(NotifyDO notify) {
        return notifyDao.update(notify);
    }

    @Transactional
    @Override
    public int remove(Long id) {
        recordDao.removeByNotifbyId(id);
        return notifyDao.remove(id);
    }

    @Transactional
    @Override
    public int batchRemove(Long[] ids) {
        recordDao.batchRemoveByNotifbyId(ids);
        return notifyDao.batchRemove(ids);
    }


    @Override
    public PageUtils selfList(Map<String, Object> map) {
        List<NotifyDTO> rows = notifyDao.listDTO(map);
        for (NotifyDTO notifyDTO : rows) {
            notifyDTO.setBefore(DateUtils.getTimeBefore(notifyDTO.getUpdateDate()));
            notifyDTO.setSender(userDao.get(notifyDTO.getCreateBy()).getName());
        }
        PageUtils page = new PageUtils(rows, notifyDao.countDTO(map));
        return page;
    }

    @Override
    public void startActivity(NotifyDO notify, String processDefinitionId) {
        notify.setStatus("1");
        notify.setProcessDefinitionId(processDefinitionId);
        notifyDao.update(notify);
    }

    @Override
    public int deploy(Long id) {
        recordDao.deployByNotifyId(id);
        return notifyDao.deployById(id);
    }

}
