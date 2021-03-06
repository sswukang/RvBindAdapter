package cn.sswukang.example.util;

import android.content.Context;
import android.support.annotation.NonNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 工具类
 *
 * @author sswukang on 2017/2/21 9:49
 * @version 1.0
 */
public class Utils {
    /**
     * 从assets读取文本信息
     */
    @NonNull
    public static String getTextFromAssets(Context context, String fileName) throws IOException {
        InputStreamReader inputReader = new InputStreamReader(context.getAssets().open(fileName));
        BufferedReader bufReader = new BufferedReader(inputReader);
        String line;
        StringBuilder result = new StringBuilder();
        while ((line = bufReader.readLine()) != null)
            result.append(line);
        return result.toString();
    }
}
