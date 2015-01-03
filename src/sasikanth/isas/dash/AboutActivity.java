package sasikanth.isas.dash;

import sasikanth.isas.dash.R;

import icr.SystemBarTintManager;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class AboutActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);
		
		
       if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
        	
        	getWindow().setFlags(LayoutParams.FLAG_TRANSLUCENT_STATUS, LayoutParams.FLAG_TRANSLUCENT_STATUS);
        	
        	if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
        	getWindow().setFlags(LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        	
        	SystemBarTintManager tintManager = new SystemBarTintManager(this);
        	tintManager.setStatusBarTintEnabled(true);
        	tintManager.setStatusBarTintColor(getResources().getColor(R.color.app_theme_color));
        	
       }
       
        ActionBar ab = getActionBar();
    	int titleId = getResources().getIdentifier("action_bar_title", "id", "android");
     	if (titleId == 0)
   		titleId = android.R.id.title;
   	
     	TextView abTitle = (TextView) findViewById(titleId);
     	ab.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.app_theme_color)));
     	ab.setIcon(new ColorDrawable(getResources().getColor(android.R.color.transparent)));  
     	abTitle.setTextColor(getResources().getColor(android.R.color.white));
     	
     	ViewGroup home = (ViewGroup) findViewById(android.R.id.home).getParent();
        ( (ImageView) home.getChildAt(0) )
            .setImageResource(R.drawable.ic_back);
     	
       
        }
	 
	 
	 @Override
		public boolean onOptionsItemSelected(MenuItem item)
		{
			switch (item.getItemId())
			{
				case android.R.id.home:
					finish();
					return true;
				default:
					return super.onOptionsItemSelected(item);
			}
	  }	 
	 
	 public void aboutasis(View view)
	 {		 
		final Dialog popup = new Dialog(this);
		
		popup.requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		popup.setContentView(R.layout.dialog_layout);
 	    
		TextView text1 = (TextView) popup.findViewById(R.id.text1);
 	    text1.setText(getString(R.string.dialog_about_asis_title));
 	    
 	   TextView text2 = (TextView) popup.findViewById(R.id.text2);
	    text2.setText(getString(R.string.dialog_about_asis_desc));
	    
	    popup.show();

 	    Button closebutton = (Button) popup.findViewById(R.id.button2);
 	    closebutton.setOnClickListener(new OnClickListener() {
           public void onClick(View v) {
               // Close dialog
               popup.dismiss();
           }
       });
	 }
	 
	 
	 public void aboutdev(View view)
	 {		 
		final Dialog popup = new Dialog(this);
		
		popup.requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		popup.setContentView(R.layout.dialog_layout);
 	    
		TextView text1 = (TextView) popup.findViewById(R.id.text1);
 	    text1.setText(getString(R.string.dialog_about_dev_title));
 	    
 	   TextView text2 = (TextView) popup.findViewById(R.id.text2);
	    text2.setText(getString(R.string.dialog_about_dev_desc));
	    
	    popup.show();

 	    Button closebutton = (Button) popup.findViewById(R.id.button2);
 	    closebutton.setOnClickListener(new OnClickListener() {
           public void onClick(View v) {
               // Close dialog
               popup.dismiss();
           }
       });
	 }
	 
	 public void aboutapp(View view)
	 {		 
		final Dialog popup = new Dialog(this);
		
		popup.requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		popup.setContentView(R.layout.dialog_layout);
 	    
		TextView text1 = (TextView) popup.findViewById(R.id.text1);
 	    text1.setText(getString(R.string.dialog_about_app_title));
 	    
 	   TextView text2 = (TextView) popup.findViewById(R.id.text2);
	    text2.setText(getString(R.string.dialog_about_app_desc));
	    
	    popup.show();

 	    Button closebutton = (Button) popup.findViewById(R.id.button2);
 	    closebutton.setOnClickListener(new OnClickListener() {
           public void onClick(View v) {
               // Close dialog
               popup.dismiss();
           }
       });
	 }
	 
}
	

