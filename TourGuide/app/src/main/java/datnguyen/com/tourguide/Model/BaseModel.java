package datnguyen.com.tourguide.Model;

import java.io.Serializable;

/**
 * Created by datnguyen on 12/21/16.
 */

public class BaseModel implements Serializable {

	private String id;
	private String title;
	private String description;
	private String imageName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
}
