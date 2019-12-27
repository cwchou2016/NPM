package us.dontcareabout.npm.client.Exception;

public class ExhibitionNotFoundException extends RuntimeException {
	public final String exhibitionName;

	public ExhibitionNotFoundException(String exhibitionName) {
		super(exhibitionName);
		this.exhibitionName = exhibitionName;
	}
}
