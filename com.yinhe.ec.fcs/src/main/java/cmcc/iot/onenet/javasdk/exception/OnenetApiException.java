//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package cmcc.iot.onenet.javasdk.exception;

public class OnenetApiException extends RuntimeException {
    private String message = null;

    public String getMessage() {
        return this.message;
    }

    public OnenetApiException(String message) {
        this.message = message;
    }
}
