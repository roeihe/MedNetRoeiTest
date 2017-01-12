/*
 * Copyright 2010-2012 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://aws.amazon.com/apache2.0
 * 
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package com.example.davidpilnik.mednet;

import android.util.Log;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBScanExpression;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.PaginatedScanList;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.DeleteTableRequest;
import com.amazonaws.services.dynamodbv2.model.DescribeTableRequest;
import com.amazonaws.services.dynamodbv2.model.DescribeTableResult;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ResourceNotFoundException;
import com.amazonaws.services.dynamodbv2.model.ScalarAttributeType;

import java.util.ArrayList;

public class DynamoDBManager {

    private static final String TAG = "DynamoDBManager";
    private static  int INDEX_PRIMARY = 1;

    /*
     * Creates a table with the following attributes: Table name: testTableName
     * Hash key: userNo type N Read Capacity Units: 10 Write Capacity Units: 5
     */
    /*
    public static void createTable() {

        Log.d(TAG, "Create table called");

        AmazonDynamoDBClient ddb = UserPreferenceDemoActivity.clientManager
                .ddb();

        KeySchemaElement kse = new KeySchemaElement().withAttributeName(
                "userNo").withKeyType(KeyType.HASH);
        AttributeDefinition ad = new AttributeDefinition().withAttributeName(
                "userNo").withAttributeType(ScalarAttributeType.N);
        ProvisionedThroughput pt = new ProvisionedThroughput()
                .withReadCapacityUnits(10l).withWriteCapacityUnits(5l);

        CreateTableRequest request = new CreateTableRequest()
                .withTableName(Constants.TEST_TABLE_NAME)
                .withKeySchema(kse).withAttributeDefinitions(ad)
                .withProvisionedThroughput(pt);

        try {
            Log.d(TAG, "Sending Create table request");
            ddb.createTable(request);
            Log.d(TAG, "Create request response successfully recieved");
        } catch (AmazonServiceException ex) {
            Log.e(TAG, "Error sending create table request", ex);
            UserPreferenceDemoActivity.clientManager
                    .wipeCredentialsOnAuthError(ex);
        }
    }
*/
    /*
     * Retrieves the table description and returns the table status as a string.
     */
    public static String getTestTableStatus() {

        try {
            AmazonDynamoDBClient ddb = Add_med_meet.clientManager.ddb();

            DescribeTableRequest request = new DescribeTableRequest()
                    .withTableName(Constants.TEST_TABLE_NAME);
            DescribeTableResult result = ddb.describeTable(request);

            String status = result.getTable().getTableStatus();
            return status == null ? "" : status;

        } catch (ResourceNotFoundException e) {
        } catch (AmazonServiceException ex) {
            Add_med_meet.clientManager
                    .wipeCredentialsOnAuthError(ex);
        }

        return "";
    }

    /*
     * Inserts ten users with userNo from 1 to 10 and random names.
     */
    public static void insertUsers() {
        AmazonDynamoDBClient ddb = Add_med_meet.clientManager
                .ddb();
        DynamoDBMapper mapper = new DynamoDBMapper(ddb);

        try {

            UserPreference2 userPreference2 = new UserPreference2();

            // System.out.println("with idgggg: " + test);
            //-----------------------------------------------------------------
            //updaite table idname+token+tablename+new set argument
            userPreference2.setUserNo(MainActivity.userId);
            userPreference2.setToken(MainActivity.userToken);
            userPreference2.setUserFacebook("test");

            Log.d(TAG, "Inserting users");
            mapper.save(userPreference2);
            Log.d(TAG, "Users inserted");

        } catch (AmazonServiceException ex) {
            Log.e(TAG, "Error inserting users");
            Add_med_meet.clientManager
                    .wipeCredentialsOnAuthError(ex);
        }
    }

    public static void insertEvent() {
        AmazonDynamoDBClient ddb = Add_med_meet.clientManager
                .ddb();
        DynamoDBMapper mapper = new DynamoDBMapper(ddb);

        try {

            UserPreference userPreference = new UserPreference();

            // System.out.println("with idgggg: " + test);

            userPreference.setMed_Meet_Name(Add_med_meet.MEDICINE);
            userPreference.setPurchase_Date(Add_med_meet.DATE);
            userPreference.setQuantity(Add_med_meet.QUANTITY);
            userPreference.setNum(Add_med_meet.DAYS_BEFORE);//day_before
            //


            //
               // userPreference.setLastName(Constants.getRandomName());

                Log.d(TAG, "Inserting events");
                mapper.save(userPreference);
                Log.d(TAG, "events inserted");

        } catch (AmazonServiceException ex) {
            Log.e(TAG, "Error inserting events");
            Add_med_meet.clientManager
                    .wipeCredentialsOnAuthError(ex);
        }
    }

    /*
     * Scans the table and returns the list of users.
     */
    public static ArrayList<UserPreference> getUserList() {

        AmazonDynamoDBClient ddb = Add_med_meet.clientManager
                .ddb();
        DynamoDBMapper mapper = new DynamoDBMapper(ddb);

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        try {
            PaginatedScanList<UserPreference> result = mapper.scan(
                    UserPreference.class, scanExpression);

            ArrayList<UserPreference> resultList = new ArrayList<UserPreference>();
            for (UserPreference up : result) {
                resultList.add(up);
            }
            return resultList;

        } catch (AmazonServiceException ex) {
            Add_med_meet.clientManager
                    .wipeCredentialsOnAuthError(ex);
        }

        return null;
    }

    /*
     * Retrieves all of the attribute/value pairs for the specified user.
     */
    public static UserPreference getUserPreference(int userNo) {

        AmazonDynamoDBClient ddb = Add_med_meet.clientManager
                .ddb();
        DynamoDBMapper mapper = new DynamoDBMapper(ddb);

        try {
            UserPreference userPreference = mapper.load(UserPreference.class,
                    userNo);

            return userPreference;

        } catch (AmazonServiceException ex) {
            Add_med_meet.clientManager
                    .wipeCredentialsOnAuthError(ex);
        }

        return null;
    }

    /*
     * Updates one attribute/value pair for the specified user.
     */
    public static void updateUserPreference(UserPreference updateUserPreference) {

        AmazonDynamoDBClient ddb = Add_med_meet.clientManager
                .ddb();
        DynamoDBMapper mapper = new DynamoDBMapper(ddb);

        try {
            mapper.save(updateUserPreference);

        } catch (AmazonServiceException ex) {
            Add_med_meet.clientManager
                    .wipeCredentialsOnAuthError(ex);
        }
    }

    /*
     * Deletes the specified user and all of its attribute/value pairs.
     */
    public static void deleteUser(UserPreference deleteUserPreference) {

        AmazonDynamoDBClient ddb = Add_med_meet.clientManager
                .ddb();
        DynamoDBMapper mapper = new DynamoDBMapper(ddb);

        try {
            mapper.delete(deleteUserPreference);

        } catch (AmazonServiceException ex) {
            Add_med_meet.clientManager
                    .wipeCredentialsOnAuthError(ex);
        }
    }

    /*
     * Deletes the test table and all of its users and their attribute/value
     * pairs.
     */
    public static void cleanUp() {

        AmazonDynamoDBClient ddb = Add_med_meet.clientManager
                .ddb();

        DeleteTableRequest request = new DeleteTableRequest()
                .withTableName(Constants.TEST_TABLE_NAME);
        try {
            ddb.deleteTable(request);

        } catch (AmazonServiceException ex) {
            Add_med_meet.clientManager
                    .wipeCredentialsOnAuthError(ex);
        }
    }

