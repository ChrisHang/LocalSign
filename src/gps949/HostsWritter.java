package gps949;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class HostsWritter {
	public synchronized boolean update(String hostName, String ip) {
		String splitter = " ";
		String fileName = "C://WINDOWS//system32//drivers//etc//hosts";
		// 更新设定文件
		List<String> lines = null;
		try {
			lines = FileUtils.readLines(new File(fileName));
		} catch (IOException e1) {
		}
		List<String> newLines = new ArrayList<String>();
		boolean findFlag = false;
		boolean updateFlag = false;
		for (String strLine : lines) {
			if (!strLine.replace("/t", " ").replace(" ", "").equals("") && !strLine.startsWith("#")) {
				strLine = strLine.replaceAll("/t", splitter);
				int index = strLine.toLowerCase().indexOf(hostName.toLowerCase());
				if (index != -1) {
					String[] array = strLine.trim().split(splitter);
					for (String name : array) {
						if (hostName.equalsIgnoreCase(name)) {
							findFlag = true;
							if (array[0].equals(ip)) {
								// 如果IP相同，则不更新
								newLines.add(strLine);
								break;
							}
							// 更新成设定好的IP地址
							StringBuilder sb = new StringBuilder();
							sb.append(ip);
							for (int i = 1; i < array.length; i++) {
								sb.append(splitter).append(array[i]);
							}
							newLines.add(sb.toString());
							updateFlag = true;
							break;
						}
					}
				}
			}
			newLines.add(strLine);
		}
		// 如果没有Host名，则追加
		if (!findFlag) {
			newLines.add(new StringBuilder(ip).append(splitter).append(hostName).toString());
		}
		if (updateFlag || !findFlag) {
			// 写设定文件
			try {
				fileName = "hosts";
				FileUtils.writeLines(new File(fileName), newLines);
				MoveOutFile("FixHost.bat");
				Runtime.getRuntime().exec(System.getProperty("user.dir") + "\\FixHost.bat");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return updateFlag;
	}
//把项目内的文件写到外部
	public void MoveOutFile(String srcPath) throws Exception {
		int index;
		byte[] bytes = new byte[1024];
		InputStream fis = getClass().getClassLoader().getResourceAsStream(srcPath);
		FileOutputStream fos = new FileOutputStream(srcPath);
		while ((index = fis.read(bytes)) != -1) {
			fos.write(bytes, 0, index);
			fos.flush();
		}
		fos.close();
		fis.close();
	}
}
