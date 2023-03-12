package ElevatorSimulatorTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ElevatorSimulator.Elevator.Elevator;
import ElevatorSimulator.Elevator.ElevatorState;
import ElevatorSimulator.Messages.*;

/**
 * The unit tests for the elevator subsystem.
 * 
 * @author Sarah Chow
 * @author Bardia Parmoun
 * @author Kyra Lothrop
 *
 */
public class ElevatorTest {	
	public static int ELEVATOR_ID = 0;
	
	public static int NUM_FLOORS = 4;
	
	public boolean shouldRun;
	
	private ArrayList<Message> messages;
	
	/**
	 * Creating the messages and adding them to an arraylist.
	 */
	@BeforeEach
	void init() {
		Message messageP = new DoorOpenedMessage(new Date(1000), 1, StopType.PICKUP, DirectionType.UP, 0);
		Message messageArrive = new ArrivedElevatorMessage(new Date(1100), 2);
		Message messageArrive2 = new ArrivedElevatorMessage(new Date(1100), 3);
		Message messageD = new DoorOpenedMessage(new Date(1000), 4, StopType.DROPOFF, DirectionType.DOWN, 1);
		
		messages = new ArrayList<>();	
		messages.add(messageP);	
		messages.add(messageArrive);
		messages.add(messageArrive2);
		messages.add(messageD);
	}
	
	/**
	 * Method to get the latest message.
	 * @return message in the ArrayList, Message
	 */
	public Message getElevatorUpdate() {
		Message temp = messages.get(0);
		messages.remove(0);
		return temp;
	}

	/**
	 * The unit test depicts the receiving of a message to the 
	 * elevator to move from floor 1 to 4.
	 * Confirms the expected environments of each 
	 * state (DOORS_OPENED, PICKUP, ARRIVE). 
	 * @throws InterruptedException 
	 */
	@Test
	void testOneElevatorRequest() throws InterruptedException {
		
		System.out.println("\n----------testOneElevatorRequest----------\n");
		
		Elevator elevator = new Elevator(ELEVATOR_ID, NUM_FLOORS);
				
		assertNotNull(elevator);
		assertEquals(1, elevator.getFloorNumber());
		assertEquals(ElevatorState.POLL, elevator.getState());
				
		shouldRun = true;
				
		int expectedFloor = 1;
		
		Message newMessage = null;
		
		while(this.shouldRun) {
			newMessage = getElevatorUpdate();

			if (newMessage.getType() == MessageType.DOORS_OPENED) {
				DoorOpenedMessage doorsOpenMessage = (DoorOpenedMessage) newMessage;
				
				if (doorsOpenMessage.getStopType() == StopType.PICKUP) {
					assertEquals(DirectionType.UP, doorsOpenMessage.getDirection());
					assertEquals(1, doorsOpenMessage.getArrivedFloor());
				} else {
					assertEquals(DirectionType.DOWN, doorsOpenMessage.getDirection());
					assertEquals(4, doorsOpenMessage.getArrivedFloor());
					
					shouldRun = false;
				}
				
			} 
			else if (newMessage.getType() == MessageType.ARRIVE) {
				if (elevator.getDirection() == DirectionType.UP) {
					expectedFloor++;
				} else {
					expectedFloor--;
				}
				// Check passive floors
				assertEquals(expectedFloor, ((ArrivedElevatorMessage)newMessage).getArrivedFloor());
			}
		}		
	}
}
