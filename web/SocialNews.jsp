<%-- 
    Document   : SocialNews
    Created on : 2014-4-8, 21:04:34
    Author     : Andy
--%>

<%@page import="news.SocialNews"%>
<%@page import="java.io.File"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="org.dom4j.io.SAXReader"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="org.dom4j.Element"%>
<%@page import="org.dom4j.Document"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>社会新闻</h1>

        <form>
            <input type="text" name="content"/>
            <input type="submit" value="submit"/>
        </form>
         <form>
            <input type="button" value="返回首页" onclick="window.location.href='MainPage.jsp'" />
        </form>
        <table border="1" >
            <th class="th_border">新闻来源</th>
            <th class="th_border">新闻标题</th>
            <th class="" >link</th>
            <th class="">开布时间</th>
                <%
                    request.setCharacterEncoding("utf8");
                    String search_key = request.getParameter("content");
                    out.print("<h2>");
                    out.print("Search_Key= " + search_key);
                    out.print("</h2>");
                    SocialNews w = new SocialNews();
                    String path = getServletContext().getRealPath("/");
                    w.init();
                    w.createXml(path);

                    SAXReader reader = new SAXReader();
                    Document document = reader.read(new File(path + "/XML/SocialNews.xml"));
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


                %>
        </table>
    </body>
</html>
