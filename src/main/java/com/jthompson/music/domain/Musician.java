package com.jthompson.music.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Null;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

@Entity
@Table(name="Musician")
@NamedQueries({
  @NamedQuery(name=Musician.FIND_ALL,query=Musician.MUSICIANS_FIND_ALL),
  @NamedQuery(name=Musician.FIND_BY_ID,query=Musician.MUSICIAN_FIND_BY_ID),
  @NamedQuery(name=Musician.FIND_BY_INSTRUMENT,query=Musician.MUSICIAN_FIND_BY_INSTRUMENT),
  @NamedQuery(name=Musician.DELETE_BY_ID,query=Musician.MUSICIAN_DELETE_BY_ID)
})
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Musician extends Resource
{
	
	@Valid
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private List<Instrument> instruments = new ArrayList<Instrument>();

	
	public List<Instrument> getInstruments() {
		return instruments;
	}
	
	public void setInstruments(List<Instrument> instruments) {
		this.instruments = instruments;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Resource other = (Resource) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	public final static String FIND_ALL = "MUSICIAN_FIND_ALL";
	protected final static String MUSICIANS_FIND_ALL = "select distinct m from Musician m left join fetch m.instruments i ";
	
	public final static String FIND_BY_ID = "MUSICIAN_FIND_BY_ID";
	protected final static String MUSICIAN_FIND_BY_ID = MUSICIANS_FIND_ALL + " WHERE m.id = :musicianId ";
	
	public final static String FIND_BY_INSTRUMENT = "MUSICIAN_FIND_BY_INSTRUMENT";
	protected final static String MUSICIAN_FIND_BY_INSTRUMENT = "select m from Musician m " +
															"left join m.instruments i " +
															"where i.name = :instrumentName"; 
	
	public final static String DELETE_BY_ID = "MUSICIAN_DELETE_BY_ID";
	protected final static String MUSICIAN_DELETE_BY_ID = "delete from Musician m where m.id = :musicianId";
	
	
}
