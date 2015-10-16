package com.softserve.tc.diaryclient.autosave.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;

/**
 * Servlet implementation class xmlToHtmlServlet
 */
public class xmlToHtmlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public xmlToHtmlServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    response.setContentType("text/html");

		String app_path;
		app_path = request.getSession().getServletContext().getRealPath("/");
		String filepathXML =  app_path  + "WEB-INF\\xsl\\autosave.xml";
		String filepathXSL =  app_path  + "WEB-INF\\xsl\\autosave.xsl";
        try {
            PrintWriter writer = response.getWriter();
            TransformerFactory tFactory = TransformerFactory.newInstance();

            Transformer transformer =
              tFactory.newTransformer
                 (new javax.xml.transform.stream.StreamSource
                    (filepathXSL));

            transformer.transform
              (new javax.xml.transform.stream.StreamSource
                    (filepathXML),
               new javax.xml.transform.stream.StreamResult
                    (writer));
            
        }
          catch (Exception e) {
            e.printStackTrace( );
            response.getWriter().append("    !!!!Problemmmm!!!!");
            }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
