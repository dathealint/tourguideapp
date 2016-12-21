package datnguyen.com.tourguide;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by datnguyen on 12/21/16.
 */

public class BaseFragment extends Fragment {


	private static final String ARG_PAGE_NUMBER = "page_number";

	public static BaseFragment createInstance(int pageIndex, String className) {
		Bundle args = new Bundle();
		args.putInt(ARG_PAGE_NUMBER, pageIndex);
		return (BaseFragment)Fragment.instantiate(MainActivity.getsContext(), className, args);
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.base_fragment, container, false);

		//get page number
		int pageNumber = getArguments().getInt(ARG_PAGE_NUMBER, 0);
		TextView tvTitle = (TextView) rootView.findViewById(R.id.tvTitle);
		tvTitle.setText("Page: " + pageNumber);

		// test, random color
		Random rnd = new Random();
		int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
		rootView.setBackgroundColor(color);

		return rootView;
	}
}
