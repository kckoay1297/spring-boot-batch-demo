package com.spring.batch.job.reader;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

public class ConsoleItemWriter<T> implements ItemWriter<T> {

	@Override
	public void write(List<? extends T> items) throws Exception {
        for (T item : items) {
        	try {
            	System.out.println(item.toString());
        	}catch(Exception e) {
        		e.printStackTrace();
        	}
        } 
	}

}
