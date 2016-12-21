package datnguyen.com.tourguide.Model;

/**
 * Created by datnguyen on 12/21/16.
 */

public class CategoryItem extends BaseModel {

	private String address;
	private String tel;
	private String website;
	private String imageName;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String image) {
		this.imageName = image;
	}
}
