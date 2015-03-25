import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * EchoClient class
 * Sends a HTTP Request and prints the answer to the terminal
 * @author hwk
 *
 */
public class EchoClient {
	private static int bufferSize = 512;

	public static void main(String[] args) throws Exception {
		byte msg[] = new byte[bufferSize];
		try
		{
			Socket s = new Socket("localhost", 8163);
			OutputStream out = s.getOutputStream();
			InputStream in = s.getInputStream();
		
			String command1 = "GET / HTTP/1.1\r\n";
			String command2 = "POST / HTTP/1.1\r\n";
			String host = "Host: localhost:8163\r\n";
			String end = "\r\n\r\n";
			
			String request1 = command1 + host + end;
			String request2 = command2 + host + end;
			
			out.write(request1.getBytes());
			out.write(request2.getBytes());
			
			while (in.read(msg) >= 0) {
				;
			}
			System.out.print(new String(msg));
			s.close();
		}
		catch (UnknownHostException uhost) 
		{
			System.err.println("");
		}
		catch (IOException io) 
		{
			
		}
		
	}
}
