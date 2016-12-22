package datnguyen.com.tourguide.Model;

import java.io.Serializable;

/**
 * Created by datnguyen on 12/21/16.
 */

public class CategoryItem extends BaseModel implements Serializable {

	private String address;
	private String tel;
	private String website;

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

}
