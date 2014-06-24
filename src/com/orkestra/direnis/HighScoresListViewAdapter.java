package com.orkestra.direnis;

import java.util.List;

import com.orkestra.direnis.R;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class HighScoresListViewAdapter extends ArrayAdapter<HighScoresRowItem> {

	Context context;

	public HighScoresListViewAdapter(Context context, int resourceId,
			List<HighScoresRowItem> items) {
		super(context, resourceId, items);
		this.context = context;
	}

	private class ViewHolder {
		TextView name;
		TextView number;
		TextView score;

		
		
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		HighScoresRowItem highScoresRowItem = getItem(position);

		LayoutInflater mInflater = (LayoutInflater) context
				.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		if (convertView == null) {
			convertView = mInflater
					.inflate(R.layout.highscores_list_item, null);
			holder = new ViewHolder();
			holder.name = (TextView) convertView
					.findViewById(R.id.highscore_name);
			holder.number = (TextView) convertView
					.findViewById(R.id.highscore_number);
			holder.score = (TextView) convertView
					.findViewById(R.id.highscore_score);

			convertView.setTag(holder);
		} else
			holder = (ViewHolder) convertView.getTag();

		holder.name.setText(highScoresRowItem.getName().toUpperCase());
		holder.number.setText(String.valueOf(highScoresRowItem.getNumber()).toUpperCase());
		holder.score.setText(String.valueOf(highScoresRowItem.getScore()).toUpperCase());
		
		holder.name.setTypeface(DirenisMain.tf);
		holder.number.setTypeface(DirenisMain.tf);
		holder.score.setTypeface(DirenisMain.tf);
		
		return convertView;
	}
}
