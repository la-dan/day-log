package cn.vlinker.daylog.common;

import java.util.Collection;

public class ResultFunctionModel {
    private Result result = Result.SUCCESS();
    private Object[] params;
    private FunctionAction action;

    private ResultFunctionModel() {
    }

    public ResultFunctionModel(FunctionAction action, Object... params) {
        this.params = params;
        this.action = action;
    }

    private final void checkParam() throws NullPointerException {
        for (Object o : params) {
            this.check(o);
        }
    }

    private final void check(Object o) throws NullPointerException {
        boolean flag = false;
        if (o != null) {
            if (o instanceof Number) {
                flag = ((Number) o).doubleValue() == 0 ? true : false;
            }
            if (o instanceof String) {
                flag = o.toString().length() <= 0 ? true : false;
            }
            if (o instanceof Collection) {
                flag = ((Collection) o).size() <= 0 ? true : false;
            }
        } else {
            flag = true;
        }

        if (flag) {
            throw new NullPointerException("请求参数填写错误");
        }
    }


    public Result execute() {
        try {
            checkParam();
        } catch (NullPointerException e) {
            result.setData(e.getMessage());
            result.setCode(400);
            return result;
        }
        try {
            result.setData(action.action());
        } catch (Exception e) {
            result = Result.ERROR(e.getMessage());
            result.setCode(500);
        }
        return result;
    }
}
