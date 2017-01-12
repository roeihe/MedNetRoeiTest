package com.example.davidpilnik.mednet;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.LoginFilter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;


import org.json.JSONObject;

import static com.example.davidpilnik.mednet.Add_med_meet.clientManager;


public class MainActivity extends AppCompatActivity {
    public static  String userId = null;
    public static  String userToken = null;
    LoginButton loginButton;
    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        loginButton= (LoginButton) findViewById(R.id.fb_login_bn);
        callbackManager = CallbackManager.Factory.create();
        /* check if already connected to facebook */
        AccessToken accessToken= AccessToken.getCurrentAccessToken();
        if (accessToken != null)
        {
            Intent intent_facebook = new Intent(MainActivity.this, MainOption.class);
            startActivity(intent_facebook);
        }
        /*      end check if connected           */
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d("statuslogin","onSuccess");
                userId=loginResult.getAccessToken().getUserId();
                userToken = loginResult.getAccessToken().getToken();
                Log.d("statuslogin",userId+userToken);

                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                Log.v("Main", response.toString());

                               // setProfileToView(object);
                            }
                        });
//execute
                /*
                clientManager = new AmazonClientManager(MainActivity.this);
                new DynamoDBManagerTask()
                        .execute(Add_med_meet.DynamoDBManagerType.INSERT_USERS);
*/
                Profile profile = Profile.getCurrentProfile();
                if (profile != null) {
                    Log.d("LOGINDATA","LOGINDATA");
                    Log.v("k", "Logged, user name=" + profile.getFirstName() + " " + profile.getLastName());
                }
                Intent intent_facebook = new Intent(MainActivity.this, MainOption.class);
                startActivity(intent_facebook);
            }

            @Override
            public void onCancel() {
                Log.d("statuslogin","onCancel");
            }

            @Override
            public void onError(FacebookException error) {
                Log.d("statuslogin","onError");

            }
        });
    }//OnCreate


    private class DynamoDBManagerTaskUSER extends
            AsyncTask<DynamoDBManagerType2, Void, DynamoDBManagerTaskResult2> {
        protected DynamoDBManagerTaskResult2 doInBackground(
                DynamoDBManagerType2... types) {

            String tableStatus = DynamoDBManager.getTestTableStatus();
            DynamoDBManagerTaskResult2 result = new DynamoDBManagerTaskResult2();
            result.setTableStatus(tableStatus);
            result.setTaskType(types[0]);
            if (types[0] == DynamoDBManagerType2.EDIT_MED_MEET) {
                if (tableStatus.length() == 0) {
                    // DynamoDBManager.editTable();
                }
            } else if (types[0] == DynamoDBManagerType2.INSERT_MED_MEET) {
                if (tableStatus.equalsIgnoreCase("ACTIVE")) {
                    DynamoDBManager.insertEvent();
                }
            }else if (types[0] == DynamoDBManagerType2.INSERT_USERS) {
                if (tableStatus.equalsIgnoreCase("ACTIVE")) {
                    DynamoDBManager.insertUsers();
                }
            }


            return result;
        }//doInBackground

        //UI THREAD
        protected void onPostExecute(DynamoDBManagerTaskResult2 result) {

            //just show in screen success insert
            if (result.getTableStatus().equalsIgnoreCase("ACTIVE")
                    && result.getTaskType() == DynamoDBManagerType2.INSERT_MED_MEET) {
                Toast.makeText(MainActivity.this,
                        "Users inserted successfully!", Toast.LENGTH_SHORT).show();
                finish();
            }
        }    //onPostExecute


    }//DynamoDBManagerTask


    public enum DynamoDBManagerType2 {
        INSERT_MED_MEET, EDIT_MED_MEET,INSERT_USERS
    }//DynamoDBManagerType


    public class DynamoDBManagerTaskResult2 {
        private MainActivity.DynamoDBManagerType2 taskType;
        private String tableStatus;

        public MainActivity.DynamoDBManagerType2 getTaskType() {
            return taskType;
        }//TO KNOE IF TO INSERT/EDIT

        public void setTaskType(MainActivity.DynamoDBManagerType2 taskType) {
            this.taskType = taskType;
        }

        public String getTableStatus() {
            return tableStatus;
        }//status of our table

        public void setTableStatus(String tableStatus) {
            this.tableStatus = tableStatus;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode,resultCode,data);
    }
}
