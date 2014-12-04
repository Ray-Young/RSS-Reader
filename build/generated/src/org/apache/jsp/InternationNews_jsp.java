package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.Map;
import java.util.HashMap;
import org.dom4j.io.SAXReader;
import java.util.Iterator;
import java.util.List;
import org.dom4j.Element;
import org.dom4j.Document;
import java.io.File;
import news.*;

public final class InternationNews_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>JSP Page</title>\n");
      out.write("    </head>\n");
      out.write("    <body>    \n");
      out.write("        <h1>国际新闻</h1>\n");
      out.write("        <form>\n");
      out.write("            Search <input type=\"text\" name=\"content\"/>\n");
      out.write("            <input type=\"submit\" value=\"submit\"/>\n");
      out.write("        </form>\n");
      out.write("       \n");
      out.write("        <form>\n");
      out.write("            <input type=\"button\" value=\"back\" onclick=\"window.location.href='MainPage.jsp'\" />\n");
      out.write("        </form>\n");
      out.write("       \n");
      out.write("        \n");
      out.write("        <table border=\"1\" >\n");
      out.write("            <th class=\"th_border\">新闻来源</th>\n");
      out.write("            <th class=\"th_border\">新闻标题</th>\n");
      out.write("            <th class=\"\" >link</th>\n");
      out.write("            <th class=\"\">开布时间</th>\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("            ");

                request.setCharacterEncoding("utf8");
                String search_key = request.getParameter("content");
                out.print("<h2>");
                out.print("Search_Key= " + search_key);
                out.print("</h2>");
                InternationalNews w = new InternationalNews();
                String path = getServletContext().getRealPath("/");
                w.init();
                w.createXml(path);
                SAXReader reader = new SAXReader();
                Document document = reader.read(new File(path + "/XML/InternationalNews.xml"));
                Element rootElm = document.getRootElement();
                Element elm = rootElm.element("news");                 //将publisher注明为root的子节点 
                List nodes = rootElm.elements("news");                        //将publisher与news绑定，这样之后的news指针会随publisher的移动而移动
       for (Iterator it = nodes.iterator(); it.hasNext();) {
                    Element elm1 = (Element) it.next();
                    if (search_key == null) {
                        Element publisher = elm1.element("publisher_title");
                        Element title = elm1.element("news_title");
                        Element link = elm1.element("news_link");
                        Element publish = elm1.element("publish_time");
                        out.print("<tr>");
                        out.print("<td>");
                        out.print(publisher.getText());
                        out.print("</td>");
                        out.print("<td>");
                        out.print(title.getText());
                        out.print("</td>");
                        out.print("<td style=\"width:300;word-break:break-all\"><a href=\"" + link.getText() + "\">");
                        out.print(link.getText());
                        out.print("</td></a>");
                        out.print("<td>");
                        out.print(publish.getText());
                        out.print("</td>");
                        out.print("</tr>");
                    } else {
                        Element publisher = elm1.element("publisher_title");
                        Element title = elm1.element("news_title");
                        Element link = elm1.element("news_link");
                        Element publish = elm1.element("publish_time");
                        if (title.getText().contains(search_key)) {
                            out.print("<tr>");
                            out.print("<td>");
                            out.print(publisher.getText());
                            out.print("</td>");
                            out.print("<td>");
                            out.print(title.getText());
                            out.print("</td>");
                            out.print("<td style=\"width:300;word-break:break-all\"><a href=\"" + link.getText() + "\">");
                            out.print(link.getText());
                            out.print("</td></a>");
                            out.print("<td>");
                            out.print(publish.getText());
                            out.print("</td>");
                            out.print("</tr>");
                        }
                    }
                }


            
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("        </table>\n");
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
