package ro.pub.cs.systems.pdsd.lab03.phonedialer;

import ro.pub.cs.systems.pdsd.lab03.phonedialer.R.id;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;


class PhoneButtons implements View.OnClickListener {

	String type="";
	Activity activity;
	public PhoneButtons(String s, Activity activity) {
		type=s;
		this.activity = activity;
	}
	
	@Override
	public void onClick(View v) {
		EditText phoneNumberEditText = (EditText) activity.findViewById(R.id.phone_number_edit_text);
		
		if(type.equals("imgback")) {
			if(!phoneNumberEditText.getText().toString().isEmpty()) {
				String text = phoneNumberEditText.getText().toString();
				text  = text.substring(0,text.length()-1);
				phoneNumberEditText.setText(text);
			}
		} else if(type.equals("imgcall")) {
			
			Intent intent = new Intent(Intent.ACTION_CALL);
			intent.setData(Uri.parse("tel:"+phoneNumberEditText.getText().toString()));
			activity.startActivity(intent);
			
		} else if(type.equals("imgHangup")) {
			activity.finish();
		} else {
			phoneNumberEditText.append(((Button)v).getText());
		}
		
		
	}
	
}

public class PhoneDialerActivity extends Activity {

	final private static int ANOTHER_ACTIVITY_REQUEST_CODE = 2015;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_dialer);
        
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        
        ImageButton imgBack = (ImageButton) findViewById(R.id.img_back);
        ImageButton imgCall = (ImageButton) findViewById(R.id.img_call);
        ImageButton imgHangup = (ImageButton) findViewById(R.id.img_hangup);
        
        Button b0 = (Button) findViewById(R.id.button0);
        Button b1 = (Button) findViewById(R.id.button1);
        Button b2 = (Button) findViewById(R.id.button2);
        Button b3 = (Button) findViewById(R.id.button3);
        Button b4 = (Button) findViewById(R.id.button4);
        Button b5 = (Button) findViewById(R.id.button5);
        Button b6 = (Button) findViewById(R.id.button6);
        Button b7 = (Button) findViewById(R.id.button7);
        Button b8 = (Button) findViewById(R.id.button8);
        Button b9 = (Button) findViewById(R.id.button9);

        Button b_star = (Button) findViewById(R.id.button_star);
        Button b_diez = (Button) findViewById(R.id.button_diez);
        
        imgBack.setOnClickListener(new PhoneButtons("imgback", this));
        imgCall.setOnClickListener(new PhoneButtons("imgcall", this));
        imgHangup.setOnClickListener(new PhoneButtons("imgHangup", this));
        
        b0.setOnClickListener(new PhoneButtons("0", this));
        b1.setOnClickListener(new PhoneButtons("1", this));
        b2.setOnClickListener(new PhoneButtons("2", this));
        b3.setOnClickListener(new PhoneButtons("3", this));
        b4.setOnClickListener(new PhoneButtons("4", this));
        b5.setOnClickListener(new PhoneButtons("5", this));
        b6.setOnClickListener(new PhoneButtons("6", this));
        b7.setOnClickListener(new PhoneButtons("7", this));
        b8.setOnClickListener(new PhoneButtons("8", this));
        b9.setOnClickListener(new PhoneButtons("9", this));
        b_star.setOnClickListener(new PhoneButtons("*", this));
        b_diez.setOnClickListener(new PhoneButtons("#", this));
        
        Button addButton = (Button) findViewById(R.id.add_contact);
        addButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				EditText numberEditText = (EditText) findViewById(R.id.phone_number_edit_text);
				if(numberEditText!=null) {
					String number = numberEditText.getText().toString();
					
					Intent intentAdd = new Intent("ro.pub.cs.systems.pdsd.lab04.contactsmanager.intent.action.ContactsManagerActivity");
					intentAdd.putExtra("ro.pub.cs.systems.pdsd.lab04.phoneNumber", number);
					startActivityForResult(intentAdd, 2015);
				}
				
			}
		});
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.phone_dialer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
