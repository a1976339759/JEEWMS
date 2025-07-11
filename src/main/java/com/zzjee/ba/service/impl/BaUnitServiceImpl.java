package com.zzjee.ba.service.impl;

import com.zzjee.ba.service.BaUnitServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import com.zzjee.ba.entity.BaUnitEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.io.Serializable;

import org.jeecgframework.core.util.ApplicationContextUtil;
import org.jeecgframework.core.util.MyClassLoader;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.web.cgform.enhance.CgformEnhanceJavaInter;

/**
 * Demo class
 *
 * @author admin
 * @date 2016/10/31
 */
@Service("baUnitService")
@Transactional
public class BaUnitServiceImpl extends CommonServiceImpl implements BaUnitServiceI {


    @Override
    public void delete(BaUnitEntity entity) throws Exception {
        super.delete(entity);
        //执行删除操作增强业务
        this.doDelBus(entity);
    }

    @Override
    public Serializable save(BaUnitEntity entity) throws Exception {
        Serializable t = super.save(entity);
        //执行新增操作增强业务
        this.doAddBus(entity);
        return t;
    }

    @Override
    public void saveOrUpdate(BaUnitEntity entity) throws Exception {
        super.saveOrUpdate(entity);
        //执行更新操作增强业务
        this.doUpdateBus(entity);
    }

    /**
     * 新增操作增强业务
     *
     * @param t
     * @return
     */
    private void doAddBus(BaUnitEntity t) throws Exception {
        //-----------------sql增强 start----------------------------
        //-----------------sql增强 end------------------------------

        //-----------------java增强 start---------------------------
        //-----------------java增强 end-----------------------------
    }

    /**
     * 更新操作增强业务
     *
     * @param t
     * @return
     */
    private void doUpdateBus(BaUnitEntity t) throws Exception {
        //-----------------sql增强 start----------------------------
        //-----------------sql增强 end------------------------------

        //-----------------java增强 start---------------------------
        //-----------------java增强 end-----------------------------
    }

    /**
     * 删除操作增强业务
     *
     * @param t
     * @return
     */
    private void doDelBus(BaUnitEntity t) throws Exception {
        //-----------------sql增强 start----------------------------
        //-----------------sql增强 end------------------------------

        //-----------------java增强 start---------------------------
        //-----------------java增强 end-----------------------------
    }

    private Map<String, Object> populationMap(BaUnitEntity t) {
        Map<String, Object> map = new HashMap<String, Object>(1024);
        map.put("id", t.getId());
        map.put("create_name", t.getCreateName());
        map.put("create_by", t.getCreateBy());
        map.put("create_date", t.getCreateDate());
        map.put("update_name", t.getUpdateName());
        map.put("update_by", t.getUpdateBy());
        map.put("update_date", t.getUpdateDate());
        map.put("sys_org_code", t.getSysOrgCode());
        map.put("sys_company_code", t.getSysCompanyCode());
        map.put("unit_code", t.getUnitCode());
        map.put("unit_zh_name", t.getUnitZhName());
        map.put("unit_en_name", t.getUnitEnName());
        map.put("unit_change", t.getUnitChange());
        map.put("unit_type", t.getUnitType());
        map.put("unit_del", t.getUnitDel());
        return map;
    }

    /**
     * 替换sql中的变量
     *
     * @param sql
     * @param t
     * @return
     */
    public String replaceVal(String sql, BaUnitEntity t) {
        sql = sql.replace("#{id}", String.valueOf(t.getId()));
        sql = sql.replace("#{create_name}", String.valueOf(t.getCreateName()));
        sql = sql.replace("#{create_by}", String.valueOf(t.getCreateBy()));
        sql = sql.replace("#{create_date}", String.valueOf(t.getCreateDate()));
        sql = sql.replace("#{update_name}", String.valueOf(t.getUpdateName()));
        sql = sql.replace("#{update_by}", String.valueOf(t.getUpdateBy()));
        sql = sql.replace("#{update_date}", String.valueOf(t.getUpdateDate()));
        sql = sql.replace("#{sys_org_code}", String.valueOf(t.getSysOrgCode()));
        sql = sql.replace("#{sys_company_code}", String.valueOf(t.getSysCompanyCode()));
        sql = sql.replace("#{unit_code}", String.valueOf(t.getUnitCode()));
        sql = sql.replace("#{unit_zh_name}", String.valueOf(t.getUnitZhName()));
        sql = sql.replace("#{unit_en_name}", String.valueOf(t.getUnitEnName()));
        sql = sql.replace("#{unit_change}", String.valueOf(t.getUnitChange()));
        sql = sql.replace("#{unit_type}", String.valueOf(t.getUnitType()));
        sql = sql.replace("#{unit_del}", String.valueOf(t.getUnitDel()));
        sql = sql.replace("#{UUID}", UUID.randomUUID().toString());
        return sql;
    }

    /**
     * 执行JAVA增强
     */
    private void executeJavaExtend(String cgJavaType, String cgJavaValue, Map<String, Object> data) throws Exception {
        if (StringUtil.isNotEmpty(cgJavaValue)) {
            Object obj = null;
            try {
                if ("class".equals(cgJavaType)) {
                    //因新增时已经校验了实例化是否可以成功，所以这块就不需要再做一次判断
                    obj = MyClassLoader.getClassByScn(cgJavaValue).newInstance();
                } else if ("spring".equals(cgJavaType)) {
                    obj = ApplicationContextUtil.getContext().getBean(cgJavaValue);
                }
                if (obj instanceof CgformEnhanceJavaInter) {
                    CgformEnhanceJavaInter javaInter = (CgformEnhanceJavaInter) obj;
                    javaInter.execute("ba_unit", data);
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new Exception("执行JAVA增强出现异常！");
            }
        }
    }
}