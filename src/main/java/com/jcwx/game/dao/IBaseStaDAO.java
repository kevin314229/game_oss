package com.jcwx.game.dao;

import java.util.List;

public interface IBaseStaDAO {
    // public void createPlayer( ASObject body, PlayerOnlineCache cache );

    public Integer delete(String statementName, Object parameterObject);

    public Object insert(String statementName, Object parameterObject);

    public List queryForList(String statementName);

    public List queryForList(String statementName, Object parameterObject);

    public Object queryForObject(String statementName);

    public Object queryForObject(String statementName, Object parameterObject);

    public Integer update(String statementName, Object parameterObject);
}
