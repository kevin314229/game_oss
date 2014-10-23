package com.jcwx.game.service.impl;

import java.util.List;

import com.googlecode.genericdao.search.ExampleOptions;
import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.ISearch;
import com.googlecode.genericdao.search.SearchResult;
import com.jcwx.game.admin.oss.domain.OssMenu;

public interface IMenuService {

    public abstract OssMenu find(String id);

    public abstract OssMenu[] find(String... ids);

    public abstract OssMenu getReference(String id);

    public abstract OssMenu[] getReferences(String... ids);

    public abstract boolean remove(OssMenu entity);

    public abstract void remove(OssMenu... entities);

    public abstract boolean removeById(String id);

    public abstract void removeByIds(String... ids);

    public abstract List<OssMenu> findAll();

    public abstract <RT> List<RT> search(ISearch search);

    public abstract <RT> RT searchUnique(ISearch search);

    public abstract int count(ISearch search);

    public abstract <RT> SearchResult<RT> searchAndCount(ISearch search);

    public abstract boolean isAttached(OssMenu entity);

    public abstract void refresh(OssMenu... entities);

    public abstract void flush();

    public abstract Filter getFilterFromExample(OssMenu example);

    public abstract Filter getFilterFromExample(OssMenu example,
	    ExampleOptions options);

}