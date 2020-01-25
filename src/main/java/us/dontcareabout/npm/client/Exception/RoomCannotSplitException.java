package us.dontcareabout.npm.client.Exception;

public class RoomCannotSplitException extends Exception {
	public final String room;

	public RoomCannotSplitException(String room) {
		super(room);
		this.room = room;
	}
}
