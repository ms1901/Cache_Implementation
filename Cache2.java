package yy;
import java.util.*;
import java.util.LinkedList;
import java.util.Queue;
public class Cache2 {
//FULLY ASSOCIATIVE CACHE	
	class Node
	{
		String block_identify="0";
		String[] arr;
		
		
		Node(int a)
		{
			
			arr=new String[a];
			
		}
		
	}
	int SIZE=0;

	public  void print(Node[] tag)
	{
		System.out.println("****CACHE VIEW****");
		for(int i=0;i<tag.length;i++)
		{
			System.out.print("[ ");
			for(int j=0;j<tag[i].arr.length;j++)
			{System.out.print(tag[i].arr[j]+"    ");}
			System.out.print(" ]");
			System.out.println();
	}
	
	}
	public static int search(Node arr[], String x) 
	{ 
	    int n = arr.length; 
	    for(int i = 0; i < n; i++) 
	    { 
	        if(arr[i].block_identify .contentEquals(x) ) 
	            return i; 
	    } 
	    return -1; 
	} 

	public void read(Node[] tag_array,String address,int index,LinkedList<String>  q)
	
	
	{
		//System.out.println("****"+address+"***"+index);
		String full_address=String.valueOf(address)+String.valueOf(index);
		full_address=full_address.trim();
		
		
		
		
		int i=search(tag_array,address);
		if(i==-1)
		{
			System.out.println("CACHE MISS");
		}
		else
		{
			if(tag_array[i].arr[Integer.parseInt(String.valueOf(index),2)]==null)
			{
				System.out.println("CACHE MISS");
			}
			else
			{
				System.out.println("CACHE HIT,ADDRESS PRESENT IN CACHE");
				System.out.println(tag_array[i].arr[Integer.parseInt(String.valueOf(index),2)]);
				q.remove(address);
				q.add(address);
			}
		}
		
		return;
		
	}
	public String write(Node[] tag_array,String address,String val,int max_length,int index,LinkedList<String>   q)
	{
		
		
		String removed_address=null;
		String full_address=address+String.valueOf(index);
		full_address=full_address.trim();

		int a=search(tag_array,address);
		
		int b=search(tag_array,"0");
		
		if(SIZE!=max_length)
		{
			
			if(a!=-1)
			{
				System.out.println("CACHE HIT");
				tag_array[a].arr[Integer.parseInt(String.valueOf(index),2)]=val;
				System.out.println("WRITTEN in cache,address already present");
				q.remove(address);
				q.add(address);
				
			}
			else
				
			{   SIZE++;
				System.out.println("CACHE MISS");
				q.add(address);   //block is not present  and since size is not full,just add
				tag_array[b].block_identify=address;
				tag_array[b].arr[Integer.parseInt(String.valueOf(index),2)]=val;
				System.out.println("WRITTEN,address added in cache");
				
				
			}
			
		}
		else
		{
			if(a==-1)
			{
				System.out.println("CACHE MISS");
			removed_address=q.remove();
			int indexintag=search(tag_array,removed_address);
			tag_array[indexintag].block_identify=address;
			tag_array[indexintag].arr[Integer.parseInt(String.valueOf(index),2)]=val;
			q.add(address);  
			SIZE++;
			System.out.println("WRITTEN");
			}
			else
			{
				System.out.println("CACHE HIT");
				
				tag_array[a].block_identify=address;
				tag_array[a].arr[Integer.parseInt(String.valueOf(index),2)]=val;
				q.remove(address);
				q.add(address);  
			}
		}
		
		
		return removed_address; //check if value has been replaced or not
		
	}

	public static void main(String[] args) {
		// assuming a 16 bit machine
		Scanner s=new Scanner(System.in);
		Cache2 cc=new Cache2();
		int size_cache=s.nextInt();
		
		int no_of_cachelines=s.nextInt();
		int block_size=s.nextInt();
		
		int qqq=(int)(65536/block_size);
		qqq= (int)(Math.log(qqq)/Math.log(2));
		//System.out.println("***"+qqq);
		Node[] tag_array=new Node[no_of_cachelines];
		for(int i=0;i<tag_array.length;i++)
		{
			tag_array[i]=cc.new Node(block_size);
		}
		LinkedList<String>  q = new LinkedList<>(); 
		s.nextLine();
		
			while(true)
			{
				
		String str=s.nextLine();
		
		if(str.contentEquals("WRITE"))
		{
			
			String add=s.nextLine();
			
			String blockadd=add.substring(0,qqq);
			
			String indexi=add.substring(qqq);
			//System.out.println("*****"+blockadd+"*****"+indexi);
			if(indexi.contentEquals(""))
			{
				indexi="0";
			}
			
		
			//System.out.println("****"+indexi+"****"+Integer.parseInt(indexi));
			
			String value=s.nextLine();
			
			String result=cc.write(tag_array,blockadd,value,no_of_cachelines,Integer.parseInt(indexi),q);
			if(result!=null)
			{
				System.out.println("Since space was full,replaced address "+result);
			}
			cc.print(tag_array);
			
		}
		else if(str.contentEquals("READ"))
		{
			
			
			String add=s.nextLine();
			String blockadd=add.substring(0,qqq);
			String indexi=add.substring(qqq);
			
			if(indexi.contentEquals(""))
			{
				indexi="0";
			}
			
			cc.read(tag_array,blockadd,Integer.parseInt(indexi),q);
			cc.print(tag_array);
			
		}
		
		
		
		//s.nextLine();
	
		
			}
		

	}

}
