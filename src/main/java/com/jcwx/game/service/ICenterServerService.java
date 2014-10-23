package com.jcwx.game.service;

import java.util.List;
import java.util.Map;

import com.jcwx.game.domain.CenterPt;
import com.jcwx.game.domain.CenterServer;

/**
 * 服务器Service接口
 * 
 * @author derek
 * @version 1.0
 */

public interface ICenterServerService {
   
    public List<CenterServer> queryCenterServers();
    
    public List<CenterPt> queryCenterPts(Integer areaId);
    
    public Map<String, Object> queryCenterArea(Integer areaId,String ptId);
}
