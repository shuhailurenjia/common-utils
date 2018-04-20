package com.zwh.common.util;

import java.util.regex.Pattern;

public class ParamsBlankLanJie {

	public static boolean checkCharacter(String param) {
		boolean result = true;
		String character = "[\\u4e00-\\u9fa5a-zA-Z_0-9-/]";
		Pattern compile = Pattern.compile(character);
		boolean find = compile.matcher(param).find();
		if(find == true){
			param = param.replaceAll("[\\u4e00-\\u9fa5a-zA-Z_0-9-/]", "");
			
			String letter = "[^(\\u4e00-\\u9fa5a-zA-Z_0-9-/)]";
			compile = Pattern.compile(letter);
			boolean f = compile.matcher(param).find();
			if(f ==true){
				result = false;
			}
		} else {
			String letter = "[^(\\u4e00-\\u9fa5a-zA-Z_0-9-/)]";
			compile = Pattern.compile(letter);
			boolean f = compile.matcher(param).find();
			if(f ==true){
				result = false;
			}
		}
		return result;
	}
}
