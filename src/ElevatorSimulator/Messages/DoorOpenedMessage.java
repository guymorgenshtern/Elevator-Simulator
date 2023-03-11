package ElevatorSimulator.Messages;

import java.util.Date;

@SuppressWarnings("serial")
public class DoorOpenedMessage extends Message{
	private StopType type;
	
	private int arrivedFloor;
	private DirectionType direction;
	private int numPassengers;
	
	/**
	 * The constructor 
	 * @param timestamp The timestamp
	 * @param arrivedFloor the floor it arrived at
	 * @param type the type of message
	 */
	public DoorOpenedMessage(Date timestamp, int arrivedFloor, StopType type, DirectionType direction, int numPassengers){
		super(SenderType.ELEVATOR, timestamp, MessageType.DOORS_OPENED);
		this.type = type;
		this.arrivedFloor = arrivedFloor;
		this.direction = direction;
		this.numPassengers = numPassengers;
		
	}
	/**
	 * Gets the stop type
	 * 
	 * @return the stop type
	 */
	public StopType getStopType() {
		return type;
	}
	/**
	 * Gets the arrived floor number
	 * 
	 * @return The arrived floor number
	 */
	public int getArrivedFloor() {
		return arrivedFloor;
	}
	
	public int getNumPassengers(){
		return numPassengers;
	}
	
	/**
	 * Gets the direction of the elevator 
	 * 
	 * @return the direction
	 */
	@Override
	public DirectionType getDirection() {
		return direction;
	}
	
	
	/**
     * @Override
     */
    public String getDescription() {
        return String.valueOf(this.type);
    }

}
