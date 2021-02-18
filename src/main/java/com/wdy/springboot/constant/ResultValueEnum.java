package com.wdy.springboot.constant;

/**
 * API返回枚举类
 *
 * @author lhp
 * @creed Talk is cheap,show me the code
 * @date 2020年12月10日 14时46分
 */
public enum ResultValueEnum implements BaseEnum<Integer> {

    /**
     * 操作失败
     */
    FAIL(0, "操作失败"),
    /**
     * 操作成功
     */
    SUCCESS(200, "操作成功"),
    /**
     * 业务异常
     */
    SERVICE_EXCEPTION(100, "业务异常:%s"),
    /**
     * 开发错误
     */
    DEVELOP_EXCEPTION(103, "开发错误:%s"),
    /**
     * 系统异常
     */
    SYSTEM_EXCEPTION(500, "系统异常:%s"),
    /**
     * 应用程序错误
     */
    SYS_APPLICATION_ERROR(1001, "应用程序错误"),
    /**
     * timestamp is not null or zero.
     */
    PARAM_TIMESTAMP_ERROR(1002, "timestamp is not null or zero."),
    /**
     * userId is not null.
     */
    PARAM_USERID_ERROR(1003, "userId is not null."),
    /**
     * plat is not null.
     */
    PARAM_PLAT_ERROR(1004, "plat is not null."),
    /**
     * 您当前的会话已超时，请重新登录
     */
    SYS_USER_NO_LOGIN(1005, "您当前的会话已超时，请重新登录"),
    /**
     * 对不起，您没有权限进行此操作。
     */
    SYS_NO_ACCESS_PERMISSIONS(1006, "对不起，您没有权限进行此操作。"),
    /**
     * 上次操作系统进行中，请稍后...
     */
    SYS_EXECUTING(1007, "上次操作系统进行中，请稍后..."),
    /**
     * 操作超时
     */
    SYS_TIME_OUT(1008, "操作超时"),
    /**
     * 重复提交
     */
    SYS_SUB_REPEAT(1009, "重复提交"),
    /**
     * 空指针错误
     */
    EXCEPTIONS_EX_NPE(1010, "空指针错误"),
    /**
     * 参数签名错误
     */
    SYS_SIGN_ERROR(1011, "参数签名错误"),
    /**
     * 发送短信失败
     */
    SYS_MESSAGE_FAIL(1012, "发送短信失败"),
    /**
     * 参数错误
     */
    PARA_ERROR(1013, "参数错误"),


    ;

    private final int code;

    private final String msg;

    ResultValueEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public Integer getKey() {
        return this.code;
    }

    @Override
    public String getValue() {
        return this.msg;
    }

    @Override
    public String toString() {
        return "[key=" + this.code + ",title=" + this.name() + "]";
    }
}
