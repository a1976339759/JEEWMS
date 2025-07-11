package org.jeecgframework.web.system.service;

import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.web.system.pojo.base.TSCategoryEntity;

/**
 * Demo class
 *
 * @author admin
 * @date 2016/10/31
 */
public interface CategoryServiceI extends CommonService {
    /**
     * 保存分类管理
     *
     * @param category
     */
    void saveCategory(TSCategoryEntity category);

}
