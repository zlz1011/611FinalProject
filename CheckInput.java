/**
 * 
 * @author lingdean
 * The CheckInput interface is only used to check if the user entered is integer. 
 */
public interface CheckInput {
	
	default public boolean checkInt(String input) {
		int money_input=0;
		boolean flag = true;
		
		try {
			money_input= Integer.parseInt(input);
			if(money_input <=0) {
				flag = false;
			}
		}catch(Exception e1) {
			flag = false;
		}
		
		return flag;
	}
	
	
}
