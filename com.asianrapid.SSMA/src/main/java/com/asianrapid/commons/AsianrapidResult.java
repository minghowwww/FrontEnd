package com.asianrapid.commons;

/**
 * <p>Title: AsianrapidResult</p>
 * <p>Description: 响应的标准化pojo</p>
 * <p>Company: Asianrapid</p> 
 * @author	王铭浩
 * @date	2017年7月11日下午1:13:16
 * @version 1.0
 */
public class AsianrapidResult {

	//响应业务状态
	private Integer code;
	//响应消息
	private String msg;
	//响应数据
	private Object data;
	
	/**
	 * <p>Title: build</p>
	 * <p>Description:返回异常的时候调用 </p>
	 * @param code
	 * @param msg
	 * @param data
	 * @return
	 */
	public static AsianrapidResult build(Integer code, String msg, Object data){
		return new AsianrapidResult(code, msg, data);
	}
	
	/**
	 * <p>Title: ok</p>
	 * <p>Description: 状态为成功并且需要响应数据时</p>
	 * @param data
	 * @return
	 */
	public static AsianrapidResult ok(Object data){
		return new AsianrapidResult(data);
	}
	
	/**
	 * <p>Title: ok</p>
	 * <p>Description: 状态为成功并且不需要响应数据时</p>
	 * @param data
	 * @return
	 */
	public static AsianrapidResult ok(){
		return new AsianrapidResult(null);
	}
	
	public AsianrapidResult(){
		
	}
	
	public AsianrapidResult(Object data) {
		super();
		this.code = 200;
		this.msg = "ok";
		this.data = data;
	}

	public AsianrapidResult(Integer code, String msg, Object data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	
	
}
