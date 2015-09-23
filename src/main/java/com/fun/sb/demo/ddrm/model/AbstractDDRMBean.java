package com.fun.sb.demo.ddrm.model;

import com.fun.sb.demo.ddrm.DistributeDataResourceManager;

import javax.annotation.PostConstruct;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by sunbeansoft on 15-9-22.
 */
public abstract class AbstractDDRMBean implements DDRMResource {

    private static DistributeDataResourceManager manager = new DistributeDataResourceManager();

    @PostConstruct
    public void init() {

        try {
            manager.regist("crm", this);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
