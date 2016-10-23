package com.example.android.hacktxsafe;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity implements RegisterFirstStepFragment.OnFragmentInteractionListener,
        RegisterSecondStepFragment.OnFragmentInteractionListener,RegisterThirdStepFragment.OnFragmentInteractionListener{

    private int currentStepNumber = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Register");

        //setContentView(R.layout.fragment_register_first_step);

        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
                // Begin the transaction
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                switch (currentStepNumber){
                    case 1 :
                        // Replace the contents of the container with the new fragment
                        ft.replace(R.id.register_fragments_placeholder, new RegisterSecondStepFragment());
                        // or ft.add(R.id.your_placeholder, new FooFragment());
                        // Complete the changes added above
                        ft.commit();
                        currentStepNumber++;
                        break;
                    case 2 :
                        // Replace the contents of the container with the new fragment
                        ft.replace(R.id.register_fragments_placeholder, new RegisterThirdStepFragment());
                        // or ft.add(R.id.your_placeholder, new FooFragment());
                        // Complete the changes added above
                        ft.commit();
                        mNextButton.setText("Register");
                        currentStepNumber++;
                        break;
                    case 3 :
                        Toast.makeText(getApplicationContext(),"Done Registering!",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }

    public void onFragmentInteraction(Uri uri){
        //you can leave it empty
        Toast.makeText(getApplicationContext(),"Register Activity Listening..",Toast.LENGTH_SHORT).show();
        return;
    }

}