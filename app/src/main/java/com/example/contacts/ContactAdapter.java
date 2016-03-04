package com.example.contacts;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ContactAdapter extends ArrayAdapter<Contact> {
	LayoutInflater inflater;
	
   public ContactAdapter(Context context, List<Contact>objects) {
		super(context,0,objects);
		
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
   
public View getView(int position, View v, ViewGroup parent) {
	   Contact aContactList = getItem(position);
		ViewHolder holder = new ViewHolder();
		
		if(v==null){
			v=inflater.inflate(R.layout.contact_model, null);
			holder.tvName=(TextView) v.findViewById(R.id.tvName);
			holder.tvContactNumber=(TextView) v.findViewById(R.id.tvContact);
			//holder.ivPoster =(ImageView) v.findViewById(R.id.ivPoster);
			v.setTag(holder);
		}
		
		holder=(ViewHolder)v.getTag();
		holder.tvName.setText(aContactList.getContactName().toString());
		holder.tvContactNumber.setText(aContactList.getContactNumber());
		//holder.ivPoster.setImageResource(aContactList.getImageId());
		return v;
	
	}
static class ViewHolder
{
	//public ImageView ivPoster;
	public TextView tvName;
	public TextView tvContactNumber;
	
}


}
