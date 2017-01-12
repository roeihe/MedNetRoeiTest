package com.example.davidpilnik.mednet;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.icu.util.Calendar;
import android.icu.util.GregorianCalendar;
import android.icu.util.TimeZone;
import android.net.Uri;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.Toast;

import static com.example.davidpilnik.mednet.R.attr.title;

public class Calender extends AppCompatActivity {

    CalendarView cv;
    int year,month,day;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String calName;
        String calId=null;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);
        cv = (CalendarView) findViewById(R.id.calender_view);

        cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int yearS, int monthS, int dayS){
                Toast.makeText(getApplicationContext(),dayS + "/" + monthS+1 + "/" + yearS,Toast.LENGTH_LONG).show();
                year=yearS;
                day=dayS;
                month=monthS;
                calendarEvent(view);
            }
        });

       // Uri calendars = Uri.parse("content://calendar/calendars");
/*
        ContentValues event = new ContentValues();
        event.put("calendar_id", calId);
        event.put("title", "Event Title");
        event.put("description", "Event Desc");
        event.put("eventLocation", "Event Location");

        event.put("dtstart", 10);
        event.put("dtend", 1000);

        event.put("allDay", 1);   // 0 for false, 1 for true
        event.put("eventStatus", 1);
        event.put("visibility", 0);
        event.put("transparency", 0);
        event.put("hasAlarm", 1); // 0 for false, 1 for true
        Uri eventsUri = Uri.parse("content://calendar/events");
        Uri url = getContentResolver().insert(eventsUri, event);

*/
    }

    // context menu!!! long click on edit & delete
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_calendar, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.AddInContextMenu:
                Intent intentToAdd = new Intent(this, Add_med_meet.class);
                startActivity(intentToAdd);
                finish();
                return true;
            case R.id.showInContextMenu:
                Intent intentToShow = new Intent(this, Show_med_meet.class);
                startActivity(intentToShow);
                finish();
                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }

    public void calendarEvent(View view) {

        //Calendar calendarEvent = Calendar.getInstance();
        Intent i = new Intent(Intent.ACTION_INSERT);
        i.setType("vnd.android.cursor.item/event");
        i.putExtra("beginTime", 10);
        i.putExtra("allDay", true);
        i.putExtra("rule", "FREQ=YEARLY");
        i.putExtra("endTime", 20+ 60 * 60 * 1000);
        i.putExtra("title", "Sample Calender Event Android Application");
        startActivity(i);
    }
}
