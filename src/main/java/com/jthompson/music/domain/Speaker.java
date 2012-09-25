package com.jthompson.music.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Entity
@Table(name="Speaker")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Speaker
{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	protected Integer id;
	
	protected String description;

	private String name;
	

}
