package com.framework.util;

import static java.lang.System.out;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.framework.core.bo.Visitor;
import com.framework.exception.InternalException;
import com.framework.util.property.SysProperty;

/**
 * 日志记录辅助类，包括程序逻辑，用户操作
 * 
 * @author zhangpeng
 * @version 1.0.0
 */
public class LogUtils {

	public static void trace(Log log, String messages) {
		log.trace(messages);
	}

	public static void debug(Log log, String messages) {
		log.debug(messages);
	}

	public static void info(Log log, String messages) {
		log.info(messages);
	}

	public static void warn(Log log, String messages) {
		log.warn(messages);
	}

	public static void error(Log log, String messages) {
		log.error(messages);
	}

	/**
	 * 打印异常信息
	 * 
	 * @param log
	 * @param e
	 */
	public static void error(Log log, Exception e) {
		log.error(e.getMessage());
		StackTraceElement[] ste = e.getStackTrace();
		for (int i = 0; i < ste.length; i++)
			log.error((ste[i]));
	}

	/**
	 * 打印异常信息
	 * 
	 * @param log
	 * @param e
	 */
	public static void error(Log log, String message, Exception e) {
		log.error(message);
		StackTraceElement[] ste = e.getStackTrace();
		for (int i = 0; i < ste.length; i++)
			log.error((ste[i]));
	}

	/**
	 * 当发生大异常时，除了打印信息外，还发邮件通知
	 * 
	 * @param log
	 * @param messages
	 */
	public static void fatal(Log log, String messages) {
		if (log != null) {
			log.fatal(messages);
			StringBuffer content = new StringBuffer();
			content.append(messages);
			if (!mailSender(content.toString(), messages)) {
				log.fatal("mailsend exception");
			}
		}
	}

	/**
	 * 当发生大异常时，除了打印信息外，还发邮件通知
	 * 
	 * @param log
	 * @param messages
	 * @param e
	 */
	public static void fatal(Log log, String messages, Exception e) {
		if (log != null) {
			log.fatal(messages);
			log.fatal(e);
			StringBuffer content = new StringBuffer();
			content.append(messages);
			content.append("<br>");
			content.append(getStackTraceInfo(e));
			if (!mailSender(content.toString(), messages)) {
				log.fatal("mailsend exception");
			}
		}
	}

	public boolean isDebugEnabled(Log log) {
		return log.isDebugEnabled();
	}

	public boolean isInfoEnabled(Log log) {
		return log.isInfoEnabled();
	}

	public boolean isTraceEnabled(Log log) {
		return log.isTraceEnabled();
	}

	/**
	 * 获得业务信息日志实例
	 * 
	 * @return
	 */
	public static Log getBusinessLogger() {
		return LogFactory.getLog("business");
	}

	/**
	 * 打印用户操作信息
	 * 
	 * @param visitor
	 *            用户
	 * @param doWhat
	 *            做了什么
	 */
	public static void businessInfo(Visitor visitor, String doWhat) {
		StringBuilder builder = new StringBuilder();
		builder.append("User(");
		if (visitor != null) {
			builder.append(visitor.getUcid());
		}
		builder.append(":");
		if (visitor != null) {
			// builder.append(visitor.getRole());
		}
		builder.append("),");
		builder.append(doWhat);
		builder.append(",from IP(");
		if (visitor != null) {
			builder.append(visitor.getIp());
		}
		builder.append(")");

		getBusinessLogger().info(builder.toString());
	}

	/**
	 * 打印用户操作信息
	 * 
	 * @param visitor
	 *            用户
	 * @param doWhat
	 *            干什么
	 * @param which
	 *            哪个对象
	 */
	public static void businessInfo(Visitor visitor, String doWhat, String which) {

		StringBuilder builder = new StringBuilder();
		builder.append("User(");
		if (visitor != null) {
			builder.append(visitor.getUcid());
		}
		builder.append(":");
		if (visitor != null) {
			// builder.append(visitor.getRole());
		}
		builder.append("),");
		builder.append(doWhat);
		builder.append(",Object(");
		builder.append(which);
		builder.append("),");
		builder.append("from IP(");
		if (visitor != null) {
			builder.append(visitor.getIp());
		}
		builder.append(")");

		getBusinessLogger().info(builder.toString());
	}

	/**
	 * 打印用户操作信息
	 * 
	 * @param visitor
	 *            用户
	 * @param doWhat
	 *            干什么
	 * @param which
	 *            哪个对象
	 * @param remark
	 *            备注
	 */
	public static void businessInfo(Visitor visitor, String doWhat,
			String which, String remark) {
		StringBuilder builder = new StringBuilder();
		builder.append("User(");
		if (visitor != null) {
			builder.append(visitor.getUcid());
		}
		builder.append(":");
		if (visitor != null) {
			// builder.append(visitor.getRole());
		}
		builder.append("),");
		builder.append(doWhat);
		builder.append(",Object(");
		builder.append(which);
		builder.append("),");
		builder.append(",Remark(");
		builder.append(remark);
		builder.append("),");
		builder.append("from IP(");
		if (visitor != null) {
			builder.append(visitor.getIp());
		}
		builder.append(")");

		getBusinessLogger().info(builder.toString());
	}

