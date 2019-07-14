package com.bookmanager.util;

import java.io.FileNotFoundException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.util.ResourceUtils;

import com.bookmanager.model.Book;
import com.bookmanager.model.Person;
import com.opencsv.CSVReader;

public class BookManagerUtil {

	public static String getAbsoluteResourcePath(String fileName) throws FileNotFoundException {
		return ResourceUtils.getFile("classpath:" + fileName).getAbsolutePath();
	}

	public static CSVReader getCSVReader(String csvFileNamePath) {
		return new CSVReader(getReader(csvFileNamePath));
	}
	
	public static Reader getReader(String csvFileNamePath) {
		Reader reader = null;
		try{
			reader = Files.newBufferedReader(Paths.get(csvFileNamePath));
		} catch(Exception e) {
			e.printStackTrace();
		}
		return reader;
	}

	public static void printPeople(List<Person> inputObjList) {
		
		inputObjList.forEach( i -> {
			System.out.println(i);
		});
		
	}

	public static void printBooks(List<Book> inputObjList) {
		
		inputObjList.forEach( i -> {
			System.out.println(i);
		});
		
	}

	
}
