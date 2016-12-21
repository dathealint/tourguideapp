package datnguyen.com.tourguide.Model;

import java.util.ArrayList;

/**
 * Created by datnguyen on 12/21/16.
 */

public class City extends BaseModel {

	private String imageName;
	private ArrayList<Category> categories;

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public ArrayList<Category> getCategories() {
		return categories;
	}

	public void setCategories(ArrayList<Category> categories) {
		this.categories = categories;
	}
}
