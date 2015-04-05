package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("<!-- \n");
      out.write("Java2 HW7\n");
      out.write("Create an HTML form with three input fields: first name, last name and e-mail. \n");
      out.write("Use scriplets to handle get requests. \n");
      out.write("Verify all input fields are non-null and display them back to the client. -->\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>JSP Page</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <h2>Martin Hong Java2 HW7</h2>\n");
      out.write("\n");
      out.write("\n");
      out.write("        ");

            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");
            if (null != firstName) {
                //if (null == firstName || null == lastName || null == email) {
        
      out.write("\n");
      out.write("\n");
      out.write("        <h1>Hello ");
      out.print( firstName);
      out.write(' ');
      out.print( lastName);
      out.write(" @ ");
      out.print( email);
      out.write(" </h1> \n");
      out.write("\n");
      out.write("\n");
      out.write("        ");
 } else {
        
      out.write("\n");
      out.write("\n");
      out.write("        <form action=\"index.jsp\" method=\"get\">\n");
      out.write("            <p>First Name: <input type=\"text\" name=\"firstName\"/></p>\n");
      out.write("            <p>Last Name: <input type=\"text\" name=\"lastName\"/></p>\n");
      out.write("            <p>Email: <input type=\"text\" name=\"email\"/></p>\n");
      out.write("            <input type=\"submit\" name=\"submit\"/>\n");
      out.write("        </form>\n");
      out.write("\n");
      out.write("        ");

            }
        
      out.write("\n");
      out.write("\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
