package com.example.android.hacktxsafe;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity implements RegisterFirstStepFragment.OnFragmentInteractionListener,
        RegisterSecondStepFragment.OnFragmentInteractionListener{

    private int currentStepNumber = 1;
    public ArrayList<PhoneContact> phoneContacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Basic Info");

        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);


        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleOnBackPressed();
            }
        });

        // Begin the transaction
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        // Replace the contents of the container with the new fragment
        ft.replace(R.id.register_fragments_placeholder, new RegisterFirstStepFragment());
        // or ft.add(R.id.your_placeholder, new FooFragment());
        // Complete the changes added above
        ft.commit();

        final Button mNextButton = (Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();

                EditText firstName = (EditText)findViewById(R.id.first_name);
                EditText lastName = (EditText)findViewById(R.id.last_name);
                EditText email = (EditText)findViewById(R.id.email);
                EditText password = (EditText)findViewById(R.id.password);
                EditText address = (EditText)findViewById(R.id.address);
                EditText phoneNumber = (EditText)findViewById(R.id.phone_number);

                if(null != firstName){
                    editor.putString("register_first_name", firstName.getText().toString());
                }
                if(null != lastName){
                    editor.putString("register_last_name", lastName.getText().toString());
                }
                if(null != email){
                    editor.putString("register_email", email.getText().toString());
                }
                if(null != password){
                    editor.putString("register_password", password.getText().toString());
                }
                if(null != address){
                    editor.putString("register_address", address.getText().toString());
                }
                if(null != phoneNumber){
                    editor.putString("register_phone_number", address.getText().toString());
                }

                editor.commit();

                // Begin the transaction
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                switch (currentStepNumber){
                    case 1 :
                        // Replace the contents of the container with the new fragment
                        ft.replace(R.id.register_fragments_placeholder, new RegisterSecondStepFragment());
                        // or ft.add(R.id.your_placeholder, new FooFragment());
                        // Complete the changes added above
                        ft.commit();
                        setTitle("Contact Info");
                        currentStepNumber++;

                        //address.setText(sharedPref.getString("register_address",""));
                        //address.setText(sharedPref.getString("register_phone_number",""));
                        break;
                    case 2 :
                        // Replace the contents of the container with the new fragment
                        ft.replace(R.id.register_fragments_placeholder, new ContactListContentFragment());
                        // or ft.add(R.id.your_placeholder, new FooFragment());
                        // Complete the changes added above
                        ft.commit();
                        mNextButton.setText("Finish");
                        currentStepNumber++;
                        break;
                    case 3 :
                        Toast.makeText(getApplicationContext(),"Done Registering!",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        phoneContacts = new ArrayList<>();
        getPhoneContactsList();
    }

    public void onFragmentInteraction(Uri uri){
        //you can leave it empty
        Toast.makeText(getApplicationContext(),"Register Activity Listening..",Toast.LENGTH_SHORT).show();
        return;
    }

    public void handleOnBackPressed() {
        //super.onBackPressed();
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        EditText firstName = (EditText)findViewById(R.id.first_name);
        EditText lastName = (EditText)findViewById(R.id.last_name);
        EditText email = (EditText)findViewById(R.id.email);
        EditText password = (EditText)findViewById(R.id.password);
        EditText address = (EditText)findViewById(R.id.address);
        EditText phoneNumber = (EditText)findViewById(R.id.phone_number);

        if(null != firstName){
            editor.putString("register_first_name", firstName.getText().toString());
        }
        if(null != lastName){
            editor.putString("register_last_name", lastName.getText().toString());
        }
        if(null != email){
            editor.putString("register_email", email.getText().toString());
        }
        if(null != password){
            editor.putString("register_password", password.getText().toString());
        }
        if(null != address){
            editor.putString("register_address", address.getText().toString());
        }
        if(null != phoneNumber){
            editor.putString("register_phone_number", address.getText().toString());
        }

        editor.commit();

        final Button mNextButton = (Button) findViewById(R.id.next_button);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        switch (this.currentStepNumber){
            case 1 :
                Intent myIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                RegisterActivity.this.startActivity(myIntent);
                finish();
                break;
            case 2 :
                // Replace the contents of the container with the new fragment
                ft.replace(R.id.register_fragments_placeholder, new RegisterFirstStepFragment());
                // or ft.add(R.id.your_placeholder, new FooFragment());
                // Complete the changes added above
                ft.commit();
                setTitle("Basic Info");
                this.currentStepNumber--;
                //firstName.setText(sharedPref.getString("register_first_name",""));
                //lastName.setText(sharedPref.getString("register_last_name",""));
                //email.setText(sharedPref.getString("register_email",""));
                //password.setText(sharedPref.getString("register_password",""));
                break;
            case 3 :
                // Replace the contents of the container with the new fragment
                ft.replace(R.id.register_fragments_placeholder, new RegisterSecondStepFragment());
                // or ft.add(R.id.your_placeholder, new FooFragment());
                // Complete the changes added above
                ft.commit();
                this.currentStepNumber--;
                mNextButton.setText("Next");
                address.setText(sharedPref.getString("register_address",""));
                address.setText(sharedPref.getString("register_phone_number",""));
                break;
        }
    }

    public void getPhoneContactsList() {
        Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null,null, null);
        while (phones.moveToNext())
        {
            String name=phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            String contactId = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID));

            phoneContacts.add(new PhoneContact(name, phoneNumber, contactId));
        }
        phones.close();
    }

    public ArrayList<PhoneContact> getPhoneContacts() {
        return phoneContacts;
    }
}