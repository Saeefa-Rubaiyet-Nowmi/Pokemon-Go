import java.io.File;
import java.util.Scanner;



public class LL {
	
	LLNode head;
    LLNode tail;
    int size;

	public LL() {
	
		this.head = null;
        this.tail = null;
        this.size = 0;
	}
	
	public LL(LLNode pkmn){
        this.head = pkmn;
        this.tail = pkmn;
        this.size = 1;
    }
	
	

	
	public String[][] setupPokemonArray () throws Exception{
		
		int pkmnCount=0;
		
		File pkmnFile = new File ("C:\\Users\\srnowmi\\Desktop\\CL3\\CompLab3\\CompLab3\\pokemonList.txt"); //initializing file object
		Scanner pkmnScanner =new Scanner (pkmnFile);
		String attributes = pkmnScanner.nextLine();
		while (pkmnScanner.hasNext()) {
			pkmnCount++;
			pkmnScanner.nextLine();
		}
		
		
		
		String[] attributeArr=attributes.split(",");
		int attributeCount = attributeArr.length;

		String[][]pkmnBox =new String[pkmnCount][attributeCount];
		pkmnScanner=new Scanner(pkmnFile);
		pkmnScanner.nextLine();
		int index=0;
		
		LLNode current= this.head;
		boolean notInList=true;
		
		//System.out.print(current.pokemon[1]);
		
		while (pkmnScanner.hasNext()) {
			String pokemon = pkmnScanner.nextLine();
			String[] pokemonArr = pokemon.split(",");
			
			while(current.next!=null) {
				if (current.pokemon[1].equals(pokemonArr[1]) ){
					//System.out.print("false1");
					notInList=false;	
				}
				else {
					//System.out.print(pokemonArr[1]);
					//System.out.print("true2");
					notInList=true;
				}
			}
			
			if(current.pokemon[1].equals(pokemonArr[1])) {
				//System.out.print("false3");
				notInList=false;
			}
			else {
				//System.out.println(pokemonArr[0]);
				//System.out.print("true4");
				notInList=true;
			}	
				
		
			if(notInList==true) {
				//System.out.print("true5");
				pkmnBox[index]=pokemonArr;
				index++;
			}
			
		
			
	}
		/*for(int i=0;i<pkmnBox.length;i++) {
			System.out.println();
			for(int j=0;j<pkmnBox[i].length;j++) {
				System.out.print(pkmnBox[i][j]+" ");
				
			}
		}*/
		return pkmnBox;
	}
	


	

