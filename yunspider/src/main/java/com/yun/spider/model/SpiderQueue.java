/**
 *Project Name: yunspider
 *File Name:    SpiderQueue.java
 *Package Name: com.yun.spider
 *Date:         2016年7月25日 下午1:19:47
 *Copyright (c) 2016,578888218@qq.com All Rights Reserved.
*/

package com.yun.spider.model;

import java.util.HashSet;
import java.util.Set;

import com.yun.spider.Queue;

/**
 *Title:      SpiderQueue<br/>
 *Description:
 *@Company:   青岛励图高科<br/>
 *@author:    刘云生
 *@version:   v1.0
 *@since:     JDK 1.7.0_80
 *@Date:      2016年7月25日 下午1:19:47 <br/>
*/
public class SpiderQueue {
	/**
	  * 已访问的url集合，即Visited表
	  */
	 private static Set<Object> visitedUrl = new HashSet<>();
	 /**
	  * 添加到访问过的 URL 队列中
	  */
	 public static void addVisitedUrl(String url) {
	  visitedUrl.add(url);
	 }
	 /**
	  * 移除访问过的 URL
	  */
	 public static void removeVisitedUrl(String url) {
	  visitedUrl.remove(url);
	 }
	 /**
	  * 获得已经访问的 URL 数目
	  */
	 public static int getVisitedUrlNum() {
	  return visitedUrl.size();
	 }
	 /**
	  * 待访问的url集合，即unVisited表
	  */
	 private static Queue unVisitedUrl = new Queue();
	 /**
	  * 获得UnVisited队列
	  */
	 public static Queue getUnVisitedUrl() {
	  return unVisitedUrl;
	 }
	 /**
	  * 未访问的unVisitedUrl出队列
	  */
	 public static Object unVisitedUrlDeQueue() {
	  return unVisitedUrl.deQueue();
	 }
	 /**
	  * 保证添加url到unVisitedUrl的时候每个 URL只被访问一次
	  */
	 public static void addUnvisitedUrl(String url) {
	  if (url != null && !url.trim().equals("") && !visitedUrl.contains(url)
	    && !unVisitedUrl.contians(url))
	   unVisitedUrl.enQueue(url);
	 }
	 /**
	  * 判断未访问的 URL队列中是否为空
	  */
	 public static boolean unVisitedUrlsEmpty() {
	  return unVisitedUrl.empty();
	 }
}

