package com.zzjee.wave.service.impl;
import com.zzjee.wave.service.WaveToDownServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import com.zzjee.wave.entity.WaveToDownEntity;
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

@Service("waveToDownService")
@Transactional
public class WaveToDownServiceImpl extends CommonServiceImpl implements WaveToDownServiceI {

	/**
	 * 删除操作增强业务
	 */
 	@Override
    public void delete(WaveToDownEntity entity) throws Exception{
 		super.delete(entity);
 		//执行删除操作增强业务
		this.doDelBus(entity);
 	}

	/**
	 * 新增操作增强业务
	 */
 	@Override
    public Serializable save(WaveToDownEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		//执行新增操作增强业务
 		this.doAddBus(entity);
 		return t;
 	}
 	
 	@Override
    public void saveOrUpdate(WaveToDownEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 		//执行更新操作增强业务
 		this.doUpdateBus(entity);
 	}
 	
 	/**
	 * 新增操作增强业务
	 * @param t
	 * @return
	 */
	private void doAddBus(WaveToDownEntity t) throws Exception{
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
	private void doUpdateBus(WaveToDownEntity t) throws Exception{
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
	private void doDelBus(WaveToDownEntity t) throws Exception{
	    //-----------------sql增强 start----------------------------
	 	//-----------------sql增强 end------------------------------
	 	
	 	//-----------------java增强 start---------------------------
	 	//-----------------java增强 end-----------------------------
 	}
 	
 	private Map<String,Object> populationMap(WaveToDownEntity t){
		Map<String,Object> map = new HashMap<String,Object>(1024);
		map.put("id", t.getId());
		map.put("create_by", t.getCreateBy());
		map.put("create_name", t.getCreateName());
		map.put("cus_code", t.getCusCode());
		map.put("cus_name", t.getCusName());
		map.put("wave_id", t.getWaveId());
		map.put("goods_id", t.getGoodsId());
		map.put("goods_name", t.getGoodsName());
		map.put("im_cus_code", t.getImCusCode());
		map.put("bin_id", t.getBinId());
		map.put("tin_id", t.getTinId());
		map.put("pro_data", t.getProData());
		map.put("base_goodscount", t.getBaseGoodscount());
		map.put("om_bei_zhu", t.getOmBeiZhu());
		map.put("base_unit", t.getBaseUnit());
		map.put("first_rq", t.getFirstRq());
		map.put("second_rq", t.getSecondRq());
		map.put("by1", t.getBy1());
		map.put("by2", t.getBy2());
		map.put("by3", t.getBy3());
		map.put("by4", t.getBy4());
		map.put("by5", t.getBy5());
		return map;
	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @param t
	 * @return
	 */
 	public String replaceVal(String sql,WaveToDownEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{create_by}",String.valueOf(t.getCreateBy()));
 		sql  = sql.replace("#{create_name}",String.valueOf(t.getCreateName()));
 		sql  = sql.replace("#{cus_code}",String.valueOf(t.getCusCode()));
 		sql  = sql.replace("#{cus_name}",String.valueOf(t.getCusName()));
 		sql  = sql.replace("#{wave_id}",String.valueOf(t.getWaveId()));
 		sql  = sql.replace("#{goods_id}",String.valueOf(t.getGoodsId()));
 		sql  = sql.replace("#{goods_name}",String.valueOf(t.getGoodsName()));
 		sql  = sql.replace("#{im_cus_code}",String.valueOf(t.getImCusCode()));
 		sql  = sql.replace("#{bin_id}",String.valueOf(t.getBinId()));
 		sql  = sql.replace("#{tin_id}",String.valueOf(t.getTinId()));
 		sql  = sql.replace("#{pro_data}",String.valueOf(t.getProData()));
 		sql  = sql.replace("#{base_goodscount}",String.valueOf(t.getBaseGoodscount()));
 		sql  = sql.replace("#{om_bei_zhu}",String.valueOf(t.getOmBeiZhu()));
 		sql  = sql.replace("#{base_unit}",String.valueOf(t.getBaseUnit()));
 		sql  = sql.replace("#{first_rq}",String.valueOf(t.getFirstRq()));
 		sql  = sql.replace("#{second_rq}",String.valueOf(t.getSecondRq()));
 		sql  = sql.replace("#{by1}",String.valueOf(t.getBy1()));
 		sql  = sql.replace("#{by2}",String.valueOf(t.getBy2()));
 		sql  = sql.replace("#{by3}",String.valueOf(t.getBy3()));
 		sql  = sql.replace("#{by4}",String.valueOf(t.getBy4()));
 		sql  = sql.replace("#{by5}",String.valueOf(t.getBy5()));
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
				// 如果cgJavaType为"class"，则通过MyClassLoader获取类并实例化
				if("class".equals(cgJavaType)){
					//因新增时已经校验了实例化是否可以成功，所以这块就不需要再做一次判断
					obj = MyClassLoader.getClassByScn(cgJavaValue).newInstance();
				}else if("spring".equals(cgJavaType)){
					// 如果cgJavaType为"spring"，则从Spring上下文中获取bean
					obj = ApplicationContextUtil.getContext().getBean(cgJavaValue);
				}
				// 判断obj是否实现了CgformEnhanceJavaInter接口
				if(obj instanceof CgformEnhanceJavaInter){
					CgformEnhanceJavaInter javaInter = (CgformEnhanceJavaInter) obj;
					javaInter.execute("wave_to_down",data);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("执行JAVA增强出现异常！");
			} 
		}
 	}
}