package com.jthompson.music.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
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

	private String composer;
	
	private Date compositionDate;
	
	private Integer ccliLicenseNumber;
	
	private Integer lengthSeconds;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, orphanRemoval=true)
	@JoinTable(name="Song_Arrangement")
	private Set<Arrangement> arrangements = new HashSet<Arrangement>();
 

	public Set<Arrangement> getArrangements() {
		return arrangements;
	}

	public void setArrangements(Set<Arrangement> arrangements) {
		this.arrangements = arrangements;
	}
	
	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return super.getDescription();
	}
	@Override
	public void setDescription(String description) {
		// TODO Auto-generated method stub
		super.setDescription(description);
	}
	
	
	public String getComposer() {
		return composer;
	}

	public void setComposer(String composer) {
		this.composer = composer;
	}

	public Date getCompositionDate() {
		return compositionDate;
	}

	public void setCompositionDate(Date compositionDate) {
		this.compositionDate = compositionDate;
	}

	public Integer getCcliLicenseLumber() {
		return ccliLicenseNumber;
	}

	public void setCcliLicenseLumber(Integer ccliLicenseLumber) {
		this.ccliLicenseNumber = ccliLicenseLumber;
	}

	public Integer getLengthSeconds() {
		return lengthSeconds;
	}

	public void setLengthSeconds(Integer lengthSeconds) {
		this.lengthSeconds = lengthSeconds;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
	
}
