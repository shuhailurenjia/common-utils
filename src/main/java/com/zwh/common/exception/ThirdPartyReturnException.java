package com.zwh.common.exception;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;

/**
 * 异常：三方接口返回错误
 */
public class ThirdPartyReturnException extends RuntimeException  {

    static final long serialVersionUID = -1L;

    public ThirdPartyReturnException() {
        super();
    }

    public ThirdPartyReturnException(String message) {
        super(message);
    }

    public ThirdPartyReturnException(String message, Throwable cause) {
        super(message, cause);
    }

    public ThirdPartyReturnException(Throwable cause) {
        super(cause);
    }

    public ThirdPartyReturnException(String invokeName,Object params,Object result) {
        this(invokeName, params, result, "");
    }

    public ThirdPartyReturnException(String invokeName,Object params,Object result,String extraMessage) {
        this(constuctorParamsToString(invokeName, params, result, extraMessage));
    }

    public ThirdPartyReturnException(String invokeName,Object params,Object result, Throwable cause) {
        this(invokeName, params, result, "",cause);
    }

    public ThirdPartyReturnException(String invokeName,Object params,Object result,String extraMessage, Throwable cause) {
        this(constuctorParamsToString(invokeName, params, result, extraMessage),cause);
    }

    protected ThirdPartyReturnException(String message, Throwable cause,
                                        boolean enableSuppression,
                                        boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }


    /**
     * 抛异常 ThirdPartyReturnException
     * @param invokeName 调用url 或者 Interface.method
     * @param params 调用参数
     * @param result 返回结果
     */
    public static void throwNew(String invokeName,Object params,Object result) {
        throwNew(invokeName, params, result, "");
    }

    /**
     * 抛异常 ThirdPartyReturnException
     * @param invokeName 调用url 或者 Interface.method
     * @param params 调用参数
     * @param result 返回结果
     * @param extraMessage 额外信息
     */
    public static void throwNew(String invokeName,Object params,Object result,String extraMessage) {
        throw new ThirdPartyReturnException(constuctorParamsToString(invokeName,params,result,extraMessage));
    }

    private static final String constuctorParamsToString(String invokeName,Object params,Object result,String extraMessage){
        LogDetail logDetail = new LogDetail();
        logDetail.setInvokeName(invokeName);
        logDetail.setParams(params);
        logDetail.setResult(result);
        logDetail.setExtraMessage(extraMessage);
        return logDetail.toString();
    }
    /**
     * 日志 内容详情类
     */
	@SuppressWarnings("unused")
    private static class LogDetail{

        private LogDetail(){}

        private String invokeName;
        private Object params;
        private Object result;
        private String extraMessage;

		public String getExtraMessage() {
            return extraMessage;
        }

        public void setExtraMessage(String extraMessage) {
            this.extraMessage = extraMessage;
        }

        public String getInvokeName() {
            return invokeName;
        }

        public void setInvokeName(String invokeName) {
            this.invokeName = invokeName;
        }

        public Object getParams() {
            return params;
        }

        public void setParams(Object params) {
            this.params = params;
        }

        public Object getResult() {
            return result;
        }

        public void setResult(Object result) {
            this.result = result;
        }

        @Override
        public String toString() {
            StringBuilder logDetail = new StringBuilder("\n第三方接口返回异常：");
            if (StringUtils.isNotBlank(invokeName)) {
                logDetail.append("\n").append("invokeName:" + invokeName);
            }
            if (params!=null) {
                logDetail.append("\n").append("params:" + toJson(params));
            }
            if (result!=null) {
                logDetail.append("\n").append("result:" + toJson(result));
            }
            if (StringUtils.isNotBlank(invokeName)) {
                logDetail.append("\n").append("extraMessage:" + extraMessage);
            }
            logDetail.append("\n");
            return logDetail.toString();
        }

        private static String toJson(Object src) {
            if (src!=null && src instanceof String) {
                return (String)src;
            }
            Gson gson = new Gson();
            return gson.toJson(src);
        }
    }

}
