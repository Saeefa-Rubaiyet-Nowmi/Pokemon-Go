/* Saeefa Rubaiyet Nowmi
   [CS1101] Comprehensive Lab 2
   This work is to be done individually. It is not permitted to. 
   share, reproduce, or alter any part of this assignment for any 
   purpose. Students are not permitted from sharing code, uploading 
   this assignment online in any form, or viewing/receiving/
   modifying code written from anyone else. This assignment is part. 
   of an academic course at The University of Texas at El Paso and 
   a grade will be assigned for the work produced individually by 
   the student.*/

import java.io.File;
import java.util.Scanner;

public class CL3_Nowmi {

	public static void main(String[] args) throws Exception{
		
		LL pkmnList = new LL();
	
		String [] pikachu= {"25","Pikachu","Electric","","25","320","35","55","40","50","50","90","1"};
		LLNode pokemon1 = new LLNode(pikachu);
		pkmnList.addToParty(pokemon1);
		
	
		pkmnList.displayMainMenu();
	}
}
    