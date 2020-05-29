package cn.trans88.kurotool.net.rx;

public class RoutingAddressNotFoundException extends Exception{
    private String message;
    public RoutingAddressNotFoundException(){

    }
    public RoutingAddressNotFoundException(String message){
        this.message=message;
    }

    public String getMessage(){
        return message;
    }

}
