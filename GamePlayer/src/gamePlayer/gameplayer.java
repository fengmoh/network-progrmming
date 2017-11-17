package gamePlayer;
import java.net.*;
import java.util.*;
import java.io.*;

public class gameplayer {
	private static InetAddress host;
	private static final int PORT = 2000;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
		host=InetAddress.getLocalHost();
		}
		catch(UnknownHostException unEp) {
			System.out.println("\nnot found!\n");
			System.exit(1);
		}
		sendOrder();
	}

	private static void sendOrder() {
		Socket socket=null;
		try 
		{
			socket= new Socket(host,PORT);
			Scanner svrfeedback =new Scanner(socket.getInputStream());
			PrintWriter plrinput =	new PrintWriter(socket.getOutputStream(),true);
			Scanner entryin= new Scanner(System.in);
			
			System.out.println("game start! Please enter your guessing character.\n");

			String message, response;
			
			do 
			{	
				message=entryin.nextLine(); //words player want to send.
				plrinput.println(message);//send svr message
				//response=svrfeedback.nextLine();
				do {
					response=svrfeedback.nextLine();
				//response=svrfeedback.nextLine();//read message from svr;
				System.out.println("SERVER> " + response);
				
				//	 response=svrfeedback.nextLine();//read message from svr;
				}while(response.charAt(0)!='-');
		
					
			
			}while (!message.equals("Exit"));
		}
			
			catch(IOException ioEx)
			{
				ioEx.printStackTrace();
			}
			
			finally
			{
				try
			 	{
				 System.out.println("\nClosing connection¡­");
				 socket.close();
			 	}
				
				catch(IOException ioEx)
				{
					System.out.println("Unable to disconnect!");
					System.exit(1);
				}
			}
			
	
		
	}
}
