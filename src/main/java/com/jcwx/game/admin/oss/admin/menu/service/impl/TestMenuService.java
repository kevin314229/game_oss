package com.jcwx.game.admin.oss.admin.menu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.genericdao.search.ExampleOptions;
import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.ISearch;
import com.googlecode.genericdao.search.SearchResult;
import com.jcwx.game.admin.oss.admin.menu.dao.TestMenuDao;
import com.jcwx.game.admin.oss.domain.OssMenu;
import com.jcwx.game.service.impl.IMenuService;

@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class TestMenuService implements IMenuService {
    @Autowired
    public  TestMenuDao baseDao;

    /* (non-Javadoc)
     * @see com.jcwx.game.service.impl.IMenuService#find(java.lang.String)
     */
    @Override
    public OssMenu find(String id) {
	return baseDao.find(id);
    }

    /* (non-Javadoc)
     * @see com.jcwx.game.service.impl.IMenuService#find(java.lang.String)
     */
    @Override
    public OssMenu[] find(String... ids) {
	return baseDao.find(ids);
    }

    /* (non-Javadoc)
     * @see com.jcwx.game.service.impl.IMenuService#getReference(java.lang.String)
     */
    @Override
    public OssMenu getReference(String id) {
	return baseDao.getReference(id);
    }

    /* (non-Javadoc)
     * @see com.jcwx.game.service.impl.IMenuService#getReferences(java.lang.String)
     */
    @Override
    public OssMenu[] getReferences(String... ids) {
	return baseDao.getReferences(ids);
    }

    /* (non-Javadoc)
     * @see com.jcwx.game.service.impl.IMenuService#remove(com.jcwx.game.admin.oss.domain.OssMenu)
     */
    @Override
    public boolean remove(OssMenu entity) {
	return baseDao.remove(entity);
    }

    /* (non-Javadoc)
     * @see com.jcwx.game.service.impl.IMenuService#remove(com.jcwx.game.admin.oss.domain.OssMenu)
     */
    @Override
    public void remove(OssMenu... entities) {
	baseDao.remove(entities);
    }

    /* (non-Javadoc)
     * @see com.jcwx.game.service.impl.IMenuService#removeById(java.lang.String)
     */
    @Override
    public boolean removeById(String id) {
	return baseDao.removeById(id);
    }

    /* (non-Javadoc)
     * @see com.jcwx.game.service.impl.IMenuService#removeByIds(java.lang.String)
     */
    @Override
    public void removeByIds(String... ids) {
	baseDao.removeByIds(ids);
    }

    /* (non-Javadoc)
     * @see com.jcwx.game.service.impl.IMenuService#findAll()
     */
    @Override
    public List<OssMenu> findAll() {
	return baseDao.findAll();
    }

    /* (non-Javadoc)
     * @see com.jcwx.game.service.impl.IMenuService#search(com.googlecode.genericdao.search.ISearch)
     */
    @Override
    public <RT> List<RT> search(ISearch search) {
	return baseDao.search(search);
    }

    /* (non-Javadoc)
     * @see com.jcwx.game.service.impl.IMenuService#searchUnique(com.googlecode.genericdao.search.ISearch)
     */
    @Override
    public <RT> RT searchUnique(ISearch search) {
	return baseDao.searchUnique(search);
    }

    /* (non-Javadoc)
     * @see com.jcwx.game.service.impl.IMenuService#count(com.googlecode.genericdao.search.ISearch)
     */
    @Override
    public int count(ISearch search) {
	return baseDao.count(search);
    }

    /* (non-Javadoc)
     * @see com.jcwx.game.service.impl.IMenuService#searchAndCount(com.googlecode.genericdao.search.ISearch)
     */
    @Override
    public <RT> SearchResult<RT> searchAndCount(ISearch search) {
	return baseDao.searchAndCount(search);
    }

    /* (non-Javadoc)
     * @see com.jcwx.game.service.impl.IMenuService#isAttached(com.jcwx.game.admin.oss.domain.OssMenu)
     */
    @Override
    public boolean isAttached(OssMenu entity) {
	return baseDao.isAttached(entity);
    }

    /* (non-Javadoc)
     * @see com.jcwx.game.service.impl.IMenuService#refresh(com.jcwx.game.admin.oss.domain.OssMenu)
     */
    @Override
    public void refresh(OssMenu... entities) {
	baseDao.refresh(entities);
    }

    /* (non-Javadoc)
     * @see com.jcwx.game.service.impl.IMenuService#flush()
     */
    @Override
    public void flush() {
	baseDao.flush();
    }

    /* (non-Javadoc)
     * @see com.jcwx.game.service.impl.IMenuService#getFilterFromExample(com.jcwx.game.admin.oss.domain.OssMenu)
     */
    @Override
    public Filter getFilterFromExample(OssMenu example) {
	return baseDao.getFilterFromExample(example);
    }

    /* (non-Javadoc)
     * @see com.jcwx.game.service.impl.IMenuService#getFilterFromExample(com.jcwx.game.admin.oss.domain.OssMenu, com.googlecode.genericdao.search.ExampleOptions)
     */
    @Override
    public Filter getFilterFromExample(OssMenu example, ExampleOptions options) {
	return baseDao.getFilterFromExample(example, options);
    }


}
