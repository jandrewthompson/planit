package com.jthompson.music.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.sun.xml.bind.AnyTypeAdapter;

import lombok.Data;

@XmlJavaTypeAdapter(AnyTypeAdapter.class)
@XmlSeeAlso({Song.class})
public interface Schedulable 
{
	
	String getName();
	
	String getDescription();
	
	Integer getDuration();
}
