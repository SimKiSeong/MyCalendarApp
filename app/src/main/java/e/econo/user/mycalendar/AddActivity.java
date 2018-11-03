package e.econo.user.mycalendar;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class AddActivity extends AppCompatActivity {

    static DBManager dbManager;
    int mYear, mMonth, mDay, mHour, mMinute, mWeek;
    EditText etTodo;
    EditText etDate;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        dbManager = MainActivity.dbManager;


        //달력 값
        Calendar cal = new GregorianCalendar();

        mYear = cal.get(Calendar.YEAR);
        mMonth = cal.get(Calendar.MONTH);
        mDay = cal.get(Calendar.DAY_OF_MONTH);
        mHour = cal.get(Calendar.HOUR_OF_DAY);
        mMinute = cal.get(Calendar.MINUTE);
        mWeek = cal.get(Calendar.WEEK_OF_YEAR);


        // 데이트 피커
        etDate = (EditText) findViewById(R.id.et_date);
        etDate.setText(String.format("%d/%d/%d", mYear,mMonth + 1, mDay));
        etTodo = (EditText) findViewById(R.id.et_todo);

        etDate.setInputType(0);

        etDate.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                // TODO Auto-generated method stub

                etDate.setInputType(1);

                new DatePickerDialog(AddActivity.this, mDateSetListener, mYear,

                        mMonth, mDay).show();
            }

        });




        // 디비에 넣기
        Button btnInsert = (Button) findViewById(R.id.btn_insert);
        btnInsert.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // insert into 테이블명 values (값, 값, 값...);
                Calendar newcal = new GregorianCalendar(mYear,mMonth,mDay);

                mWeek = newcal.get(Calendar.WEEK_OF_YEAR);

                String year = String.valueOf(mYear);
                String month = String.valueOf(mMonth);
                String week = String.valueOf(mWeek);
                String date = String.valueOf(mDay);
                String todo = etTodo.getText().toString();


                if (!todo.equals("") && todo.length() > 0) {
                    dbManager.insert("insert into TODO_LIST values(null, '"+year + "','" + month + "','" + week + "','" + date + "','" +  todo + "');");
                    Toast.makeText(getApplicationContext(), "성공적으로 추가되었습니다", Toast.LENGTH_SHORT).show();
                    Intent newintent = new Intent(AddActivity.this, MainActivity.class);
                    AddActivity.this.startActivity(newintent);
                    AddActivity.this.onStop();


                } else

                    Toast.makeText(getApplicationContext(), "입력정보를 다시확인해주세요", Toast.LENGTH_SHORT).show();//Display toast if edittext is empty



            }
        });


        //돌아가기 버튼
        Button btnback = (Button) findViewById(R.id.btn_back);
        btnback.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

               //Intent newintent = new Intent(AddActivity.this, MainActivity.class);
                //AddActivity.this.startActivity(newintent);
                AddActivity.this.finish();

            }
        });



    }

    DatePickerDialog.OnDateSetListener mDateSetListener =

            new DatePickerDialog.OnDateSetListener() {



                @Override

                public void onDateSet(DatePicker view, int year, int monthOfYear,

                                      int dayOfMonth) {

                    // TODO Auto-generated method stub

                    //사용자가 입력한 값을 가져온뒤

                    mYear = year;

                    mMonth = monthOfYear;

                    mDay = dayOfMonth;

                    //텍스트뷰의 값을 업데이트함

                    UpdateNow();

                }

            };

    void UpdateNow(){

        etDate.setText(String.format("%d/%d/%d", mYear,

                mMonth + 1, mDay));

    }



}
