package com.zwh.common.passport;


/**
 * 认证token信息工具类
 * Created by Cougar on 17-02-27.
 */
public class TokenInfo {

    private AuthFlag auth;//登录相关授权
    private RoleFlag role;//用户角色授权
    private String uid;//用户Id
    private String did;//设备Id
    private int crtTime;//token创建时间

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TokenInfo{");
        sb.append("auth=").append(auth.getValue());
        sb.append(", role=").append(role.getValue());
        sb.append(", uid=").append(uid);
        sb.append(", did=").append(did);
        sb.append(", crtTime=").append(crtTime);
        sb.append('}');
        return sb.toString();
    }

    public AuthFlag getAuth() {
        return auth;
    }

    public void setAuth(AuthFlag auth) {
        this.auth = auth;
    }

    public RoleFlag getRole() {
        return role;
    }

    public void setRole(RoleFlag role) {
        this.role = role;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uin) {
        this.uid = uin;
    }

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }

    public int getCrtTime() {
        return crtTime;
    }

    public void setCrtTime(int crtTime) {
        this.crtTime = crtTime;
    }


}
