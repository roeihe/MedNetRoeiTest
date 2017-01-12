package com.example.davidpilnik.mednet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainOption extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_option);

        Button Add_button = (Button) findViewById(R.id.Add);
        Button Show_button = (Button) findViewById(R.id.show);
        Button Calender_button = (Button) findViewById(R.id.calender);
        Button Server_button = (Button) findViewById(R.id.server);



        // Initialize the Amazon Cognito credentials provider
        /*
        CognitoCachingCredentialsProvider credentialsProvider = new CognitoCachingCredentialsProvider(
                getApplicationContext(),
                "us-west-2:699c0c97-e4ee-4a91-970f-baf7e2d76d4f", // Identity Pool ID
                Regions.US_WEST_2 // Region
        );
*/


        //AmazonDynamoDBClient ddbClient = new AmazonDynamoDBClient(credentialsProvider);
        //  DynamoDBMapper mapper = new DynamoDBMapper(ddbClient);







        Add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_add = new Intent(MainOption.this, Add_med_meet.class);

                startActivity(intent_add);
            }
        });

        Show_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_show = new Intent(MainOption.this, Show_med_meet.class);

                startActivity(intent_show);
            }
        });

        Calender_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_calender = new Intent(MainOption.this, Calender.class);

                startActivity(intent_calender);
            }
        });

        Server_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_server = new Intent(MainOption.this, Add_server.class);

                startActivity(intent_server);
            }
        });
    }
}
