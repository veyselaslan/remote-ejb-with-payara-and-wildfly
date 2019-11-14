package com.wildfly.service;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.wildfly.entity.Log;

@Stateless
public class LogServiceBean implements LogService {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void createLog(Date processTime, String source, String log) {
		try {
			Log logRecord = new Log();
			logRecord.setProcessTime(processTime);
			logRecord.setSource(source);
			logRecord.setLog(log);
			em.persist(logRecord);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Log> getLogs() {
		TypedQuery<Log> logs = null;
		List<Log> result = null;
		try {
			logs = em.createQuery("Select l from Log l", Log.class);
			result = logs.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
