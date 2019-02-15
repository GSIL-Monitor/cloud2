package beans;

import lombok.Data;
import java.io.Serializable;

/**
 * 统一返回结果
 * @author ziv
 * @date 2019-01-25
 */
@Data
public class Result implements Serializable {

    private static final long serialVersionUID = -3948389268046368059L;

    private Integer state;

    private String msg;

    private Object data;

    public Result() {}

    public Result(Integer state, String msg) {
        this.state = state;
        this.msg = msg;
    }

    public Result(Integer state, Object data) {
        this.state = state;
        this.data = data;
    }

    public Result(String errorMessage) {
        this.msg = errorMessage;
    }

    public static Result success() {
        Result result = new Result();
        result.setResultCode(ResultCode.SUCCESS);
        return result;
    }

    public static Result success(Object data) {
        Result result = new Result();
        result.setResultCode(ResultCode.SUCCESS);
        result.setData(data);
        return result;
    }

    public static Result failure(ResultCode resultCode) {
        Result result = new Result();
        result.setResultCode(resultCode);
        return result;
    }

    public static Result failure(ResultCode resultCode, Object data) {
        Result result = new Result();
        result.setResultCode(resultCode);
        result.setData(data);
        return result;
    }

    public static Result createBySuccessCodeMessage(Integer successCode, String successMessage){
        return new Result(successCode, successMessage);
    }

    public static Result createBySuccess(Integer successCode, Object data){
        return new Result(successCode, data);
    }

    public static  Result createBySuccess(String msg, Object data) {
        Result result = new Result();
        result.setResultCode(ResultCode.SUCCESS);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    public static Result createByErrorMessage(String errorMessage){
        return new Result(errorMessage);
    }

    public void setResultCode(ResultCode code) {
        this.state = code.code();
        this.msg = code.message();
    }
}
