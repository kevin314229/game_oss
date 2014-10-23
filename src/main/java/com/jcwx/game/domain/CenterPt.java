package com.jcwx.game.domain;

import com.jcwx.game.common.domain.BaseDomain;

public class CenterPt extends BaseDomain {

    private static final long serialVersionUID = 16912290510827527L;

    private int areaId;
   
    private String name;
   
    private String ptName ;
   
    private String ptCode ;

    public int getAreaId() {
        return areaId;
    }
    
    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getPtName() {
        return ptName;
    }
    
    public void setPtName(String ptName) {
        this.ptName = ptName;
    }
    
    public String getPtCode() {
        return ptCode;
    }
    
    public void setPtCode(String ptCode) {
        this.ptCode = ptCode;
    }
   
    

  
   

}