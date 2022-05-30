package com.LockersPvtLtd.LockedmedCom;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

public class LockedmeDriver {
	public static void main(String[] args) throws IOException {
		System.out.println("Welcome to the LockedMe.com");
		System.out.println("Developed by; \n	Name: Er. Vishal Chhadekar \n" + "	Role: Full Stack Developer");
		System.out.println("Tell us how can we  help you? \nChoose the following options:");
		System.out.println();
		System.out.println("1. To get all files from directory in ascending order\n"
				+ "2. To perform business operations \n3. Back to the main menu\n" + "4. To close the application ");
		Scanner userInput = new Scanner(System.in);
		int userResponse = userInput.nextInt();

		// use switch statement to perform further operations
		switch (userResponse) {

		case 1:
			// call a method to sort files directory in ascending order
			getDirectory();
			break;
		case 2:
			performBusinessOperations();
			int in = userInput.nextInt();
			switch (in) {
			case 1:
				// take users details
				String name = userInput.next();
				String address = userInput.next();
				long mobNumber = userInput.nextLong();
				int age = userInput.nextInt();
				// call a method to add a new file
				String path = new String("B://UserFilesDirectory//");
				createFileUsingFileClass(path, name, address, mobNumber, age);

				break;
			case 2:
				// delete a file
				String userName = userInput.next();
				deleteFile(userName);
				break;
			case 3:
				// Search a file by it's name
				String userName1 = userInput.next();

				File file = getUserFile("B://UserFilesDirectory//", userName1 + ".txt");
				if (!(file == null)) {
					System.out.println("File exists, Successful operation");
					System.out.println(file.getName());
				} else {
					System.out.println("FNF, unsuccessful operation");
				}
				break;

			}
			break;
		case 3:
			// Navigate the main menu (i.e. repeat the process from 1)

			break;
		case 4:
			// close the application
			System.exit(0);
			break;
		// Default case statement
		default:
			System.out.println("Invalid Entry, please try again!");
			System.out.println("Thank you for using the Lockme.com");
		}

	}

	// method to get fileDirectory in ascending order
	public static void getDirectory() {
		String path = new String("B://UserFilesDirectory//");
		File file = new File(path);
		File getDirectory[] = file.listFiles();
		Arrays.sort(getDirectory);

		for (File ele : getDirectory) {
			System.out.println(ele.getName());
		}

	}

	// Method to add newUserfile to the directory

	private static void createFileUsingFileClass(String path, String name, String address, long mobNumber, int age)
			throws IOException {
		File file = new File(path + name + ".txt");
//  
		// Create a file
		if (file.createNewFile()) {
			System.out.println("File is created!");
		} else {
			System.out.println("File already exists.");
		}

		// Write Content
		FileWriter WritingUserDetials = new FileWriter(file);
		WritingUserDetials.write(
				"User Name: " + name + "\nAddress: " + address + "\nMob. Number:" + mobNumber + "\n" + "Age: " + age);
		WritingUserDetials.close();
	}

	
	public static void performBusinessOperations() {
		System.out.println("Choose following options:");
		System.out.println("1. To add a file to the directory\n" + "2. To delete a file\n3. To search a file");
	}

	// Method to search a file --by it's name
	public static File getUserFile(String path, String fileName) {
		new String("B://UserFilesDirectory//");
		File file = new File(path);
		File getDirectory[] = file.listFiles();

		for (File ele : getDirectory) {
			if (ele.getName().equals(fileName)) {
				return ele;
			}

		}
		return null;
	}

	public static void deleteFile(String userName) {
		try {
			Files.deleteIfExists(Paths.get("B://UserFilesDirectory//" + userName + ".txt"));
		} catch (NoSuchFileException e) {
			System.out.println("No such file/directory exists");
		} catch (DirectoryNotEmptyException e) {
			System.out.println("Directory is not empty.");
		} catch (IOException e) {
			System.out.println("Invalid permissions.");
		}
		System.out.println("Deletion successful.");
	}

}