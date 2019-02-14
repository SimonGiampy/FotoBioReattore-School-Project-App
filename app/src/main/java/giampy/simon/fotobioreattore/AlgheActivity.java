package giampy.simon.fotobioreattore;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class AlgheActivity extends AppCompatActivity implements DetailsFragment.OnContinueClickListener{

    int imageIdentifier;
    String textIdentifier;  //testo da cambiare nel listener quando va implementato e checkato per creare un testo finale

    Bundle args;
    DetailsFragment detailsFragment;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alghe);

        if (getSupportActionBar()!=null) {
            getSupportActionBar().setTitle("Scegli il tipo di alga");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        detailsFragment = new DetailsFragment();
        args = new Bundle();
        args.putString(ValuesController.FEATURE1, getResources().getString(R.string.crescita));
        args.putString(ValuesController.FEATURE2, getResources().getString(R.string.cibo));
        args.putString(ValuesController.FEATURE3, getResources().getString(R.string.reperibilita));
        args.putInt(ValuesController.INDEX_OF_COSTI, 0);
        fragmentManager = getSupportFragmentManager();

        Spinner spinner = (Spinner) findViewById(R.id.alghe_type);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.tipo_alghe, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0: {
                        imageIdentifier = getResources().getIdentifier("@android:drawable/transparent", null, getPackageName());
                        args.putInt(ValuesController.IMAGE_ID, imageIdentifier);
                        args.putString(ValuesController.DESCRIPTION_ID, getResources().getString(R.string.seleziona_alga));
                        args.putBoolean(ValuesController.ENABLED_CHART, false);
                    }
                    break;
                    case 1: {  //valori da cambiare
                        imageIdentifier = getResources().getIdentifier("@drawable/alga_spirulina", null, getPackageName());
                        textIdentifier = getResources().getStringArray(R.array.tipo_alghe) [position];
                        args.putInt(ValuesController.IMAGE_ID, imageIdentifier);
                        args.putString(ValuesController.DESCRIPTION_ID, getResources().getString(R.string.spirulina_description));
                        args.putInt(ValuesController.FEATURE1_VALUE, 90);
                        args.putInt(ValuesController.FEATURE2_VALUE, 85);
                        args.putInt(ValuesController.FEATURE3_VALUE, 80);
                        args.putBoolean(ValuesController.ENABLED_CHART, true);
                        args.putString(ValuesController.TEXT_ID, textIdentifier);
                    }
                    break;
                    case 2: {  //valori da cambiare e immagine
                        imageIdentifier = getResources().getIdentifier("@drawable/chlorella", null, getPackageName());
                        textIdentifier = getResources().getStringArray(R.array.tipo_alghe) [position];
                        args.putInt(ValuesController.IMAGE_ID, imageIdentifier);
                        args.putString(ValuesController.DESCRIPTION_ID, getResources().getString(R.string.chlorella_description));
                        args.putInt(ValuesController.FEATURE1_VALUE, 85);
                        args.putInt(ValuesController.FEATURE2_VALUE, 95);
                        args.putInt(ValuesController.FEATURE3_VALUE, 85);
                        args.putBoolean(ValuesController.ENABLED_CHART, true);
                        args.putString(ValuesController.TEXT_ID, textIdentifier);
                    }
                    break;
                    case 3: {  //valori da cambiare e immagine
                        imageIdentifier = getResources().getIdentifier("@drawable/cyano", null, getPackageName());
                        textIdentifier = getResources().getStringArray(R.array.tipo_alghe) [position];
                        args.putInt(ValuesController.IMAGE_ID, imageIdentifier);
                        args.putString(ValuesController.DESCRIPTION_ID, getResources().getString(R.string.cyanobacteria_description));
                        args.putInt(ValuesController.FEATURE1_VALUE, 75);
                        args.putInt(ValuesController.FEATURE2_VALUE, 85);
                        args.putInt(ValuesController.FEATURE3_VALUE, 80);
                        args.putBoolean(ValuesController.ENABLED_CHART, true);
                        args.putString(ValuesController.TEXT_ID, textIdentifier);
                    }
                    break;
                }
                fragmentManager.beginTransaction().detach(detailsFragment).commit();
                detailsFragment = new DetailsFragment();
                detailsFragment.setArguments(args);
                fragmentManager.beginTransaction().add(R.id.fragment, detailsFragment).commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        detailsFragment.setArguments(args);
        fragmentManager.beginTransaction().add(R.id.fragment, detailsFragment).commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onContinueClickListener() {
        ValuesController controller = new ValuesController();
        controller.setTypeOfAlghe(args.getString(ValuesController.TEXT_ID));
        Intent intent = new Intent(AlgheActivity.this, FinalProductActivity.class);
        startActivity(intent);
    }
}
