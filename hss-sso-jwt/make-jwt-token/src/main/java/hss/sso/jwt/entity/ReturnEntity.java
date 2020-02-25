package hss.sso.jwt.entity;

public class ReturnEntity {
    private int code;
    private String message;
    private Object data;

    public ReturnEntity() {
    }

    public ReturnEntity(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static ReturnEntity successResult(Object data) {
        return new ReturnEntity(1, "", data);
    }

    public static ReturnEntity failedResult(String message) {
        return new ReturnEntity(0, message, null);
    }
}
