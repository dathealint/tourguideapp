package datnguyen.com.tourguide;

/**
 * Created by datnguyen on 12/21/16.
 */

public class FoodFragment extends BaseFragment {
	public FoodFragment() {

	}

	protected int resRowLayout() {
		// need to override in subclasses
		return R.layout.cat_image_row;
	}

//	protected int rowHeight() {
//		// need to override in subclasses
//		return (int)getResources().getDimension(R.dimen.row_image_height);
//	}
}
