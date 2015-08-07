package com.interview.index;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.interview.mongo.connector.OpLogReaderManager;
import com.interview.util.conversion.MongoDBService;

public class BootStrapper {

	private static Logger log = Logger.getLogger(BootStrapper.class);

	private static Properties properties = new Properties();

	public static void main(String[] args) {
		try {

			Thread.sleep(10000);

			String propFilePath = args[0];
			System.out.println("Path:" + propFilePath);
			InputStream inputFile = new FileInputStream(new File(propFilePath));
			properties.load(inputFile);

			MongoDBService.init(properties.getProperty("mongohost"),
					new Integer(properties.getProperty("mongoport")));
			SolrIndexingService.init(properties.getProperty("solrHost"),
					new Integer(properties.getProperty("solrPort")));

			OpLogReaderManager oplogManager = new OpLogReaderManager();
			oplogManager.start(properties.getProperty("mongohost"),
					new Integer(properties.getProperty("mongoport")));
		} catch (Exception e) {
			log.debug("Problem while starting:" + e.getMessage());
		}
	}
}