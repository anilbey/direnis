package com.orkestra.direnis;


import java.util.List;

import com.orkestra.direnis.R;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomListViewAdapter extends ArrayAdapter<RowItem> {

   Context context;

   public CustomListViewAdapter(Context context, int resourceId,
           List<RowItem> items) {
       super(context, resourceId, items);
       this.context = context;
   }

   /*private view holder class*/
   private class ViewHolder {
       ImageView imageView;
       TextView txtTitle;
       TextView txtDesc;
       TextView itemPower;
       TextView power;
       TextView itemValue;
       TextView value;
   }

   public View getView(int position, View convertView, ViewGroup parent) {
       ViewHolder holder = null;
       RowItem rowItem = getItem(position);

       LayoutInflater mInflater = (LayoutInflater) context
               .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
       if (convertView == null) {
           convertView = mInflater.inflate(R.layout.list_item, null);
           holder = new ViewHolder();
           holder.txtDesc = (TextView) convertView.findViewById(R.id.desc);
           holder.txtTitle = (TextView) convertView.findViewById(R.id.listTitle);
           holder.imageView = (ImageView) convertView.findViewById(R.id.icon);
           holder.itemValue = (TextView) convertView.findViewById(R.id.value);
           holder.itemPower= (TextView) convertView.findViewById(R.id.power);
           holder.power = (TextView) convertView.findViewById(R.id.powertext);
           holder.value= (TextView) convertView.findViewById(R.id.valuetext);
           convertView.setTag(holder);
       } else
           holder = (ViewHolder) convertView.getTag();

       holder.txtDesc.setText(rowItem.getDesc().toUpperCase());
       holder.txtTitle.setText(rowItem.getTitle().toUpperCase());
       holder.imageView.setImageResource(rowItem.getImageId());
       holder.itemPower.setText(String.valueOf(rowItem.getPower()).toUpperCase());
       holder.itemValue.setText(String.valueOf(rowItem.getValue()).toUpperCase());
       holder.power.setText("Güç:".toUpperCase());
       holder.value.setText("Fiyat:".toUpperCase());
       
       holder.txtDesc.setTypeface(DirenisMain.tf);
       holder.txtTitle.setTypeface(DirenisMain.tf);
       holder.txtDesc.setTypeface(DirenisMain.tf);
       holder.itemPower.setTypeface(DirenisMain.tf);
       holder.power.setTypeface(DirenisMain.tf);
       holder.value.setTypeface(DirenisMain.tf);
       holder.itemValue.setTypeface(DirenisMain.tf);
       return convertView;
   }
}