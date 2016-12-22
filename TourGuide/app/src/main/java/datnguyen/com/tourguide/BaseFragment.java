package datnguyen.com.tourguide;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.NoCopySpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import datnguyen.com.tourguide.Model.Category;
import datnguyen.com.tourguide.Model.CategoryItem;

/**
 * Created by datnguyen on 12/21/16.
 */

public class BaseFragment extends Fragment {

	private static final String ARG_PAGE_NUMBER = "page_number";
	private static final String ARG_CATEGORY = "category";

	private ListView lvCatDetail = null;
	private TextView tvCatDescription = null;

	private Category category = null;
	private CategoryAdapter categoryAdapter;

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;

		// update list of cat items
		if (this.isAdded()) {
			if (categoryAdapter == null) {
				categoryAdapter = new CategoryAdapter(getContext(), this.resRowLayout(), this.category.getItems());
				lvCatDetail.setAdapter(categoryAdapter);
			} else {
				categoryAdapter.clear();
				categoryAdapter.addAll(this.category.getItems());
			}

			categoryAdapter.notifyDataSetChanged();

			Log.v("","items count: " + this.category.getItems().size());
			// update category description
			tvCatDescription.setText(this.category.getDescription());
		}
	}

	public static BaseFragment createInstance(int pageIndex, Category category, String className) {
		Bundle args = new Bundle();
		args.putInt(ARG_PAGE_NUMBER, pageIndex);
		args.putSerializable(ARG_CATEGORY, category);
		return (BaseFragment)Fragment.instantiate(MainActivity.getsContext(), className, args);
	}

	protected int resRowLayout() {
		// need to override in subclasses
		return R.layout.cat_base_row;
	}

	protected int rowHeight() {
		// need to override in subclasses
		return 0;
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.base_fragment, container, false);

		// grab control
		lvCatDetail = (ListView) rootView.findViewById(R.id.lvCatContent);
		lvCatDetail.setBackgroundColor(getResources().getColor(R.color.colorListBackground));
		lvCatDetail.setMinimumHeight(rowHeight());

		// set description text as header of listview
		View header = (View) inflater.inflate(R.layout.category_header, null);
		tvCatDescription = (TextView) header.findViewById(R.id.tvCatDescription);
		lvCatDetail.addHeaderView(header);

		Category catFromArg = (Category) getArguments().getSerializable(ARG_CATEGORY);
		setCategory(catFromArg);

		return rootView;
	}
}
