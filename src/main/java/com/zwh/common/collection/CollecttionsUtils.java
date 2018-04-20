package com.zwh.common.collection;

import java.util.Collection;

public class CollecttionsUtils {

	public static boolean isEmputy(Collection<?> lst) {
		return lst == null || lst.isEmpty();
	}

	public static boolean isNotEmputy(Collection<?> lst) {
		return isEmputy(lst) == false;
	}
}
