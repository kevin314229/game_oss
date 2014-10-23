/**
 * 
 */
package com.jcwx.game.service.oss.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jcwx.game.dao.IBaseDAO;
import com.jcwx.game.domain.OssOperation;
import com.jcwx.game.service.oss.IOssOperationService;

/**
 * @author Administrator
 * 
 */
@Service
public class OssOperationService implements IOssOperationService {

    @Autowired
    private IBaseDAO baseDao;

    @Override
    public Integer createOssOperation(OssOperation ossOperation) {
	return (Integer) baseDao.insert("OssOperation.createOssOperation",
		ossOperation);
    }

    @Override
    public Integer deleteOssOperationByID(Integer id) {
	return baseDao.delete("OssOperation.deleteOssOperationByID", id);
    }

    @Override
    public OssOperation getOssOperationByID(Integer id) {
	return (OssOperation) baseDao.queryForObject(
		"OssOperation.getOssOperationByID", id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<OssOperation> getOssOperationList() {
	return baseDao.queryForList("OssOperation.getOssOperationList");
    }

    @Override
    public Integer updateOssOperation(OssOperation ossOperation) {
	return baseDao.update("OssOperation.updateOssOperation", ossOperation);
    }

}
