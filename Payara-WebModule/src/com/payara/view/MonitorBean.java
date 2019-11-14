package com.payara.view;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.payara.util.ServiceUtil;
import com.wildfly.entity.Log;
import com.wildfly.service.LogService;

@Named
@SessionScoped
public class MonitorBean implements Serializable {

	private static final long serialVersionUID = 6787664332946823939L;
	
	private LogService logService;
	private List<Log> logs;
	private int logCount;

	@PostConstruct
	public void init() {
		logService = ServiceUtil.getRemoteBean(LogService.class);
		logs = logService.getLogs();
		logCount = logs.size();
		Collections.sort(logs, Collections.reverseOrder());
	}

	public LogService getLogClientService() {
		return logService;
	}

	public void setLogClientService(LogService logService) {
		this.logService = logService;
	}

	public List<Log> getLogs() {
		return logs;
	}

	public void setLogs(List<Log> logs) {
		this.logs = logs;
	}

	public int getLogCount() {
		return logCount;
	}

	public void setLogCount(int logCount) {
		this.logCount = logCount;
	}

	public void refreshLogs() {
		logs = logService.getLogs();
		logCount = logs.size();
		Collections.sort(logs, Collections.reverseOrder());
	}

}
