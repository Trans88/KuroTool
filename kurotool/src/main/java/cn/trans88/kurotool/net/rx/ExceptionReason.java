package cn.trans88.kurotool.net.rx;

/**
 * Created by Tendoasan on 2019/1/17.
 * 网络请求出错原因
 */

public enum ExceptionReason {
    /**
     * 解析数据失败
     */
    PARSE_ERROR,
    /**
     * 网络问题
     */
    BAD_NETWORK,
    /**
     * 连接错误
     */
    CONNECT_ERROR,
    /**
     * 连接超时
     */
    CONNECT_TIMEOUT,
    /**
     * 未知错误
     */
    UNKNOWN_ERROR,
}
