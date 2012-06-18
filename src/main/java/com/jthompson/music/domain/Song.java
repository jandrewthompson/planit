package com.jthompson.music.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Song extends Schedulable
{
	
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, orphanRemoval=true)
	@JoinTable(name="Song_Arrangement")
	private List<Arrangement> arrangements = new ArrayList<Arrangement>();
 

	public List<Arrangement> getArrangements() {
		return arrangements;
	}

	public void setArrangements(List<Arrangement> arrangements) {
		this.arrangements = arrangements;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
	
}
