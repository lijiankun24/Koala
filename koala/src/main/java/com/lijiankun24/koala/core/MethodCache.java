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
    public static int Request() {
        mCacheMethods.add(new MethodInfo());
        return mCacheMethods.size() - 1;
    }

    /**
     * 更新行号
     *
     * @param id      方法 Id
     * @param lineNum 方法行号
     */
    public static void UpdateLineNum(int id, int lineNum) {
        mCacheMethods.get(id).setLineNum(lineNum);
    }

    /**
     * 更新类名方法名
     *
     * @param id         方法 Id
     * @param fileName   方法文件名
     * @param className  方法类名
     * @param methodName 方法名
     */
    public static void UpdateMethodName(int id, String fileName, String className, String methodName) {
        MethodInfo methodInfo = mCacheMethods.get(id);
        methodInfo.setFileName(fileName);
        methodInfo.setClassName(className);
        methodInfo.setMethodName(methodName);
    }

    public static void updateMethodDurationAndResult(Object result, long endTime, int id) {
        MethodInfo methodInfo = mCacheMethods.get(id);
        methodInfo.setEndTime(endTime);
        methodInfo.setResult(result);
    }

    public static void printMethodInfo(int id) {
        MethodInfo methodInfo = mCacheMethods.get(id);
        System.out.println(methodInfo.toString());
    }
}
