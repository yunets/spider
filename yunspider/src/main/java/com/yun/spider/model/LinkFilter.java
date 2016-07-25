/**
 *Project Name: yunspider
 *File Name:    LinkFilter.java
 *Package Name: com.yun.spider.model
 *Date:         2016年7月25日 下午1:45:48
 *Copyright (c) 2016,578888218@qq.com All Rights Reserved.
*/

package com.yun.spider.model;
/**
 *Title:      LinkFilter<br/>
 *Description:
 *@Company:   青岛励图高科<br/>
 *@author:    刘云生
 *@version:   v1.0
 *@since:     JDK 1.7.0_80
 *@Date:      2016年7月25日 下午1:45:48 <br/>
*/
public class LinkFilter {
	public boolean accept(String url) {
	    if (url.startsWith("http://www.zhihu.com/"))
	     return true;
	    else
	     return false;
	   }
}

