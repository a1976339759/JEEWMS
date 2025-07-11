package com.zzjee.uniapp.service.impl;
import com.zzjee.uniapp.service.WmsAppFunctionServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import com.zzjee.uniapp.entity.WmsAppFunctionEntity;
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

@Service("wmsAppFunctionService")
@Transactional
public class WmsAppFunctionServiceImpl extends CommonServiceImpl implements WmsAppFunctionServiceI {

	/**
	 * 定义一个名为delete的方法，接收一个WmsAppFunctionEntity类型的参数entity，并抛出异常
	 * @return
	 */
 	public void delete(WmsAppFunctionEntity entity) throws Exception{
 		super.delete(entity);
 		//执行删除操作增强业务
		this.doDelBus(entity);
 	}


 	public Serializable save(WmsAppFunctionEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		//执行新增操作增强业务
 		this.doAddBus(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(WmsAppFunctionEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 		//执行更新操作增强业务
 		this.doUpdateBus(entity);
 	}
 	
 	/**
	 * 新增操作增强业务
	 * @param t
	 * @return
	 */
	private void doAddBus(WmsAppFunctionEntity t) throws Exception{
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
	private void doUpdateBus(WmsAppFunctionEntity t) throws Exception{
		//-----------------sql增强 start----------------------------
	 	//-----------------sql增强 end------------------------------
	 	
	 	//-----------------java增强 start---------------------------
	 	//-----------------java增强 end-----------------------------
 	}
 	/**
	 * 删除操作增强业务
	 * @return
	 */
	private void doDelBus(WmsAppFunctionEntity t) throws Exception{
	    //-----------------sql增强 start----------------------------
	 	//-----------------sql增强 end------------------------------
	 	
	 	//-----------------java增强 start---------------------------
	 	//-----------------java增强 end-----------------------------
 	}
 	
 	private Map<String,Object> populationMap(WmsAppFunctionEntity t){
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
		map.put("appmodel_code", t.getAppmodelCode());
		map.put("appmodel_name", t.getAppmodelName());
		map.put("appmodel_sort", t.getAppmodelSort());
		map.put("type", t.getType());
		map.put("route", t.getRoute());
		map.put("picture", t.getPicture());
		map.put("if_bind", t.getIfBind());
		map.put("query1", t.getQuery1());
		map.put("query2", t.getQuery2());
		map.put("query3", t.getQuery3());
		map.put("query4", t.getQuery4());
		map.put("query5", t.getQuery5());
		map.put("query6", t.getQuery6());
		return map;
	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @param t
	 * @return
	 */
 	public String replaceVal(String sql,WmsAppFunctionEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{create_name}",String.valueOf(t.getCreateName()));
 		sql  = sql.replace("#{create_by}",String.valueOf(t.getCreateBy()));
 		sql  = sql.replace("#{create_date}",String.valueOf(t.getCreateDate()));
 		sql  = sql.replace("#{update_name}",String.valueOf(t.getUpdateName()));
 		sql  = sql.replace("#{update_by}",String.valueOf(t.getUpdateBy()));
 		sql  = sql.replace("#{update_date}",String.valueOf(t.getUpdateDate()));
 		sql  = sql.replace("#{sys_org_code}",String.valueOf(t.getSysOrgCode()));
 		sql  = sql.replace("#{sys_company_code}",String.valueOf(t.getSysCompanyCode()));
 		sql  = sql.replace("#{appmodel_code}",String.valueOf(t.getAppmodelCode()));
 		sql  = sql.replace("#{appmodel_name}",String.valueOf(t.getAppmodelName()));
 		sql  = sql.replace("#{appmodel_sort}",String.valueOf(t.getAppmodelSort()));
 		sql  = sql.replace("#{type}",String.valueOf(t.getType()));
 		sql  = sql.replace("#{route}",String.valueOf(t.getRoute()));
 		sql  = sql.replace("#{picture}",String.valueOf(t.getPicture()));
 		sql  = sql.replace("#{if_bind}",String.valueOf(t.getIfBind()));
 		sql  = sql.replace("#{query1}",String.valueOf(t.getQuery1()));
 		sql  = sql.replace("#{query2}",String.valueOf(t.getQuery2()));
 		sql  = sql.replace("#{query3}",String.valueOf(t.getQuery3()));
 		sql  = sql.replace("#{query4}",String.valueOf(t.getQuery4()));
 		sql  = sql.replace("#{query5}",String.valueOf(t.getQuery5()));
 		sql  = sql.replace("#{query6}",String.valueOf(t.getQuery6()));
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
					obj = MyClassLoader.getClassByScn(cgJavaValue).newInstance();
				}else if("spring".equals(cgJavaType)){
					// 如果cgJavaType为"spring"，则从Spring上下文中获取bean
					obj = ApplicationContextUtil.getContext().getBean(cgJavaValue);
				}
				if(obj instanceof CgformEnhanceJavaInter){
					// 将obj转换为CgformEnhanceJavaInter类型
					CgformEnhanceJavaInter javaInter = (CgformEnhanceJavaInter) obj;
					// 调用execute方法执行增强逻辑
					javaInter.execute("wms_app_function",data);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("执行JAVA增强出现异常！");
			} 
		}
 	}
}