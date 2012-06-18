package com.jthompson.music.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
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
	
	private String componser;
	
	private Date arrangementDate;
	
	private String licenseString;
	
	@Column(name="pdfData")
	private Byte[] pdfBytes;

	public String getArranger() {
		return arranger;
	}

	public void setArranger(String arranger) {
		this.arranger = arranger;
	}

	public String getComponser() {
		return componser;
	}

	public void setComponser(String componser) {
		this.componser = componser;
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

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
