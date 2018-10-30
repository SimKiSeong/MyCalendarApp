package e.econo.user.mycalendar;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
    Button bt1,bt2,bt3, bt4;
    FragmentManager fm;
    FragmentTransaction tran;
    CalendarFragment frag1;
    WeeklyFragment frag2;
    DailyFragment frag3;
    static DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbManager = new DBManager(getApplicationContext(), "Study.db", null, 1);
        dbManager.getReadableDatabase();


        bt1 = (Button) findViewById(R.id.bt1);
        bt2 = (Button) findViewById(R.id.bt2);
        bt3 = (Button) findViewById(R.id.bt3);
        bt4 = (Button) findViewById(R.id.bt4);
        bt1.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
               setFrag(0); ;
            }
        });
        bt2.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFrag(1); ;
            }
        });
        bt3.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFrag(2); ;
            }
        });
        bt4.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 인텐트 해야함
                Intent todayIntent = new Intent(MainActivity.this,AddActivity.class);
                MainActivity.this.startActivity(todayIntent);

            }
        });
        frag1 = new CalendarFragment(); //프래그먼트 객채셍성
        frag2 = new WeeklyFragment(); //프래그먼트 객채셍성
        frag3 = new DailyFragment(); //프래그먼트 객채셍성
        setFrag(0); //프래그먼트 교체



    }

    public void setFrag(int n){    //프래그먼트를 교체하는 작업을 하는 메소드를 만들었습니다
        fm = getSupportFragmentManager();
        tran = fm.beginTransaction();
        switch (n){
            case 0:
                tran.replace(R.id.main_frame, frag1 );  //replace의 매개변수는 (프래그먼트를 담을 영역 id, 프래그먼트 객체) 입니다.
                tran.commit();
                break;
            case 1:
                tran.replace(R.id.main_frame, frag2);  //replace의 매개변수는 (프래그먼트를 담을 영역 id, 프래그먼트 객체) 입니다.
                tran.commit();
                break;
            case 2:
                tran.replace(R.id.main_frame, frag3);  //replace의 매개변수는 (프래그먼트를 담을 영역 id, 프래그먼트 객체) 입니다.
                tran.commit();
                break;
        }
    }

}
