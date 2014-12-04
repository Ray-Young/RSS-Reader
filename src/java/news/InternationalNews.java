/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package news;
import com.sun.org.apache.xml.internal.serialize.OutputFormat;  
import java.io.FileNotFoundException;  
import java.io.FileOutputStream;  
import java.io.IOException;  
import java.io.OutputStreamWriter;
import java.io.PrintWriter;  
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;  
import javax.xml.parsers.DocumentBuilderFactory;  
import javax.xml.parsers.ParserConfigurationException;  
import javax.xml.transform.OutputKeys;  
import javax.xml.transform.Transformer;  
import javax.xml.transform.TransformerConfigurationException;  
import javax.xml.transform.TransformerException;  
import javax.xml.transform.TransformerFactory;  
import javax.xml.transform.dom.DOMSource;  
import javax.xml.transform.stream.StreamResult;  
import org.gnu.stealthp.rsslib.RSSChannel;
import org.gnu.stealthp.rsslib.RSSException;
import org.gnu.stealthp.rsslib.RSSHandler;
import org.gnu.stealthp.rsslib.RSSImage;  
import org.gnu.stealthp.rsslib.RSSItem;  
import org.gnu.stealthp.rsslib.RSSParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;  
import org.w3c.dom.Node;  
import org.w3c.dom.NodeList;  
import org.xml.sax.SAXException;  


/**
 *
 * @author Andy
 */

//网易RSS国际版    http://news.163.com/special/00011K6L/rss_gn.xml
//新浪RSS国际版    http://rss.sina.com.cn/news/world/focus15.xml
//人民RSS国际版    http://www.people.com.cn/rss/world.xml
//新华RSS国际版    http://www.xinhuanet.com/world/news_world.xml
public class InternationalNews  {
    private Document document;  
    private String fileName;  

      
    public void init() {  
        try {  
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();  
            DocumentBuilder builder = factory.newDocumentBuilder();  
            this.document = builder.newDocument();  
        } catch(ParserConfigurationException  e) {  
            System.out.println(e.getMessage());  
        }  
    }  
      
