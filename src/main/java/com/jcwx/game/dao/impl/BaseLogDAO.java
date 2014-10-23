package com.jcwx.game.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.jcwx.game.dao.IBaseLogDAO;

//@Repository
public class BaseLogDAO extends SqlMapClientDaoSupport implements IBaseLogDAO {
    // @Autowired()
    private @Qualifier("logSqlMapClient")
    SqlMapClient mySqlMapClient;

    @Override
    public Integer delete(String statementName, Object parameterObject) {
	return this.getSqlMapClientTemplate().delete(statementName,
		parameterObject);
    }

    public SqlMapClient getMySqlMapClient() {
	return mySqlMapClient;
    }

    @PostConstruct
    public void injectSessionFactory() {
	super.setSqlMapClient(mySqlMapClient);
    }

    @Override
    public Object insert(String statementName, Object parameterObject) {
	return this.getSqlMapClientTemplate().insert(statementName,
		parameterObject);
    }

    @Override
    public List queryForList(String statementName) {
	return this.getSqlMapClientTemplate().queryForList(statementName);
    }

    @Override
    public List queryForList(String statementName, Object parameterObject) {
	return this.getSqlMapClientTemplate().queryForList(statementName,
		parameterObject);
    }

    @Override
    public Object queryForObject(String statementName) {
	return this.getSqlMapClientTemplate().queryForObject(statementName);
    }

    @Override
    public Object queryForObject(String statementName, Object parameterObject) {
	return this.getSqlMapClientTemplate().queryForObject(statementName,
		parameterObject);
    }

    public void setMySqlMapClient(SqlMapClient mySqlMapClient) {
	this.mySqlMapClient = mySqlMapClient;
    }

    @Override
    public Integer update(String statementName, Object parameterObject) {
	return this.getSqlMapClientTemplate().update(statementName,
		parameterObject);
    }

}
