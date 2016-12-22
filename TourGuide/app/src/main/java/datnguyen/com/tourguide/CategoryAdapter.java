package datnguyen.com.tourguide;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.List;

import datnguyen.com.tourguide.Model.CategoryItem;

/**
 * Created by datnguyen on 12/22/16.
 */

public class CategoryAdapter extends ArrayAdapter {

	private int resRowId = 0;
	public CategoryAdapter(Context context, int resource, List objects) {
		super(context, resource, objects);
		resRowId = resource;
	}

	@NonNull
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			LayoutInflater inflater = LayoutInflater.from(getContext());
			convertView = inflater.inflate(this.resRowId, parent, false);

			viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
			viewHolder.tvDescription = (TextView) convertView.findViewById(R.id.tvDescription);
			viewHolder.imvIcon = (ImageView) convertView.findViewById(R.id.imvIcon);
			viewHolder.tvAddress = (TextView) convertView.findViewById(R.id.tvAddress);
			viewHolder.tvTel = (TextView) convertView.findViewById(R.id.tvTel);
			viewHolder.tvWebsite = (TextView) convertView.findViewById(R.id.tvWebsite);

			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		CategoryItem categoryItem = (CategoryItem) this.getItem(position);
		viewHolder.tvTitle.setText(categoryItem.getTitle());
		viewHolder.tvDescription.setText(categoryItem.getDescription());
		String imageName = categoryItem.getImageName();
		Log.v("","imagename: " + imageName);

		if (viewHolder.imvIcon != null) {
			if (!TextUtils.isEmpty(imageName)) {
				viewHolder.imvIcon.setImageResource(getContext().getResources().getIdentifier(imageName, "drawable", getContext().getPackageName()));
			} else {
				viewHolder.imvIcon.setImageDrawable(null);
			}
		}

		if (viewHolder.tvAddress != null) {
			if (!TextUtils.isEmpty(categoryItem.getAddress())) {
				viewHolder.tvAddress.setText(categoryItem.getAddress());
			} else {
				viewHolder.tvAddress.setText("");
			}
		}

		if (viewHolder.tvTel != null) {
			if (!TextUtils.isEmpty(categoryItem.getTel())) {
				viewHolder.tvTel.setText(categoryItem.getTel());
			} else {
				viewHolder.tvTel.setText("");
			}
		}

		if (viewHolder.tvWebsite != null) {
			if (!TextUtils.isEmpty(categoryItem.getWebsite())) {
				viewHolder.tvWebsite.setText(categoryItem.getWebsite());
			} else {
				viewHolder.tvWebsite.setText(null);
			}
		}

		return convertView;
	}

	 private class ViewHolder {
		 TextView tvTitle;
		 TextView tvDescription;
		 TextView tvAddress;
		 TextView tvTel;
		 TextView tvWebsite;
		 ImageView imvIcon;
	 }
}
