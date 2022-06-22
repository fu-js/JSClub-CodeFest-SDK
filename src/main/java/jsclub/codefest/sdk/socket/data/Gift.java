package jsclub.codefest.sdk.socket.data;

public class Gift extends Position {
    public static final String GIFT_G = "G";
    public static final String GIFT_S = "S";
    public static final String GIFT_T = "T";

    public String gift_type;

    public Gift(int col, int row) {
        super(col, row);

    }
}
