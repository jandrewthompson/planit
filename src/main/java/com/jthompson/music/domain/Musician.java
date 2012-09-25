package com.jthompson.music.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
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
public class Musician implements Displayable
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	protected Integer id;
	
	protected String description;

	private String name;
	
	@Valid
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private List<Instrument> instruments = new ArrayList<Instrument>();
	
	@Override
	public String display() {
		return name;
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
