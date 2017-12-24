package user1;

import java.net.*;
import java.util.*;
import java.io.*;

public class user1 {
	private static InetAddress host;
	private static final int PORT = 2000;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		try {
			host = InetAddress.getLocalHost();
			Socket socket = null;
			socket = new Socket(host, PORT);
			PrintWriter plrinput = new PrintWriter(socket.getOutputStream(), true);

			SendOrder sendOrder = new SendOrder(socket, plrinput);
			ReadMessage readmessage = new ReadMessage(socket);

			sendOrder.start();
			readmessage.start();

		} catch (UnknownHostException unEp) {
			System.out.println("\nnot found!\n");
			System.exit(1);
		}

	}

}

class ReadMessage extends Thread {
	Socket socket;

	public ReadMessage(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		Scanner svrfeedback;
		try {
			svrfeedback = new Scanner(socket.getInputStream());
			String response;
			try {
				do {
					response = svrfeedback.nextLine();
					// response=svrfeedback.nextLine();//read message from svr;
					System.out.println("SERVER> " + response);
				} while (response.charAt(0) != '-');
			} catch (Exception i) {
				interrupt();
			}
		} catch (IOException e) {
			Thread main = Thread.currentThread();
			main.interrupt();
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}

class SendOrder extends Thread {
	Socket socket;
	PrintWriter plrinput;

	public SendOrder(Socket socket, PrintWriter plrinput) {
		this.socket = socket;
		this.plrinput = plrinput;
	}

	public void run() {
		String message;
		Scanner entryin = new Scanner(System.in);
		try {
			System.out.println("game start! Please enter your guessing character.\n");
			do {
				message = entryin.nextLine(); // words player want to send.
				plrinput.println(message);// send svr message
			} while (!message.equals("Exit"));

		}

		finally {
			try {
				util.a = 1;
				System.out.println("\nClosing connection…");
				socket.close();
				entryin.close();				
				interrupt();
			}

			catch (IOException ioEx) {
				System.out.println("Unable to disconnect!");
				System.exit(1);
			}
		}

	}

}
