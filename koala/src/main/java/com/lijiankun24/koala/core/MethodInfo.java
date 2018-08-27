package com.lijiankun24.koala.core;

/**
 * Cost.java
 * <p>
 * Created by lijiankun03 on 2018/8/25.
 */
public class MethodInfo {
    private String mClassName;      // 类名
    private String mMethodName;     // 方法名
    private String mFileName;       // 文件名
    private int mLineNum;           // 行号
    private long mStartTime;        // 开始时间
    private long mEndTime;          // 结束时间
    private Object mResult;          // 返回结果

    @Override
    public String toString() {
        return mClassName + ":" + mMethodName + ":" + mLineNum;
    }

    /**
     * @return 返回类名
     */
    public String getClassName() {
        return mClassName;
    }

    /**
     * @param className 设置类名
     */
    public void setClassName(String className) {
        mClassName = className;
    }

    /**
     * @return 返回方法名
     */
    public String getMethodName() {
        return mMethodName;
    }

    /**
     * @param methodName 设置方法名
     */
    public void setMethodName(String methodName) {
        mMethodName = methodName;
    }

    /**
     * @return 返回文件名
     */
    public String getFileName() {
        return mFileName;
    }

    /**
     * @param fileName 设置文件名
     */
    public void setFileName(String fileName) {
        mFileName = fileName;
    }

    /**
     * @return 返回行号
     */
    public int getLineNum() {
        return mLineNum;
    }

    /**
     * @param lineNum 设置行号
     */
    public void setLineNum(int lineNum) {
        mLineNum = lineNum;
    }

    /**
     * @return 返回开始时间
     */
    public long getStartTime() {
        return mStartTime;
    }

    /**
     * 设置开始时间
     *
     * @param startTime 开始时间
     */
    public void setStartTime(long startTime) {
        this.mStartTime = startTime;
    }

    /**
     * @return 返回结束时间
     */
    public long getEndTime() {
        return mEndTime;
    }

    /**
     * 设置结束时间
     *
     * @param endTime 结束时间
     */
    public void setEndTime(long endTime) {
        this.mEndTime = endTime;
    }

    /**
     * @return 返回方法执行时间
     */
    public long getMethodCost() {
        if (mStartTime == 0 || mEndTime == 0) {
            return 0;
        }
        if (mEndTime < mStartTime) {
            return 0;
        }
        return (mEndTime - mStartTime);
    }

    public Object getResult() {
        return mResult;
    }

    public void setResult(Object result) {
        this.mResult = result;
    }
}
