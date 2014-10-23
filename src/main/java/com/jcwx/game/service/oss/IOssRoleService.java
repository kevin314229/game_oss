package com.jcwx.game.service.oss;

import java.util.List;

import com.jcwx.game.domain.OssRole;

public interface IOssRoleService {

    public Integer createOssRole(OssRole ossRole);

    public Integer deleteOssRoleByID(Integer ossRoleID);

    public OssRole getOssRoleByID(Integer ossRoleID);

    public OssRole getOssRoleByProjectIdAndUserName(Integer projectID,
	    String UserName);

    public List<OssRole> getOssRoleList();

    public List<OssRole> getOssRoleListByProjectId(Integer projectId);

    public List<OssRole> getOssRoleListByProjectIdAndUserName(
	    Integer projectID, String UserName);

    public Integer updateOssRole(OssRole ossRole);

}
