/**
 * 
 */
package com.jcwx.game.service.oss;

import java.util.List;

import com.jcwx.game.domain.OssOperation;

/**
 * @author Administrator
 * 
 */
public interface IOssOperationService {

    /**
     * 创建OssOperation
     * 
     * @param ossOperation
     * @return 数据影响条数
     */
    public Integer createOssOperation(OssOperation ossOperation);

    /**
     * 通过主键ID删除OssOperation
     * 
     * @param id
     * @return 数据影响条数
     */
    public Integer deleteOssOperationByID(Integer id);

    /**
     * 通过主键ID查询OssOperation
     * 
     * @param id
     * @return OssOperation
     */
    public OssOperation getOssOperationByID(Integer id);

    /**
     * 查询所有的OssOperation
     * 
     * @return OssOperation的集合
     */
    public List<OssOperation> getOssOperationList();

    /**
     * 修改OssOperation
     * 
     * @param ossOperation
     * @return 数据影响条数
     */
    public Integer updateOssOperation(OssOperation ossOperation);

}
