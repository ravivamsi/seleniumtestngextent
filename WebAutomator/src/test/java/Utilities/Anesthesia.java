/**
 * 
 */
package Utilities;

/**
 * @author vamsiravi
 *
 */
public class Anesthesia {
	public static void sleep(int sec) {
		try {
			Thread.sleep(Integer.parseInt(sec + "000"));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
