package com.jthompson.music.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Document(collection="arrangements")
public class Arrangement implements Schedulable, Displayable
{
	private String id;

	private String name;
	
	private String description;

	private String arranger;
	
	private String composer;
	
	private Date arrangementDate;
	
	private String licenseString;
	
	private Integer lengthSeconds;

	private Set<String> keys = new HashSet<String>();
	
	private Byte[] pdfBytes;
	
	public Integer getDuration()
	{
		return lengthSeconds;
	}
	
	@Override
	public String display() {
		return description;
	}

	
}
