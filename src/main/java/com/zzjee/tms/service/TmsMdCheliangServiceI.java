package com.zzjee.tms.service;
import com.zzjee.tms.entity.TmsMdCheliangEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;
/**
 * Demo class
 *
 * @author admin
 * @date 2016/10/31
 */
public interface TmsMdCheliangServiceI extends CommonService {

 	public void delete(TmsMdCheliangEntity entity) throws Exception;

 	public Serializable save(TmsMdCheliangEntity entity) throws Exception;

 	public void saveOrUpdate(TmsMdCheliangEntity entity) throws Exception;

}
