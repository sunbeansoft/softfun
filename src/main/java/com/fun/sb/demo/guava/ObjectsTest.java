package com.fun.sb.demo.guava;

import com.google.common.base.Objects;
import org.junit.Test;
  
/**
 * Objects：Object方法工具类
 * User: Realfighter
 * Date: 2014/8/20
 * Time: 9:42
 */
public class ObjectsTest {
  
    //打印方法
    private static void print(Object obj) {
        System.out.println(String.valueOf(obj));
    }
  
    @Test
    public void testObjects() {
        //equal方法：用于判断两个对象是否相等,避免空指针
        Objects.equal(null, 123);//false
        Objects.equal(123, 123);//true
        Objects.equal(123, null);//false
        Objects.equal(null, null);//true
  
        //hashCode方法：获取对传入的字段做哈希运算后产生的hashCode值
        //判断两个对象完全相同时，除了equals为真，hashCode也需要相同
  
        //firstNonNull：传入两个参数，返回两个参数中不为空的那个，如果二者都不为空，返回第一个参数。
        print(Objects.firstNonNull(null,"you are null"));//this is null
        print(Objects.firstNonNull("i am not null","you are null"));//i am not null
        print(Objects.firstNonNull(null,null));//NullPointerException
  
        //toStringHelper方法:用于轻松愉快的编写toString方法
        print(Objects.toStringHelper(this)
                .add("name", "Realfighter")
                .add("age", null)
        //注释omitNullValues后输出：ObjectsTest{name=Realfighter, age=null}
                .omitNullValues()
                .toString()); //ObjectsTest{name=Realfighter}
    }
  
}