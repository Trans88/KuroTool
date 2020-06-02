package cn.trans88.kurotool.net.webSocket;

import java.util.ArrayList;
import java.util.List;

public class WsManagerBuilder {
    private String wsUrl;
    private Boolean isReconnect =true;
    private Integer reconnectTime = 10*1000;
    private Integer heartBeatTime = 30*1000;
    private String heartBeatContext;
    private List<String> openConnectSendStringList = new ArrayList<>();
    private List<byte[]> openConnectSendByteList =new ArrayList<>();

    public WsManagerBuilder wsUrl(String wsUrl){
        this.wsUrl = wsUrl;
        return this;
    }

    public WsManagerBuilder reconnect(boolean isReconnect){
        this.isReconnect =isReconnect;
        return this;
    }

    public WsManagerBuilder reconnectTime(Integer reconnectTime){
        this.reconnectTime =reconnectTime;
        return this;
    }

    public WsManagerBuilder addSendStringList(List<String> openConnectSendContextList){
        this.openConnectSendStringList =openConnectSendContextList;
        return this;
    }

    public WsManagerBuilder addSendString(String openConnectSendContext){
        this.openConnectSendStringList.add(openConnectSendContext);
        return this;
    }

//    public WsManagerBuilder openConnectSendContext(List<byte[]> openConnectSendByteList){
//        this.openConnectSendByteList =openConnectSendByteList;
//        return this;
//    }
//
//    public WsManagerBuilder addSendString(byte[] openConnectSendByte){
//        this.openConnectSendByteList.add(openConnectSendByte);
//        return this;
//    }

    public WsManagerBuilder heartBeatContext(String context){
        heartBeatContext =context;
        return this;
    }
    public WsManagerBuilder heartBeatTime(Integer heartBeatTime){
        this.heartBeatTime =heartBeatTime;
        return this;
    }

    public WsManager build() {
        return new WsManager(wsUrl,isReconnect,reconnectTime,heartBeatTime,heartBeatContext,openConnectSendStringList,openConnectSendByteList);
    }
}
