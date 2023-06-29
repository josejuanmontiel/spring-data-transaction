package org.example.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Represents an arbitrary sequence of numbers.
 */
@Entity
@Table(name = "sequence_lost", schema = "public")
public class SequenceLost extends Model {
	
	@NotNull
	private String name;

	@Column
	@NotNull
	private String value;

	/**
	 * Deliberately hidden to prevent direct instantiation.
	 */
	SequenceLost() {
	}

	/**
	 * Creates a sequence with a specified name.
	 *
	 * @param name The sequence name.
	 */
	public SequenceLost(final String name, final String value) {
		this.name = name;
		this.value = value;
	}

	/**
	 * Gets the sequence name.
	 *
	 * @return The sequence name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the current sequence value.
	 *
	 * @return The current sequence value.
	 */
	public String getValue() {
		return value;
	}
}
