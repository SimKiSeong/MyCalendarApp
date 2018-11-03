package e.econo.user.mycalendar;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NewWeeklyFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NewWeeklyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewWeeklyFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public NewWeeklyFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewWeeklyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewWeeklyFragment newInstance(String param1, String param2) {
        NewWeeklyFragment fragment = new NewWeeklyFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    // 리스트 관련
    private ListView noticeListView;
    private  NoticeListAdapter adapter;
    private List<Notice> noticeList;
    private ImageButton rightButton;
    private ImageButton leftButton;
    private TextView dateText;
    private TextView weekText;
    private ImageView noDate;

    //날자 관련
    static DBManager dbManager = MainActivity.dbManager;
    int mYear, mMonth, mDay, mHour, mMinute, mWeek;
    int fYear,fWeek,fMonth,fDay,lYear,lMonth,lDay;


    @Override
    public void onActivityCreated(Bundle b) {
        super.onActivityCreated(b);

        noDate = (ImageView)getView().findViewById(R.id.nodate);

        Calendar cal = new GregorianCalendar();
        mYear = cal.get(Calendar.YEAR);
        mMonth = cal.get(Calendar.MONTH);
        mDay = cal.get(Calendar.DAY_OF_MONTH);
        mHour = cal.get(Calendar.HOUR_OF_DAY);
        mMinute = cal.get(Calendar.MINUTE);
        mWeek = cal.get(Calendar.WEEK_OF_YEAR);

        //달력 값
        Calendar fcal = new GregorianCalendar(mYear,mMonth,mDay);
        fcal.add(Calendar.DATE,-(cal.get(Calendar.DAY_OF_WEEK)-1));

        Calendar lcal = new GregorianCalendar(mYear,mMonth,mDay);
        lcal.add(Calendar.DATE,(7-cal.get(Calendar.DAY_OF_WEEK)));


        fYear = fcal.get(Calendar.YEAR);
        fMonth = fcal.get(Calendar.MONTH);
        fDay = fcal.get(Calendar.DAY_OF_MONTH);
        fWeek = fcal.get(Calendar.WEEK_OF_MONTH);

        lYear = lcal.get(Calendar.YEAR);
        lMonth = lcal.get(Calendar.MONTH);
        lDay = lcal.get(Calendar.DAY_OF_MONTH);

        makenotice();
        maketext();



        rightButton = (ImageButton)getView().findViewById(R.id.rightButton);
        rightButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // insert into 테이블명 values (값, 값, 값...);
                Calendar cal = new GregorianCalendar();
                CustomCalendar newCal = new CustomCalendar(mYear,mMonth,mWeek,mDay);
                newCal.nextWeek();
                mYear = newCal.year;
                mMonth = newCal.month;
                mWeek = newCal.week;
                mDay = newCal.date;

                Calendar fcal = new GregorianCalendar(mYear,mMonth,mDay);
                fcal.add(Calendar.DATE,-(cal.get(Calendar.DAY_OF_WEEK)-1));

                Calendar lcal = new GregorianCalendar(mYear,mMonth,mDay);
                lcal.add(Calendar.DATE,(7-cal.get(Calendar.DAY_OF_WEEK)));

                fYear = fcal.get(Calendar.YEAR);
                fMonth = fcal.get(Calendar.MONTH);
                fDay = fcal.get(Calendar.DAY_OF_MONTH);
                fWeek = fcal.get(Calendar.WEEK_OF_MONTH);

                lYear = lcal.get(Calendar.YEAR);
                lMonth = lcal.get(Calendar.MONTH);
                lDay = lcal.get(Calendar.DAY_OF_MONTH);

                makenotice();
                maketext();

            }
        });

        leftButton = (ImageButton)getView().findViewById(R.id.leftButton);
        leftButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // insert into 테이블명 values (값, 값, 값...);
                Calendar cal = new GregorianCalendar();
                CustomCalendar newCal = new CustomCalendar(mYear,mMonth,mWeek,mDay);
                newCal.lastWeek();
                mYear = newCal.year;
                mMonth = newCal.month;
                mWeek = newCal.week;
                mDay = newCal.date;

                Calendar fcal = new GregorianCalendar(mYear,mMonth,mDay);
                fcal.add(Calendar.DATE,-(cal.get(Calendar.DAY_OF_WEEK)-1));

                Calendar lcal = new GregorianCalendar(mYear,mMonth,mDay);
                lcal.add(Calendar.DATE,(7-cal.get(Calendar.DAY_OF_WEEK)));

                fYear = fcal.get(Calendar.YEAR);
                fMonth = fcal.get(Calendar.MONTH);
                fDay = fcal.get(Calendar.DAY_OF_MONTH);
                fWeek = fcal.get(Calendar.WEEK_OF_MONTH);

                lYear = lcal.get(Calendar.YEAR);
                lMonth = lcal.get(Calendar.MONTH);
                lDay = lcal.get(Calendar.DAY_OF_MONTH);

                makenotice();

                maketext();

            }
        });



    }

    public void maketext() {

        Calendar cal = new GregorianCalendar();
        CustomCalendar newCal = new CustomCalendar(mYear,mMonth,mWeek,mDay);

        Calendar checkCal = new GregorianCalendar(fYear,fMonth,1);
        if(checkCal.get(Calendar.DAY_OF_WEEK)!=1){
                        fWeek = fWeek - 1;
        }

        dateText = (TextView)getView().findViewById(R.id.textDate);
        dateText.setText(String.valueOf(fMonth+1)+"월 "+String.valueOf(fWeek)+"째주");

        weekText = (TextView)getView().findViewById(R.id.textWeek);
        weekText.setText(String.valueOf(fMonth+1)+". "+String.valueOf(fDay)+" ~ "+String.valueOf(lMonth+1)+". "+String.valueOf(lDay));

    }


    public void makenotice() {

        Cursor cursor;
        boolean find = true;
        noticeList = new ArrayList<>();

        for(int i = 0;i<7;i++) {
            Calendar checkcal = new GregorianCalendar(fYear,fMonth,fDay);
            checkcal.add(Calendar.DATE,i);

            int checkYear = checkcal.get(Calendar.YEAR);
            int checkMonth = checkcal.get(Calendar.MONTH);
            int checkDate = checkcal.get(Calendar.DATE);

            Log.v("년도",String.valueOf(checkYear));
            Log.v("월",String.valueOf(checkMonth));
            Log.v("일",String.valueOf(checkDate));

            cursor = dbManager.todoNotice();
            while (cursor.moveToNext()) {
                int num;
                String first;
                String newYear;
                String newMonth;
                String newWeek;
                String newDate;
                String newTodo;

                first = cursor.getString(0);
                newYear = cursor.getString(1);
                newMonth = cursor.getString(2);
                newWeek = cursor.getString(3);
                newDate = cursor.getString(4);
                newTodo = cursor.getString(5);

                // 년 달 월 같으면 입력
                if (newYear.equals(String.valueOf(checkYear)) && newMonth.equals(String.valueOf(checkMonth))&&newDate.equals(String.valueOf(checkDate))) {
                    find = false;
                    noticeList.add(new Notice(first, newYear, String.valueOf(mMonth + 1), newWeek, newDate, newTodo));
                }
                noticeListView = (ListView) getView().findViewById(R.id.noticeListView);
                adapter = new NoticeListAdapter(getContext().getApplicationContext(), noticeList);
                noticeListView.setAdapter(adapter);

            }
        }

        if(find){
            noDate.setVisibility(View.VISIBLE);
        }else{
            noDate.setVisibility(View.GONE);
        }

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_weekly, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
       /* if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