	/**
	 * 打印用户批处理操作信息
	 * 
	 * @param visitor
	 *            用户
	 * @param doWhat
	 *            干什么
	 * @param condition
	 *            批处理的条件
	 */
	public static void businessBatchInfo(Visitor visitor, String doWhat,
			String condition) {
		StringBuilder builder = new StringBuilder();
		builder.append("User(");
		if (visitor != null) {
			builder.append(visitor.getUcid());
		}
		builder.append(":");
		if (visitor != null) {
			// builder.append(visitor.getRole());
		}
		builder.append("),");
		builder.append(doWhat);
		builder.append(",Condition(");
		builder.append(condition);
		builder.append("),");
		builder.append(",from IP(");
		if (visitor != null) {
			builder.append(visitor.getIp());
		}
		builder.append(")");

		getBusinessLogger().info(builder.toString());
	}

	/**
	 * 打印用户批处理操作信息
	 * 
	 * @param visitor
	 *            用户
	 * @param doWhat
	 *            干什么
	 * @param condition
	 *            批处理的条件
	 * @param remark
	 *            备注
	 */
	public static void businessBatchInfo(Visitor visitor, String doWhat,
			String condition, String remark) {
		StringBuilder builder = new StringBuilder();
		builder.append("User(");
		if (visitor != null) {
			builder.append(visitor.getUcid());
		}
		builder.append(":");
		if (visitor != null) {
			// builder.append(visitor.getRole());
		}
		builder.append("),");
		builder.append(doWhat);
		builder.append(",Condition(");
		builder.append(condition);
		builder.append("),");
		builder.append(",Remark(");
		builder.append(remark);
		builder.append("),");
		builder.append(",from IP(");
		if (visitor != null) {
			builder.append(visitor.getIp());
		}
		builder.append(")");

		getBusinessLogger().info(builder.toString());
	}

	/**
	 * 发送邮件
	 * 
	 * @param content
	 * @param title
	 * @return
	 */
	private static boolean mailSender(String content, String title) {

		String hostIp = "";
		try {
			hostIp = Inet4Address.getLocalHost().getHostAddress() + "/";
		} catch (UnknownHostException e) {

		}

		String mailTitle = hostIp + Constants.LOGUTILS_MAILTITLE + "/"
				+ getSystemDate();
		int len = 0;
		int lenLimit = 255;

		if (title != null) {
			len = title.length();
			if (len > lenLimit) {
				len = lenLimit;
			}
			mailTitle += title.substring(0, len);
		}
		String mailToList = SysProperty.LOG_MAILTO;
		String mailFrom = SysProperty.LOG_MAILFROM;

		if (content == null) {
			return false;
		} else {
			try {
				MailUtils
						.sendHtmlMail(mailFrom, mailToList, mailTitle, content);
			} catch (InternalException e) {
				return false;
			}
		}
		return true;
	}

	private static String getSystemDate() {
		return DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * the following content is added by Rising. thanks
	 */
	public static void showMapList(List<Map<String, ? extends Object>> mapList) {
		out.print(mapList.size());
		for (Iterator<Map<String, ? extends Object>> ite = mapList.iterator(); ite
				.hasNext();) {
			showMap(ite.next());
		}
	}

	public static void showMap(Map<String, ? extends Object> map) {
		map = new TreeMap<String, Object>(map);
		for (Iterator<String> ite = map.keySet().iterator(); ite.hasNext();) {
			String key = ite.next();
			out.println(key + ":" + map.get(key));
		}
	}

	public static void showArray(Object[] objs) {
		if (objs != null) {
			out.println("array length = " + objs.length);
			for (int i = 0; i < objs.length; i++) {
				out.print(objs[i]);
				out.print(",");
			}
			out.println();
		}
	}

	public static void showList(Collection<? extends Object> objCollection) {
		if (objCollection != null) {
			out.println("list size = " + objCollection.size());
			for (Iterator<? extends Object> ite = objCollection.iterator(); ite
					.hasNext();) {
				out.print(ite.next());
				out.println(",");
			}
			out.println();
		} else {
			out.println("THe collection is null!");
		}
	}
	
	/**
	 * 获得方法被调用的堆栈信息
	 * 
	 * @return
	 */
	private static String getStackTraceInfo(Exception e) {
		StringBuffer stackTraceInfo = new StringBuffer();
		
		StackTraceElement[] trace = e.getStackTrace();
        for (int i=0; i < trace.length; i++) {
        	stackTraceInfo.append("\tat " + trace[i]);
        	stackTraceInfo.append("<br>");
        }
		
		return stackTraceInfo.toString();
	}
}
