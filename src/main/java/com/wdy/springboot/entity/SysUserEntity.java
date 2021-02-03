package com.wdy.springboot.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author wdy
 * @since 2021-02-03
 */
@TableName("sys_user")
public class SysUserEntity extends Model<SysUserEntity> {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("key")
    private String key;

    /**
     * 登录名
     */
    @TableField("name")
    private String name;

    /**
     * 用户姓名
     */
    @TableField("account")
    private String account;

    /**
     * 密码(MD5加密)
     */
    @TableField("password")
    private String password;

    @TableField("salt2")
    private String salt2;

    @TableField("org_key")
    private String orgKey;

    @TableField("org_name")
    private String orgName;

    @TableField("picture")
    private String picture;

    /**
     * 照片对象
     */
    @TableField("picture_obj")
    private String pictureObj;

    @TableField("open_id")
    private String openId;

    @TableField("union_id")
    private String unionId;

    @TableField("gender")
    private Integer gender;

    /**
     * 邮件地址
     */
    @TableField("email")
    private String email;

    /**
     * 联系电话
     */
    @TableField("phone")
    private String phone;

    /**
     * 状态 {1:正常,2:锁定,3:禁用}
     */
    @TableField("status")
    private String status;

    @TableField("online_status")
    private Integer onlineStatus;

    /**
     * 登录失败次数
     */
    @TableField("login_fail_times")
    private Integer loginFailTimes;

    /**
     * 登录时间
     */
    @TableField("last_login_time")
    private LocalDateTime lastLoginTime;

    /**
     * 解锁时间(密码错误五次锁定账户24小时)
     */
    @TableField("unlock_time")
    private LocalDateTime unlockTime;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("creator")
    private String creator;

    @TableField("update_time")
    private LocalDateTime updateTime;

    @TableField("updator")
    private String updator;

    @TableField("version")
    private LocalDateTime version;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;

    @TableField("deleted")
    private Boolean deleted;

    @TableField("manager_level")
    private String managerLevel;

    @TableField("manager_level_name")
    private String managerLevelName;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt2() {
        return salt2;
    }

    public void setSalt2(String salt2) {
        this.salt2 = salt2;
    }

    public String getOrgKey() {
        return orgKey;
    }

    public void setOrgKey(String orgKey) {
        this.orgKey = orgKey;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getPictureObj() {
        return pictureObj;
    }

    public void setPictureObj(String pictureObj) {
        this.pictureObj = pictureObj;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getOnlineStatus() {
        return onlineStatus;
    }

    public void setOnlineStatus(Integer onlineStatus) {
        this.onlineStatus = onlineStatus;
    }

    public Integer getLoginFailTimes() {
        return loginFailTimes;
    }

    public void setLoginFailTimes(Integer loginFailTimes) {
        this.loginFailTimes = loginFailTimes;
    }

    public LocalDateTime getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(LocalDateTime lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public LocalDateTime getUnlockTime() {
        return unlockTime;
    }

    public void setUnlockTime(LocalDateTime unlockTime) {
        this.unlockTime = unlockTime;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdator() {
        return updator;
    }

    public void setUpdator(String updator) {
        this.updator = updator;
    }

    public LocalDateTime getVersion() {
        return version;
    }

    public void setVersion(LocalDateTime version) {
        this.version = version;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String getManagerLevel() {
        return managerLevel;
    }

    public void setManagerLevel(String managerLevel) {
        this.managerLevel = managerLevel;
    }

    public String getManagerLevelName() {
        return managerLevelName;
    }

    public void setManagerLevelName(String managerLevelName) {
        this.managerLevelName = managerLevelName;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "SysUserEntity{" +
        "id=" + id +
        ", key=" + key +
        ", name=" + name +
        ", account=" + account +
        ", password=" + password +
        ", salt2=" + salt2 +
        ", orgKey=" + orgKey +
        ", orgName=" + orgName +
        ", picture=" + picture +
        ", pictureObj=" + pictureObj +
        ", openId=" + openId +
        ", unionId=" + unionId +
        ", gender=" + gender +
        ", email=" + email +
        ", phone=" + phone +
        ", status=" + status +
        ", onlineStatus=" + onlineStatus +
        ", loginFailTimes=" + loginFailTimes +
        ", lastLoginTime=" + lastLoginTime +
        ", unlockTime=" + unlockTime +
        ", createTime=" + createTime +
        ", creator=" + creator +
        ", updateTime=" + updateTime +
        ", updator=" + updator +
        ", version=" + version +
        ", remark=" + remark +
        ", deleted=" + deleted +
        ", managerLevel=" + managerLevel +
        ", managerLevelName=" + managerLevelName +
        "}";
    }
}
