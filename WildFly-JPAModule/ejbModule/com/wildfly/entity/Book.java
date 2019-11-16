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

import com.wildfly.model.Category;

@Entity
@Table(name = "T_BOOK")
public class Book implements Serializable, Comparable<Book> {

	private static final long serialVersionUID = 2249829101796491414L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "B_SEQ")
    	@SequenceGenerator(sequenceName = "T_BOOK_SEQUENCE", allocationSize = 1, initialValue = 1, name = "B_SEQ")
	@Column(name = "ID")
	private Long id;
	@Nationalized
	@Column(name = "TITLE", length = 50)
	private String title;
	@Nationalized
	@Column(name = "DESCRIPTION", length = 100)
	private String description;
	@Column(name = "UNIT_COST")
	private Float unitCost;
	@Column(name = "CATEGORY")
	private Category category;
	@Nationalized
	@Column(name = "IMAGE_URL", length = 250)
	private String imageUrl;
	@Temporal(TemporalType.DATE)
	@Column(name = "PUBLICATION_DATE")
	private Date publicationDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Float getUnitCost() {
		return unitCost;
	}

	public void setUnitCost(Float unitCost) {
		this.unitCost = unitCost;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Date getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
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
		Book other = (Book) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", description=" + description + ", unitCost=" + unitCost
				+ ", category=" + category + ", imageUrl=" + imageUrl + ", publicationDate=" + publicationDate + "]";
	}

	@Override
	public int compareTo(Book o) {
		if(this.id == o.getId())
			return 0;
		else if(this.id > o.getId())
			return 1;
		else
			return -1;
		
	}



}
