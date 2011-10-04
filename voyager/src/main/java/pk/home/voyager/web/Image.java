package pk.home.voyager.web;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Image
 */
public class Image extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Image() {
		super();
		// TODO Auto-generated constructor stub
	}

	private static final String BASE_FILES_DIR = "/tmp";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// get the filename from the "file" parameter
		String fileName = (String) request.getParameter("file");
		if (fileName == null || fileName.equals(""))
			throw new ServletException(
					"Invalid or non-existent file parameter in Image servlet.");
		
		
		//Устранение уязвимости
		fileName = fileName.replace("../", "");

		// add the .pdf suffix if it doesn't already exist

		ServletOutputStream stream = null;
		BufferedInputStream buf = null;
		try {

			stream = response.getOutputStream();
			File pdf = new File(BASE_FILES_DIR + "/" + fileName);

			// set response headers

			// Get the absolute path of the image
			ServletContext sc = getServletContext();
			String filename = sc.getRealPath(BASE_FILES_DIR + fileName);

			// Get the MIME type of the image
			String mimeType = sc.getMimeType(filename);
			if (mimeType == null) {
				sc.log("Could not get MIME type of " + filename);
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				return;
			}

			// Set content type
			response.setContentType(mimeType);

			// response.setContentType("application/pdf");

			// Это раскоментировать если сделать как атачмент
			//response.addHeader("Content-Disposition", "attachment; filename="
			//		+ fileName);

			response.setContentLength((int) pdf.length());

			FileInputStream input = new FileInputStream(pdf);
			buf = new BufferedInputStream(input);
			int readBytes = 0;

			// read from the file; write to the ServletOutputStream
			while ((readBytes = buf.read()) != -1)
				stream.write(readBytes);

		} catch (IOException ioe) {

			throw new ServletException(ioe.getMessage());

		} finally {

			// close the input/output streams
			if (stream != null)
				stream.close();
			if (buf != null)
				buf.close();
		}

	}

}
