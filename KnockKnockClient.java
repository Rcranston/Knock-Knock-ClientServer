// Ryan Cranston
// CSCD 330 Assignment 2
// KnockKnockClient is the Client end software that talks to the server as a call response for telling Knock Knock Jokes
// Source code References https://tutorialspoint.dev/computer-science/computer-network-tutorials/socket-programming-in-java


import java.net.*;
import java.io.*;

public class KnockKnockClient {
 
	    private Socket			 socket  = null; 
	    private DataInputStream  input   = null; 
	    private DataOutputStream out     = null;
	    private DataInputStream  in      = null;
	  
	    // constructor to put ip address and port 
	    public KnockKnockClient(String address, int port) 
	    { 
	        try
	        { 
	            socket = new Socket(address, port); 

	            input  = new DataInputStream(System.in); 

	            in    = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
	            out    = new DataOutputStream(socket.getOutputStream()); 
	        } 
	        catch(IOException i) 
	        { 
	            System.out.println(i); 
	        } 

	        String line = ""; 
	  
	        while (true)
	        { 
	            try
	            { 
	                line = input.readLine();
	                out.writeUTF(line);
	                
	                line = in.readUTF(); 
	                if(line.contains("End of Joke"))
	                	break;
                    System.out.println(line);
	            } 
	            catch(IOException i) 
	            { 
	                System.out.println(i); 
	            } 
	        } 
	  
	        // close the connection 
	        try
	        { 
	            input.close(); 
	            out.close();
	            in.close();
	            socket.close(); 
	        } 
	        catch(IOException i) 
	        { 
	            System.out.println(i); 
	        } 
	    } 
	  
	    public static void main(String args[]) 
	    { 
	    	KnockKnockClient client = new KnockKnockClient("127.0.0.1", 5000); 
	    } 
	} 