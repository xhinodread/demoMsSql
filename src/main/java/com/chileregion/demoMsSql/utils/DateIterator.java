package com.chileregion.demoMsSql.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

public class DateIterator implements Iterator<Date>, Iterable<Date>{
    private Calendar start = Calendar.getInstance();
    private Calendar end = Calendar.getInstance();
    private Calendar current = Calendar.getInstance();
    private int sw = 0, sw_fin =0;
    public DateIterator(Date start, Date end) {
        System.out.println( "DateIterator start: " +  start);
        System.out.println( "DateIterator end: " +  end);

        this.start.setTime(start);
        this.end.setTime(end);
        this.current.setTime(start);
        //this._current.setTime(start);
    }
    public boolean hasNext() {
        //System.out.println( "DateIterator 1: " );
        //System.out.println( _current.getTime() );
       return !current.after(end);
    }
    public Date next() {
        //System.out.println( "DateIterator 2: " + current.getTime() + " " + end.getTime());
        //System.out.println( "DateIterator 2: " + current.before(end));
        //System.out.println( "DateIterator 2: " + current.before(start));
        //System.out.println( "DateIterator 2 comp'aer: " + current.compareTo(start) );
        //System.out.println( "DateIterator 2: " + current.getTime());
        //if( current.compareTo(start) == 0){
        if( sw == 0){
            sw = 1;
            return start.getTime();
        }

        //current.add(Calendar.DATE, 1);
        if( current.after(end) ){
           // current= (end);
            sw_fin = 1;
            return end.getTime();
        }
        if(sw_fin == 0){
            current.add(Calendar.DATE, 1);

            return current.getTime();
        }else{
            return null;
        }
    }
    public void remove() {
        throw new UnsupportedOperationException("Cannot remove");
    }
    @Bean
    public Iterator<Date> iterator() {
        return this;
    }

}
