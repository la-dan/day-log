package cn.vlinker.logclient.config;

public class HttpConfig {
    /**
     * 因为暂不了解javafx调用controller方式,现猜测为直接实例化,所以@value不能使用,待后续改进
     */
    public final static String host = "192.168.111.32";
//    public final static String host = "localhost";

    public final static int port = 32320;

    public static String getCode = "http://" + host + ":" + port + "/getCode";

    public static String addUserPath = "/admin/user/add";

    public static String subLog = "/admin/log/add";

    public static String getUser = "http://" + host + ":" + port + "/admin/user/get";

    public static String getDate = "http://" + host + ":" + port + "/admin/log/getDate";
}
