package com.zzjee.wm.service;

import com.zzjee.wm.entity.BaStoreAreaEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

/**
 * Demo class
 *
 * @author admin
 * @date 2016/10/31
 */
public interface BaStoreAreaServiceI extends CommonService {

    public void delete(BaStoreAreaEntity entity) throws Exception;

    public Serializable save(BaStoreAreaEntity entity) throws Exception;

    public void saveOrUpdate(BaStoreAreaEntity entity) throws Exception;

}
