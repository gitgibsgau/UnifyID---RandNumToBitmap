/**
 * 
 */
package com.gs.unifyid.bitmap;

/**
 * @author igauravshinde
 *
 */
public class RandomNumBitmap {
	
	public static void main(String[] args) {
		
		HTTPHandler randHTTP = new HTTPHandler();
		
		System.out.println("client sends HTTP GET request");
		try {
			randHTTP.requestGET("10000");

		} catch (Exception ex) {
			// TODO: handle exception
			ex.printStackTrace();
		}
		randHTTP.generateBitmap();
	}
}
