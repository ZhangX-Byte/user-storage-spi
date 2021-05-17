package com.toy.keycloak.auth.provider.user;

/**
 * 用户信息
 *
 * @author Zhang_Xiang
 * @since 2021/5/13 15:04:21
 */
public class User {

    /**
     * 用户 ID
     */
    private final Long id;

    /**
     * 登录账号
     */
    private String loginAccountId;

    /**
     * 密码
     */
    private String password;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 头像
     */
    private String headImageUrl;

    /**
     * 电话号码
     */
    private String telNo;

    public User(Long id, String loginAccountId, String password, String nickName, String headImageUrl, String telNo) {
        this.id = id;
        this.loginAccountId = loginAccountId;
        this.password = password;
        this.nickName = nickName;
        this.headImageUrl = headImageUrl;
        this.telNo = telNo;
    }

    public Long getId() {
        return id;
    }


    public void setLoginAccountId(String userName) {
        loginAccountId = userName;
    }

    public String getLoginAccountId() {
        return loginAccountId;
    }

}
