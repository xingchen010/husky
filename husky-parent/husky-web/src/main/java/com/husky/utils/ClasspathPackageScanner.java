package com.husky.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

/**
 * 扫描包
 * @author swq
 * @create_date 2018年12月23日
 */
public class ClasspathPackageScanner implements PackageScanner {
	private Logger logger = LoggerFactory.getLogger(ClasspathPackageScanner.class);
	private String[] basePackage;
	private ClassLoader cl;

	/**
	 * 初始化
	 * 
	 * @param basePackage
	 */
	public ClasspathPackageScanner(String[] basePackage) {
		this.basePackage = basePackage;
		this.cl = getClass().getClassLoader();
	}

	public ClasspathPackageScanner(String[] basePackage, ClassLoader cl) {
		this.basePackage = basePackage;
		this.cl = cl;
	}

	/**
	 * 获取指定包下的所有字节码文件的全类名
	 */
	public List<String> getFullyQualifiedClassNameList() throws IOException {
		logger.info("开始扫描包{}下的所有类", basePackage);
		List<String> nameList = Lists.newArrayList();
		for (String basePackage : basePackage) {
			doScan(basePackage, nameList);
		}
		return nameList;
	}

	/**
	 * doScan函数
	 * 
	 * @param basePackage
	 * @param nameList
	 * @return
	 * @throws IOException
	 */
	private List<String> doScan(String basePackage, List<String> nameList) throws IOException {
		String splashPath = StringUtil.dotToSplash(basePackage);
		URL url = cl.getResource(splashPath); // file:/D:/WorkSpace/java/ScanTest/target/classes/com/scan
		String filePath = StringUtil.getRootPath(url);
		List<String> names = null; // contains the name of the class file. e.g., Apple.class will be stored as
									// "Apple"
		if (isJarFile(filePath)) {// 先判断是否是jar包，如果是jar包，通过JarInputStream产生的JarEntity去递归查询所有类
			if (logger.isDebugEnabled()) {
				logger.debug("{} 是一个JAR包", filePath);
			}
			names = readFromJarFile(filePath, splashPath);
		} else {
			if (logger.isDebugEnabled()) {
				logger.debug("{} 是一个目录", filePath);
			}
			names = readFromDirectory(filePath);
		}
		for (String name : names) {
			if (isClassFile(name)) {
				nameList.add(toFullyQualifiedName(name, basePackage));
			} else {
				doScan(basePackage + "." + name, nameList);
			}
		}
		if (logger.isDebugEnabled()) {
			for (String n : nameList) {
				logger.debug("找到{}", n);
			}
		}
		return nameList;
	}

	private String toFullyQualifiedName(String shortName, String basePackage) {
		StringBuilder sb = new StringBuilder(basePackage);
		sb.append('.');
		sb.append(StringUtil.trimExtension(shortName));
		// 打印出结果
		logger.info("scan class name:" + sb.toString());
		return sb.toString();
	}

	private List<String> readFromJarFile(String jarPath, String splashedPackageName) {
		if (logger.isDebugEnabled()) {
			logger.debug("从JAR包中读取类: {}", jarPath);
		}
		JarInputStream jarIn = null;
		List<String> nameList = new ArrayList<String>();
		try {
			jarIn = new JarInputStream(new FileInputStream(jarPath));
			JarEntry entry = jarIn.getNextJarEntry();
			
			while (null != entry) {
				String name = entry.getName();
				if (name.startsWith(splashedPackageName) && isClassFile(name)) {
					nameList.add(name);
				}

				entry = jarIn.getNextJarEntry();
			}
		} catch (IOException e) {
			logger.error(e.getMessage());
		} finally {
			if (jarIn != null) {
				try {
					jarIn.close();
				} catch (IOException e) {
					logger.error(e.getMessage());
				}
			}
		}

		return nameList;
	}

	private List<String> readFromDirectory(String path) {
		File file = new File(path);
		String[] names = file.list();

		if (null == names) {
			return null;
		}

		return Arrays.asList(names);
	}

	private boolean isClassFile(String name) {
		return name.endsWith(".class");
	}

	private boolean isJarFile(String name) {
		return name.endsWith(".jar");
	}

	/**
	 * For test purpose.
	 */
	public static void main(String[] args) throws Exception {
//		PackageScanner scan = new ClasspathPackageScanner("com.scan");
//		scan.getFullyQualifiedClassNameList();
		long start = System.currentTimeMillis();
		for (int i = 0; i < 10000; i++) {
			String a = String.format("今天星期%d, 地点:%s,序号:%d", 3,"深圳", i);	
		}
		long end = System.currentTimeMillis();
		for (int i = 0; i < 10000; i++) {
			String a = "今天星期" + 3 +  " 地点:" + "深圳" + ",序号：" + i;	
		}
		long end1 = System.currentTimeMillis();
		System.out.println(end-start);
		System.out.println(end1-end);
	}
}