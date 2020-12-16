// Ryan Cranston
// CSCD 330 Assignment 2
// KnockKnockServer is the Server side software that talks to the Client as a call response for telling Knock Knock Jokes
// Source code References https://tutorialspoint.dev/computer-science/computer-network-tutorials/socket-programming-in-java


import java.net.*;
import java.io.*;
import java.util.*;

public class KnockKnockServer {

	    private Socket          socket   = null; 
	    private ServerSocket    server   = null; 
	    private DataInputStream in       = null; 
	    private DataOutputStream out     = null;

	    public KnockKnockServer(int port) 
	    { 
	        try
	        { 
	            server = new ServerSocket(port); 
	  
	            socket = server.accept(); 

	            in    = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
               out   = new DataOutputStream(socket.getOutputStream());  
	  
	            String line = ""; 
               String[][] Joke={
               {"Hawaii","Voodoo","Nana","Hatch","Mustache"},
               {"I'm fine, Hawaii you?","Voodoo you think you are, asking me so many questions?","Nana your business.","God bless you.","Mustache you a question, but I'll shave it for later"}
               };
               Random random=new Random();
               int sel =random.nextInt(5);
               int prog=0;
               System.out.println("*Joke "+sel +" Selected*");
	  
	            while (!line.equals("End of Joke")) 
	            { 
	                try
	                { 
	                    line = in.readUTF();
                       line=line.toLowerCase(); 
	                    System.out.println(line);
                       
                       if(line.contains("joke"))
                         {out.writeUTF("Knock knock");
                         System.out.println("Knock knock");
                         prog=1;}
                       
                       else if(line.contains("whos there"))
                         {out.writeUTF(Joke[0][sel]);
                         System.out.println(Joke[0][sel]);
                         prog=2;}
                         
                       else if(line.contains(Joke[0][sel].toLowerCase()+" who"))
                       {out.writeUTF(Joke[1][sel]);
                        System.out.println(Joke[1][sel]);
                        prog=3;}
                        
                        else
                        {
                           switch(prog)
                           {
                              case 0:
                              out.writeUTF("Your supposed to say 'Tell me a joke'");
                              System.out.println("Your supposed to say 'Tell me a joke'");
                              break;
                              
                              case 1:
                              out.writeUTF("Your supposed to say 'Whos There'");
                              System.out.println("Your supposed to say 'Whos There'");
                              break;
                              
                              case 2:
                              out.writeUTF("*Your supposed to say '"+Joke[0][sel]+" who'*");
                              System.out.println("*Your supposed to say '"+Joke[0][sel]+" who'*");
                              break;
                              
                              case 3:
                              out.writeUTF("End of Joke");
                              line="End of Joke";
                              break;
                           }
                        }
                         
                         
	  
	                } 
	                catch(IOException i) 
	                { 
	                    System.out.println(i); 
	                } 
	            } 

	            socket.close(); 
	            in.close();
               out.close(); 
	        } 
	        catch(IOException i) 
	        { 
	            System.out.println(i); 
	        } 
	    } 
	  
	    public static void main(String args[]) 
	    { 
	    	KnockKnockServer server = new KnockKnockServer(5000); 
	    } 
} 