package com.zzjee.conf.service.impl;
import com.zzjee.conf.service.WxConfigServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import com.zzjee.conf.entity.WxConfigEntity;
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

@Service("wxConfigService")
@Transactional
public class WxConfigServiceImpl extends CommonServiceImpl implements WxConfigServiceI {

	/**
	 * 删除指定的客户其他信息实体，并在删除后执行额外的业务逻辑增强。
	 *
	 * @param entity
	 */
 	@Override
	public void delete(WxConfigEntity entity) throws Exception{
		//调用父类的delete方法
 		super.delete(entity);
 		//执行删除操作增强业务
		this.doDelBus(entity);
 	}

 	@Override
	public Serializable save(WxConfigEntity entity) throws Exception{
		//调用父类的save方法,执行实际的保存操作
 		Serializable t = super.save(entity);
 		//执行新增操作增强业务
 		this.doAddBus(entity);
 		return t;
 	}

 	@Override
	public void saveOrUpdate(WxConfigEntity entity) throws Exception{
		//调用父类的saveOrUpdate方法
 		super.saveOrUpdate(entity);
 		//执行更新操作增强业务
 		this.doUpdateBus(entity);
 	}

 	/**
	 * 新增操作增强业务
	 * @param t
	 * @return
	 */
	private void doAddBus(WxConfigEntity t) throws Exception{
		//-----------------sql增强 start----------------------------
	 	//-----------------sql增强 end------------------------------

	 	//-----------------java增强 start---------------------------
	 	//-----------------java增强 end-----------------------------
 	}
 	/**
	 * 更新操作增强业务
	 * @param t
	 * @return
	 */
	private void doUpdateBus(WxConfigEntity t) throws Exception{
		//-----------------sql增强 start----------------------------
	 	//-----------------sql增强 end------------------------------

	 	//-----------------java增强 start---------------------------
	 	//-----------------java增强 end-----------------------------
 	}
 	/**
	 * 删除操作增强业务
	 * @param t
	 * @return
	 */
	private void doDelBus(WxConfigEntity t) throws Exception{
	    //-----------------sql增强 start----------------------------
	 	//-----------------sql增强 end------------------------------

	 	//-----------------java增强 start---------------------------
	 	//-----------------java增强 end-----------------------------
 	}
	/**
	 * 将WxConfigEntity对象的属性转换为一个Map
	 * @param t
	 * @return
	 */
 	private Map<String,Object> populationMap(WxConfigEntity t){
		Map<String,Object> map = new HashMap<String,Object>(1024);
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
		map.put("app_code", t.getAppCode());
		map.put("app_remark", t.getAppRemark());
		map.put("app_id", t.getAppId());
		map.put("app_secret", t.getAppSecret());
		map.put("app_key", t.getAppKey());
		map.put("mch_id", t.getMchId());
		map.put("notify_url", t.getNotifyUrl());
		map.put("grant_type", t.getGrantType());
		map.put("wx_by1", t.getWxBy1());
		map.put("wx_by2", t.getWxBy2());
		map.put("wx_by3", t.getWxBy3());
		map.put("wx_by4", t.getWxBy4());
		map.put("wx_by5", t.getWxBy5());
		return map;
	}

 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @param t
	 * @return
	 */
 	public String replaceVal(String sql,WxConfigEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{create_name}",String.valueOf(t.getCreateName()));
 		sql  = sql.replace("#{create_by}",String.valueOf(t.getCreateBy()));
 		sql  = sql.replace("#{create_date}",String.valueOf(t.getCreateDate()));
 		sql  = sql.replace("#{update_name}",String.valueOf(t.getUpdateName()));
 		sql  = sql.replace("#{update_by}",String.valueOf(t.getUpdateBy()));
 		sql  = sql.replace("#{update_date}",String.valueOf(t.getUpdateDate()));
 		sql  = sql.replace("#{sys_org_code}",String.valueOf(t.getSysOrgCode()));
 		sql  = sql.replace("#{sys_company_code}",String.valueOf(t.getSysCompanyCode()));
 		sql  = sql.replace("#{bpm_status}",String.valueOf(t.getBpmStatus()));
 		sql  = sql.replace("#{app_code}",String.valueOf(t.getAppCode()));
 		sql  = sql.replace("#{app_remark}",String.valueOf(t.getAppRemark()));
 		sql  = sql.replace("#{app_id}",String.valueOf(t.getAppId()));
 		sql  = sql.replace("#{app_secret}",String.valueOf(t.getAppSecret()));
 		sql  = sql.replace("#{app_key}",String.valueOf(t.getAppKey()));
 		sql  = sql.replace("#{mch_id}",String.valueOf(t.getMchId()));
 		sql  = sql.replace("#{notify_url}",String.valueOf(t.getNotifyUrl()));
 		sql  = sql.replace("#{grant_type}",String.valueOf(t.getGrantType()));
 		sql  = sql.replace("#{wx_by1}",String.valueOf(t.getWxBy1()));
 		sql  = sql.replace("#{wx_by2}",String.valueOf(t.getWxBy2()));
 		sql  = sql.replace("#{wx_by3}",String.valueOf(t.getWxBy3()));
 		sql  = sql.replace("#{wx_by4}",String.valueOf(t.getWxBy4()));
 		sql  = sql.replace("#{wx_by5}",String.valueOf(t.getWxBy5()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}

 	/**
	 * 执行JAVA增强
	 */
 	private void executeJavaExtend(String cgJavaType,String cgJavaValue,Map<String,Object> data) throws Exception {
 		if(StringUtil.isNotEmpty(cgJavaValue)){
			Object obj = null;
			try {
				if("class".equals(cgJavaType)){
					//因新增时已经校验了实例化是否可以成功，所以这块就不需要再做一次判断
					//使用自定义类加载器加载并实例化类
					obj = MyClassLoader.getClassByScn(cgJavaValue).newInstance();
				}else if("spring".equals(cgJavaType)){
					obj = ApplicationContextUtil.getContext().getBean(cgJavaValue);
				}
				if(obj instanceof CgformEnhanceJavaInter){
					CgformEnhanceJavaInter javaInter = (CgformEnhanceJavaInter) obj;
					//调用execute方法，执行Java增强逻辑
					javaInter.execute("wx_config",data);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("执行JAVA增强出现异常！");
			}
		}
 	}
}
