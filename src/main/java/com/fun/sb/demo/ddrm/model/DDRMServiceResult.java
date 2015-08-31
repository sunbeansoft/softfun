package com.fun.sb.demo.ddrm.model;

/**
 * Created by sunbeansoft on 15-8-31.
 */
public class DDRMServiceResult {
    /**
     * 是否成功
     */
    private boolean success;

    /**
     * 是否需要将数据返回给client
     */
    private boolean needWrite;

    /**
     * 返回的数据
     */
    private Object result;

    /**
     * @param success   是否成功
     * @param needWrite 是否需要返回给客户端
     */
    public DDRMServiceResult(boolean success, boolean needWrite) {
        this.success = success;
        this.needWrite = needWrite;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean isNeedWrite() {
        return needWrite;
    }

    public void setNeedWrite(boolean needWrite) {
        this.needWrite = needWrite;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