	public void displayMainMenu() throws Exception { 
		
		String[][] pkmnBox=setupPokemonArray ();
		try {
			boolean exit_flag=false;
		
			while(exit_flag==false) {
		
				System.out.println("-----------------------------------------");
				System.out.println("Welcome to the Pokemon Center");
				System.out.println("Please Select One of the Following Options:");
				System.out.println("1) View Box");
				System.out.println("2) View Party");
				System.out.println("3) Deposit Pokemon (Remove from Party)");
				System.out.println("4) Withdraw Pokemon (Add to Party)");
				System.out.println("5) View Pokemon Stats");
				System.out.println("6) Logout");
				System.out.println("-----------------------------------------");
				
				Scanner scnr_options = new Scanner(System.in);  //scanner for choosing from main menu
				int options =scnr_options.nextInt();      	
					
				if (options==1){
					System.out.println("View Box");
					System.out.println("-----------------------------------------");
					
					System.out.println("Choose a filter criteria (only one). Or Type anything else to see everyone in the box.");
					System.out.println("-Type");
					System.out.println("-Level");
					System.out.println("-Generation");
					
					Scanner scnr_filter = new Scanner(System.in);
					String filter =scnr_filter.nextLine();
					
					displayPokemon (pkmnBox,filter);
				}
				
				else if (options==2){
					System.out.println("Please Select One of the Following Options:");
					System.out.println(" i) View Party");
					System.out.println(" ii) Get from Party");
					System.out.println(" iii) In Party?");
					
					Scanner scnr_options2 = new Scanner(System.in);  //scanner for choosing from main menu
					int options2 =scnr_options2.nextInt(); 
					
					if(options2==1) {
						System.out.println(" i) View Party");
						displayParty(this.head);//display the pokemons in party
						
					}
					if(options2==2) {
						System.out.println("Get from Party");
						System.out.println("Which Pokemon Do you want to get from Party?");
						
						Scanner scnr_getpkmn = new Scanner(System.in);  
						String getPkmn =scnr_getpkmn.nextLine(); 
						getFromParty(this.head,getPkmn);
					}
					if(options2==3) {
						System.out.println("In Party?");
						System.out.println("Which Pokemon Do you want to search in Party?");
						
						Scanner scnr_inParty = new Scanner(System.in);  
						String inPkmn =scnr_inParty.nextLine(); 
						System.out.println(inParty(inPkmn));
					}
					
				}
			
				else if (options==3){
					System.out.println("Deposit Pokemon (Remove from Party)");
			    	System.out.println ("Whom do you want remove from your party? There must be at least one pokemons always at the party.");
			    	Scanner pkmn_scanner = new Scanner (System.in);
			    	String pkmn_node =pkmn_scanner.nextLine(); 
			    	removeFromParty(pkmn_node);
			    	displayParty(this.head);
				}
				
				
				
				else if (options==4){
					System.out.println("Withdraw Pokemon (Add to Party). Max six.");
				
			    	int length=getlength();
			    	
			    		System.out.println ("Whom do you want to add to your party? You can add upto six pokemons.");
				    	Scanner pkmn_scanner = new Scanner (System.in);
				    	
				    	String pkmn_node =pkmn_scanner.nextLine(); 
				    	boolean inParty=inParty(pkmn_node);
				    	
				    	if(inParty==true) {
				    		System.out.println("This pokemon is already in Party. Cannot be added again.");
				    		
				    	}
				    	if(length>6) {
				    		System.out.println("The party is already full. Remove one to add another.");
				    		
				    	}
				    	else if (inParty==false & length<=6){
					    	for(int i = 0;i< pkmnBox.length;i++){
					    		if(pkmn_node.equals(pkmnBox[i][1])){
									LLNode newPkmn = new LLNode(pkmnBox[i]);
									addToParty(newPkmn);
									System.out.println(newPkmn.pokemon[1]+"   (lvl. "+newPkmn.pokemon[4]+" ). "+ "  Added to Party" );
									displayParty(this.head);
					    	}
				    	}
				}
				}  	
		
	
				
				
				
				else if (options==5){
					System.out.println("View Pokemon Stats");
					//String[][] pkmnBox2=setupPokemonArray ();
					System.out.println ("What pokemon do you want to see? (Can be from either your party or your box.)");
					Scanner getPokemon_scanner = new Scanner (System.in);
			    	
			    	String getPokemon =getPokemon_scanner.nextLine(); 
					
					//pkmnList.getFromParty(pkmnList.head, getPokemon );
					viewStat (pkmnBox, getPokemon);
					
				}
				else if (options==6){
					System.out.println("See you Later!");
					exit_flag=true;	
				}
				else  {
					System.out.println("Invalid Input. Try again");
				}
			}
	}
		
	catch (Exception e){
		System.out.println("Invalid Input");
	   }

	}
	
        

	
	
	
	
	public void viewStat (String[][]pkmnBox, String pkmn){
		System.out.println("#  |   Name        |  Type 1       |    Type 2      |  Level | Total | HP | Attack | Defense | Sp. Atk | Sp. Def | Speed | Generation");
		
		for (int i=0; i< pkmnBox.length; i++) {
			if(pkmnBox[i][1].equals(pkmn)) {
				for(int j=0;j<pkmnBox[i].length;j++) {
					System.out.print(pkmnBox[i][j]+ "   ");
				}
			}
		}
			
		
	}
	
	
	
	
	
	
	
	
	/*This is the method used to display all your Pokémon currently in the box. 
	 * This method will receive the variable where all the Pokémon are stored
in the box, and it will also require the user to enter a String that represents
 the filtering criteria. If the input filter criteria do not match any of the implemented
  filtering options, the method should display all the Pokémon in the box.
	 */
	

