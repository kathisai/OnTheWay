package components.calendar;

import java.util.GregorianCalendar;

public interface CalendarEventsListener {
    public void onUpdateSelectedMonth(GregorianCalendar selectedDateCal);

    public void onUpdateNavigatedMonth(GregorianCalendar navigatedMonthCal);
}
