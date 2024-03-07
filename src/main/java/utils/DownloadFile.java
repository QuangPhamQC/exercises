package utils;

import java.io.File;

public class DownloadFile {
	// Get the downloaded Path
	public String getPathContainDownload() {
		String path = "";
		String machine_name;
		machine_name = System.getProperty("user.home");
		path = String.format("%s" + File.separator + "Downloads" + File.separator, machine_name);
		return path;
	}

//	// Wait for download successfully with full file name
//	public void waitForDownloadFileFullnameCompleted(String fileName) throws Exception {
//		int i = 0;
//		while (i < 30) {
//			boolean exist = isFileExists(fileName);
//			if (exist == true) {
//				i = 30;
//			}
//			Thread.sleep(500);
//			i = i + 1;
//		}
//	}
	// Wait for download successfully with full file name
	public boolean waitForDownloadFileFullnameCompleted(String fileName) throws Exception {
		int i = 0;
		while (i < 30) {
			boolean exist = isFileExists(fileName);
			if (exist == true) {
				return true;
			}
			Thread.sleep(500);
			i = i + 1;
		}
		return false;
	}

	// Check file is existed with full file name
	public boolean isFileExists(String file) {
		try {
			String pathFolderDownload = getPathContainDownload();
			File files = new File(pathFolderDownload + file);
			boolean exists = files.exists();
			return exists;
		} catch (Exception e) {
			System.out.print(e.getMessage());
			return false;
		}
	}

	// Count file in folder
	public int countFilesInDirectory() {
		String pathFolderDownload = getPathContainDownload();
		File file = new File(pathFolderDownload);
		int i = 0;
		for (File listOfFiles : file.listFiles()) {
			if (listOfFiles.isFile()) {
				i++;
			}
		}
		return i;
	}

	// Delete file with full name
	public void deleteFileFullName(String fileName) {
		if (isFileExists(fileName)) {
			deleteFullName(fileName);
		}
	}

	// Delete full name file
	public void deleteFullName(String fileName) {
		try {
			if (isFileExists(fileName)) {
				String pathFolderDownload = getPathContainDownload();
				File files = new File(pathFolderDownload + fileName);
				files.delete();
			}
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
	}

//	// Wait for Download file successfully with contains file name
//	public void waitForDownloadFileContainsNameCompleted(String fileName) throws Exception {
//		int i = 0;
//		while (i < 30) {
//			boolean exist = isFileContain(fileName);
//			if (exist == true) {
//				i = 30;
//			}
//			Thread.sleep(500);
//			i = i + 1;
//		}
//	}
	// Wait for Download file successfully with contains file name
	public boolean waitForDownloadFileContainsNameCompleted(String fileName) throws Exception {
		int i = 0;
		while (i < 30) {
			boolean exist = isFileContain(fileName);
			if (exist == true) {
				return true;
			}
			Thread.sleep(500);
			i = i + 1;
		}
		return false;
	}

	// check existed file with contain file name
	public boolean isFileContain(String fileName) {
		try {
			boolean flag = false;
			String pathFolderDownload = getPathContainDownload();
			File dir = new File(pathFolderDownload);
			File[] files = dir.listFiles();
			if (files == null || files.length == 0) {
				flag = false;
			}
			for (int i = 0; i < files.length; i++) {
				if (files[i].getName().contains(fileName)) {
					flag = true;
				}
			}
			return flag;
		} catch (Exception e) {
			System.out.print(e.getMessage());
			return false;
		}
	}

	// Delete file with contain file name
	public void deleteFileContainName(String fileName) {
		deleteContainName(fileName);
	}

	public void deleteContainName(String fileName) {
		try {
			String files;
			String pathFolderDownload = getPathContainDownload();
			File file = new File(pathFolderDownload);
			File[] listOfFiles = file.listFiles();
			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {
					files = listOfFiles[i].getName();
					if (files.contains(fileName)) {
						new File(listOfFiles[i].toString()).delete();
					}
				}
			}
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
	}

}
