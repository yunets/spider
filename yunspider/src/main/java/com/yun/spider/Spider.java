/**
 *Project Name: yunspider
 *File Name:    Spider.java
 *Package Name: com.yun.spider
 *Date:         2016年7月25日 上午11:40:33
 *Copyright (c) 2016,578888218@qq.com All Rights Reserved.
*/

package com.yun.spider;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;

/**
 *Title:      Spider<br/>
 *Description:
 *@Company:   青岛励图高科<br/>
 *@author:    刘云生
 *@version:   v1.0
 *@since:     JDK 1.7.0_80
 *@Date:      2016年7月25日 上午11:40:33 <br/>
*/
public class Spider {
	private static HttpClient httpClient = new HttpClient();
	 /**
	  * @param path
	  *            目标网页的链接
	  * @return 返回布尔值，表示是否正常下载目标页面
	  * @throws Exception
	  *             读取网页流或写入本地文件流的IO异常
	  */
	 public static boolean downloadPage(String path) throws Exception {
	  // 定义输入输出流
	  InputStream input = null;
	  OutputStream output = null;
	  
	  
	  File file=new File("D:\\LuceneworkSpace\\spider.html");
	  FileOutputStream fileout=new FileOutputStream(file);
	  OutputStreamWriter writer=new OutputStreamWriter(fileout);
	  // 得到 post 方法
	  GetMethod getMethod = new GetMethod(path);
	  // 执行，返回状态码
	  int statusCode = httpClient.executeMethod(getMethod);
	  // 针对状态码进行处理
	  // 简单起见，只处理返回值为 200 的状态码
	  if (statusCode == HttpStatus.SC_OK) {
	   input = getMethod.getResponseBodyAsStream();
	   // 通过对URL的得到文件名
	   String filename = path.substring(path.lastIndexOf('/') + 1)
	     + ".html";
	   // 获得文件输出流
	   output = new FileOutputStream(filename);
	   // 输出到文件
	   int tempByte = -1;
	   while ((tempByte = input.read()) > 0) {
	    output.write(tempByte);
	    writer.write(tempByte);
	    
	   }
	  
	    
	   System.out.println(output);
	   // 关闭输入流
	   if (input != null) {
	    input.close();
	   }
	   // 关闭输出流
	   if (output != null) {
	    output.close();
	    writer.close();
	    fileout.close();
	   }
	   return true;
	  }
	  return false;
	 }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			   // 抓取百度首页，输出
			   Spider.downloadPage("http://www.baidu.com");
			  } catch (Exception e) {
			   e.printStackTrace();
			  }
	}

}

