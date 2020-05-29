package cn.trans88.kurotool.net.rx;
import java.util.List;

public class BasicListBean<T>{
    public int code;
    public String msg;
    public List<T> data;

    @Override
    public String toString() {
        return "BasicListBean{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
