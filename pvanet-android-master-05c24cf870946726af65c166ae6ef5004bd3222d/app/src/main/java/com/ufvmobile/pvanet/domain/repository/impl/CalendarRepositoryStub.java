package com.ufvmobile.pvanet.domain.repository.impl;

import android.content.Context;
import android.support.annotation.Nullable;

import com.ufvmobile.pvanet.domain.model.PvanetCalendar;
import com.ufvmobile.pvanet.domain.repository.CalendarRepository;

import java.util.List;

/**
 * @author Igor Vilela
 * @since 16/08/2016
 */
public class CalendarRepositoryStub implements CalendarRepository {

    List<PvanetCalendar> mCalendar = null;
    private Context mContext;
    private String mStudentId;
    private String mStudentPassword;

    public CalendarRepositoryStub(Context context, String studentId, String studentPassword) {
        mContext = context;
        mStudentId = studentId;
        mStudentPassword = studentPassword;
    }

    @Nullable
    @Override
    public List<PvanetCalendar> getCalendar() {
        mCalendar = fetchFromNetwork();
        return mCalendar;
    }

    private List<PvanetCalendar> fetchFromNetwork() {
        return stubCalendar();
    }

    private List<PvanetCalendar> stubCalendar() {
        return null;
    }
        /*final String AssigmentJson = "[{\n" +
                "\t\"codigo\": 10443,\n" +
                "\t\"dataEntrega\": \"2016-10-23T00:00:00-02:00\",\n" +
                "\t\"titulo\": \"Ponto de Controle 2 (INF498)\"\n" +
                "}, {\n" +
                "\t\"codigo\": 10444,\n" +
                "\t\"dataEntrega\": \"2016-11-27T00:00:00-02:00\",\n" +
                "\t\"titulo\": \"Ponto de Controle 3 (INF498)\"\n" +
                "}, {\n" +
                "\t\"codigo\": 10446,\n" +
                "\t\"dataEntrega\": \"2016-10-23T00:00:00-02:00\",\n" +
                "\t\"titulo\": \"Ponto de Controle 5 (INF499)\"\n" +
                "}, {\n" +
                "\t\"codigo\": 10447,\n" +
                "\t\"dataEntrega\": \"2016-11-27T00:00:00-02:00\",\n" +
                "\t\"titulo\": \"Ponto de Controle 6 (INF499)\"\n" +
                "}]";

        final String ScheduleJson = "[\n" +
                "  {\n" +
                "    \"codigo\": 19477,\n" +
                "    \"data\": \"2016-10-23T00:00:00-02:00\",\n" +
                "    \"descricao\": \"3ª Prova de INf115\",\n" +
                "    \"exibir\": true\n" +
                "  },\n" +
                "  {\n" +
                "    \"codigo\": 19478,\n" +
                "    \"data\": \"2016-09-06T00:00:00-03:00\",\n" +
                "    \"descricao\": \"Entrega do 1º Trabalho Prático\",\n" +
                "    \"exibir\": true\n" +
                "  },\n" +
                "  {\n" +
                "    \"codigo\": 19475,\n" +
                "    \"data\": \"2016-09-15T00:00:00-03:00\",\n" +
                "    \"descricao\": \"1ª Prova de INF115\",\n" +
                "    \"exibir\": true\n" +
                "  },\n" +
                "  {\n" +
                "    \"codigo\": 19476,\n" +
                "    \"data\": \"2016-10-20T00:00:00-02:00\",\n" +
                "    \"descricao\": \"2ª Prova de INF115\",\n" +
                "    \"exibir\": true\n" +
                "  }\n" +
                "]";
        try {

            ArrayList<Assignment> assignments;
            ArrayList<ScheduleOld> schedules;

            Type collectionType = new TypeToken<ArrayList<ScheduleOld>>(){}.getType();
            schedules = new Gson().fromJson(ScheduleJson, collectionType);

            collectionType = new TypeToken<ArrayList<Assignment>>(){}.getType();
            assignments = new Gson().fromJson(AssigmentJson, collectionType);

            ArrayList<PvanetCalendar> calendar = new ArrayList<>();
            ArrayList<PvanetCalendar> calendarAux = new ArrayList<>();
            calendarAux.addAll(CalendarConverter.convertAssigmentToCalendar(null, assignments));
            calendar.addAll(CalendarConverter.convertScheduleToCalendar(calendarAux, schedules));

            return calendar;
        } catch(JsonParseException e) {
            return null;
        }
    }*/
}
