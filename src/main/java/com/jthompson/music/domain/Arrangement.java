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

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Arrangement 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	private String name;
	
	private String description;

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

	
}
