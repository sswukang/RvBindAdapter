package cn.sswukang.example.model;

/**
 * BindingConversion 辅助对象，避免影响其它DataBinding数据。
 *
 * @author sswukang on 2017/3/2 15:13
 * @version 1.0
 */
public class Conversion<T> {
    private T data;

    public Conversion(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
