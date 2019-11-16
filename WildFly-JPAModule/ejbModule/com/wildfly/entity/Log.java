package com.wildfly.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Nationalized;

@Entity
@Table(name = "T_LOG")
public class Log implements Serializable, Comparable<Log> {

	private static final long serialVersionUID = -1364544137247557945L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "L_SEQ")
	@SequenceGenerator(sequenceName = "T_LOG_SEQUENCE", allocationSize = 1, initialValue = 1, name = "L_SEQ")
	@Column(name = "ID")
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "PROCESS_TIME")
	private Date processTime;
	@Nationalized
	@Column(name = "SOURCE")
	private String source;
	@Nationalized
	@Column(name = "LOG")
	private String log;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getProcessTime() {
		return processTime;
	}

	public void setProcessTime(Date processTime) {
		this.processTime = processTime;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getLog() {
		return log;
	}

	public void setLog(String log) {
		this.log = log;
	}

	@Override
	public String toString() {
		return "Log [id=" + id + ", processTime=" + processTime + ", source=" + source + ", log=" + log + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Log other = (Log) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public int compareTo(Log o) {
		if (this.id == o.getId())
			return 0;
		else if (this.id > o.getId())
			return 1;
		else
			return -1;
	}

}
