/**
 * 
 */
package com.gs.unifyid.bitmap;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author igauravshinde
 *
 */
public class HTTPHandler {
	
	private final Integer x = 250;			//width of image
	private final Integer y = 250;			//height of image
	
	public int[] image = new int[x*y];

	public void requestGET(String num) throws Exception {
		
		//hitting the server Using RANDOM.org
		String connURL = "https://www.random.org/integers/?num="+num+"&min=0&max=255&col=1&base=10&format=plain&rnd=new";

		//HTTP handling
		URL url = new URL(connURL);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		connection.setRequestProperty("User-Agent", "gaurav.shinde@sjsu.edu");

		//handling response from HTTP
		int rc = connection.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + connURL);
		System.out.println("RESPONSE from HTTP Server : " + rc);

		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String IL;
		StringBuffer res = new StringBuffer();

		int ind = 0;
		while ((IL = in.readLine()) != null) {
			res.append(IL);
			if(ind < 250*250) {
				image[ind] = Integer.parseInt(IL);
				ind++;
			}
		}
		in.close();
		System.out.println(res.toString());

	}




	public void generateBitmap() {
		System.out.println("Generating BITMAP ====> ");

		try {
			requestGET("10000");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		BufferedImage img = new BufferedImage(x, y, BufferedImage.TYPE_INT_ARGB);

		int index = 0;
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				img.setRGB(i, j, image[index]);
				index++;
			}
		}
	}



}
