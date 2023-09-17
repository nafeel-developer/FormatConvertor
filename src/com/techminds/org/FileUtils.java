package com.techminds.org;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FileUtils {

	public static File createFile(String sourcePath) {
		File file = new File(sourcePath);
		return file;
	}

	public static void convertOnlyDirectory(String folderLocation, String extension, boolean isAllConversion,
			String selectiveFileFormat) {
		List<File> files;
		try {
			if (isAllConversion) {
				files = Files.list(Paths.get(folderLocation)).filter(Files::isRegularFile).map(Path::toFile)
						.collect(Collectors.toList());
			} else {
				files = fileFilters(folderLocation, selectiveFileFormat);
			}
			for (File file : files) {
				changeExtension(file, extension);
				System.out.println(file.getName());
			}
			System.out.println("Total Files Moved:" + files.size());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public static void convertFolderAndSubFolderFiles(String path, String extension) {
		List<File> files;
		try {
			files = Files.find(Paths.get(path), Integer.MAX_VALUE, (filePath, fileAttr) -> fileAttr.isRegularFile())
					.map(Path::toFile).collect(Collectors.toList());

			for (File file : files) {
				changeExtension(file, extension);
				System.out.println(file.getName());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String changeExtension(File file, String extension) {
		String filename = file.getName();
		System.out.println(file.getName() + "" + extension);
		if (filename.contains(".")) {
			filename = filename.substring(0, filename.lastIndexOf('.'));
		}
		filename += "." + extension;

		file.renameTo(new File(file.getParentFile(), filename));
		return file.getName();
	}

	private static List<File> fileFilters(String folderPath, String selectiveFormat) {
		File directory = new File(folderPath);
		List<File> fileList = new ArrayList<File>();
		File[] files = null;
		if (directory.isDirectory()) {
			FileFilter logFileFilter = (file) -> {
				return file.getName().endsWith("." + selectiveFormat);
			};
			files = directory.listFiles(logFileFilter);
			fileList = convertArrayToList(files);
			return fileList;
		}
		return fileList;
	}

	public static <T> List<T> convertArrayToList(T array[]) {
		// create a list from the Array
		return Arrays.stream(array).collect(Collectors.toList());
	}

	@SuppressWarnings("unused")
	private static void printList(List<File> sampleFile) {
		sampleFile.forEach(s -> System.out.println(s.getName()));
	}

}
