package datnguyen.com.tourguide;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

import datnguyen.com.tourguide.Model.Category;

import static datnguyen.com.tourguide.Constants.JSON_ITEM_ID_FOOD;
import static datnguyen.com.tourguide.Constants.JSON_ITEM_ID_GETTINGAROUND;
import static datnguyen.com.tourguide.Constants.JSON_ITEM_ID_LOCATION;
import static datnguyen.com.tourguide.Constants.JSON_ITEM_ID_TIPS;

/**
 * Created by datnguyen on 12/21/16.
 */

public class CustomPagerAdapter extends FragmentPagerAdapter {

	private ArrayList<Category> categories;

	public CustomPagerAdapter(FragmentManager fm) {
		super(fm);
		this.categories = new ArrayList<>();
	}

	public void setCategories(ArrayList<Category> categories) {
		this.categories = categories;
	}

	@Override
	public BaseFragment getItem(int position) {
		Category category = this.categories.get(position);
		if (category.getId().equals(JSON_ITEM_ID_TIPS)) {
			TipsFragment fragment = (TipsFragment) TipsFragment.createInstance(position, category, TipsFragment.class.getName());
			return fragment;
		} else if (category.getId().equals(JSON_ITEM_ID_FOOD)) {
			FoodFragment fragment = (FoodFragment) FoodFragment.createInstance(position, category, FoodFragment.class.getName());
			return fragment;
		} else if (category.getId().equals(JSON_ITEM_ID_LOCATION)) {
			LocationFragment fragment = (LocationFragment) LocationFragment.createInstance(position, category, LocationFragment.class.getName());
			return fragment;
		} else if (category.getId().equals(JSON_ITEM_ID_GETTINGAROUND)) {
			GettingAroundFragment fragment = (GettingAroundFragment) GettingAroundFragment.createInstance(position, category, GettingAroundFragment.class.getName());
			return fragment;
		} else {
			return null;
		}
	}

	@Override
	public int getCount() {
		return categories.size();
	}

	@Override
	public CharSequence getPageTitle(int position) {
		Category category = this.categories.get(position);
		return category.getTitle();
	}
}