	public void displayPokemon (String[][] pokemonBox,String filter) {
		
		LLNode current = new LLNode();
		current=this.head;
		
		
        
		if (filter.equals("Type")) {
				
			try {
				System.out.println("Please input the 1st type you want to filter by:");
				Scanner scnr_type = new Scanner(System.in);
				String type1 =scnr_type.nextLine();
			
				System.out.println("Please input the 1st type you want to filter by. If you don't want a second type, input -1");
				String type2 =scnr_type.nextLine();
				
				boolean inList=false;
				LLNode temp= new LLNode();
				temp=this.head;
				
				for(int i=0;i<pokemonBox.length;i++) {
					
					
					while(temp.next!=null) {
						if(temp.pokemon[1].equals(pokemonBox[i][1])) {
							inList=true;
							break;
						}
						else {
							inList=false;
						}
						temp=temp.next;
					}
					if(temp.next==null) {
						if(temp.pokemon[1].equals(pokemonBox[i][1])) {
							inList=true;
							
						}
						else {
							inList=false;
							
						}
					}
					
				
				
					if(inList==false & ((type1.equals(pokemonBox[i][2])) || (type1.equals(pokemonBox[i][3]))) & type2.equals("-1") ){
						System.out.println(pokemonBox[i][0] + ")    "+pokemonBox[i][1]+ "     "+"  Type1: "+type1);
						
					}
					

					else if (inList==false &  ((type2.equals(pokemonBox[i][2])) || (type2.equals(pokemonBox[i][3]))) & ( (type1.equals("-1")) )) {
						System.out.println(pokemonBox[i][0] + ")    "+pokemonBox[i][1]+ "     "+"  Type2: "+type2);
						
						}
					else if (inList==false &  ((type2.equals(pokemonBox[i][2])) || (type2.equals(pokemonBox[i][3]))) & ((type1.equals(pokemonBox[i][2])) || (type1.equals(pokemonBox[i][3])))) {
						System.out.println(pokemonBox[i][0] + ")    "+pokemonBox[i][1]+ "     "+"  Type1: "+type1+ "  Type2: "+type2);
						
						}
					else   {
						
						   
						}
				
				}
				
			}
			
			
			
			
		catch (Exception e){
	           System.out.println("Something is wrong/ Invalid Input");
	       }
		}
		
		
		
		
		else if (filter.equals("Level")) {
			try {
			
			
				System.out.println("Please input the lower bound level you want to filter by (min 1, max 100):");
				Scanner scnr_level = new Scanner(System.in);
				int level_lower =scnr_level.nextInt();
				System.out.println("Please input the upper bound level you want to filter by (must be equal or higher than lower bound, max 100) :");
				int level_upper =scnr_level.nextInt();
				
				

				boolean inList=false;
				LLNode temp= new LLNode();
				temp=this.head;
				
				for(int i=0;i<pokemonBox.length;i++) {
					
					while(temp.next!=null) {
						if(temp.pokemon[1].equals(pokemonBox[i][1])) {
							inList=true;
							break;
						}
						else {
							inList=false;
						}
						temp=temp.next;
					}
					if(temp.next==null) {
						if(temp.pokemon[1].equals(pokemonBox[i][1])) {
							inList=true;
							
						}
						else {
							//System.out.println("4");
							inList=false;
							
						}
					}
					
					if (inList==false & level_lower>=1 & level_lower<=100 & level_upper >= level_lower & level_upper<=100 ) {
						
								String s= pokemonBox[i][4];
								int level=Integer.parseInt(s);
							
								if ( level >= level_lower & level <= level_upper ) {
									System.out.println(pokemonBox[i][0] + ")    "+pokemonBox[i][1]+ "     "+"Level: "+pokemonBox[i][4]);
								}
					}
					
					else   {
						
						   
					}
			}
					
			}
			
			
			catch (Exception e){
		           System.out.println("Something is wrong/ Invalid Input");
		       }
				
				
				
		}
		
		
		
		
		
		
			else if (filter.equals("Generation")) {
				
				try {
					
					System.out.println("Please input the generation you want to filter by (only generation 1, 2 and 3 are available):");
					Scanner scnr_generation = new Scanner(System.in);
					int generation =scnr_generation.nextInt();
					
					boolean inList=false;
					LLNode temp= new LLNode();
					temp=this.head;
					
					for(int i=0;i<pokemonBox.length;i++) {
						
						while(temp.next!=null) {
							if(temp.pokemon[1].equals(pokemonBox[i][1])) {
								inList=true;
								break;
							}
							else {
								inList=false;
							}
							temp=temp.next;
						}
						if(temp.next==null) {
							if(temp.pokemon[1].equals(pokemonBox[i][1])) {
								inList=true;
								
							}
							else {
								//System.out.println("4");
								inList=false;
								
							}
						}
						
						
						if (inList==false & (generation>=1) & (generation <= 3) ) {
							
									String g= pokemonBox[i][12];
									int gen=Integer.parseInt(g);
								
									if ( generation==gen ) {
										System.out.println(pokemonBox[i][0] + ")    "+pokemonBox[i][1]+ "     "+"(lvl. "+pokemonBox[i][4]+")" +"  (Gen. "+pokemonBox[i][12]+")");
										//break;
									}
								}
						else {
							
						}
					}
				}	
			
			
			catch (Exception e){
		           System.out.println("Something is wrong/ Invalid Input");
		       }
				
		}
		
		
		
		
		
			else  {
				
				try {
					
					boolean inList=false;
					LLNode temp= new LLNode();
					temp=this.head;
					
					for(int i=0;i<pokemonBox.length;i++) {
						
						
						while(temp.next!=null) {
							if(temp.pokemon[1].equals(pokemonBox[i][1])) {
								inList=true;
								break;
							}
							else {
								inList=false;
							}
							temp=temp.next;
						}
						if(temp.next==null) {
							if(temp.pokemon[1].equals(pokemonBox[i][1])) {
								inList=true;
								
							}
							else {
								//System.out.println("4");
								inList=false;
								
							}
						}
						
						if(inList==false ) {
							
									System.out.print(pokemonBox[i][0]+" " + pokemonBox[i][1] + " lvl)"+ pokemonBox[i][4]);
									System.out.println();
								}
							
					
						else {
							
						}
					
					}
				}
		
		catch (Exception e){
	           System.out.println("Something is wrong/ Invalid Input");
	       }
		}
	}
		
			
	
	
	
