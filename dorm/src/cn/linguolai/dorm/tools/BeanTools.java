package cn.linguolai.dorm.tools;



import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

/**
 * 实现一个Bean工具，用于快速地封装Bean属性，
 * 使用反射机制实现，其中依赖apache地commons工具包
 */
public class BeanTools {
    public BeanTools() {
    }

    /**
     * 将MAP参数封装到Bean中，要求key和Bean地属性名i子
     * @param z
     * @param map
     * @param <T>
     * @return
     */
    public static <T> T toBean(Class<T> z, Map map) {

        try {
            T instance = z.newInstance();
            BeanUtils.populate(instance, map);
            return instance;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 处理get编码问题
     * @param t
     * @param <T>
     * @return
     */
    public static <T> T endcoding(T t) {

        Class clazz = t.getClass();
        //获取成员变量
        Field[] fields = clazz.getDeclaredFields();

        //便利所有成员
        for (int i = 0; i < fields.length; i++) {
            //设置访问权限
            fields[i].setAccessible(true);

            //获取属性类型
            Class type = fields[i].getType();
            if (type.toString().endsWith("String")) {
                try {
                    //获取属性值
                    String value = (String) fields[i].get(t);
                    if (value != null) {
                        //处理编码
                        value = new String(value.getBytes("ISO-8859-1"), "UTF-8");
                        //重新存入
                        fields[i].set(t, value);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }

        }
        return t;
    }
}