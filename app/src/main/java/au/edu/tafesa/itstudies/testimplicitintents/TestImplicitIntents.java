package au.edu.tafesa.itstudies.testimplicitintents;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.app.Activity;
import android.widget.Toast;
import android.net.Uri;
import android.telephony.SmsManager;
import java.util.List;


public class TestImplicitIntents  extends Activity {

    private static final String CLASS_TAG = "TestImplicitIntents";
    private String phone = "";
    private String message = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_implicit_intents);
        Log.i(CLASS_TAG, "onCreate started...");

        setContentView(R.layout.activity_test_implicit_intents);

        Button b;

        // WEB PAGE
        b = (Button) this.findViewById(R.id.btnGoogleSearch);
        b.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(Intent.ACTION_WEB_SEARCH);
                intent.setData(Uri.parse("http://www.google.com"));
                startActivity(intent);
            }
        });

        // VIEW CONTACTS
        b = (Button) this.findViewById(R.id.btnContacts);
        b.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivity(intent);
            }
        });

        // Email
        b = (Button) this.findViewById(R.id.btnEmail);
        b.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                        //send an Email
                String emailAddressList[] = {"Test1@gotmail.com", "Test2@hotmail.com"};
                String emailSubject = "Test email subject";
                String emailText = "Test email text";
                intent = new Intent(Intent.ACTION_SEND);
                intent.setType("plain/text");
                intent.putExtra(Intent.EXTRA_EMAIL, emailAddressList);
                intent.putExtra(Intent.EXTRA_SUBJECT, emailSubject);
                intent.putExtra(Intent.EXTRA_TEXT, emailText);

                if(isIntentAvailable(TestImplicitIntents.this, intent)){
                    startActivity(intent);
                }

                else{
                    Toast.makeText(TestImplicitIntents.this, "No one can handle " + intent,Toast.LENGTH_LONG).show();
                }
            }
        });


        Button btnSend;
        btnSend = this.findViewById(R.id.btnSendSMS);
        HandleButtonSendOnClick buttonSendOnClick;
        buttonSendOnClick = new HandleButtonSendOnClick();
        btnSend.setOnClickListener(buttonSendOnClick);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendSMSIntent;
                Uri uri = Uri.parse("smsto:" + phone);
                sendSMSIntent = new Intent(Intent.ACTION_SENDTO, uri);
                sendSMSIntent.putExtra("sms body", message);
                startActivity(sendSMSIntent);
                try {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phone, null, message, null, null);
                    Toast.makeText(getApplicationContext(), "SMS Sent To " + phone, Toast.LENGTH_LONG).show();
                }
                catch (Exception e){
                    Toast.makeText(getApplicationContext(), e.getMessage(),Toast.LENGTH_LONG).show();

                }
                }
        });




    }

    public class HandleButtonSendOnClick implements View.OnClickListener{
        @Override
        public void onClick(View v) {
                Intent sendSMSIntent;
                Uri uri = Uri.parse("smsto:" + phone);
                sendSMSIntent = new Intent(Intent.ACTION_SENDTO, uri);
                sendSMSIntent.putExtra("sms body", message);
                startActivity(sendSMSIntent);

        }
    }

    public static boolean isIntentAvailable(Context ctx, Intent intent){
        final PackageManager mgr = ctx.getPackageManager();
        List<ResolveInfo> list = mgr.queryIntentActivities(intent,PackageManager.MATCH_DEFAULT_ONLY);
        return list.size() > 0;

    }



}
