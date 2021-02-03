package com.wdy.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author HHL
 * @since 2019-12-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

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


}
