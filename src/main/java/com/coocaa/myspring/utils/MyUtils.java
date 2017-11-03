package com.coocaa.myspring.utils;

import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Mr giraffe utils
 * Created by Mr giraffe on 15/9/22.
 */
public class MyUtils {
    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected HttpSession session;
    protected ModelMap model;

    /**
     * 转换
     */
    public static class Conversion {
        /**
         * 对象转换成map
         *
         * @param params  返回map
         * @param object  需要转换的对象
         * @param ex_Null 是否排除空
         * @return
         */
        public static HashMap<String, Object> objToMap(HashMap<String, Object> params, Object object, boolean ex_Null) {
            Field[] fields = object.getClass().getDeclaredFields();

            //获取所有get方法
            Collection methodList = new ArrayList();
            Field field = fields[0];
            Method[] methods = field.getDeclaringClass().getMethods();
            for (Method method : methods) {
                if (method.getName().startsWith("get")) {
                    if (!methodList.contains(method.getName())) {
                        methodList.add(method.getName());
                    }
                }
            }
            //遍历所有get方法
            for (Field item : fields) {
                String name = item.getName();
                Iterator it = methodList.iterator();
                while (it.hasNext()) {
                    String methodName = it.next().toString();
                    if (methodName.toLowerCase().equals("get" + name.toLowerCase())) {
                        try {
                            Method m = object.getClass().getMethod(methodName);
                            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            String val = "";
                            if (ex_Null) {
                                val = m.invoke(object) == null ? "" : m.invoke(object).toString();
                                if (!val.isEmpty()) {
                                    if (m.getGenericReturnType().equals(Date.class)) {
                                        Date date = (Date) m.invoke(object);
                                        val = date == null ? "" : format.format(date);
                                    }
                                    params.put(name, val);
                                }
                            } else {
                                val = m.invoke(object) == null ? "" : m.invoke(object).toString();
                                if (!val.isEmpty() && m.getGenericReturnType().equals(Date.class)) {
                                    Date date = (Date) m.invoke(object);
                                    val = date == null ? "" : format.format(date);
                                }
                                params.put(name, val);
                            }
                            break;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            return params;
        }

        /**
         * 对象转String
         *
         * @param object  要转换的对象
         * @param ex_Null 是否排除空
         * @return
         */
        public static String objToParamsString(Object object, boolean ex_Null) {
            String str = "";
            Field[] fields = object.getClass().getDeclaredFields();

            //获取所有get方法
            Collection methodList = new ArrayList();
            Field field = fields[0];
            Method[] methods = field.getDeclaringClass().getMethods();
            for (Method method : methods) {
                if (method.getName().startsWith("get")) {
                    if (!methodList.contains(method.getName())) {
                        methodList.add(method.getName());
                    }
                }
            }
            //遍历所有get方法
            for (Field item : fields) {
                String name = item.getName();
                Iterator it = methodList.iterator();
                while (it.hasNext()) {
                    String methodName = it.next().toString();
                    if (methodName.toLowerCase().indexOf(name.toLowerCase()) > 0) {
                        try {
                            Method m = object.getClass().getMethod(methodName);
                            if (ex_Null) {
                                if ((m.invoke(object) != null)) {
                                    String val = m.invoke(object).toString();
                                    if (str != "") {
                                        str += "&" + item.getName().toString() + "=" + val;
                                    } else {
                                        str += item.getName().toString() + "=" + val;
                                    }
                                }
                            } else {
                                String val = m.invoke(object) == null ? "" : m.invoke(object).toString();
                                if (str != "") {
                                    str += "&" + item.getName().toString() + "=" + val;
                                } else {
                                    str += item.getName().toString() + "=" + val;
                                }
                            }

                            break;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            return str;
        }
    }

    /**
     * DataTables插件相关
     */
    public static class DataTables {
        /**
         * 获取请求的排序列及模糊查询关键字
         *
         * @param request request
         * @param params  需要返回的map
         * @return
         */
        public static HashMap<String, Object> getRequestSortParams(HttpServletRequest request, HashMap<String, Object> params) {
            //获取排序列号
            String columnNum = request.getParameter("order[0][column]");
            //获取排序方式
            String dir = request.getParameter("order[0][dir]");
            if (dir != null && dir.length() > 0) {
                //获取排序列名
                String column = request.getParameter("columns[" + columnNum + "][data]");
                params.put("orderBy", column + " " + dir);
            }
            String search = request.getParameter("searchKey");
            if (search != null) {
                params.put("search", search);
            }
            return params;
        }
    }

    /**
     * 自定义uuid
     */
    public static class MyUuid {

        public static String[] chars = new String[]{"a", "b", "c", "d", "e", "f",
                "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
                "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
                "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
                "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
                "W", "X", "Y", "Z"};


        public static String generateShortUuid() {
            StringBuffer shortBuffer = new StringBuffer();
            String uuid = UUID.randomUUID().toString().replace("-", "");
            for (int i = 0; i < 8; i++) {
                String str = uuid.substring(i * 4, i * 4 + 4);
                int x = Integer.parseInt(str, 16);
                shortBuffer.append(chars[x % 0x3E]);
            }
            return shortBuffer.toString();
        }

        public static String getUuid() {
            return UUID.randomUUID().toString().replace("-", "");
        }

        public static void main(String[] args) {
            Date sd = new Date();
            HashMap<String, String> hm = new HashMap<>();
            for (Integer i = 0; i < 1; i++) {
                String sUuid = generateShortUuid();
                hm.put(sUuid, sUuid);
                System.out.println(sUuid);
            }
            System.out.println(hm.size());
            System.out.println(sd);
            System.out.println(new Date());
        }
    }
}
