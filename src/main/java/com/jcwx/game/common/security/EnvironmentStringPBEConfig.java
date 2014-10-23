package com.jcwx.game.common.security;

public class EnvironmentStringPBEConfig extends
	org.jasypt.encryption.pbe.config.EnvironmentStringPBEConfig {
    public EnvironmentStringPBEConfig() {
	setAlgorithm("PBEWithMD5AndDES");
	setPassword(MasterKeyUtil.getMasterKey());
    }
}
