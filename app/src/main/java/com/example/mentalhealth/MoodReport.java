package com.example.mentalhealth;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;


/**
 * This class will control the data and present the view into fragment_report_mood.xml.
 * <p>
 * A simple {@link Fragment} subclass.
 * Use the {@link MoodReport#newInstance} factory method to
 * create an instance of this fragment.
 * <p>
 * References:
 * 1. NP android documentation
 * 2. stackoverflow
 */
public class MoodReport extends Fragment {
    // variable for our bar chart
    BarChart barChart;

    // variable for our bar data.
    BarData barData;
    // variable for our bar data set.
    BarDataSet barDataSet;

    // array list for storing entries.
    ArrayList barEntriesArrayList;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private BarChart chart;

    /**
     * Autogenerated constructor that is required.
     */
    public MoodReport() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MoodReport.
     */
    // TODO: Rename and change types and number of parameters
    public static MoodReport newInstance(String param1, String param2) {
        MoodReport fragment = new MoodReport();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Autogenerated stub
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    /**
     * Returns a view of the last 7 entries.
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return a bar graph with the last 7 entries.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_mood_report, container, false);
        MoodDBHelper moodDB = new MoodDBHelper(view.getContext());
        ArrayList<MoodModal> dbData = moodDB.ReadSortByDate();
        HashMap<Integer, Integer> ratings = new HashMap<Integer, Integer>();
        ArrayList<BarEntry> entries = new ArrayList<BarEntry>();
        int check = 7;
        for (int i = dbData.size() - 1; i >= 0; i--) {


            if (check != 0) {
                BarEntry barEntry = new BarEntry(dbData.size() - i, dbData.get(i).getMoodRating());
                entries.add(barEntry);
                check--;
            }
        }
        Collections.reverse(entries);


        barChart = view.findViewById(R.id.fragment_verticalbarchart_chart);
        // creating a new bar data set.
        barDataSet = new BarDataSet(entries, "Dates");

// creating a new bar data and
// passing our bar data set.
        barData = new BarData(barDataSet);

// below line is to set data
// to our bar chart.
        barChart.setData(barData);

// adding color to our bar data set.
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);

// setting text color.
        barDataSet.setValueTextColor(Color.BLACK);

        // Set the x-axis to the bottom.
        barChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
// setting text size
        barDataSet.setValueTextSize(16f);
        barChart.getDescription().setEnabled(false);


        return view;
    }
}