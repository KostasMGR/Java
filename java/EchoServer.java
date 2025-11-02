import java.io.*;
import java.net.*;

public class EchoServer
{
	public static void main(String[] args) throws IOException
	{
		int portNumber = 7777;
		
		if (args.length > 0)
		{
			portNumber = Integer.parseInt(args[0]);
		}
		
		System.out.println("Server Running on port " + portNumber + "...");
		
		try (ServerSocket serverSocket = new ServerSocket(portNumber))
		{
			while(true)
			{
				try(Socket clientSocket = serverSocket.accept();
						PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
						BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())))
				{
					String input=in.readLine();
					if (input==null) continue;
					
					String response = processExpression(input.trim());
					out.println(response);
					System.out.println("Processed" + input +"->" + response);
				}catch(IOException e){
					System.out.println("Could not start server on port " + portNumber);
				}		
			}
		}
	}
			
			private static String processExpression(String expr)
			{
				char operator = 0;
				int opIndex = -1;
				for (char c : new char[] {'+', '-', '*','/'})
				{
					int idx = expr.indexOf(c);
					if(idx!=-1)
					{
						operator = c;
						opIndex = idx;
						break;
					}
				}
				
				if(operator == 0)
				{
					return "E 1 ";
				}
				try 
				{
					String leftStr = expr.substring(0, opIndex).trim();
					String rightStr = expr.substring(opIndex + 1).trim();
					
					double left = Double.parseDouble(leftStr);
					double right = Double.parseDouble(rightStr);
					double result = 0;
					
					switch (operator)
					{
				    case '+': result = left + right; break;
	                case '-': result = left - right; break;
	                case '*': result = left * right; break;
	                case '/': if (right == 0) return "E 3";
	                			result = left/right;
	                			break;
					}
					return "R" + result;
				}
				catch (NumberFormatException e) 
				{
					return "E 2";
				}
			}
	}
	}