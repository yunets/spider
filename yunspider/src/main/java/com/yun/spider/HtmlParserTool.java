/**
 *Project Name: yunspider
 *File Name:    HtmlParserTool.java
 *Package Name: com.yun.spider
 *Date:         2016年7月25日 下午1:27:29
 *Copyright (c) 2016,578888218@qq.com All Rights Reserved.
*/

package com.yun.spider;

import java.util.HashSet;
import java.util.Set;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.filters.OrFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import com.yun.spider.model.LinkFilter;
/**
 *Title:      HtmlParserTool<br/>
 *Description:
 *@Company:   青岛励图高科<br/>
 *@author:    刘云生
 *@version:   v1.0
 *@since:     JDK 1.7.0_80
 *@Date:      2016年7月25日 下午1:27:29 <br/>
*/
public class HtmlParserTool {
	// 获取一个网站上的链接，filter 用来过滤链接
	 public static Set<String> extracLinks(String url, LinkFilter filter) {
	  Set<String> links = new HashSet<String>();
	  try {
	   Parser parser = new Parser(url);
	   parser.setEncoding("gb2312");
	   // 过滤 <frame >标签的 filter，用来提取 frame 标签里的 src 属性
	   NodeFilter frameFilter = new NodeFilter() {
	    private static final long serialVersionUID = 1L;
	    @Override
	    public boolean accept(Node node) {
	     if (node.getText().startsWith("frame src=")) {
	      return true;
	     } else {
	      return false;
	     }
	    }
	   };
	   // OrFilter 来设置过滤 <a> 标签和 <frame> 标签
	   OrFilter linkFilter = new OrFilter(new NodeClassFilter(
	     LinkTag.class), frameFilter);
	   // 得到所有经过过滤的标签
	   NodeList list = parser.extractAllNodesThatMatch(linkFilter);
	   for (int i = 0; i < list.size(); i++) {
	    Node tag = list.elementAt(i);
	    if (tag instanceof LinkTag)// <a> 标签
	    {
	     LinkTag link = (LinkTag) tag;
	     String linkUrl = link.getLink();// URL
	     if (filter.accept(linkUrl))
	      links.add(linkUrl);
	    } else// <frame> 标签
	    {
	     // 提取 frame 里 src 属性的链接， 如 <frame src="test.html"/>
	     String frame = tag.getText();
	     int start = frame.indexOf("src=");
	     frame = frame.substring(start);
	     int end = frame.indexOf(" ");
	     if (end == -1)
	      end = frame.indexOf(">");
	     String frameUrl = frame.substring(5, end - 1);
	     if (filter.accept(frameUrl))
	      links.add(frameUrl);
	    }
	   }
	  } catch (ParserException e) {
	   e.printStackTrace();
	  }
	  return links;
	 }
}

