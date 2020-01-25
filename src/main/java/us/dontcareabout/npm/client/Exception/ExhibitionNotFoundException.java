package us.dontcareabout.npm.client.Exception;

public class ExhibitionNotFoundException extends Exception {
	public final String exhibitionName;

	public ExhibitionNotFoundException(String exhibitionName) {
		super(exhibitionName);
		this.exhibitionName = exhibitionName;
	}
}
