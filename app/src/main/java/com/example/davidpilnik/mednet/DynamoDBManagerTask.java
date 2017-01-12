package com.example.davidpilnik.mednet;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
/**
 * Created by David Pilnik on 30/12/2016.
 */

/*
public class DynamoDBManagerTask extends
        AsyncTask<Add_med_meet.DynamoDBManagerType, Void, Add_med_meet.DynamoDBManagerTaskResult> {
    protected Add_med_meet.DynamoDBManagerTaskResult doInBackground(
            Add_med_meet.DynamoDBManagerType... types) {

        String tableStatus = DynamoDBManager.getTestTableStatus();
        Add_med_meet.DynamoDBManagerTaskResult result = new Add_med_meet.DynamoDBManagerTaskResult();
        result.setTableStatus(tableStatus);
        result.setTaskType(types[0]);
        if (types[0] == Add_med_meet.DynamoDBManagerType.EDIT_MED_MEET) {
            if (tableStatus.length() == 0) {
                // DynamoDBManager.editTable();
            }
        } else if (types[0] == Add_med_meet.DynamoDBManagerType.INSERT_MED_MEET) {
            if (tableStatus.equalsIgnoreCase("ACTIVE")) {
                DynamoDBManager.insertEvent();
            }
        }

        return result;
    }//doInBackground

    //UI THREAD
    protected void onPostExecute(Add_med_meet.DynamoDBManagerTaskResult result) {

        //just show in screen success insert
        if (result.getTableStatus().equalsIgnoreCase("ACTIVE")
                && result.getTaskType() == Add_med_meet.DynamoDBManagerType.INSERT_MED_MEET) {
           // Toast.makeText(Add_med_meet.this,
                    //"Users inserted successfully!", Toast.LENGTH_SHORT).show();
        }




    }    //onPostExecute


}//DynamoDBManagerTask


*/