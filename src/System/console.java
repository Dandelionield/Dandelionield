package System;

/*
 *
 * @author Dandelion
 * 
 */

public class console{

	public static void clean(){
		
		try{

            String os = System.getProperty("os.name");

            if (os.contains("Windows")){
				
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
				
            }else{
				
                new ProcessBuilder("clear").inheritIO().start().waitFor();
				
            }
			
        }catch (Exception e){
		
            e.printStackTrace();
			
        }
		
	}
	
	public static void pause(int MiliSeconds){
		
		try {
			
			Thread.sleep(MiliSeconds);
			
		} catch (InterruptedException ev){
			
			ev.printStackTrace();
			
		}
		
	}

}