package com.app.code;

/**
 * <p>功 能：标点符号枚举</p>
 * <p>版 权：Copyright (c) 2017</p>
 * <p>创建时间：2017年6月13日 下午5:43:13</p>
 * @author 王建
 * @version 1.0
 */
public enum Symbol {
	/** 逗号 */
	COMMA(","),
	/** 句号 */
	DOT("."),
	/** 冒号 */
	COLON(":"),
	/** 分号 */
	SEMICOLON(";"),
	/** 斜杠 */
	SLASH("/"),
	/** 反斜杠 */
	BACKSLASH("\\"),
	/** 加号 */
	PLUS("+"),
	/** 减号 */
	DASH("-"),
	/** 等号 */
	EQUALS("="),
	/** 下划线 */
	UNDERSCORE("_"),
	/** 左方括号 */
	LEFT_SQUARE_BRACKET("["),
	/** 右方括号 */
	RIGHT_SQUARE_BRACKET("]"),
	/** 左大括号 */
	LEFT_CURLY_BRACE("{"),
	/** 右大括号 */
	RIGHT_CURLY_BRACE("}"),
	/** 与 */
	AMPERSAND("&"),
	/** 重音符(Tab键上面) */
	ACCENT("`"),
	/** 换行符 */
	LINE_BREAK("\r\n");

	private String symbol;

	private Symbol(String symbol) {
		this.symbol = symbol;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
}