package com.fun.sb.demo.ddrm;


import com.baidu.nuomi.crm.ddrm.model.DDRMResult;

/**
 * 通过这个方法获取DistributeDataResourseManager中所管理的数据
 * Created by sunbeansoft on 15-8-19.
 */

public interface DDRMAgent {

    /**
     * 获取数据
     *
     * @param domain        域名称
     * @param classFullName 类全名
     * @return
     */
    public DDRMResult fetchDDRMData(String domain, String classFullName);

    /**
     * 修改数据
     *
     * @param domain        域名称
     * @param classFullName 类全名
     * @param jsonData      新数据
     * @return
     */
    public boolean putDDRMData(String domain, String classFullName, String jsonData);

}
