package datnguyen.com.tourguide.Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by datnguyen on 12/21/16.
 */

public class Category extends BaseModel implements Serializable {
	private ArrayList<CategoryItem> items;

	public ArrayList<CategoryItem> getItems() {
		return items;
	}

	public void setItems(ArrayList<CategoryItem> items) {
		this.items = items;
	}
}
