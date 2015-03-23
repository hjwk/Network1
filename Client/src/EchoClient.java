import java.io.*;
import java.net.Socket;

public class EchoClient {

	public static void main(String[] args) throws Exception{
		Socket s = new Socket("localhost", 8163);
		OutputStream out = s.getOutputStream();
		InputStream in = s.getInputStream();
		byte msg[] = new byte[10000];
		out.write("GET / HTTP1.1\r\n".getBytes("UTF-8"));
		
		while(in.read(msg) <= 0){;}
		System.out.print(new String(msg));
		s.close();

	}

}