	/*This method will add the given Pokémon to the end of the linked list
	 *  and increase the size counter of the linked list.
If the party contains Pokémon, it means it is full, so no more 
Pokémon can be added unless one is removed first.*/

	
    
    

	
    public void addToParty(LLNode pkmn){
    	
    	
    	size=0;
    	
	    	if (this.head ==null) {
	    		this.head=pkmn;
	    		this.size++;
	    	}
	    	else if (this.head!=null) {
	    		LLNode n=this.head;
	    		while (n.next !=null) {
	    			n=n.next;
	    			this.size++;
	    		}
	    		n.next=pkmn;
	    		
	    	}
	    	else {
	    		System.out.println("List length exceeds. Can not add more pokemon to party.");
	    	}
    	}
    
    	
    	
    
   
    
    
    	
   
    

    //get length of the list
    
    public int getlength() {
    	int count=1;
    	LLNode current =this.head;
    	while(current.next!=null) {
    		count++;
    		current=current.next;
    	}
    	return count;
    }
    
    
   
    
    
    
    /*This method will receive the name of a Pokémon to remove 
     * from the party. If the Pokémon is found, it will remove it 
     * from the linked list that represents the party. If it is not found, 
     * it will only return an array containing an empty String
     * 
     */
    
    public void removeFromParty(String pkmn_node)
    {
    	
        // Store head node
        LLNode current = head, prev = null;
 
        // If head node itself holds the key to be deleted
        
        
        if (current != null && (current.pokemon[1].equals (pkmn_node))) {
            head = current.next; // Changed head
            return;
        }
 
        // Search for the key to be deleted, keep track of
        // the previous node as we need to change temp.next
        while (current != null && !(current.pokemon[1].equals (pkmn_node))) {
            prev = current;
            current = current.next;
        }
 
        // If key was not present in linked list
        if (current == null)
            return;
 
        // Unlink the node from linked list
        prev.next = current.next;
    }
 
    
    
    
    
    /*This method will return the Pokémon that matches the name of getPokémon, 
     * or an array of empty Strings if the Pokémon is not in the linked list representing the party. 
This method DOES NOT remove the Pokémon from the linked list.
*This method must be done recursively.
*/
    

    public String[] getFromParty(LLNode headNode, String getPokemon ) {
    	
    	String[] emptyArray= new String [0];
    	
    	if(headNode.next==null & !(headNode.pokemon[1].equals(getPokemon))) {
			return emptyArray;
		}
    	else if(headNode.pokemon[1].equals(getPokemon)) {
    		System.out.print(headNode.pokemon[1]+ "   lvl)"+ headNode.pokemon[4]);
    		return headNode.pokemon;
    	}
    	
    	else {
    		return getFromParty(headNode.next, getPokemon);
    		
		}
	
    	
    }
    
    
    
    /*This method will traverse the linked list RECURSIVELY and displaying the 
     * Pokémon in order from first to last, displaying their name and their level.
     */
    
    public void displayParty(LLNode headNode ) {
    	
    	
    	if(headNode.next==null) {
    			System.out.print(headNode.pokemon[1]+ " lvl) "+headNode.pokemon[4]);
    		}
    		
    	else {
    		//LLNode current=headNode.next;
    		System.out.print(headNode.pokemon[1]+ " lvl) "+headNode.pokemon[4]);
    		System.out.print(" <-- ");
    		displayParty(headNode.next);
    		
    	}
 }
    
    
    /*This method will traverse the linked list and look for the Pokémon that matches the given name.*/

    public boolean inParty(String getPokemon ) {
    	
    	LLNode current=this.head;
    	boolean inParty=false;
    	
    	while(current.next!=null) {
    		if(current.pokemon[1].equals(getPokemon)) {
    			inParty=true;
    		}
    		else {
    			inParty=false;
    		}
    	}
    	
    	if(current.next==null) {
    		if(current.pokemon[1].equals(getPokemon)) {
    			inParty=true;
    		}
    		else {
    			inParty=false;
    		}
    		
    	}
	
    	return inParty;
    	
    }
    
    
   
 
    
    

}