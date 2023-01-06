package com.formacionbdi.microservicios.app.heroes.utils;
import java.util.Base64;
public class Utils {
	  private Utils() {}

	  private static final String SECRET_KEY = "D4@p4Yp3rT#c!";

	  
	  /**
	   * @param originalInput
	   * @return
	   */
	  public static String encrypt(String originalInput) {

	    try {
	      return Base64.getEncoder().encodeToString(originalInput.getBytes());
	    } catch (Exception e) {
	      return null;
	    }
	  }

	  /**
	   * @param encodedString
	   * @return
	   */
	  public static String decrypt(String encodedString) {

	    try {
	      byte[] decodedBytes = Base64.getDecoder().decode(encodedString);

	      return new String(decodedBytes);
	    } catch (Exception e) {
	      return null;
	    }
	  }

	  /**
	   * @param text
	   * @return
	   */
	  public static Boolean isBase64(String text) {

	    String regex = "^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{4}|[A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)$";

	    return text.matches(regex);
	  }



}
