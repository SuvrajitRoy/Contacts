package com.example.contacts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;




import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import android.provider.ContactsContract;
import android.provider.ContactsContract.Contacts;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.text.Editable;
import android.text.TextWatcher;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


public class Contact_view extends Fragment {
	
	Button addbtn;
	//EditText inputSearch;
	ListView lv;
	ArrayList<Contact> allcontact = new ArrayList<Contact>();
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		 View view = getActivity()
					.getLayoutInflater().inflate(R.layout.contact_view, container, false);
		// inputSearch = (EditText) view.findViewById(R.id.inputSearch);
		 lv = (ListView) view.findViewById(R.id.contactListLv);
		 allcontact = getContactLists();
		 Collections.sort(allcontact, new Comparator<Contact>() {
			 public int compare(Contact l, Contact rh){
				 return l.getContactName().compareTo(rh.getContactName());
			 }
		});
		 
		 lv.setAdapter(new ContactAdapter(getActivity() , getContactLists()));//.setAdapter(new ContactAdapter( this,getContactLists()));
		
		 lv.setOnItemClickListener(new OnItemClickListener() 
		 {
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id)
				{
				  // When clicked, show a toast with the TextView text
				   Toast.makeText(getActivity(),  position + "Selected", Toast.LENGTH_SHORT).show();
//				   
					Intent intent = new Intent(getActivity() , ContactInfo.class);
					intent.putExtra("cid", position);
					startActivity(intent);
					
					//showAlertDialogFor(position);
					
				    
				}
			});
		 
		 
		 addbtn = (Button) view.findViewById(R.id.addbtn);
			addbtn.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View view) {
					Intent i = new Intent (getActivity() , AddContact.class);
					startActivity(i);
				}
			});
		 return view;
	}
	
	
	
//	protected void showAlertDialogFor(final int position) {
//		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//		builder.setTitle("Delete").setMessage("Are your sure?")
//		.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//			
//			@Override
//			public void onClick(DialogInterface dialog, int which) {
//				removeItemFromList(position);
//			}
//		})
//		.setNegativeButton("No", new DialogInterface.OnClickListener() {
//			
//			@Override
//			public void onClick(DialogInterface dialog, int which) {
//				// TODO Auto-generated method stub
//				
//			}
//		});
//		
//		AlertDialog dialog = builder.create();
//		dialog.show();
//	}
//
//	public void removeItemFromList(int position) {
//		getContactLists().remove(getContactLists().get(position));
//			getContactLists().notifyAll();
//	}

	
	public ArrayList<Contact> getContactLists(){
		ArrayList<Contact> newContactLists= new ArrayList<Contact>();
		
		Cursor phones = getActivity().getContentResolver()
				.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
						null,null,null);
		   while (phones.moveToNext())
		   {
		    String name=phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
		    String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
		   
		    newContactLists.add(new Contact(name,phoneNumber));
		  }
		   
		   phones.close();
		   
		   return newContactLists;
		
	}
	
	

	
//	public InputStream openPhoto(long contactId) {
//	     Uri contactUri = ContentUris.withAppendedId(Contacts.CONTENT_URI, contactId);
//	     Uri photoUri = Uri.withAppendedPath(contactUri, Contacts.Photo.CONTENT_DIRECTORY);
//	     Cursor cursor = getContentResolver().query(photoUri,
//	          new String[] {Contacts.Photo.PHOTO}, null, null, null);
//	     if (cursor == null) {
//	         return null;
//	     }
//	     try {
//	         if (cursor.moveToFirst()) {
//	             byte[] data = cursor.getBlob(0);
//	             if (data != null) {
//	                 return new ByteArrayInputStream(data);
//	             }
//	         }
//	     } finally {
//	         cursor.close();
//	     }
//	     return null;
//	 }
//	 
	



	
	

}
