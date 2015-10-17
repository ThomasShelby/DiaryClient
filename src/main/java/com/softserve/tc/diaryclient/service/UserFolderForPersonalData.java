package com.softserve.tc.diaryclient.service;

import java.io.File;

public class UserFolderForPersonalData {
	
	public static String createFolderForUser(String nickName) {
		String rootPath = System.getProperty("catalina.home");
		File dir = new File(rootPath + File.separator + "tmpFiles");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        rootPath = dir.getAbsolutePath();
        dir = new File(rootPath+ File.separator + nickName);
        if (!dir.exists()) {
            dir.mkdirs();
        }
		return dir.getAbsolutePath();
	}

}
