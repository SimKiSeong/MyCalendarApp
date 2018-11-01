package e.econo.user.mycalendar;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by user on 2018-11-01.
 */

public class CustomCalendar {

    int year;
    int month;
    int date;
    int week;

    CustomCalendar(int year, int month, int week ,int date){
        this.year = year;
        this.month = month;
        this.date = date;
        this.week = week;
    }

    void nextday(){
        if(month == 0||month == 2||month == 4||month == 6||month == 7||month == 9){
            if(date == 31){
             month++;
             date = 1;
            }
            else{
            date ++;
            }
        }else if(month == 1){
            // 윤달 체크
            if(year%400==0||(year%4==0&&year%100!=0)){
                if(date == 29){
                    month++;
                    date = 1;
                }
            }else{
                if(date == 28){
                    month++;
                    date = 1;
                }else{
                    date++;
                }
            }

        }else if(month == 11){
            if(date == 31){
                year++;
                month = 0;
                date = 1;
            }else{
                date ++;
            }

        }else{
            if(date == 30) {
                month++;
                date = 1;
            }else{
                date++;
            }
        }

    }

    void lastday(){

        if(date == 1){
            if(month == 0){
                year--;
                month = 11;
                date = 31;
            }else if(month==1||month==3||month==5||month==8||month==10){
                month--;
                date = 31;
            }else if(month==2){
                month--;
                if(year%400==0||(year%4==0&&year%100!=0)){
                    date = 29;
                }else{
                    date = 28;}
            }else{
                month--;
                date = 30;
                }
        }else{
            date--;
        }
    }

    void nextWeek(){
        for(int i = 0; i<7;i++){
            nextday();
        }
        Calendar cal = new GregorianCalendar(year,month,date);
        week = cal.get(Calendar.WEEK_OF_MONTH);
    }

    void lastWeek(){
        for(int i = 0; i<7;i++){
            lastday();
        }
        Calendar cal = new GregorianCalendar(year,month,date);
        week = cal.get(Calendar.WEEK_OF_MONTH);

    }



}
