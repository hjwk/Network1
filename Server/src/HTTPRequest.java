public class HTTPRequest {
	private static String HTTP = new String("HTTP/1.1");

	// Server and content info
	private static String server = new String("Host : EchoServer\r\n");
	private static String contentType = new String(
			"Content-Type : text/html\r\n");
	private static String length = new String("Content-Length: ");

	// HTML doc
	private static String header = new String("<HTML>\r\n")
			+ new String(
					"  <!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN>\r\n")
			+ new String("<HEAD>\r\n")
			+ new String("  <TITLE>EchoServer Results</TITLE>\r\n")
			+ new String("</HEAD>\r\n\r\n");
	private static String content = new String("<BODY BGCOLOR=\"#F2F2F2\">\r\n")
			+ new String(
					"<H1 ALIGN=\"CENTER\"><font color=\"#0B610B\">EchoServer Results</font></H1>\r\n")
			+ new String(
					"Here is the reqest line and request headers sent by your browser:\r\n");
	private static String request = new String("<PRE>");
	private static String end = new String("</PRE>\r\n</BODY>\r\n</HTML>\r\n");

	public static String echo(String s)
	{
		int len = (header.length() + content.length() + request.length())
				+ s.length() + 4;
		String info = server + contentType + length + len + "\r\n\r\n";
		String resp = header + content + request + s + end;
		
		return HTTP + parseRequest(s) + info + resp;
	}
	
	/*
	 * 
	 */
	public static String parseRequest(String s) {
		String splits[];
		String ret = new String("200 OK\r\n");

		if (s == null || s.length() <= 0)
			ret = new String("400 Bad Request\r\n");

		String str = s.substring(0, s.indexOf("\r\n"));
		splits = str.split("\\s");
		if (splits.length != 3)
			ret = new String("400 Bad Request\r\n");

		if (!splits[0].equals("GET") || !splits[1].equals("/")
				|| !splits[2].equals("HTTP/1.1"))
			ret = new String("400 Bad Request\r\n");

		if (splits[0].equals("POST") || splits[0].equals("PUT")
				|| splits[0].equals("OPTIONS") || splits[0].equals("DELETE")
				|| splits[0].equals("TRACE") || splits[0].equals("CONNECT"))
			ret = new String("501 Not Implemented\r\n");

		return ret;
	}
}
