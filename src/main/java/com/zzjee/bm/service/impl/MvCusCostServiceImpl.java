package com.zzjee.bm.service.impl;
import com.zzjee.bm.service.MvCusCostServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import com.zzjee.bm.entity.MvCusCostEntity;
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

@Service("mvCusCostService")
@Transactional
public class MvCusCostServiceImpl extends CommonServiceImpl implements MvCusCostServiceI {

	/**
	 * 删除指定的客户其他信息实体，并在删除后执行额外的业务逻辑增强。
	 *
	 * @param entity
	 */
 	@Override
    public void delete(MvCusCostEntity entity) throws Exception{
		//调用父类的delete方法
 		super.delete(entity);
 		//执行删除操作增强业务
		this.doDelBus(entity);
 	}
 	@Override
    public Serializable save(MvCusCostEntity entity) throws Exception{
		//调用父类的save方法,执行实际的保存操作
 		Serializable t = super.save(entity);
 		//执行新增操作增强业务
 		this.doAddBus(entity);
 		return t;
 	}
 	@Override
    public void saveOrUpdate(MvCusCostEntity entity) throws Exception{
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
	private void doAddBus(MvCusCostEntity t) throws Exception{
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
	private void doUpdateBus(MvCusCostEntity t) throws Exception{
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
	private void doDelBus(MvCusCostEntity t) throws Exception{
	    //-----------------sql增强 start----------------------------
	 	//-----------------sql增强 end------------------------------

	 	//-----------------java增强 start---------------------------
	 	//-----------------java增强 end-----------------------------
 	}
	/**
	 * 将MvCusCostEntity对象的属性转换为一个Map
	 * @param t
	 * @return
	 */
 	private Map<String,Object> populationMap(MvCusCostEntity t){
		Map<String,Object> map = new HashMap<String,Object>(1024);
		map.put("id", t.getId());
		map.put("cus_code", t.getCusCode());
		map.put("cus_name", t.getCusName());
		map.put("cost_data", t.getCostData());
		return map;
	}

 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @param t
	 * @return
	 */
 	public String replaceVal(String sql,MvCusCostEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{cus_code}",String.valueOf(t.getCusCode()));
 		sql  = sql.replace("#{cus_name}",String.valueOf(t.getCusName()));
 		sql  = sql.replace("#{cost_data}",String.valueOf(t.getCostData()));
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
					javaInter.execute("mv_cus_cost",data);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("执行JAVA增强出现异常！");
			}
		}
 	}
}
