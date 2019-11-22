package com.wildfly.service;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import com.wildfly.entity.Log;

@Remote
public interface LogService {

	void createLog(Date processTime, String source, String log);
	List<Log> getLogs();

}
