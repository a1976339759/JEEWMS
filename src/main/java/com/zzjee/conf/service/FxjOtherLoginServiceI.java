package com.zzjee.conf.service;
import com.zzjee.conf.entity.FxjOtherLoginEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;
/**
 * Demo class
 *
 * @author admin
 * @date 2016/10/31
 */
public interface FxjOtherLoginServiceI extends CommonService{

 	public void delete(FxjOtherLoginEntity entity) throws Exception;

 	public Serializable save(FxjOtherLoginEntity entity) throws Exception;

 	public void saveOrUpdate(FxjOtherLoginEntity entity) throws Exception;

}
