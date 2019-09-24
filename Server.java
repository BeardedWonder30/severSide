import java.net.*;
import java.lang.*;
import java.util.*;
import java.io.*;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
 
public class Server {
    public static void main(String[] args) throws IOException {
         
        if (args.length != 1) {
            System.err.println("Usage: java EchoServer <port number>");
            System.exit(1);
        }
         
        int portNumber = Integer.parseInt(args[0]);
         
        try (
            ServerSocket serverSocket = new ServerSocket(Integer.parseInt(args[0]));
            Socket clientSocket = serverSocket.accept();     
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);                   
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        ) {
            String inputLine;
            String valid = "Please enter a valid input";
            while ((inputLine = in.readLine()) != null) {
            	if(inputLine.equals("1")) {
            		java.util.Date date=new java.util.Date();
            		out.println(date);
            	}
            	
            	else if(inputLine.equals("2")) {
            	 RuntimeMXBean runtimeBean = ManagementFactory.getRuntimeMXBean();
    			 long upTime = runtimeBean.getUptime();
    			 out.println(upTime/1000 + " seconds");
            	}
            	
            	else if(inputLine.equals("3")) {
            		Process run = Runtime.getRuntime().exec(new String[] {"bash", "-c", "free"});
            		//long processors = Runtime.getRuntime().availableProcessors();
            		//long memory = Runtime.getRuntime().freeMemory();
            		//long totalMem = Runtime.getRuntime().totalMemory();
            		
            		//out.println("Available processors (cores) " + processors +". Free memory  available" + memory + ". Total memory available "
            			//	+ totalMem + ".");
            		out.println(run);
            		
            		//Host memory use
        			//Command: free -h (possibly)
            	}
            	
            	/*else if(inputLine.equals("4")) {
            		//Host Netstat
        			//Command: netstat
            	}*/
            	
            	else if(inputLine.equals("5")) {
            		String user = System.getProperty("user.name");
            		out.println(user);
            		//Host current users
        			//Command: users
            	}
            	
            	/*else if(inputLine.equals("6")) {
            		//Host running processes
        			//Command: ps aux (or just 'ps' for a much shorter output...)
            	}*/
            	
            	else if(inputLine.equals("7")) {
            		out.println("Disconnected");
            	break;
            	}
            	
            	else {
            		out.println(valid);
            	}
            }
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
}