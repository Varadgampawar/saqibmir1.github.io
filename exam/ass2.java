//rmi server

import java.rmi.*;  
import java.rmi.server.*;  
public class RMIServer extends UnicastRemoteObject implements MyInterface
{  
	public RMIServer () throws RemoteException
	{
	        System.out.println("RMI server is running now..!!");
	} 
	public static void main(String arg[])
	{
	        try
	        {
	        	RMIServer p=new RMIServer();
	        	Naming.rebind("RMIinterface", p);
	        	
	        }
	        catch(Exception e)
	        {
	        	System.out.println("Exception occured :" + e.getMessage() );
	        }
	        
	}
	public int calculator(int first_num,int second_num, char operation) throws RemoteException
	{ 
			System.out.println("Receivd data................");
			System.out.println("First Number : "+first_num);
			System.out.println("First Number : "+second_num);
			System.out.println("operation : "+operation);
			
			
			if(operation == '+'){
			   return first_num+second_num;
		    } 
		    else if(operation == '-'){
		       return  first_num - second_num;
		    }
		    
		    return 0;
		    
	}
}  






//rmi client
import java.rmi.*;
import java.util.Scanner;
import java.io.*;
public class RMIClient
{
	public static void main(String args[])
	{
		try
		{
			Scanner sc = new Scanner(System.in);

			MyInterface p=(MyInterface) Naming.lookup("RMIinterface");
			System.out.print("Enter First Number : ");
			int first_number= sc.nextInt();
			System.out.print("Enter Second  Number : ");
			int second_number= sc.nextInt();
			System.out.print("Enter Operation ( + or - ) : ");
			char operation = sc.next().charAt(0);

			int result= p.calculator(first_number,second_number,operation);
			System.out.println("Rresult is : "+result);
		}
		catch (Exception e)
		{
			System.out.println("Exception occured:"+ e.getMessage());
		}
	}
}







//myinterface
import java.rmi.*;
public interface MyInterface extends Remote
{
	public int  calculator(int first_num,int second_num, char operation) throws RemoteException;
}



