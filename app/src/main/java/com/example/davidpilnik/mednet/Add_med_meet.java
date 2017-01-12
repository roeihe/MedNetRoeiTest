package com.example.davidpilnik.mednet;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Add_med_meet extends AppCompatActivity {
   // private static final String TAG = "UserPreferenceDemoActivity";
    public static AmazonClientManager clientManager = null;
    public static  String MEDICINE = null;
    public static  int QUANTITY = 0;
    public static  String DATE = null;
    public static  int DAYS_BEFORE = 0;
    EditText Quantity =null;
    EditText Name_Med_Meet =null;
    EditText Purchase_Date =null;
    EditText Days_Before =null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_med_meet);
        Button Submit = (Button) findViewById(R.id.submit);
        Name_Med_Meet = (EditText) findViewById(R.id.medicine_name);
        Purchase_Date = (EditText) findViewById(R.id.purchase_date);
        Days_Before = (EditText) findViewById(R.id.day_before);
        Quantity = (EditText) findViewById(R.id.quantity);

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String QUANTITY2;
                String DAYS_BEFORE2;
                MEDICINE = Name_Med_Meet.getText().toString();
                QUANTITY2 = Quantity.getText().toString();
                DATE = Purchase_Date.getText().toString();
                DAYS_BEFORE2 = Days_Before.getText().toString();

                QUANTITY= Integer.parseInt(QUANTITY2);
                DAYS_BEFORE= Integer.parseInt(DAYS_BEFORE2);
                if (MEDICINE == null || Quantity.getText().toString().isEmpty() || DATE == null || Days_Before.getText().toString().isEmpty())
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Add_med_meet.this);
                    builder.setMessage("All the field's must be fill");
                    AlertDialog alert = builder.create();
                    alert.show();
                }
                else {
                    clientManager = new AmazonClientManager(Add_med_meet.this);
                    new DynamoDBManagerTask()
                            .execute(DynamoDBManagerType.INSERT_MED_MEET);
                }
            }
        });

    }//onCreate

    private class DynamoDBManagerTask extends
        AsyncTask<DynamoDBManagerType, Void, DynamoDBManagerTaskResult> {
        protected DynamoDBManagerTaskResult doInBackground(
                DynamoDBManagerType... types) {

            String tableStatus = DynamoDBManager.getTestTableStatus();
            DynamoDBManagerTaskResult result = new DynamoDBManagerTaskResult();
            result.setTableStatus(tableStatus);
            result.setTaskType(types[0]);
            if (types[0] == DynamoDBManagerType.EDIT_MED_MEET) {
                if (tableStatus.length() == 0) {
                   // DynamoDBManager.editTable();
                }
            } else if (types[0] == DynamoDBManagerType.INSERT_MED_MEET) {
                if (tableStatus.equalsIgnoreCase("ACTIVE")) {
                    DynamoDBManager.insertEvent();
                }
            }else if (types[0] == DynamoDBManagerType.INSERT_USERS) {
                if (tableStatus.equalsIgnoreCase("ACTIVE")) {
                    DynamoDBManager.insertUsers();
                }
            }


            return result;
        }//doInBackground

        //UI THREAD
        protected void onPostExecute(DynamoDBManagerTaskResult result) {

            //just show in screen success insert
            if (result.getTableStatus().equalsIgnoreCase("ACTIVE")
                    && result.getTaskType() == DynamoDBManagerType.INSERT_MED_MEET) {
                Toast.makeText(Add_med_meet.this,
                        "Users inserted successfully!", Toast.LENGTH_SHORT).show();
                finish();
            }
        }    //onPostExecute


    }//DynamoDBManagerTask


    public enum DynamoDBManagerType {
        INSERT_MED_MEET, EDIT_MED_MEET,INSERT_USERS
    }//DynamoDBManagerType


    public class DynamoDBManagerTaskResult {
        private DynamoDBManagerType taskType;
        private String tableStatus;

        public DynamoDBManagerType getTaskType() {
            return taskType;
        }//TO KNOW IF TO INSERT/EDIT

        public void setTaskType(DynamoDBManagerType taskType) {
            this.taskType = taskType;
        }

        public String getTableStatus() {
            return tableStatus;
        }//status of our table

        public void setTableStatus(String tableStatus) {
            this.tableStatus = tableStatus;
        }
    }
}//Add_med_meet