package com.lijiankun24.koala.core;

import android.util.Log;

import java.util.Locale;

public class Printer {
    private static final String TAG = "KoalaLog";

    private static final char TOP_LEFT_CORNER = '┌';
    private static final char BOTTOM_LEFT_CORNER = '└';
    private static final char HORIZONTAL_LINE = '│';
    private static final String DOUBLE_DIVIDER = "───────────────────────────────────------";
    private static final String TOP_BORDER = TOP_LEFT_CORNER + DOUBLE_DIVIDER + DOUBLE_DIVIDER;
    private static final String BOTTOM_BORDER = BOTTOM_LEFT_CORNER + DOUBLE_DIVIDER + DOUBLE_DIVIDER;

    private static final String CLASS_NAME_FORMAT = "%s The class's name: %s";
    private static final String METHOD_NAME_FORMAT = "%s The method's name: %s";
    private static final String ARGUMENT_FORMAT = "%s The arguments: ";
    private static final String RESULT_FORMAT = "%s The result: ";
    private static final String COST_TIME_FORMAT = "%s The cost time: %dms";

    public static void printMethodInfo(MethodInfo methodInfo) {
        Log.i(String.valueOf(0) + TAG, TOP_BORDER);
        Log.i(String.valueOf(1) + TAG, String.format(CLASS_NAME_FORMAT, HORIZONTAL_LINE, methodInfo.getClassName()));
        Log.i(String.valueOf(2) + TAG, String.format(METHOD_NAME_FORMAT, HORIZONTAL_LINE, methodInfo.getMethodName()));
        Log.i(String.valueOf(3) + TAG, String.format(ARGUMENT_FORMAT, HORIZONTAL_LINE) + methodInfo.getArgumentList());
        Log.i(String.valueOf(4) + TAG, String.format(RESULT_FORMAT, HORIZONTAL_LINE) + methodInfo.getResult());
        Log.i(String.valueOf(5) + TAG, String.format(Locale.CHINA, COST_TIME_FORMAT, HORIZONTAL_LINE, methodInfo.getCost()));
        Log.i(String.valueOf(6) + TAG, BOTTOM_BORDER);
    }
}
