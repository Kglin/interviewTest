package net.interview.test.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author Kglin
 * @version 1.0
 * @since 2024/6/1
 */

@ApiModel(value = "数据返回包装模型")
public class JsonResult<T> {
    @ApiModelProperty(value = "状态码", required = true)
    private int status;
    @ApiModelProperty(value = "错误信息", required = false)
    private String message;
    @ApiModelProperty(value = "数据", required = false)
    private T data;

    public JsonResult(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public JsonResult(T data) {
        this.status = 0;
        this.message = null;
        this.data = data;
    }

    public JsonResult(String message, T data) {
        this.status = 0;
        this.message = message;
        this.data = data;
    }

    public JsonResult(int status, String message) {
        this.status = status;
        this.message = message;
        this.data = null;
    }

    @JsonIgnore
    public boolean isOk() {
        return status == 0;
    }

    @JsonIgnore
    public boolean isNotOk() {
        return status != 0;
    }

    public static <T> JsonResult<T> ok(T data) {
        return new JsonResult<>(data);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
