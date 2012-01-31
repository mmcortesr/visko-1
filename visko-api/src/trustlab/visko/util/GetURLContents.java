package trustlab.visko.util;

import java.io.*;
import java.net.*;

public class GetURLContents {
	public static byte[] downloadFile(String url) {// download file via http
													// protocol and return
													// contents as byte array

		try {
			ByteArrayOutputStream output = new ByteArrayOutputStream();

			// initialize connection
			URL someURL = new URL(url);
			URLConnection fileLocation = someURL.openConnection();

			BufferedInputStream bis = new BufferedInputStream(
					fileLocation.getInputStream());
			byte[] buff = new byte[1024];
			int bytesRead;
			// int totalBytes = 0;

			// Simple read/write loop
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				output.write(buff, 0, bytesRead);
			}

			// close inputstream
			bis.close();

			return output.toByteArray();

		} catch (MalformedURLException u) {
			u.printStackTrace();
			return null;

		} catch (IOException i) {

			i.printStackTrace();
			return null;
		}
	}

	public static String downloadText(String _uri) throws IOException,
			MalformedURLException {
		return GetURLContents.downloadText(new URL(_uri));
	}

	public static String downloadText(URL u) throws IOException {

		URLConnection conn = u.openConnection();
		conn.setUseCaches(true);
		Object resp = conn.getContent();
		// String ct = conn.getContentType();
		InputStream body = (InputStream) resp;
		InputStreamReader isr = new InputStreamReader(body);
		LineNumberReader lr = new LineNumberReader(isr);
		StringBuffer ret = new StringBuffer();

		while (true) {
			String line = lr.readLine();
			// System.out.println("Line:" + line);
			if (line == null) {
				break;
			}
			// ret.append(line).append(System.getProperty("line.separator"));
			ret.append(line).append("\n");
		}
		return ret.toString();
	}

	public static String downloadRawChars(String _uri) throws IOException,
			MalformedURLException {
		return GetURLContents.downloadRawChars(new URL(_uri));
	}

	public static String downloadRawChars(URL u) throws IOException {

		URLConnection conn = u.openConnection();
		Object resp = conn.getContent();
		// String ct = conn.getContentType();
		InputStream body = (InputStream) resp;
		StringBuffer ret = new StringBuffer();
		int currentChar;
		while ((currentChar = body.read()) != -1) {
			ret.append(Character.toString((char) currentChar));
		}
		return ret.toString();
	}

	public static boolean isWellFormedURL(String url) {
		try {
			new URL(url);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static URI getURI(String namespace) {
		URI ontURI = null;
		try {
			ontURI = new URI(namespace);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		return ontURI;
	}

	public static String getContentType(String urlStr) throws IOException,
			MalformedURLException {
		String cType = null;
		URL url = new URL(urlStr);
		URLConnection conn = url.openConnection();
		cType = conn.getContentType();
		return cType;
	}

} /* END of GetURLContents */