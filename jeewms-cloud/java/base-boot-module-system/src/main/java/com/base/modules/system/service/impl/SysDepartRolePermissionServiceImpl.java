package com.base.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.base.common.util.IPUtils;
import com.base.common.util.oConvertUtils;
import com.base.modules.system.entity.SysDepartRolePermission;
import com.base.modules.system.mapper.SysDepartRolePermissionMapper;
import com.base.modules.system.service.ISysDepartRolePermissionService;
import org.jeecg.common.util.SpringContextUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @Description: 部门角色权限
 * @Author: base-boot
 * @Date:   2020-02-12
 * @Version: V1.0
 */
@Service
public class SysDepartRolePermissionServiceImpl extends ServiceImpl<SysDepartRolePermissionMapper, SysDepartRolePermission> implements ISysDepartRolePermissionService {

    @Override
    public void saveDeptRolePermission(String roleId, String permissionIds, String lastPermissionIds) {
        String ip = "";
        try {
            //获取request
            HttpServletRequest request = SpringContextUtils.getHttpServletRequest();
            //获取IP地址
            ip = IPUtils.getIpAddr(request);
        } catch (Exception e) {
            ip = "127.0.0.1";
        }
        List<String> add = getDiff(lastPermissionIds,permissionIds);
        if(add!=null && add.size()>0) {
            List<SysDepartRolePermission> list = new ArrayList<SysDepartRolePermission>();
            for (String p : add) {
                if(oConvertUtils.isNotEmpty(p)) {
                    SysDepartRolePermission rolepms = new SysDepartRolePermission(roleId, p);
                    rolepms.setOperateDate(new Date());
                    rolepms.setOperateIp(ip);
                    list.add(rolepms);
                }
            }
            this.saveBatch(list);
        }

        List<String> delete = getDiff(permissionIds,lastPermissionIds);
        if(delete!=null && delete.size()>0) {
            for (String permissionId : delete) {
                this.remove(new QueryWrapper<SysDepartRolePermission>().lambda().eq(SysDepartRolePermission::getRoleId, roleId).eq(SysDepartRolePermission::getPermissionId, permissionId));
            }
        }
    }

    /**
     * 从diff中找出main中没有的元素
     * @param main
     * @param diff
     * @return
     */
    private List<String> getDiff(String main, String diff){
        if(oConvertUtils.isEmpty(diff)) {
            return null;
        }
        if(oConvertUtils.isEmpty(main)) {
            return Arrays.asList(diff.split(","));
        }

        String[] mainArr = main.split(",");
        String[] diffArr = diff.split(",");
        Map<String, Integer> map = new HashMap<>(1024);
        for (String string : mainArr) {
            map.put(string, 1);
        }
        List<String> res = new ArrayList<String>();
        for (String key : diffArr) {
            if(oConvertUtils.isNotEmpty(key) && !map.containsKey(key)) {
                res.add(key);
            }
        }
        return res;
    }
}
