package org.example.service;

import org.example.data.SequenceLostRepository;
import org.example.data.SequenceRepository;
import org.example.domain.Sequence;
import org.example.domain.SequenceLost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


/**
 * Responsible for generating sequence numbers of a specific type.
 */
public abstract class SequenceGenerator {

	@Autowired
	protected SequenceRepository sequenceRepository;

	@Autowired
	protected SequenceLostRepository sequenceLostRepository;	
	
	/**
	 * Gets the next sequence number of a specific type.
	 *
	 * @return The next sequence number of a specific type.
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public String next(String brand, String period) {
		
		SequenceLost result = sequenceLostRepository.findTopByName(getName(brand, period));
		if (result!=null) {
			final String value = result.getValue();

			sequenceLostRepository.delete(result.getID());

			return value;
		} else {
			// Attempt to load a sequence of a specified name.
			Sequence sequence = sequenceRepository.findByName(getName(brand, period));
			if (sequence == null) {
				// If not found, create a new one.
				sequence = new Sequence(getName(brand, period));
			}

			final Long value = sequence.getValue();
			sequenceRepository.saveAndFlush(sequence);

			return sequence.getName() + String.format("%07d", value);
		}
	}
	
	@Transactional
	public void storeLost(String brand, String period, String value) {
		sequenceLostRepository.saveAndFlush(new SequenceLost(getName(brand, period), value));
	}
	

	/**
	 * Gets the name of the sequence for which numbers need to be generated.
	 *
	 * @return The name of the sequence for which numbers need to be generated.
	 */
	abstract String getName(String brand, String period);
}
