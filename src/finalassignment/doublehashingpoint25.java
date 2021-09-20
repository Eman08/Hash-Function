package finalassignment;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class doublehashingpoint25 {
	static ArrayList <xandy> a1=new ArrayList <>();
	   
	public static boolean sum(long T,long k[],long hashTable[],int M,int q)
	{      int probesequence=0;
		   int flag=0;
		   
	    	for (int j = 0; j<k.length; j++) {
	    		int i=0;
	    		long Y = (T-k[j]);	
	    		
	    		long s= q- Math.floorMod(Y, q); //second hash function
	    		long x=Math.floorMod(Y,M); //calculate the modulus for Y
	    		
	    		 probesequence++;
	    			while(hashTable[Math.floorMod((x+(s*i)), M)]!=-1 && (i<M))
			    	 {
			    	    probesequence++;
			    	    
			    			if(hashTable[Math.floorMod((x+(s*i)), M)]==Y) { //if x+i*s contains the exact value of Y (Pair exists
			    			   flag=1;	
			    			   xandy o1=new xandy(T,k[j],Y); //for storing t, x and y
			    			   a1.add(o1);
			    			   break;
			    			}
			    			i++;
			    			
			    	 }
			   
	    	}
	    	 System.out.println("T:"+T+" "+"Average Probe sequence: "+probesequence/1000000.0);
	    	 
		
		if(flag==1) //returns true if Y exists
		 return true;
		
		return false;
	}

	public static void main(String[] args) throws FileNotFoundException {

		
		
		FileReader f = new FileReader ("dataset#3.txt");
		Scanner in = new Scanner (f);
		
		long [] k = new long [1000000];
	
	    int a = 0; 
	    
		//reading values of the file into an array k
		while (in.hasNextLong()) {
			long element = in.nextLong();
			k[a]=element;
			a++;
		}
		
		//alpha is 0.25
		int modulo=   4000037;
		int q=3999971;
	    long [] hashTable = new long [4000037];
	    
	    int counter=0; //probe counter for insertion
	    
	    //setting all values of hash table as -1
	    for(int i=0;i<hashTable.length;i++)
	    {
	    	hashTable[i]=-1;
	    }
	    
	    long start = System.currentTimeMillis();;
	    for (int i = 0; i<k.length; i++ ) {
			 
		      long x=Math.floorMod(k[i],modulo);
		      long s= q- (Math.floorMod(k[i], q));
		      int j=1;
		      
		    
		   
		      if(hashTable[(int) x]==-1) //if initial place is empty, value gets stored at that index of hash table
		      {
		    	  hashTable[(int)x]=k[i];
		    	  counter++;
		      }
		      
		      else if(hashTable[(int) x]!=-1)
		      { counter++;
		    	  while(true)
			    	 {
			    	
			    		if(j<modulo) {
			    			counter++;
			    		if(hashTable[Math.floorMod((x+(s*j)), modulo)]==-1)
			    		{
			    			hashTable[Math.floorMod((x+(s*j)), modulo)]=k[i];
			    			break;
			    			
			    		}
			    		j++;
			    			
			    	 }}
		      }
		    	  
		      }
	    long end = System.currentTimeMillis();;
	    
	    long startsearch = System.currentTimeMillis();;
	    long [] T = {3,4,5,6,7,8,9,10};
	    //sends to function sum, to calculate X and Y
	    
	      for (int i = 0; i<T.length; i++) {
		  boolean value=sum(T[i],k,hashTable,modulo,q);
		    System.out.println("Value of T: "+T[i]+" Distinct pair exists: "+value);
		    System.out.println("---------------------------------------------------------------------"+"\n");
		    }
	  long endsearch = System.currentTimeMillis();;
	  
	  for(int i=0;i<a1.size();i++)
	    {
	    	System.out.println(a1.get(i).getX()+","+a1.get(i).getY());
	    }
	    
	    System.out.println("Average probe at insertion: "+counter/1000000.0 ); //calculates average probe count for 1 million entries
	    System.out.println("Total execution time for insertion in milliseconds: " + (end-start) + " " + "ms");
		System.out.println("Total execution time for searching in milliseconds: " + (endsearch-startsearch) + " " + "ms");
		System.out.println("Counter: "+counter);

	}
	}
