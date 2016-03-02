package com.dbflowtest.com.dbflowtest.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by COMP on 16-02-2016.
 */
public class PatientSearchDto {

    @SerializedName("SiteCode")
    String SiteCode;
    @SerializedName("UR")
    String UR;
    @SerializedName("UserId")
    String UserId;
    @SerializedName("AppCode")
    String AppCode;

    public String getSiteCode() {
        return SiteCode;
    }

    public void setSiteCode(String siteCode) {
        SiteCode = siteCode;
    }

    public String getUR() {
        return UR;
    }

    public void setUR(String UR) {
        this.UR = UR;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getAppCode() {
        return AppCode;
    }

    public void setAppCode(String appCode) {
        AppCode = appCode;
    }


}
