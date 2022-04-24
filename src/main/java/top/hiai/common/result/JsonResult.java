package top.hiai.common.result;

import lombok.Data;

import java.io.Serializable;


@Data
public class JsonResult implements Serializable {

    private int code; // 200是正常，非200表示异常
    private String msg;
    private Object data;

    /**
     * 成功，返回状态200 返回数据
     * @param data
     * @return
     */
    public static JsonResult succ(Object data) {
        return succ(200, "操作成功", data);
    }

    public static JsonResult succ() {
        return succ(200, "操作成功", null);
    }

    public static JsonResult succ(int code, String msg, Object data) {
        JsonResult r = new JsonResult();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }

    public static JsonResult fail(String msg) {
        return fail(405, msg, null);
    }

    public static JsonResult fail(String msg, Object data) {
        return fail(405, msg, data);
    }

    public static JsonResult fail(int code, String msg, Object data) {
        JsonResult r = new JsonResult();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }

}
