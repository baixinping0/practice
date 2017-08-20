package com.baixinping.cvtepro.common.exeception;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baixinping.cvtepro.common.utils.format.FormatUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;


@Component
public class MyExeceptiom implements HandlerExceptionResolver{
	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object arg2, Exception exception) {

		String fileName = FormatUtil.formatDate(System.currentTimeMillis());
		//获取当前项目的根路径
		//\install\apache-tomcat-7.0.79\temp\my_webapp\webapps\cvtep
		String rootPath = request.getSession().getServletContext().getRealPath("");
		//创建日志目录
		String dir = rootPath+ "\\logs";
		new File(dir).mkdirs();
		//创建日志文件
		File file = new File(dir + "\\" + fileName + ".log");
		try {
			file.createNewFile();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(file, true);
			StringWriter sw = new StringWriter();
			exception.printStackTrace(new PrintWriter(sw, true));
			String newLine = System.getProperty("line.separator");
			out.write((FormatUtil.formatDateTime(System.currentTimeMillis()) + newLine).getBytes());
			out.write(sw.toString().getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return new ModelAndView("error");
	}
}
