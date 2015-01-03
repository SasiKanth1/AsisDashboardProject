package sasikanth.isas.dash;

import sasikanth.isas.dash.R;

import icr.SystemBarTintManager;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class ContactActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact);
		
		
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
	 
	 public void gplus(View view)
	 {		 
	 startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.developer_gplus))));		 
	 }
	 
	 public void email(View view)
	 {		 
		 Intent i = new Intent(Intent.ACTION_SEND);
			i.putExtra(Intent.EXTRA_EMAIL , new String[]{(getString(R.string.developer_email))});
			i.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.email_subject));
			i.setType("plain/text");
			try {
			    startActivity(Intent.createChooser(i, "Send mail..."));
			} catch (android.content.ActivityNotFoundException ex) {
			    Toast.makeText(this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
			    }	 
	 }
	 
}
	

