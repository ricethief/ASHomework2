package au.edu.tafesa.itstudies.testimplicitintents;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.app.Activity;

public class TestImplicitIntents  extends Activity {

    private static final String CLASS_TAG = "TestImplicitIntents";

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
    }
}
