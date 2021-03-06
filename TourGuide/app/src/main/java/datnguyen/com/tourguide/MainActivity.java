package datnguyen.com.tourguide;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import datnguyen.com.tourguide.Model.Category;
import datnguyen.com.tourguide.Model.CategoryItem;
import datnguyen.com.tourguide.Model.City;

import static datnguyen.com.tourguide.Constants.DURATION_FADE_TOP_IMAGE;
import static datnguyen.com.tourguide.Constants.FILE_JSON;
import static datnguyen.com.tourguide.Constants.JSON_CATEGORIES_KEY;
import static datnguyen.com.tourguide.Constants.JSON_DESCRIPTION_KEY;
import static datnguyen.com.tourguide.Constants.JSON_ID_KEY;
import static datnguyen.com.tourguide.Constants.JSON_IMAGE_KEY;
import static datnguyen.com.tourguide.Constants.JSON_ITEMS_KEY;
import static datnguyen.com.tourguide.Constants.JSON_ITEM_ADDRESS_KEY;
import static datnguyen.com.tourguide.Constants.JSON_ITEM_TEL_KEY;
import static datnguyen.com.tourguide.Constants.JSON_ITEM_WEBSITE_KEY;
import static datnguyen.com.tourguide.Constants.JSON_TITLE_KEY;

public class MainActivity extends AppCompatActivity {

	public final static String VIEW_TAG = "MainActivity";

	private City currentCity = null;
	private ViewPager viewPager = null;
	private ImageView imvHeader = null;
	private CustomPagerAdapter pagerAdapter = null;

	static private Context sContext;

	public static Context getsContext() {
		return sContext;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		sContext = getApplicationContext();

		// grab UI Controls
		viewPager = (ViewPager) findViewById(R.id.viewPager);
		imvHeader = (ImageView) findViewById(R.id.imvHeader);

		pagerAdapter = new CustomPagerAdapter(getSupportFragmentManager());
		viewPager.setAdapter(pagerAdapter);

		ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

			}

			@Override
			public void onPageSelected(int position) {
				// set image
				Category category = currentCity.getCategories().get(position);
				String imageName = category.getImageName();
				Log.v(VIEW_TAG, "category: " + category.getTitle() + "desc: " + category.getDescription() + " imageName: " + imageName);

				if (!TextUtils.isEmpty(imageName)) {
					TransitionDrawable td = new TransitionDrawable(new Drawable[]{
							imvHeader.getDrawable(),
							getResources().getDrawable(getResources().getIdentifier(imageName, "drawable", getPackageName()))
					});

					td.setCrossFadeEnabled(true);
					imvHeader.setImageDrawable(td);

					td.startTransition(DURATION_FADE_TOP_IMAGE);
				}
			}

			@Override
			public void onPageScrollStateChanged(int state) {

			}
		};

		viewPager.addOnPageChangeListener(pageChangeListener);

		// load data
		loadJSONData();
	}

	/**
	 * method to help read content from a file in Assets folder
	 *
	 * @param fileName fileName to read from Assets folder
	 * @return
	 */
	private String stringFromFile(String fileName) {
		// load content from json file
		String jsonContent = null;
		try {
			InputStream is = getResources().openRawResource(R.raw.singapore);
			int size = is.available();

			byte[] buffer = new byte[size];
			is.read(buffer);
			is.close();

			// get string content out of buffer, using UTF-8 encoding
			jsonContent = new String(buffer, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonContent;
	}

	/***
	 * Load questions from local json file and pass to Question object
	 */
	private void loadJSONData() {
		String jsonString = stringFromFile(FILE_JSON);

		// now parse to JSON object
		try {
			JSONObject jsonRoot = new JSONObject(jsonString);

			currentCity = new City();

			// get city info out of JSON
			String cityTitle = jsonRoot.optString(JSON_TITLE_KEY);
			String cityDesc = jsonRoot.optString(JSON_DESCRIPTION_KEY);
			String imageName = jsonRoot.optString(JSON_IMAGE_KEY);
			currentCity.setTitle(cityTitle);
			currentCity.setDescription(cityDesc);
			currentCity.setImageName(imageName);

			// now we parse categories
			JSONArray categoriesJSON = jsonRoot.optJSONArray(JSON_CATEGORIES_KEY);
			ArrayList<Category> categories = new ArrayList<>();

			for (int i = 0; i < categoriesJSON.length(); i++) {
				JSONObject jsonObject = (JSONObject) categoriesJSON.get(i);
				Category category = categoryFromJSONObject(jsonObject);
				categories.add(category);
			}

			// finally, set categories for this city
			currentCity.setCategories(categories);

			viewPager.setOffscreenPageLimit(categories.size());
			// assign datasource and notify pager to update
			pagerAdapter.setCategories(categories);
			pagerAdapter.notifyDataSetChanged();

		} catch (JSONException e) {
			e.printStackTrace();
		}

	}

	private Category categoryFromJSONObject(JSONObject jsonObject) {

		// create new Category object
		Category category = new Category();

		String catId = jsonObject.optString(JSON_ID_KEY);
		String catTitle = jsonObject.optString(JSON_TITLE_KEY);
		String catDesc = jsonObject.optString(JSON_DESCRIPTION_KEY);
		String catImage = jsonObject.optString(JSON_IMAGE_KEY);

		category.setId(catId);
		category.setTitle(catTitle);
		category.setDescription(catDesc);
		category.setImageName(catImage);

		// an array to hold all items of array
		ArrayList<CategoryItem> catItems = new ArrayList<>();

		JSONArray itemsJSON = jsonObject.optJSONArray(JSON_ITEMS_KEY);
		for (int j = 0; j < itemsJSON.length(); j++) {
			try {
				JSONObject itemJSON = (JSONObject) itemsJSON.get(j);
				CategoryItem catItem = categoryItemFromJSONObject(itemJSON);
				catItems.add(catItem);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

		Log.v(VIEW_TAG, "catitesms: " + catItems.size());
		// assign all cat items to this category
		category.setItems(catItems);
		return category;
	}

	private CategoryItem categoryItemFromJSONObject(JSONObject itemJSON) {
		String itemId = itemJSON.optString(JSON_ID_KEY);
		String itemTitle = itemJSON.optString(JSON_TITLE_KEY);
		String itemDesc = itemJSON.optString(JSON_DESCRIPTION_KEY);
		String itemImage = itemJSON.optString(JSON_IMAGE_KEY);
		String itemAddress = itemJSON.optString(JSON_ITEM_ADDRESS_KEY);
		String itemTel = itemJSON.optString(JSON_ITEM_TEL_KEY);
		String itemWebsite = itemJSON.optString(JSON_ITEM_WEBSITE_KEY);

		CategoryItem catItem = new CategoryItem();
		catItem.setId(itemId);
		catItem.setTitle(itemTitle);
		catItem.setDescription(itemDesc);
		catItem.setImageName(itemImage);
		catItem.setAddress(itemAddress);
		catItem.setTel(itemTel);
		catItem.setWebsite(itemWebsite);
		return catItem;
	}
}
