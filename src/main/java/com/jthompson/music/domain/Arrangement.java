package com.jthompson.music.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Arrangement extends Schedulable
{

	private String arranger;
	
	private String composer;
	
	private Date arrangementDate;
	
	private String licenseString;
	
	private Integer lengthSeconds;
	
	@ElementCollection(fetch=FetchType.EAGER)
	@Column(name="ARR_KEYS")
	private Set<String> keys = new HashSet<String>();
	
	@Column(name="pdfData")
	private Byte[] pdfBytes;

	public String getArranger() {
		return arranger;
	}

	public void setArranger(String arranger) {
		this.arranger = arranger;
	}

	public String getComponser() {
		return composer;
	}

	public void setComponser(String componser) {
		this.composer = componser;
	}

	public Date getArrangementDate() {
		return arrangementDate;
	}

	public void setArrangementDate(Date arrangementDate) {
		this.arrangementDate = arrangementDate;
	}

	public String getLicenseString() {
		return licenseString;
	}

	public void setLicenseString(String licenseString) {
		this.licenseString = licenseString;
	}

	public Byte[] getPdfBytes() {
		return pdfBytes;
	}

	public void setPdfBytes(Byte[] pdfBytes) {
		this.pdfBytes = pdfBytes;
	}

	public Integer getLengthMinutes() {
		return lengthSeconds;
	}
	public void setLengthMinutes(Integer lengthMinutes) {
		this.lengthSeconds = lengthMinutes;
	}
	public Set<String> getKeys() {
		return keys;
	}
	public void setKeys(Set<String> keys) {
		this.keys = keys;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
