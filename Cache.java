package yy;
import java.util.*;

import java.util.LinkedList;
import java.util.Queue;

import yy.Cache2.Node;
public class Cache {
	//DIRECT MAPPING
	class Node
	{
		String block_identify="0";
		String[] arr;
		
		
		Node(int a)
		{
			
			arr=new String[a];
			
		}
		
	}
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
	int SIZE=0;
	public static int search(Node arr[],String x) 
	{ 
	    int n = arr.length; 
	    for(int i = 0; i < n; i++) 
	    { 
	        if(arr[i].block_identify.contentEquals(x)) 
	            return i; 
	    } 
	    return -1; 
	} 
	
	public void read(Node[] tag_array,int ind,String address,int index)
	
	
	{
		String full_address=String.valueOf(address)+String.valueOf(index);
		full_address=full_address.trim();
		
		
		
		if(!(tag_array[ind].block_identify.contentEquals(address)))
		{
			System.out.println("READ MISS");
		}
		else
		{
			if(tag_array[ind].arr[Integer.parseInt(String.valueOf(index),2)]==null)
			{
				System.out.println("READ MISS");
			}
			else
			{
				
				System.out.println(tag_array[ind].arr[Integer.parseInt(String.valueOf(index),2)]);
				System.out.println("READ HIT");
				System.out.println("ADDRESS READ FROM:"+address);
				
			}
		}
		
		return;
		
	}
	public void write(Node[] tag_array,int idexing,String address,String val,int max_length,int index)
	{//max length is no of cache lines
		
		
		String full_address=String.valueOf(address)+String.valueOf(index);
		full_address=full_address.trim();
	
		
				
		tag_array[idexing].block_identify=address;
		tag_array[idexing].arr[Integer.parseInt(String.valueOf(index),2)]=val;
		System.out.println("WRITTEN in  cache");
		System.out.println("ADDRESS WRITTEN TO :"+address);
		System.out.println("VALUE WRITTEN:"+val);
				
		
	}

	public static void main(String[] args) {
		// assuming a 16 bit machine
		Scanner s=new Scanner(System.in);
		Cache cc=new Cache();
		int size_cache=s.nextInt();
		
		int no_of_cachelines=s.nextInt();
		int block_size=s.nextInt();
		
		int qqq=(int)(65536/block_size);
		qqq= (int)(Math.log(qqq)/Math.log(2));
		int no=(int)(Math.log(no_of_cachelines)/Math.log(2));
		//System.out.println("***"+qqq);
		Node[] tag_array=new Node[no_of_cachelines];
		for(int i=0;i<tag_array.length;i++)
		{
			tag_array[i]=cc.new Node(block_size);
		}
		
		s.nextLine();
		
			while(true)
			{
				
		String str=s.nextLine();
		//System.out.println(str);
		if(str.contentEquals("WRITE"))
		{
			//System.out.println(tag_array.length);
			String add=s.nextLine();
			
			String blockadd=add.substring(0,qqq-no);
		
			String indexi=add.substring(qqq);
			
			if(indexi.contentEquals(""))
			{
				indexi="0";
			}
			
			String block;
			if(blockadd.contentEquals(""))
			{
				block="0";
			}
			else
			{
				block=blockadd;
				
			}
			int index=Integer.parseInt(String.valueOf(block),2)% no_of_cachelines;
			
			String value=s.nextLine();
			
			cc.write(tag_array,index,block,value,no_of_cachelines,Integer.parseInt(indexi));
			cc.print(tag_array);
			
			
		}
		else if(str.contentEquals("READ"))
		{
			
			//int address=s.nextInt();
			String add=s.nextLine();
			String blockadd=add.substring(0,qqq-no);
			String indexi=add.substring(qqq);
			//System.out.println("INDEXI***"+indexi);
			if(indexi.contentEquals(""))
			{
				indexi="0";
			}

			String block;
			if(blockadd.contentEquals(""))
			{
				block="0";
			}
			else
			{
				block=(blockadd);
				
			}
			int index=Integer.parseInt(String.valueOf(block),2)% no_of_cachelines;
			
			cc.read(tag_array,index,(blockadd),Integer.parseInt(indexi));
			cc.print(tag_array);
		}
		
		
		
		//s.nextLine();
	
		
			}
		

	}

}
