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
import android.widget.ListView;

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
    //날자 관련
    static DBManager dbManager = MainActivity.dbManager;
    int mYear, mMonth, mDay, mHour, mMinute, mWeek;

    @Override
    public void onActivityCreated(Bundle b) {
        super.onActivityCreated(b);

        makenotice();
        noticeListView = (ListView) getView().findViewById(R.id.noticeListView);
        adapter = new NoticeListAdapter(getContext().getApplicationContext(), noticeList);
        noticeListView.setAdapter(adapter);

    }

    public void makenotice() {

        Cursor cursor;
        cursor = dbManager.todoNotice();

        //달력 값
        Calendar cal = new GregorianCalendar();

        mYear = cal.get(Calendar.YEAR);
        mMonth = cal.get(Calendar.MONTH);
        mDay = cal.get(Calendar.DAY_OF_MONTH);
        mHour = cal.get(Calendar.HOUR_OF_DAY);
        mMinute = cal.get(Calendar.MINUTE);
        mWeek = cal.get(Calendar.WEEK_OF_MONTH);

        noticeList = new ArrayList<>();
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



            if (newYear.equals(String.valueOf(mYear)) && newDate.equals(String.valueOf(mDay)) && newMonth.equals(String.valueOf(mMonth))) {
                noticeList.add(new Notice(newYear, newMonth, newWeek, newDate, newTodo));
            }
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
