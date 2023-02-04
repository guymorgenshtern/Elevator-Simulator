/**
 * 
 */
package ElevatorSimulatorTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ElevatorSimulator.Buffer;
import ElevatorSimulator.Messages.*;

/**
 * The unit tests for the buffer class.
 * 
 * @author Sarah Chow
 * @author Bardia Parmoun
 *
 */
public class BufferTest {
	/**
	 * Validates that the buffer put and get functions work as expected.
	 * Puts a message in the buffer and retrieves it.
	 */
	@Test
	public void testBuffer() {	
		Buffer buffer = new Buffer();
		
		assertTrue(buffer.isEmpty());
		
		buffer.put(new RequestElevatorMessage("<TIMESTAMP>", 1, null, 4));
		
		assertFalse(buffer.isEmpty());

		Message message = buffer.get();
		
		assertTrue(buffer.isEmpty());
		
		System.out.println(message.getDescription());
	}
}
