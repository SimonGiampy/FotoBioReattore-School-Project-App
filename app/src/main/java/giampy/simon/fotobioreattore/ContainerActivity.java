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

public class ContainerActivity extends AppCompatActivity implements DetailsFragment.OnContinueClickListener{

    int imageIdentifier;
    String textIdentifier;

    Bundle args;
    DetailsFragment detailsFragment;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

        if (getSupportActionBar()!=null) {
            getSupportActionBar().setTitle("Scegli il contenitore");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        detailsFragment = new DetailsFragment();
        args = new Bundle();
        args.putString(ValuesController.FEATURE1, getResources().getString(R.string.efficienza));
        args.putString(ValuesController.FEATURE2, getResources().getString(R.string.manutenzione));
        args.putString(ValuesController.FEATURE3, getResources().getString(R.string.costo));
        args.putInt(ValuesController.INDEX_OF_COSTI, 3);
        fragmentManager = getSupportFragmentManager();

        Spinner spinner = (Spinner) findViewById(R.id.contenitori);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.tipo_contenitori, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0: {
                        imageIdentifier = getResources().getIdentifier("@android:drawable/transparent", null, getPackageName());
                        args.putInt(ValuesController.IMAGE_ID, imageIdentifier);
                        args.putString(ValuesController.DESCRIPTION_ID, getResources().getString(R.string.seleziona_container));
                        args.putBoolean(ValuesController.ENABLED_CHART, false);
                    }
                    break;
                    case 1: {
                        imageIdentifier = getResources().getIdentifier("@drawable/tubolari", null, getPackageName());
                        textIdentifier = getResources().getStringArray(R.array.tipo_contenitori) [position];
                        args.putInt(ValuesController.IMAGE_ID, imageIdentifier);
                        args.putString(ValuesController.DESCRIPTION_ID, getResources().getString(R.string.tubolare_description));
                        args.putInt(ValuesController.FEATURE1_VALUE, 75);
                        args.putInt(ValuesController.FEATURE2_VALUE, 90);
                        args.putInt(ValuesController.FEATURE3_VALUE, 60);
                        args.putBoolean(ValuesController.ENABLED_CHART, true);
                        args.putString(ValuesController.TEXT_ID, textIdentifier);
                    }
                    break;
                    case 2: {
                        imageIdentifier = getResources().getIdentifier("@drawable/a_cilindro", null, getPackageName());
                        textIdentifier = getResources().getStringArray(R.array.tipo_contenitori) [position];
                        args.putInt(ValuesController.IMAGE_ID, imageIdentifier);
                        args.putString(ValuesController.DESCRIPTION_ID, getResources().getString(R.string.cilindro_description));
                        args.putInt(ValuesController.FEATURE1_VALUE, 80);
                        args.putInt(ValuesController.FEATURE2_VALUE, 75);
                        args.putInt(ValuesController.FEATURE3_VALUE, 60);
                        args.putBoolean(ValuesController.ENABLED_CHART, true);
                        args.putString(ValuesController.TEXT_ID, textIdentifier);
                    }
                    break;
                    case 3: {
                        imageIdentifier = getResources().getIdentifier("@drawable/a_pannello", null, getPackageName());
                        textIdentifier = getResources().getStringArray(R.array.tipo_contenitori) [position];
                        args.putInt(ValuesController.IMAGE_ID, imageIdentifier);
                        args.putString(ValuesController.DESCRIPTION_ID, getResources().getString(R.string.pannello_description));
                        args.putInt(ValuesController.FEATURE1_VALUE, 55);
                        args.putInt(ValuesController.FEATURE2_VALUE, 75);
                        args.putInt(ValuesController.FEATURE3_VALUE, 65);
                        args.putBoolean(ValuesController.ENABLED_CHART, true);
                        args.putString(ValuesController.TEXT_ID, textIdentifier);
                    }
                    break;
                    case 4: {
                        imageIdentifier = getResources().getIdentifier("@drawable/sistema_aperto", null, getPackageName());
                        textIdentifier = getResources().getStringArray(R.array.tipo_contenitori) [position];
                        args.putInt(ValuesController.IMAGE_ID, imageIdentifier);
                        args.putString(ValuesController.DESCRIPTION_ID, getResources().getString(R.string.opensystem_description));
                        args.putInt(ValuesController.FEATURE1_VALUE, 40);
                        args.putInt(ValuesController.FEATURE2_VALUE, 80);
                        args.putInt(ValuesController.FEATURE3_VALUE, 90);
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
        controller.setTypeOfContainer(args.getString(ValuesController.TEXT_ID));

        Intent intent = new Intent(ContainerActivity.this, AlgheActivity.class);
        startActivity(intent);
    }
}
