package com.fourmob.colorpicker.sample;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.fourmob.colorpicker.ColorPickerDialog;
import com.fourmob.colorpicker.ColorPickerSwatch;

/**
 * Created by bailey on 15. 5. 14..
 */
public class TimetableChange  extends FragmentActivity {
    private Button put_starttimechange;
    private Button put_endtimechange;
    private Button EndTime;

    int mSelectedHour;
    int mSelectedMinutes;


    int mSelectedHourplus;
    int mSelectedMinutesplus;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timetable_input_dig);
        Button savebtn = (Button) findViewById(R.id.savebtn);
        Button deletebtn = (Button) findViewById(R.id.deletebtn);
        put_starttimechange = (Button) findViewById(R.id.start_time_btn);
        put_endtimechange  = (Button) findViewById(R.id.end_time_btn);
        final Intent intent = getIntent();
        //  String date = intent.getStringExtra("date");

        final EditText put_subject = (EditText) findViewById(R.id.input_subject);
        final EditText put_classroom = (EditText) findViewById(R.id.input_classroom);

        Bundle extras = intent.getExtras();
        String subject = extras.getString("subject");
        String classroom = extras.getString("classroom");
        String starttime = extras.getString("starttime");
        String endtime = extras.getString("endtime");

        put_subject.setText(subject);
        put_classroom.setText(classroom);
        put_starttimechange.setText(starttime);
        put_endtimechange.setText(endtime);
        //시작 시간
        Button btnChangeTime = (Button) findViewById(R.id.start_time_btn);
        btnChangeTime.setText(starttime);
        String[] time = (starttime).split(":");
        int hour = Integer.parseInt(time[0]);
        int min = Integer.parseInt(time[1]);
        mSelectedHour = hour;
        mSelectedMinutes = min;

        // 끝나는 시간
        put_endtimechange.setText(endtime);
        String[] endtimearry = (endtime).split(":");
        int endhour = Integer.parseInt(endtimearry[0]);
        int endmin = Integer.parseInt(endtimearry[1]);

        mSelectedHourplus = endhour;
        mSelectedMinutesplus =endmin;



        String monday_checked = intent.getStringExtra("monday_boolean");

        String tuesday_checked = intent.getStringExtra("tuesday_boolean");

        String wednesday_checked = intent.getStringExtra("wednesday_boolean");

        String thursday_checked = intent.getStringExtra("thursday_boolean");

        String friday_checked = intent.getStringExtra("friday_boolean");



        CheckBox week1 = (CheckBox) findViewById(R.id.week1);
        CheckBox week2 = (CheckBox) findViewById(R.id.week2);
        CheckBox week3 = (CheckBox) findViewById(R.id.week3);
        CheckBox week4 = (CheckBox) findViewById(R.id.week4);
        CheckBox week5 = (CheckBox) findViewById(R.id.week5);


