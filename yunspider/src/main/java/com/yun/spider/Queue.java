/**
 *Project Name: yunspider
 *File Name:    Queue.java
 *Package Name: com.yun.spider
 *Date:         2016年7月25日 下午1:18:50
 *Copyright (c) 2016,578888218@qq.com All Rights Reserved.
*/

package com.yun.spider;

import java.util.LinkedList;

/**
 *Title:      Queue<br/>
 *Description:
 *@Company:   青岛励图高科<br/>
 *@author:    刘云生
 *@version:   v1.0
 *@since:     JDK 1.7.0_80
 *@Date:      2016年7月25日 下午1:18:50 <br/>
*/
public class Queue {
	/**
	  * 定义一个队列，使用LinkedList实现
	  */
	 private LinkedList<Object> queue = new LinkedList<Object>(); // 入队列
	 /**
	  * 将t加入到队列中
	  */
	 public void enQueue(Object t) {
	  queue.addLast(t);
	 }
	 /**
	  * 移除队列中的第一项并将其返回
	  */
	 public Object deQueue() {
	  return queue.removeFirst();
	 }
	 /**
	  * 返回队列是否为空
	  */
	 public boolean isQueueEmpty() {
	  return queue.isEmpty();
	 }
	 /**
	  * 判断并返回队列是否包含t
	  */
	 public boolean contians(Object t) {
	  return queue.contains(t);
	 }
	 /**
	  * 判断并返回队列是否为空
	  */
	 public boolean empty() {
	  return queue.isEmpty();
	 }
}

