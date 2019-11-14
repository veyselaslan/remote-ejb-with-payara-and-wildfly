package com.payara.util;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ServiceUtil {
	
	private static Properties props;
	private static Context context;
	private static final String LOOKUP = "ejb:/WildFly-JPAModule//%sBean!%s";

	static {
		props = new Properties();
		props.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		try {
			context = new InitialContext(props);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public static <T> T getRemoteBean(Class<T> type) {
		String lookupBean = String.format(LOOKUP, type.getSimpleName(), type.getName());
		try {
			T bean = (T) context.lookup(lookupBean);
			if (bean != null)
				return bean;
			else
				throw new Exception(type.getSimpleName() + " Lookup Failed!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	

	
}
