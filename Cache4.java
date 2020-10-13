package yy;
import java.util.LinkedList;
import java.util.Scanner;
public class Cache4 {
		//N WAY associative  MAPPING
		class Node
		{
			String block_identify="0";
				String [] arr;
			Node(int a)
			{ arr=new String[a];  }
			
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
		
		public void read(Node[] tag_array,int ind,String tag,int index,int noofblocks,LinkedList<String>  q )
		
		
		{
			int set_number=ind;
			 ind=ind*noofblocks;
			
			for(int i=ind;i<ind+noofblocks;i++)
			{//System.out.println(tag_array[i].block_identify);
				if(tag_array[i].block_identify.contentEquals(tag))
				{
					q.remove(tag);
					System.out.println("READ HIT,tag is "+ tag+".Set number is "+set_number);
					System.out.println("VALUE IS   "+tag_array[i].arr[Integer.parseInt(String.valueOf(index),2)]);
					q.add(tag);
					return;
				}
				
			}
			
		
				System.out.println("READ MISS");
			return;
			
		}
		public void write(Node[] tag_array,int idexing,String tag,String val,int max_length,int index,int noofblocks,LinkedList<String>  q  )
		{
			idexing=idexing*noofblocks;
			for(int i=idexing;i<idexing+noofblocks;i++)
			{
				if(tag_array[i].block_identify.contentEquals(tag))
				{
					System.out.println("CACHE HIT");
					q.remove(tag);
					tag_array[i].arr[Integer.parseInt(String.valueOf(index),2)]=val;
					System.out.println("WRITTEN IN CACHE,address already present");
					q.add(tag);
					return;
				}
				
			}
			for(int i=idexing;i<idexing+noofblocks;i++)
			{
				if(tag_array[i].block_identify=="0")
				{
					System.out.println("CACHE MISS");
					q.add(tag);
					tag_array[i].block_identify=tag;
					tag_array[i].arr[Integer.parseInt(String.valueOf(index),2)]=val;
					System.out.println("WRITTEN IN CACHE");
					return;
				}
				
			}
			
			System.out.println("CACHE MISS");
			String a=tag_array[idexing].block_identify;
			q.remove(a);
			tag_array[idexing].block_identify=tag;
			q.add(tag);
			tag_array[idexing].arr[Integer.parseInt(String.valueOf(index),2)]=val;
			//q.add(e)
			System.out.println("Since no more space is present in set,to add another new block, block with tag "+a+" was removed");
			System.out.println("WRITTEN IN CACHE");
				
			
			return;
				
				
			
			
			
			
					
			
		}

		public static void main(String[] args) {
			// assuming a 16 bit machine
			Scanner s=new Scanner(System.in);
			Cache4 cc=new Cache4();
			int size_cache=s.nextInt();
			
			int no_of_cachelines=s.nextInt();
			int block_size=s.nextInt();
			int noofblocksinset=s.nextInt();
			int div=(int)(no_of_cachelines/noofblocksinset); 
			int qqqq= (int)(Math.log(div)/Math.log(2));
			int qqq=(int)(65536/block_size);
			
			qqq= (int)(Math.log(qqq)/Math.log(2));
			//System.out.println("qqqq"+qqqq+"qqq"+qqq);
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
				String tag=blockadd.substring(0,qqqq);
				String indexi=add.substring(qqq);
				//System.out.println("BLOCKADD"+blockadd+" "+"tag"+tag);
				
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
				
				int setindex=Integer.parseInt(String.valueOf(block),2)%div ;
				//System.out.println("setindex"+setindex);
				
				
				String value=s.nextLine();
				
				cc.write(tag_array,setindex,tag,value,no_of_cachelines,Integer.parseInt(indexi),noofblocksinset,q);
				cc.print(tag_array);
				
				
			}
			else if(str.contentEquals("READ"))
			{
				
				
				String add=s.nextLine();
				String blockadd=add.substring(0,qqq);
				String tag=blockadd.substring(0,qqqq);
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
					block=(blockadd);
					
				}
			
				int setindex=Integer.parseInt(String.valueOf(block),2)%div ;
				//System.out.println(setindex+"ssetindex");
				cc.read(tag_array,setindex,tag,Integer.parseInt(indexi),noofblocksinset,q);
				cc.print(tag_array);
			}
			
			
			
			
		
			
				}
			

		}

	}
