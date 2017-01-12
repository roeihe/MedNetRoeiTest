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
 * Created by David Pilnik on 02/01/2017.
 */
/*
public class DynamoDBManagerTaskUSERS {
    AsyncTask<MainActivity.DynamoDBManagerType, Void, MainActivity.DynamoDBManagerTaskResult>

    {
        protected MainActivity.DynamoDBManagerTaskResult doInBackground(
            MainActivity.DynamoDBManagerType... types) {

        String tableStatus = DynamoDBManager.getTestTableStatus();
        MainActivity.DynamoDBManagerTaskResult result = new MainActivity.DynamoDBManagerTaskResult();
        result.setTableStatus(tableStatus);
        result.setTaskType(types[0]);
        //if (types[0] == MainActivity.DynamoDBManagerType.EDIT_USERS) {
         //   if (tableStatus.length() == 0) {
                // DynamoDBManager.editTable();
          //  }
       // }
        //}
        if (types[0] == MainActivity.DynamoDBManagerType.INSERT_USERS) {
            if (tableStatus.equalsIgnoreCase("ACTIVE")) {
                DynamoDBManager.insertUsers();
            }
        }


        return result;
    }//doInBackground

        //UI THREAD
    protected void onPostExecute(MainActivity.DynamoDBManagerTaskResult result) {

        //just show in screen success insert
        if (result.getTableStatus().equalsIgnoreCase("ACTIVE")
                && result.getTaskType() == MainActivity.DynamoDBManagerType.INSERT_USERS) {
            // Toast.makeText(MainActivity.this,
            //"Users inserted successfully!", Toast.LENGTH_SHORT).show();
        }




    }    //onPostExecute


}//DynamoDBManagerTask


*/