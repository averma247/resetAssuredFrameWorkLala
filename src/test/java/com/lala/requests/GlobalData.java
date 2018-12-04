package com.lala.requests;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GlobalData {

	public static String NewOrderID=null;
	public static Properties prop = new Properties();
	
	public static void readConfigFile() {


		InputStream input = null;

		try {

			input = new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/config.properties");

			// load a properties file
			prop.load(input);

			// get the property value and print it out
			System.out.println("Base URL "+prop.getProperty("baseURL"));
			//System.out.println(prop.getProperty("dbuser"));
			//System.out.println(prop.getProperty("dbpassword"));

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String args[]){
		
		System.out.println(System.getProperty("user.dir"));
		
	}


}
