/**
 * 
 */
package TestData;

/**
 * @author vamsiravi
 *
 */
public class Credentials {

	
	public Object[][] getCredentials(){
		
		
		// Row stands for how many different data types test should run
				//coloumn stands for how many values per each test
				
				// Array size is 2
				// 0,1
		Object[][] credentials=new Object[2][3];
		//0th row
		credentials[0][0]="nonrestricteduser@qw.com";
		credentials[0][1]="123456";
		credentials[0][2]="Restrcited User";
		//1st row
		credentials[1][0]="restricteduser@qw.com";
		credentials[1][1]="456788";
		credentials[1][2]= "Non restricted user";
		
		return credentials;
	}
	
	
	
	public Object[][] getHHonorMembers(){
		Object[][] hHonorMemberDetails=new Object[10][3];
		
		// 0th Row
		
		return hHonorMemberDetails;
	}
	
}
