/**
 *Project Name: yunspider
 *File Name:    BfsSpider.java
 *Package Name: com.yun.spider
 *Date:         2016年7月25日 下午1:44:53
 *Copyright (c) 2016,578888218@qq.com All Rights Reserved.
*/

package com.yun.spider;

import java.util.Set;

import com.yun.spider.model.LinkFilter;
import com.yun.spider.model.SpiderQueue;

/**
 *Title:      BfsSpider<br/>
 *Description:
 *@Company:   青岛励图高科<br/>
 *@author:    刘云生
 *@version:   v1.0
 *@since:     JDK 1.7.0_80
 *@Date:      2016年7月25日 下午1:44:53 <br/>
*/
public class BfsSpider {
	/**
	  * 使用种子初始化URL队列
	  */
	 private void initCrawlerWithSeeds(String[] seeds) {
	  for (int i = 0; i < seeds.length; i++)
	   SpiderQueue.addUnvisitedUrl(seeds[i]);
	 }
	 // 定义过滤器，提取以 http://www.xxxx.com开头的链接
	 public void crawling(String[] seeds) {
	  LinkFilter filter = new LinkFilter() {
	   public boolean accept(String url) {
	    if (url.startsWith("http://www.zhihu.com/"))
	     return true;
	    else
	     return false;
	   }
	  };
	  // 初始化 URL 队列
	  initCrawlerWithSeeds(seeds);
	  // 循环条件：待抓取的链接不空且抓取的网页不多于 1000
	  while (!SpiderQueue.unVisitedUrlsEmpty()
	    && SpiderQueue.getVisitedUrlNum() <= 1000) {
	   // 队头 URL 出队列
	   String visitUrl = (String) SpiderQueue.unVisitedUrlDeQueue();
	   if (visitUrl == null)
	    continue;
	   DownTool downLoader = new DownTool();
	   // 下载网页
	   downLoader.downloadFile(visitUrl);
	   // 该 URL 放入已访问的 URL 中
	   SpiderQueue.addVisitedUrl(visitUrl);
	   // 提取出下载网页中的 URL
	   Set<String> links = HtmlParserTool.extracLinks(visitUrl, filter);
	   // 新的未访问的 URL 入队
	   for (String link : links) {
	    SpiderQueue.addUnvisitedUrl(link);
	   }
	  }
	 }
	 // main 方法入口
	 public static void main(String[] args) {
	  BfsSpider crawler = new BfsSpider();
	  crawler.crawling(new String[] { "http://www.zhihu.com/" });
	 }
}

