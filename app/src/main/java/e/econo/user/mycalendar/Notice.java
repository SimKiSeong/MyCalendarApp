package e.econo.user.mycalendar;

/**
 * Created by user on 2018-01-14.
 */

public class Notice {

    String year;
    String month;
    String week;
    String date;
    String todo;


    public Notice(String year,String month, String week, String date, String todo) {
        this.year = year;
        this.month = month;
        this.week = week;
        this.date = date;
        this.todo = todo;
    }



    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTodo() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }


}
