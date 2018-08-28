package com.lijiankun24.koala.core;

import java.util.Vector;

/**
 * Cost.java
 * <p>
 * Created by lijiankun03 on 2018/8/25.
 */
public class MethodCache {
    /**
     * 方法缓存默认大小
     */
    private static final int INIT_CACHE_SIZE = 10240;
    /**
     * 方法名缓存
     */
    private static Vector<MethodInfo> mCacheMethods = new Vector<>(INIT_CACHE_SIZE);

    /**
     * 占位并生成方法ID
     *
     * @return 返回 方法 Id
     */
    public static int request() {
        mCacheMethods.add(new MethodInfo());
        return mCacheMethods.size() - 1;
    }

    public static void updateMethodInfo(Object result, String className, String methodName, String methodDesc, long startTime, int id) {
        MethodInfo methodInfo = mCacheMethods.get(id);
        long endTime = System.currentTimeMillis();
        methodInfo.setCost((endTime - startTime));
        System.nanoTime();
        methodInfo.setResult(result);
        methodInfo.setMethodDesc(methodDesc);
        methodInfo.setClassName(className);
        methodInfo.setMethodName(methodName);
    }

    public static void printMethodInfo(int id) {
        MethodInfo methodInfo = mCacheMethods.get(id);
        System.out.println(methodInfo.toString());
    }
}
