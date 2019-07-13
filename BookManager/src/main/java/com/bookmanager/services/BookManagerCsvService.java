package com.bookmanager.services;

import java.io.IOException;
import java.util.List;

public interface BookManagerCsvService<T> {
	
	public List<T> read(String csvFileNamePath, String[] columns, Class<T> tClass) throws IOException;
	
}
