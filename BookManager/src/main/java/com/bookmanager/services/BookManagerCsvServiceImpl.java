package com.bookmanager.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.bookmanager.util.BookManagerUtil;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class BookManagerCsvServiceImpl<T> implements BookManagerCsvService<T>{

	@Override
	public List<T> read(String csvFileNamePath, String[] columns, Class<T> tClass) throws IOException {
		List<T> entities = new ArrayList<T>();
		
		ColumnPositionMappingStrategy<T> strategy = new ColumnPositionMappingStrategy<T>();
		strategy.setType(tClass);
		strategy.setColumnMapping(columns);
		
		CsvToBean<T> csvToBean = new CsvToBeanBuilder<T>(BookManagerUtil.getReader(csvFileNamePath))
				.withMappingStrategy(strategy)
				.withSkipLines(1)
				.withIgnoreLeadingWhiteSpace(true)
				.build();
		
		Iterator<T> entityIterator = csvToBean.iterator();
		while(entityIterator.hasNext()) {
			entities.add(entityIterator.next());
		}

		return entities;
	}

}
