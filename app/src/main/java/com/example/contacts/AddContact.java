package com.example.contacts;

import java.util.ArrayList;

import android.app.Activity;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.OperationApplicationException;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.provider.ContactsContract.RawContacts;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class AddContact extends Activity implements OnClickListener{
	final static String LOG_TAG = "Contacts++";
	//-
	TextView tv1;
	EditText et1,et2,et3,et4,et5,et6,et7;
	Button b1;
final static int idb1 = Menu.FIRST + 1;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Hide titlebar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        
        ScrollView vscroll = new ScrollView(this);
		vscroll.setFillViewport(false);
		//panel in scroll: add all controls/ objects to this layout
		LinearLayout m_panel = new LinearLayout (this);
		m_panel.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
		m_panel.setOrientation(LinearLayout.VERTICAL);
		m_panel.setPadding(10,10,10,10);
		vscroll.addView(m_panel);
		
		//Create some controls
		tv1 = new TextView(this);
		tv1.setText("Add a contact");
		tv1.setTextSize(40);
		m_panel.addView(tv1);
		
		et1 = new EditText(this);
		et1.setHint("Enter Firstname");
		
		m_panel.addView(et1);
		et2 = new EditText(this);
		et2.setHint("Enter Lastname");
	
		m_panel.addView(et2);
		et3 = new EditText(this);
		et3.setHint("Enter Phone");
		
		m_panel.addView(et3);
		et4 = new EditText(this);
		et4.setHint("Enter Mobile");
		
		m_panel.addView(et4);
		et5 = new EditText(this);
		et5.setHint("Enter Company");
	
		m_panel.addView(et5);
		et6 = new EditText(this);
		et6.setHint("Enter Email");
		
		m_panel.addView(et6);
		et7 = new EditText(this);
		et7.setHint("Enter Website");
		
		m_panel.addView(et7);

		b1 = new Button(this);
		b1.setId(idb1);
		b1.setOnClickListener((OnClickListener) this);
		b1.setText("Add Contact");
		m_panel.addView(b1);
		
        setContentView(vscroll);
    }

	@Override
	public void onClick(View arg0) {
		int id = arg0.getId();
		if (id == idb1) {
			if (SaveContact())
				Toast.makeText(getBaseContext(), "Contact Saved!", Toast.LENGTH_SHORT).show();
			else
				Toast.makeText(getBaseContext(), "Error saving contact, see LogCat!", Toast.LENGTH_SHORT).show();
			
		}
	}
	
	boolean SaveContact() {
		//Get text
		String 	szFirstname = et1.getText().toString(),
				szLastname = et2.getText().toString(),
				szPhone = et3.getText().toString(),
				szMobile = et4.getText().toString(),
				szCompany = et5.getText().toString(),
				szEmail = et6.getText().toString(),
				szWeb = et7.getText().toString();

		//Create a new contact entry!
		String szFullname = szFirstname+" "+szLastname;
		
		ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>();
        int rawContactInsertIndex = ops.size();
        
        ops.add(ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI)
                .withValue(RawContacts.ACCOUNT_TYPE, null)
                .withValue(RawContacts.ACCOUNT_NAME, null)
                .build());
        //INSERT NAME
        ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID,rawContactInsertIndex)
                .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, szFullname) // Name of the person
                .withValue(ContactsContract.CommonDataKinds.StructuredName.FAMILY_NAME, szLastname) // Name of the person
                .withValue(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME, szFirstname) // Name of the person
                .build());
        //INSERT PHONE
        ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID,   rawContactInsertIndex)
                .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, szPhone) // Number of the person
                .withValue(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_WORK)
                .build()); //   
        //INSERT MOBILE
        ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID,   rawContactInsertIndex)
                .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, szMobile) // Number of the person
                .withValue(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE)
                .build()); //
       
        //INSERT EMAIL
        ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID,   rawContactInsertIndex)
                .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.Email.DATA, szEmail) 
                .withValue(ContactsContract.CommonDataKinds.Email.TYPE, ContactsContract.CommonDataKinds.Email.TYPE_WORK)
                .build()); //
        //INSERT WEBSITE
        ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID,   rawContactInsertIndex)
                .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Website.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.Website.URL, szWeb) // 
                .withValue(ContactsContract.CommonDataKinds.Website.TYPE, ContactsContract.CommonDataKinds.Website.TYPE_WORK)
                .build()); //
      
        //INSERT COMPANY / JOB
        ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, rawContactInsertIndex)
                .withValue(ContactsContract.Data.MIMETYPE,ContactsContract.CommonDataKinds.Organization.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.Organization.COMPANY, szCompany)
                .withValue(ContactsContract.CommonDataKinds.Organization.TYPE, ContactsContract.CommonDataKinds.Organization.TYPE_WORK)
               /* .withValue(ContactsContract.CommonDataKinds.Organization.TITLE, m_szJob)
                .withValue(ContactsContract.CommonDataKinds.Organization.TYPE, ContactsContract.CommonDataKinds.Organization.TYPE_WORK)*/
                .build());
      
		// SAVE CONTACT IN BCR Structure
		Uri newContactUri = null;
		//PUSH EVERYTHING TO CONTACTS
        try
        {
            ContentProviderResult[] res = getContentResolver().applyBatch(ContactsContract.AUTHORITY, ops);
            if (res!=null && res[0]!=null) {
            	newContactUri = res[0].uri;	
            	//02-20 22:21:09 URI added contact:content://com.android.contacts/raw_contacts/612
            	Log.d(LOG_TAG, "URI added contact:"+ newContactUri);
            }
            else Log.e(LOG_TAG, "Contact not added.");
        }
        catch (RemoteException e)
        { 
            // error
        	Log.e(LOG_TAG, "Error (1) adding contact.");
        	newContactUri = null;
        }
        catch (OperationApplicationException e) 
        {
            // error
        	Log.e(LOG_TAG, "Error (2) adding contact.");
        	newContactUri = null;
        }  
        Log.d(LOG_TAG, "Contact added to system contacts.");
        
        if (newContactUri == null) {
        	Log.e(LOG_TAG, "Error creating contact");
        	return false;
        }
        
        return true;
	}

}
