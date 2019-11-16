package com.payara.view;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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

	@PostConstruct
	public void init() {
		logService = ServiceUtil.getRemoteBean(LogService.class);
		if(logService != null) {
			logs = logService.getLogs();
			Collections.sort(logs, Collections.reverseOrder());
			FacesMessage msg = new FacesMessage("SUCCESS: LogServiceBean has been loaded from WildFly");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		else {
			FacesMessage msg = new FacesMessage("WARNING: LogServiceBean could not be loaded from WildFly");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		
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

	public void refreshLogs() {
		logs = logService.getLogs();
		Collections.sort(logs, Collections.reverseOrder());
	}

}
