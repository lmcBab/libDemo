package com.lmc.bab.libdemo.retrofit;

/**
 * Created by limin on 2017/10/10.
 */

public class ModelLatestVersion {
    private String appVersion;//	最新版本号
    private String updateLog;//	更新日志
    private int isForce;//	是否强制升级
    private String appUpgradeUrl;//	升级地址
    private int isUpgrade;//	是否需要升级

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getUpdateLog() {
        return updateLog;
    }

    public void setUpdateLog(String updateLog) {
        this.updateLog = updateLog;
    }

    public int getIsForce() {
        return isForce;
    }

    public void setIsForce(int isForce) {
        this.isForce = isForce;
    }

    public String getAppUpgradeUrl() {
        return appUpgradeUrl;
    }

    public void setAppUpgradeUrl(String appUpgradeUrl) {
        this.appUpgradeUrl = appUpgradeUrl;
    }

    public int getIsUpgrade() {
        return isUpgrade;
    }

    public void setIsUpgrade(int isUpgrade) {
        this.isUpgrade = isUpgrade;
    }
}
