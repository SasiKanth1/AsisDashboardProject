package sasikanth.isas.dash;

import icr.SystemBarTintManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import sasikanth.isas.dash.R;


import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class ZooperActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_zooper);
		
		
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
	 
	 
	 public void installskin(View view)
	 {
		 showInstallableSkins();
	 }

	 
	 public void getzooper(View view)
	 {
		 final AlertDialog.Builder menuAleart = new AlertDialog.Builder(this);
	    	final String[] menuList = { "Amazon App Store", "Google Play Store" };
	    	menuAleart.setItems(menuList,new DialogInterface.OnClickListener() {
	    	 public void onClick(DialogInterface dialog, int item) {
	    	  switch (item) {
	    	  case 0:
	    		  boolean installed = appInstalledOrNot("com.amazon.venezia");
	    	        if (installed) {
	    	            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("amzn://apps/android?p=org.zooper.zwpro")));
	    	        } else {
	    	            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.amazon.com/gp/mas/dl/android?p=org.zooper.zwpro")));
	    	        }
	    	  break;
	    	  case 1:
	    		  startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=org.zooper.zwpro")));
	    	  break;
	    	  }
	    	 }
	    	});
	    	AlertDialog menuDrop = menuAleart.create();
	    	menuDrop.show();
	 }	 
	 
	 
	 public void iconsets(View view) {
	        AssetManager assetManager = getAssets();
	        String[] files = null;
	        try {
	            files = assetManager.list("IconSets");
	        } catch (IOException e) {
	            Log.e("tag", e.getMessage());
	        }

	        for (String filename : files) {
	            System.out.println("File name => " + filename);
	            InputStream in = null;
	            OutputStream out = null;
	            try {
	                in = assetManager.open("IconSets/" + filename);   // if files resides inside the "fonts" directory itself
	                out = new FileOutputStream(Environment.getExternalStorageDirectory().toString() + "/ZooperWidget/IconSets/" + filename);
	                copyFile(in, out);
	                in.close();
	                in = null;
	                out.flush();
	                out.close();
	                out = null;
	            } catch (Exception e) {
	                Log.e("tag", e.getMessage());
	            }
	            Toast.makeText(this, "Iconsets installed successfully", Toast.LENGTH_SHORT).show();
	        }
	    }
	 
	 
	 private void copyFile(InputStream in, OutputStream out) throws IOException {
	        byte[] buffer = new byte[1024];
	        int read;
	        while ((read = in.read(buffer)) != -1) {
	            out.write(buffer, 0, read);
	        }
	    }
	 
	 
	 private boolean appInstalledOrNot(String uri) {
	        PackageManager pm = getPackageManager();
	        boolean app_installed = false;
	        try {
	            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
	            app_installed = true;
	        } catch (PackageManager.NameNotFoundException e) {
	            app_installed = false;
	        }
	        return app_installed;
	    }
	 
	 private class RepairSkinAsyncTask extends AsyncTask<Void, Void, Void> {
	        @Override
	        protected Void doInBackground(Void... nothing) {
	            String SDCARD_MYAPK_APK = Environment.getExternalStorageDirectory()
	                    .getPath() + File.separator + "temporary92384534$.apk";
	            deleteOldSkin(SDCARD_MYAPK_APK);
	            saveSkinToSdCard(SDCARD_MYAPK_APK);
	            startAppInstaller(SDCARD_MYAPK_APK);
	            return null;
	        }
	    }

	    private void showInstallableSkins() {
	        if (isSDcardAvailable()) {
	            new RepairSkinAsyncTask().execute();
	        } else {
	            Toast.makeText(this, "SD card not available", Toast.LENGTH_LONG)
	                    .show();
	        }
	    }

	    private void deleteOldSkin(String pathToSkin) {
	        File file = new File(pathToSkin);
	        if (file.exists()) {
	            file.delete();
	        }
	    }

	    /**
	     * @param assetManager
	     * @param in
	     * @param out
	     * @param pathToSkin
	     */
	    private void saveSkinToSdCard(String pathToSkin) {
	        AssetManager assetManager = getAssets();
	        InputStream in = null;
	        OutputStream out = null;
	        try {
	            in = assetManager.open(getString(R.string.apkfilename));
	            try {
	                out = new FileOutputStream(pathToSkin);
	            } catch (FileNotFoundException e) {
	                e.printStackTrace();
	            }
	            byte[] buffer = new byte[1024];
	            int read;
	            while ((read = in.read(buffer)) != -1) {
	                out.write(buffer, 0, read);
	            }
	            in.close();
	            in = null;
	            out.flush();
	            out.close();
	            out = null;
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    /**
	     * @param pathToSkin
	     */
	    private void startAppInstaller(String pathToSkin) {
	        Intent intent = new Intent(Intent.ACTION_VIEW);
	        intent.setDataAndType(Uri.fromFile(new File(pathToSkin)),
	                "application/vnd.android.package-archive");
	        startActivity(intent);
	    }

	    private boolean isSDcardAvailable() {
	        String state = Environment.getExternalStorageState();
	        return state.contentEquals(Environment.MEDIA_MOUNTED)
	                || state.contentEquals(Environment.MEDIA_MOUNTED_READ_ONLY);
	    }
	
}
	

