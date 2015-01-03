package sasikanth.isas.dash;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.wallpaper.activity.HomeActivity;


public class MainActivity extends Activity {
	
	// Data members
	private Intent emailIntent;
	private String feedback;
	private EditText feedbackBox;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
       if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
        	
        	getWindow().setFlags(LayoutParams.FLAG_TRANSLUCENT_STATUS, LayoutParams.FLAG_TRANSLUCENT_STATUS);
        	
        	if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
        	getWindow().setFlags(LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        	
       }
       
       ActionBar ab = getActionBar();
       
   	  int titleId = getResources().getIdentifier("action_bar_title", "id", "android");
   	  if (titleId == 0)
   		titleId = android.R.id.title;
   	
   	  TextView abTitle = (TextView) findViewById(titleId);
    	ab.setBackgroundDrawable(new ColorDrawable(getResources().getColor(android.R.color.transparent)));
    	ab.setIcon(new ColorDrawable(getResources().getColor(android.R.color.transparent)));  
    	abTitle.setTextColor(getResources().getColor(android.R.color.transparent));
       
    }
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.menu_main, menu);
		
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
			case R.id.changelog:
				
				final Dialog popup = new Dialog(this);
				
				popup.requestWindowFeature(Window.FEATURE_NO_TITLE);
				
				popup.setContentView(R.layout.dialog_layout);
		 	    
				TextView text1 = (TextView) popup.findViewById(R.id.text1);
		 	    text1.setText(getString(R.string.changelog_title));
		 	    
		 	   TextView text2 = (TextView) popup.findViewById(R.id.text2);
			    text2.setText(getString(R.string.changelog));
			    
			    popup.show();

		 	    Button closebutton = (Button) popup.findViewById(R.id.button2);
		 	    closebutton.setOnClickListener(new OnClickListener() {
		           public void onClick(View v) {
		               // Close dialog
		               popup.dismiss();
		           }
		       });
				return true;
				
			case R.id.request:
				
			  final Dialog popup1 = new Dialog(this);
				
				popup1.requestWindowFeature(Window.FEATURE_NO_TITLE);
				
				popup1.setContentView(R.layout.request_dialog_layout);
		 	    
				TextView text11 = (TextView) popup1.findViewById(R.id.text1);
		 	    text11.setText(getString(R.string.request_title));
			    
			    popup1.show();

		 	    Button closebutton1 = (Button) popup1.findViewById(R.id.button1);
		 	    closebutton1.setOnClickListener(new OnClickListener() {
		           public void onClick(View v) {
		               // Close dialog
		               popup1.dismiss();
		           }
		       });
		 	    
		 	   Button submitbutton = (Button) popup1.findViewById(R.id.button2);
		 	    submitbutton.setOnClickListener(new OnClickListener() {
		           public void onClick(View v) {
		               
		        	emailIntent = new Intent(android.content.Intent.ACTION_SEND);
		        	emailIntent.setType("plain/text");

		        	emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{(getString(R.string.developer_email))});
		        	emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, getString(R.string.request_sub));

		        	feedbackBox = (EditText) popup1.findViewById(R.id.edittext2);
		    		feedback = feedbackBox.getText().toString();
		    		
		        	emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, feedback);
		        	
		        	startActivity(Intent.createChooser(emailIntent, "Insert title for dialog box."));
		        	   
		           }
		       });
				return true;
				
			case R.id.rate:
				
				startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.playstore_link))));
				return true;
				
			default:
				return super.onOptionsItemSelected(item);
		}
	}
       
	public void openwall(View view)
	  {
		   startActivity(new Intent(this, HomeActivity.class));
	  }
	
	 public void openzooper(View view)
	  {
		   startActivity(new Intent(this, ZooperActivity.class));
	  }
	 
	 public void opencontact(View view)
	  {
		   startActivity(new Intent(this, ContactActivity.class));
	  }
	 
	 public void openabout(View view)
	  {
		   startActivity(new Intent(this, AboutActivity.class));
	  }

	
}
	

