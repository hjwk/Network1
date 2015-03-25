import java.io.IOException;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * EchoServer
 * @author Hwk
 *
 */
public class EchoServer {

	public static void main(String[] args){
		int n = 1;
		
		//Service executor = new Service(cachedThreadPool);
		ExecutorService executor = Executors.newCachedThreadPool();
		
		try {
			ServerSocket ss = new ServerSocket(8163);
			
			while(true)
			{
				Socket ts = ss.accept();
				Worker w = new Worker(ts, n);
				n++;
				ts.setSoTimeout(1000);
				executor.execute(w);
				//w.start();
			}
		}
		catch (IOException io)
		{
			System.err.print("Error on socket: " + io.getMessage());
		}
		executor.shutdown();
	}

}
