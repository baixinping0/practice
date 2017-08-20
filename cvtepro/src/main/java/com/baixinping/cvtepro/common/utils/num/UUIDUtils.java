package com.baixinping.cvtepro.common.utils.num;

import java.util.UUID;

public class UUIDUtils {
	public static String getUuid(){
		return UUID.randomUUID().toString().replace("-", "");
	}
}