//        Toast.makeText(TimetableChange.this, "mon: " + monday_checked, Toast.LENGTH_SHORT).show();
//        Toast.makeText(TimetableChange.this, "tue: " + tuesday_checked, Toast.LENGTH_SHORT).show();
//        Toast.makeText(TimetableChange.this, "wends : " + wednesday_checked, Toast.LENGTH_SHORT).show();
//        Toast.makeText(TimetableChange.this, "thurs : " + thursday_checked, Toast.LENGTH_SHORT).show();

        if(monday_checked != null){
            if(monday_checked.equals("true")){
                week1.setChecked(true);
                Log.v("checking", "monday is checked2");
                Toast.makeText(TimetableChange.this, "selectedColor : " + monday_checked, Toast.LENGTH_SHORT).show();
            }
        }

        if(tuesday_checked != null){
            if(tuesday_checked.equals("true")){

                week2.setChecked(!week2.isChecked());
                Log.v("checking", "tuesday is checked2");
            }
        }

        if(wednesday_checked != null){
            if(wednesday_checked.equals("true")){

                week3.setChecked(!week3.isChecked());
                Log.v("checking", "wednesday is checked2");
            }
        }

        if(thursday_checked != null){
            if(thursday_checked.equals("true")){

                week4.setChecked(!week4.isChecked());
                Log.v("checking", "thursday is checked2");
            }
        }
        if(friday_checked != null){
            if(friday_checked.equals("true")){

                week5.setChecked(!week5.isChecked());
                Log.v("checking", "friday is checked2");
            }
        }

        //강의명, 강의실을 적는 창을 각각 클릭했을 때 출력된 데이터를 지워준다.
        put_subject.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                put_subject.setText(null);
            }});
        put_classroom.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                put_classroom.setText(null);

            }});
        put_starttimechange.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                put_starttimechange.setText(null);

            }
        });
        put_endtimechange.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                put_endtimechange.setText(null);

            }
        });
        // 시작 하는 시간을 변경하는 경우
        btnChangeTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showTimePickerDialog(mSelectedHour, mSelectedMinutes, true, mOnTimeSetListener);

            }
        });



        //끝나는 시간을 변경하는 경우
        put_endtimechange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showTimePickerDialog(mSelectedHourplus, mSelectedMinutesplus, true, mOnTimeSetListener1);

            }
        });

        //저장 버튼을 누를 경우 데이터를 보내주는 역할
        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("clicked","true");
                int id = intent.getIntExtra("id", 0);
                String date = intent.getStringExtra("date");

                intent.putExtra("id", id);
                intent.putExtra("subject", put_subject.getText().toString());
                intent.putExtra("classroom", put_classroom.getText().toString());
                intent.putExtra("starttime", put_starttimechange.getText().toString());
                intent.putExtra("endtime", put_endtimechange.getText().toString());

                setResult(RESULT_OK, intent);

                finish();
            }
        });

        deletebtn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {

                intent.putExtra("clicked","false");
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        int color= intent.getIntExtra("colorpicker", 0);

        final Button color_picker = (Button)findViewById(R.id.color_picker);
        final ColorPickerDialog colorPickerDialog = new ColorPickerDialog();
        colorPickerDialog.initialize(R.string.dialog_title, new int[]{Color.CYAN, Color.LTGRAY,
                        Color.BLACK, Color.BLUE, Color.GREEN, Color.MAGENTA, Color.RED, Color.GRAY, Color.YELLOW},
                color, 3, 2);
        colorPickerDialog.setOnColorSelectedListener(new ColorPickerSwatch.OnColorSelectedListener() {
            @Override
            public void onColorSelected(int color) {
                findViewById(R.id.color_picker).setBackgroundColor(color);
                //Toast.makeText(TimetableInput.this, "selectedColor : " + color, Toast.LENGTH_SHORT).show();
                intent.putExtra("colorpicker", color);

            }


        });

        color_picker.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                colorPickerDialog.show(getSupportFragmentManager(), "colorpicker");
            }
        });

        SharedPreferences sharedprefs = getSharedPreferences("Test", MODE_PRIVATE);
        sharedprefs.edit().putInt("colorint" , color).apply();

    }//oncreate end


    private TimePickerDialog showTimePickerDialog(int hour, int min, boolean is24Hour, TimePickerDialog.OnTimeSetListener listener) {
        TimePickerDialog dialog = new TimePickerDialog(this, listener, hour, min, is24Hour);
        dialog.show();
        return dialog;
    }


    private void updateTimeUI() {
        String hour = (mSelectedHour > 9) ? ""+mSelectedHour: "0"+mSelectedHour ;
        String minutes = (mSelectedMinutes > 9) ?""+mSelectedMinutes : "0"+mSelectedMinutes;

        put_starttimechange.setText(hour + ":" + minutes);
    }

    final TimePickerDialog.OnTimeSetListener mOnTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int hour, int min) {
            // update the current variables (hour and minutes)
            mSelectedHour = hour;
            mSelectedMinutes = min;

            // update txtTime with the selected time
            updateTimeUI();
        }
    };
    private void updateTimeUI1() {
        String hour = (mSelectedHourplus > 9) ? ""+mSelectedHourplus: "0"+mSelectedHourplus ;
        String minutes = (mSelectedMinutesplus > 9) ?""+mSelectedMinutesplus : "0"+mSelectedMinutesplus;
        put_endtimechange.setText(hour + ":" + minutes);
    }


    final TimePickerDialog.OnTimeSetListener mOnTimeSetListener1 = new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int hour, int min) {
            // update the current variables (hour and minutes)
            mSelectedHourplus = hour;
            mSelectedMinutesplus = min;

            // update txtTime with the selected time
            updateTimeUI1();
        }
    };
}



