package com.example.contacts;

import android.app.ActionBar;

import android.app.FragmentTransaction;
import android.app.ActionBar.Tab;

import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Window;


public class MainActivity extends FragmentActivity {
	ViewPager pager;
	
	DummyAdapter adapter;



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);

		 
		final ActionBar actionbar = getActionBar();
       

        
        actionbar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);


		 
		adapter = new DummyAdapter(getSupportFragmentManager());
			pager = (ViewPager) findViewById(R.id.pager);
			pager.setAdapter(adapter);
			pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
				
				@Override
				public void onPageSelected(int position) {
					actionbar.selectTab(actionbar.getTabAt(position));
					
				}
				
				@Override
				public void onPageScrolled(int arg0, float arg1, int arg2) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void onPageScrollStateChanged(int arg0) {
					// TODO Auto-generated method stub
					
				}
			});
			
			 actionbar.addTab(actionbar.newTab().setText("CONTACTS").setTabListener(tabListener));
			 actionbar.addTab(actionbar.newTab().setText("Google Contacts").setTabListener(tabListener));
			 actionbar.addTab(actionbar.newTab().setText("DIALER").setTabListener(tabListener));
	}
	
	public ActionBar.TabListener tabListener = new ActionBar.TabListener() {
		
		@Override
		public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onTabSelected(Tab tab, FragmentTransaction arg1) {
		pager.setCurrentItem(tab.getPosition());
			
		}
		
		@Override
		public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
			// TODO Auto-generated method stub
			
		}
	};
	


public class DummyAdapter extends FragmentStatePagerAdapter{
		
		public DummyAdapter (FragmentManager fm){
			super(fm);
			
		}

		@Override
		public Fragment getItem(int position) {
			Fragment fragment = null;
	        switch (position) {
			case 0:
				fragment =  new Contact_view();
				break;
			case 1:
				fragment = new Message_view();
				break;
			case 2: 
				fragment = new Dialer_view();
				break;

			default:
				break;
			}
			return fragment;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 3;
		}


}
}