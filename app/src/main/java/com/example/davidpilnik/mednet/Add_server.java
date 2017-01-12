package com.example.davidpilnik.mednet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Add_server extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_server);

        Button Guard_button = (Button) findViewById(R.id.add_guard);


        Guard_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_add = new Intent(Add_server.this, Find_Guard.class);

                startActivity(intent_add);
            }
        });
    }
}
