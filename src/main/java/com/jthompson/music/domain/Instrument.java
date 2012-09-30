package com.jthompson.music.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Table
@XmlRootElement
@Document(collection="instruments")
public class Instrument 
{
	
	private String id;
	
	private String name;

	
}
