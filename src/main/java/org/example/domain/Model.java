package org.example.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;
import javax.persistence.Version;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

/**
 * Represents a domain entity.
 */
@MappedSuperclass
public abstract class Model {
	
	@Generated(GenerationTime.INSERT)
	@GeneratedValue(strategy = GenerationType.AUTO)
//	@GeneratedValue(generator = "sequence")
//	@SequenceGenerator(name = "sequence", sequenceName = "public.UNIQUE_ID", allocationSize = 1)
	@Id
	private Long id;

	
	@Version
	@Column(name = "optlock", columnDefinition = "integer DEFAULT 0", nullable = false)
	private long version = 0L;	
	
	/**
	 * Gets the unique identifier for this entity.
	 *
	 * @return The unique identifier for this entity.
	 */
	public Long getID() {
		return id;
	}
	
	public long getVersion() {
		return version;
	}
	
	public void setVersion(long version) {
		this.version = version;
	}
}
