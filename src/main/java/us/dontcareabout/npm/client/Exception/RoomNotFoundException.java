package us.dontcareabout.npm.client.Exception;

public class RoomNotFoundException extends RuntimeException {
	public final String room;

	public RoomNotFoundException(String room) {
		super(room);
		this.room = room;
	}
}
