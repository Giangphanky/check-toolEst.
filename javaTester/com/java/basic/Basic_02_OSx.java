package com.java.basic;

public class Basic_02_OSx {
	public String iosName = "iOS.jpg";
	public String osName = System.getProperty("os.name");
	public String projectPath = System.getProperty("user.dir");

	public static void main(String[] args) {
		Basic_02_OSx osx = new Basic_02_OSx();

		System.out.println("OS name = " + osx.osName);
		System.out.println("Relative directory path = " + osx.projectPath);
		System.out.println("Upload folder = " + osx.getDirectorySlash("uploadFiles"));
		System.out.println("Image path = " + osx.projectPath + osx.getDirectorySlash("uploadFiles") + osx.iosName);
	}

	public String getDirectorySlash(String folderName) {
		if (isMac() || isUnix() || isSolaris()) {
			folderName = "/" + folderName + "/";
		} else {
			folderName = "\\" + folderName + "\\";
		}
		return folderName;
	}

	public boolean isWindows() {
		return (osName.toLowerCase().indexOf("win") >= 0);
	}

	public boolean isMac() {
		return (osName.toLowerCase().indexOf("mac") >= 0);
	}

	public boolean isUnix() {
		return (osName.toLowerCase().indexOf("nix") >= 0 || osName.toLowerCase().indexOf("nux") >= 0 || osName.toLowerCase().indexOf("aix") > 0);
	}

	public boolean isSolaris() {
		return (osName.toLowerCase().indexOf("sunos") >= 0);
	}

}