    //创建一个xml文件  
    public void createXml(String path)  {  
        RSSHandler handler_people = new RSSHandler(); 
        RSSHandler handler_Sina = new RSSHandler(); 
        RSSHandler handler_NetEase = new RSSHandler(); 
        RSSHandler handler_Sohu = new RSSHandler(); 
        String remoteRSS_People="http://www.people.com.cn/rss/world.xml"; 
        String remoteRSS_Sina="http://rss.sina.com.cn/news/world/focus15.xml"; 
        String remoteRSS_NetEase="http://news.163.com/special/00011K6L/rss_gj.xml"; 
        String remoteRSS_Sohu="http://rss.news.sohu.com/rss/guoji.xml"; 
        
        try { 
            RSSParser.parseXmlFile(new URL(remoteRSS_People), handler_people, false);
        } catch (RSSException ex) {
            Logger.getLogger(InternationalNews.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(InternationalNews.class.getName()).log(Level.SEVERE, null, ex);
        }
        RSSChannel channel = handler_people.getRSSChannel();
        Element Channel = this.document.createElement("root");
        this.document.appendChild(Channel);
        List channelItems = channel.getItems(); 
        int itemSize= channelItems.size(); 
        if(itemSize >=1){ 
            for (int i=0;i<itemSize;i++){ 
                int itemNo = i+1; 
                RSSItem item = (RSSItem)channelItems.get(i); 
                Element elements = this.document.createElement("news");
                elements.setAttribute("newsNo", ""+itemNo);
                Channel.appendChild(elements);
                //(2)摘要的标题 
                String Title_People = item.getTitle(); 
                //(4)摘要的链接 
                String Link_People = item.getLink(); 
                //(5)摘要的发布日期 
                String Pub_People = item.getPubDate(); 
                Element publisher_title=this.document.createElement("publisher_title");
                publisher_title.appendChild(this.document.createTextNode("人民网国际新闻"));
                Element title_people = this.document.createElement("news_title");
                title_people.appendChild(this.document.createTextNode(Title_People));
                Element link_people = this.document.createElement("news_link");
                link_people.appendChild(this.document.createTextNode(Link_People));
                Element pub_people = this.document.createElement("publish_time");
                pub_people.appendChild(this.document.createTextNode(Pub_People));
                elements.appendChild(publisher_title);
                elements.appendChild(title_people);
                elements.appendChild(link_people);
                elements.appendChild(pub_people);
            } 
        } 
        
        try { 
            RSSParser.parseXmlFile(new URL(remoteRSS_Sina), handler_Sina, false);
        } catch (RSSException ex) {
            Logger.getLogger(InternationalNews.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(InternationalNews.class.getName()).log(Level.SEVERE, null, ex);
        }
        RSSChannel channel_Sina = handler_Sina.getRSSChannel();
        List channelItems_Sina = channel_Sina.getItems(); 
        int itemSize_Sina= channelItems_Sina.size(); 
        
        if(itemSize_Sina >=1){ 
            for (int i=0;i<itemSize_Sina;i++){ 
                int itemNo = i+1; 
                RSSItem item = (RSSItem)channelItems_Sina.get(i); 
                 
                Element elements = this.document.createElement("news");
                elements.setAttribute("newsNo", ""+itemNo);
                Channel.appendChild(elements);
                //(2)摘要的标题 
                String Title_Sina = item.getTitle(); 
                //(4)摘要的链接 
                String Link_Sina = item.getLink(); 
                //(5)摘要的发布日期 
                String Pub_Sina = item.getPubDate(); 
                Element publisher_title=this.document.createElement("publisher_title");
                publisher_title.appendChild(this.document.createTextNode("新浪网国际新闻"));
                Element title_people = this.document.createElement("news_title");
                title_people.appendChild(this.document.createTextNode(Title_Sina));
                Element link_people = this.document.createElement("news_link");
                link_people.appendChild(this.document.createTextNode(Link_Sina));
                Element pub_people = this.document.createElement("publish_time");
                pub_people.appendChild(this.document.createTextNode(Pub_Sina));
                elements.appendChild(publisher_title);
                elements.appendChild(title_people);
                elements.appendChild(link_people);
                elements.appendChild(pub_people);
            } 
        } 
        try { 
            RSSParser.parseXmlFile(new URL(remoteRSS_NetEase), handler_NetEase, false);
        } catch (RSSException ex) {
            Logger.getLogger(InternationalNews.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(InternationalNews.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        
        RSSChannel channel_NetEase = handler_NetEase.getRSSChannel();
        List channelItems_NetEase = channel_NetEase.getItems(); 
        int itemSize_NetEase= channelItems_NetEase.size(); 
        
        if(itemSize_NetEase >=1){ 
            for (int i=0;i<itemSize_NetEase;i++){ 
                int itemNo = i+1; 
                RSSItem item = (RSSItem)channelItems_NetEase.get(i); 
                 
                Element elements = this.document.createElement("news");
                elements.setAttribute("newsNo", ""+itemNo);
                Channel.appendChild(elements);
                //(2)摘要的标题 
                String Title_Sina = item.getTitle();                           //这里是以item为操作对象，所以不用再更改变量名了
                //(4)摘要的链接 
                String Link_Sina = item.getLink(); 
                //(5)摘要的发布日期 
                String Pub_Sina = item.getPubDate(); 
                
                Element publisher_title=this.document.createElement("publisher_title");
                publisher_title.appendChild(this.document.createTextNode("网易网国际新闻"));
                Element title_people = this.document.createElement("news_title");
                title_people.appendChild(this.document.createTextNode(Title_Sina));
                Element link_people = this.document.createElement("news_link");
                link_people.appendChild(this.document.createTextNode(Link_Sina));
                Element pub_people = this.document.createElement("publish_time");
                pub_people.appendChild(this.document.createTextNode(Pub_Sina));
                elements.appendChild(publisher_title);
                elements.appendChild(title_people);
                elements.appendChild(link_people);
                elements.appendChild(pub_people);
                
            } 
        } 
        
        try { 
            RSSParser.parseXmlFile(new URL(remoteRSS_Sohu), handler_Sohu, false);
        } catch (RSSException ex) {
            Logger.getLogger(InternationalNews.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(InternationalNews.class.getName()).log(Level.SEVERE, null, ex);
        }
        RSSChannel channel_Sohu = handler_Sohu.getRSSChannel();

        List channelItems_Sohu = channel_Sohu.getItems(); 
        int itemSize_Sohu= channelItems_Sohu.size(); 
        
        if(itemSize_Sohu >=1){ 
            for (int i=0;i<itemSize_Sohu;i++){ 
                int itemNo = i+1; 
                RSSItem item = (RSSItem)channelItems_Sohu.get(i); 
                 
                Element elements = this.document.createElement("news");
                elements.setAttribute("newsNo", ""+itemNo);
                Channel.appendChild(elements);                        //这里是以item为操作对象，所以之后就不用再更改变量名了
                //(2)摘要的标题 
                String Title_Sina = item.getTitle();                           
                //(4)摘要的链接 
                String Link_Sina = item.getLink(); 
                //(5)摘要的发布日期 
                String Pub_Sina = item.getPubDate(); 
                
                Element publisher_title=this.document.createElement("publisher_title");
                publisher_title.appendChild(this.document.createTextNode("搜狐网国际新闻"));
                Element title_people = this.document.createElement("news_title");
                title_people.appendChild(this.document.createTextNode(Title_Sina));
                Element link_people = this.document.createElement("news_link");
                link_people.appendChild(this.document.createTextNode(Link_Sina));
                Element pub_people = this.document.createElement("publish_time");
                pub_people.appendChild(this.document.createTextNode(Pub_Sina));
                elements.appendChild(publisher_title);
                elements.appendChild(title_people);
                elements.appendChild(link_people);
                elements.appendChild(pub_people);
   
            } 
        } 
        
    

        TransformerFactory tf = TransformerFactory.newInstance();
        try {
            Transformer transformer = tf.newTransformer();
            DOMSource source = new DOMSource(document);
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");          //这里必须用UTF，而不能用GBK2312
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            FileOutputStream p = new FileOutputStream(path+"/XML/InternationalNews.xml");
            Writer pw = new OutputStreamWriter(p, "UTF-8");
            StreamResult result = new StreamResult(pw);
            transformer.transform(source, result);
            System.out.println("生成XML_InternationalNews文件成功!");
        } catch (TransformerConfigurationException e) {
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (TransformerException e) {
            System.out.println(e.getMessage());
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(InternationalNews.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
    }

