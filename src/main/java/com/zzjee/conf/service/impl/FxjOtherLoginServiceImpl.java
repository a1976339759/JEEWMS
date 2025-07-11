package com.zzjee.conf.service.impl;

import com.zzjee.conf.service.FxjOtherLoginServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import com.zzjee.conf.entity.FxjOtherLoginEntity;
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
@Service("fxjOtherLoginService")
@Transactional
public class FxjOtherLoginServiceImpl extends CommonServiceImpl implements FxjOtherLoginServiceI {

    /**
     * 删除指定的客户其他信息实体，并在删除后执行额外的业务逻辑增强。
     *
     * @param entity
     */
    @Override
    public void delete(FxjOtherLoginEntity entity) throws Exception {
        //调用父类的delete方法
        super.delete(entity);
        //执行删除操作增强业务
        this.doDelBus(entity);
    }

    @Override
    public Serializable save(FxjOtherLoginEntity entity) throws Exception {
        //调用父类的save方法,执行实际的保存操作
        Serializable t = super.save(entity);
        //执行新增操作增强业务
        this.doAddBus(entity);
        return t;
    }

    @Override
    public void saveOrUpdate(FxjOtherLoginEntity entity) throws Exception {
        //调用父类的saveOrUpdate方法
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
    private void doAddBus(FxjOtherLoginEntity t) throws Exception {
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
    private void doUpdateBus(FxjOtherLoginEntity t) throws Exception {
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
    private void doDelBus(FxjOtherLoginEntity t) throws Exception {
        //-----------------sql增强 start----------------------------
        //-----------------sql增强 end------------------------------

        //-----------------java增强 start---------------------------
        //-----------------java增强 end-----------------------------
    }

    /**
     * 将FxjOtherLoginEntity对象的属性转换为一个Map
     *
     * @param t
     * @return
     */
    private Map<String, Object> populationMap(FxjOtherLoginEntity t) {
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
        map.put("bpm_status", t.getBpmStatus());
        map.put("username", t.getUsername());
        map.put("realname", t.getRealname());
        map.put("otherid", t.getOtherid());
        map.put("otherre1", t.getOtherre1());
        map.put("otherre2", t.getOtherre2());
        map.put("otherre3", t.getOtherre3());
        map.put("otherre4", t.getOtherre4());
        map.put("other_type", t.getOtherType());
        return map;
    }

    /**
     * 替换sql中的变量
     *
     * @param sql
     * @param t
     * @return
     */
    public String replaceVal(String sql, FxjOtherLoginEntity t) {
        sql = sql.replace("#{id}", String.valueOf(t.getId()));
        sql = sql.replace("#{create_name}", String.valueOf(t.getCreateName()));
        sql = sql.replace("#{create_by}", String.valueOf(t.getCreateBy()));
        sql = sql.replace("#{create_date}", String.valueOf(t.getCreateDate()));
        sql = sql.replace("#{update_name}", String.valueOf(t.getUpdateName()));
        sql = sql.replace("#{update_by}", String.valueOf(t.getUpdateBy()));
        sql = sql.replace("#{update_date}", String.valueOf(t.getUpdateDate()));
        sql = sql.replace("#{sys_org_code}", String.valueOf(t.getSysOrgCode()));
        sql = sql.replace("#{sys_company_code}", String.valueOf(t.getSysCompanyCode()));
        sql = sql.replace("#{bpm_status}", String.valueOf(t.getBpmStatus()));
        sql = sql.replace("#{username}", String.valueOf(t.getUsername()));
        sql = sql.replace("#{realname}", String.valueOf(t.getRealname()));
        sql = sql.replace("#{otherid}", String.valueOf(t.getOtherid()));
        sql = sql.replace("#{otherre1}", String.valueOf(t.getOtherre1()));
        sql = sql.replace("#{otherre2}", String.valueOf(t.getOtherre2()));
        sql = sql.replace("#{otherre3}", String.valueOf(t.getOtherre3()));
        sql = sql.replace("#{otherre4}", String.valueOf(t.getOtherre4()));
        sql = sql.replace("#{other_type}", String.valueOf(t.getOtherType()));
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
                    //使用自定义类加载器加载并实例化类
                    obj = MyClassLoader.getClassByScn(cgJavaValue).newInstance();
                } else if ("spring".equals(cgJavaType)) {
                    obj = ApplicationContextUtil.getContext().getBean(cgJavaValue);
                }
                if (obj instanceof CgformEnhanceJavaInter) {
                    CgformEnhanceJavaInter javaInter = (CgformEnhanceJavaInter) obj;
                    //调用execute方法，执行Java增强逻辑
                    javaInter.execute("fxj_other_login", data);
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new Exception("执行JAVA增强出现异常！");
            }
        }
    }
}
