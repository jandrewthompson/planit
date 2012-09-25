package com.jthompson.music.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Song implements Displayable
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	private String name;
	
	private String description;
	
	private String composer;
	
	private Date compositionDate;
	
	private Integer ccliLicenseNumber;
	
	private Integer lengthSeconds;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, orphanRemoval=true)
	@JoinTable(name="Song_Arrangement")
	private Set<Arrangement> arrangements = new HashSet<Arrangement>();

	@Override
	public String display() {
		return description;
	}
	
}
