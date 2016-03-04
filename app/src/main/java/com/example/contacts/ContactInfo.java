package com.example.contacts;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ContactInfo extends Activity implements OnClickListener {
 
    // GUI Widget
    TextView lblNumber ,Contactname;
    Button btnCall;
    String number;
    String value;
    int val;
 
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contactinfo);
        Contactname = (TextView) findViewById(R.id.textView1);
 
        lblNumber = (TextView) findViewById(R.id.lblNumber);
        btnCall = (Button) findViewById(R.id.btnCall);
        
 
       // value = getIntent().getStringExtra("cid");
        val = getIntent().getIntExtra("cid", 0);
//        Intent intent = getIntent();
//        if (null != intent) {
//      	value= intent.getStringExtra("cid");}
 
        // Read Contact number of specific contact with help of Content Resolver
        
        
//        ContentResolver cr = getContentResolver();
//        Cursor c = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
//                new String[] { ContactsContract.CommonDataKinds.Phone.NUMBER },
//              ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=?",
//               
//                new String[] { cid }, null);
//        c.moveToFirst();
//        number = c.getString(c
//                .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
// 
//        Bundle extras = getIntent().getExtras();
//        if (extras != null) {
//            value = extras.getString("cid");
//        }
        //Contactname.setText(value);
        Cursor phones = getContentResolver()
				.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, 
						new String[] { ContactsContract.CommonDataKinds.Phone.NUMBER },
						null,null,null);
		   //phones.moveToFirst();
		  //phones.moveToNext();
		  // phones.moveToLast();
        //phones.getPosition();
        phones.moveToPosition(val);
		  // phones.moveToLast();
		   
		   number = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
		   
		   
        // Display Contact Number into Label
        lblNumber.setText(number);
        btnCall.setOnClickListener(this);
       // phones.close();
    }
 
    @Override
    public void onClick(View v) {
 
        if (v == btnCall) {
            // Implicit Intent to make call
            Intent iCall = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"
                    + number));
            startActivity(iCall);
        }
 
    }
}
