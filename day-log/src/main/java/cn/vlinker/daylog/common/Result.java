package cn.vlinker.daylog.common;

public class Result {
    private static final int OK_CODE = 200;
    private static final String OK_MESSAGE = "Success";
    private static final int ERROR_CODE = 400;
    private static final String ERROR_MESSAGE = "Error";

    private int code;
    private String message;
    private Object data;

    private Result() {
    }

    private Result(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static Result SUCCESS() {
        return SUCCESS(null);
    }

    public static Result SUCCESS(Object data) {
        return new Result(OK_CODE, OK_MESSAGE, data);
    }

    public static Result ERROR(Object data) {
        return new Result(ERROR_CODE, ERROR_MESSAGE, data);
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

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
