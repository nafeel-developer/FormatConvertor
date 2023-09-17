package com.techminds.org;

import java.io.File;
import java.util.Scanner;

public class FormatConverter {
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner myObj = new Scanner(System.in); // Create a Scanner object
		System.out.println("Select File or Files");
		System.out.println("1.File 2.Files");

		String fileOrFiles = myObj.nextLine(); // Read user input
		if (fileOrFiles.equalsIgnoreCase("1")) {
			fileConverter();
		} else if (fileOrFiles.equalsIgnoreCase("2")) {
			filesConverter();
		} else {
			System.out.println("Not a valid option");
			return;
		}
	}

	private static void fileConverter() {
		@SuppressWarnings("resource")
		Scanner myObj = new Scanner(System.in); // Create a Scanner object
		System.out.println("Enter File path with name and extenstion");
		String filePath = myObj.nextLine();

		System.out.println("Type Changing Format extension");
		String extension = myObj.nextLine();
		File file = FileUtils.createFile(filePath);
		if (file.exists()) {
			FileUtils.changeExtension(file, extension.toLowerCase());
		} else {
			System.out.println("No Files in the given location");
			return;
		}
	}

	private static void filesConverter() {
		@SuppressWarnings("resource")
		Scanner myObj = new Scanner(System.in); // Create a Scanner object
		System.out.println("Enter Folder path");
		String folderPath = myObj.nextLine();

		File folder = FileUtils.createFile(folderPath);
		if (folder.isDirectory()) {
			System.out.println("Select");
			System.out.println("1.Only Selected Folder 2.Folder and Sub Folder Files");
			String folderOrSubFolders = myObj.nextLine();

			if (folderOrSubFolders.equalsIgnoreCase("1")) {
				// only selected Folder
				System.out.println("Select");
				System.out.println("1.All files in the folder 2.Selective File Format");
				String fileFormatSelection = myObj.nextLine();

				System.out.println("Type Changing Format extension");
				String extension = myObj.nextLine();
				if (fileFormatSelection.equalsIgnoreCase("1")) {
					FileUtils.convertOnlyDirectory(folderPath, extension.toLowerCase(), true, "");
				} else if (fileFormatSelection.equalsIgnoreCase("2")) {
					System.out.println("Type Selective File Format extension");
					String selectiveExtension = myObj.nextLine();
					FileUtils.convertOnlyDirectory(folderPath, extension.toLowerCase(), 
							false, selectiveExtension.toLowerCase());
					// folders and subfolders2
				} else {
					System.out.println("Not a valid option");
					return;
				}
			} else if (folderOrSubFolders.equalsIgnoreCase("2")) {
				System.out.println("Type Changing Format extension");
				String extension = myObj.nextLine();
				// folders and subfolders
				FileUtils.convertFolderAndSubFolderFiles(folderPath, extension.toLowerCase());
			} else {
				System.out.println("Not a valid option");
				return;
			}
		} else {
			System.out.println("No Folders in the given location");
			return;
		}
	}
}
