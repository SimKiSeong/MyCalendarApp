package e.econo.user.mycalendar;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by user on 2018-01-14.
 */

public class NoticeListAdapter extends BaseAdapter {

    public NoticeListAdapter(Context context, List<Notice> noticeList) {
        this.context = context;
        this.noticeList = noticeList;
    }

    private Context context;
    private List<Notice>  noticeList;


    @Override
    public int getCount() {
        return noticeList.size();
    }

    @Override
    public Object getItem(int i) {
        return noticeList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {

        View v = View.inflate(context, R.layout.notice, null);

        TextView workText = (TextView) v.findViewById(R.id.workText);
        TextView dateText = (TextView) v.findViewById(R.id.dateText);

        dateText.setText(noticeList.get(i).getYear()+"-"+noticeList.get(i).getMonth()+"-"+noticeList.get(i).getDate());
        workText.setText("-> "+noticeList.get(i).getTodo());


        v.setTag(noticeList.get(i).getYear());

        return v;
    }

    @Nullable
    @Override
    public CharSequence[] getAutofillOptions() {
        return new CharSequence[0];
    }



}
