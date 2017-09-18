/**
 * 
 */
package Utilities;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;

/**
 * @author vamsiravi
 *
 */
public class GetScreenRecording {

	public static void startScreenRecording(){
		GraphicsConfiguration gc = GraphicsEnvironment
	               .getLocalGraphicsEnvironment()
	               .getDefaultScreenDevice()
	               .getDefaultConfiguration();

		
	}
}