//    @DynamoDBTable(tableName = Constants.TEST_TABLE_NAME)
@DynamoDBTable(tableName = Constants.TEST_TABLE_NAME)
public static class UserPreference {
        private int num_before;
        private String med_Meet_Name;
        private int quantity ;
        private String purchase_Date;
        private Boolean vibrate;
        private Boolean silent;
        private String colorTheme;

        @DynamoDBHashKey(attributeName = "num")
        public int getNum() {
            return num_before;
        }

        public void setNum(int num_before) {
            this.num_before = num_before;
        }

        @DynamoDBAttribute(attributeName = "med_meet_name")
        public String getMed_Meet_Name() {
            return med_Meet_Name;
        }

        public void setMed_Meet_Name(String medmeetName) {
            this.med_Meet_Name = medmeetName;
        }

        @DynamoDBAttribute(attributeName = "quantity")
        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        @DynamoDBAttribute(attributeName = "date")
        public String getPurchase_Date() {
            return purchase_Date;
        }

        public void setPurchase_Date(String date) {
            this.purchase_Date = date;
        }

    }


    @DynamoDBTable(tableName = Constants.USER_TABLE_ID)
    public static class UserPreference2 {

        private String userNo;
        private String token;
        private  String userFacebook;

        @DynamoDBHashKey(attributeName = "userNo")
        public String getUserNo() {
            return userNo;
        }
        public void setUserNo(String userNo) {
            this.userNo = userNo;
        }

        @DynamoDBAttribute(attributeName = "token")
        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
        @DynamoDBAttribute(attributeName = "userFacebook")
        public String getUserFacebook() {
            return userFacebook;
        }

        public void setUserFacebook(String userFacebook) {
            this.userFacebook = userFacebook;
        }
    }
}
