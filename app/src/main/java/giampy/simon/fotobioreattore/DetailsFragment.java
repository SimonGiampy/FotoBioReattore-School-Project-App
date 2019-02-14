package giampy.simon.fotobioreattore;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.ArrayList;

public class DetailsFragment extends Fragment {

    int imageID;
    String description;
    String text;

    String feature1;
    String feature2;
    String feature3;
    int feature1Value;
    int feature2Value;
    int feature3Value;
    int indexOfCosti;

    boolean enabledChart;

    Bundle args;

    OnContinueClickListener listener;

    public DetailsFragment() {
        //cambiare testo iniziale schermata avvio
        //aggiungere wiki bioreattore
        //aggiungere pulsante help con descrizione festa scienfiest
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            args = getArguments();
            imageID = args.getInt(ValuesController.IMAGE_ID);
            description = args.getString(ValuesController.DESCRIPTION_ID);
            feature1 = args.getString(ValuesController.FEATURE1);
            feature2 = args.getString(ValuesController.FEATURE2);
            feature3 = args.getString(ValuesController.FEATURE3);
            feature1Value = args.getInt(ValuesController.FEATURE1_VALUE);
            feature2Value = args.getInt(ValuesController.FEATURE2_VALUE);
            feature3Value = args.getInt(ValuesController.FEATURE3_VALUE);
            indexOfCosti = args.getInt(ValuesController.INDEX_OF_COSTI);
            enabledChart = args.getBoolean(ValuesController.ENABLED_CHART);
            text = args.getString(ValuesController.TEXT_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_details, container, false);
        ImageView imageView = (ImageView) v.findViewById(R.id.image);
        TextView textView = (TextView) v.findViewById(R.id.description);
        imageView.setImageResource(imageID);
        textView.setText(description);

        BarChart chart = (BarChart) v.findViewById(R.id.chart);
        chart.setNoDataText("");
        chart.setEnabled(false);

        Button continua = (Button) v.findViewById(R.id.next1);
        continua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onContinueClickListener();
            }
        });

        if (enabledChart) {
            continua.setVisibility(View.VISIBLE);

            ArrayList<BarEntry> barEntryArrayList1 = new ArrayList<>();
            barEntryArrayList1.add(new BarEntry(1f, feature1Value));
            ArrayList<BarEntry> barEntryArrayList2 = new ArrayList<>();
            barEntryArrayList2.add(new BarEntry(3f, feature2Value));
            ArrayList<BarEntry> barEntryArrayList3 = new ArrayList<>();
            barEntryArrayList3.add(new BarEntry(5f, feature3Value));

            BarDataSet dataSet1 = new BarDataSet(barEntryArrayList1, feature1);
            BarDataSet dataSet2 = new BarDataSet(barEntryArrayList2, feature2);
            BarDataSet dataSet3 = new BarDataSet(barEntryArrayList3, feature3);

            int[] color = new int[3];
            int[] values = {feature1Value, feature2Value, feature3Value};
            for (int x = 0; x < 3; x++) {
                if (values[x] > 70) {
                    if (indexOfCosti == x - 1 && indexOfCosti!=0) {
                        color[x] = R.color.basso;
                    } else {
                        color[x] = R.color.alto;
                    }
                } else if (values[x] > 50) {
                    color[x] = R.color.medio;
                } else {
                    if (indexOfCosti == x - 1 && indexOfCosti!=0) {
                        color[x] = R.color.alto;
                    } else {
                        color[x] = R.color.basso;
                    }
                }
            }

            dataSet1.setColor(ContextCompat.getColor(getContext(), color[0]));
            dataSet2.setColor(ContextCompat.getColor(getContext(), color[1]));
            dataSet3.setColor(ContextCompat.getColor(getContext(), color[2]));

            dataSet1.setValueTextSize(12f);
            dataSet2.setValueTextSize(12f);
            dataSet3.setValueTextSize(12f);


            BarData barData = new BarData(dataSet1, dataSet2, dataSet3);
            barData.setBarWidth(1f);
            barData.setValueFormatter(new IValueFormatter() {
                @Override
                public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                    if (value > 70) {
                        return "Alto";
                    } else if (value > 50) {
                        return "Medio";
                    } else {
                        return "Basso";
                    }
                }
            });

            Legend legend = chart.getLegend();
            legend.setEnabled(true);
            legend.setTextSize(16f);
            legend.setWordWrapEnabled(true);

            XAxis xAxis = chart.getXAxis();
            xAxis.setCenterAxisLabels(true);

            chart.setEnabled(true);
            chart.setData(barData);
            chart.setFitBars(true);
            chart.setGridBackgroundColor(ContextCompat.getColor(getContext(), android.R.color.white));
            chart.setDrawBorders(true);
            chart.getAxisLeft().setEnabled(false);
            chart.getAxisRight().setEnabled(false);
            chart.getXAxis().setEnabled(false);
            chart.setTouchEnabled(false);
            chart.setDrawGridBackground(true);
            Description description = new Description();
            description.setTextSize(12f);
            description.setText("Statistiche");
            description.setYOffset(description.getYOffset() - 15f);
            chart.setDescription(description);
            chart.invalidate();
        }

        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (OnContinueClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnContinueClickListener");
        }
    }

    interface OnContinueClickListener {
        void onContinueClickListener();
    }
}
