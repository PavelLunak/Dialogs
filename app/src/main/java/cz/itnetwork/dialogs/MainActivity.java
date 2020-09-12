package cz.itnetwork.dialogs;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnNoButtonAlert;
    Button btnCancelButtonAlert;
    Button btnYesNoButtonsAlert;
    Button btnSingleChoiceListAlert;
    Button btnMultiChoiceListAlert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNoButtonAlert = findViewById(R.id.btnNoButtonAlert);
        btnCancelButtonAlert = findViewById(R.id.btnCancelButtonAlert);
        btnYesNoButtonsAlert = findViewById(R.id.btnYesNoButtonsAlert);
        btnSingleChoiceListAlert = findViewById(R.id.btnSingleChoiceListAlert);
        btnMultiChoiceListAlert = findViewById(R.id.btnMultiChoiceListAlert);

        btnNoButtonAlert.setOnClickListener(this);
        btnCancelButtonAlert.setOnClickListener(this);
        btnYesNoButtonsAlert.setOnClickListener(this);
        btnSingleChoiceListAlert.setOnClickListener(this);
        btnMultiChoiceListAlert.setOnClickListener(this);
    }

    public void showNoButtonAlert() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Titulek");
        alertDialogBuilder.setMessage("Zpráva pro uživatele");
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void showCancelButtonAlert() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Titulek");
        alertDialogBuilder.setMessage("Dialog s jedním tlačítkem");

        alertDialogBuilder.setNeutralButton("Zavřít", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Zavřeno", Toast.LENGTH_LONG).show();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void showYesNoButtonsAlert() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        //alertDialogBuilder.setTitle("Titulek");
        alertDialogBuilder.setMessage("Dialog s tlačítky ANO a NE");

        //Zakázání zavření dialogu tlačítkem ZPĚT
        alertDialogBuilder.setCancelable(false);

        //Vytvoření a nastavení vlastní hlavičky
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.custom_title, null);
        TextView labelTitle = view.findViewById(R.id.labelTitle);
        labelTitle.setText("Vlastní titulek :-)");
        alertDialogBuilder.setCustomTitle(view);

        alertDialogBuilder.setPositiveButton("Ano", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "ANO", Toast.LENGTH_LONG).show();
            }
        });

        alertDialogBuilder.setNegativeButton("Ne", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "NE", Toast.LENGTH_LONG).show();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void showSingleChoiceListAlert() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Vyber den v týdnu");

        // Nesmí být nastaven následující zakomentovaný parametr. Jinak nebude seznam zobrazen
        //alertDialogBuilder.setMessage("Zpráva pro uživatele");

        final List<String> daysInWeek = new ArrayList<String>();
        daysInWeek.add("Pondělí");
        daysInWeek.add("Úterý");
        daysInWeek.add("Středa");
        daysInWeek.add("Čtvrtek");
        daysInWeek.add("Pátek");
        daysInWeek.add("Sobota");
        daysInWeek.add("Neděle");

        ArrayAdapter arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_single_choice,
                daysInWeek
        );

        alertDialogBuilder.setSingleChoiceItems(
                arrayAdapter,
                -1,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String selectedItem = daysInWeek.get(i);
                        Toast.makeText(MainActivity.this, selectedItem, Toast.LENGTH_LONG).show();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void showMultiChoiceListAlert() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Zvol barvy");

        // Nesmí být nastaven následující zakomentovaný parametr. Jinak nebude seznam zobrazen
        //alertDialogBuilder.setMessage("Zpráva pro uživatele");

        final String[] colors = new String[]{
                "Bílá",
                "Černá",
                "Modrá",
                "Žlutá",
                "Červená",
                "Zelená",
                "Hnědá"
        };

        final boolean[] checkedColors = new boolean[]{
                false,
                false,
                false,
                false,
                false,
                false,
                false
        };

        alertDialogBuilder.setMultiChoiceItems(colors, checkedColors, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                checkedColors[which] = isChecked;
            }
        });

        alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String result = null;

                for (int i = 0; i < checkedColors.length; i ++) {
                    if (checkedColors[i] == true) {
                        if (result == null) {
                            result = "";
                        } else {
                            result += ", ";
                        }

                        result += colors[i];
                    }
                }

                if (result == null) {
                    Toast.makeText(MainActivity.this, "Nebyla vybrána žádná barva...", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, result, Toast.LENGTH_LONG).show();
                }
            }
        });

        alertDialogBuilder.setNeutralButton("Zrušit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Zrušeno", Toast.LENGTH_LONG).show();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnNoButtonAlert:
                showNoButtonAlert();
                break;
            case R.id.btnCancelButtonAlert:
                showCancelButtonAlert();
                break;
            case R.id.btnYesNoButtonsAlert:
                showYesNoButtonsAlert();
                break;
            case R.id.btnSingleChoiceListAlert:
                showSingleChoiceListAlert();
                break;
            case R.id.btnMultiChoiceListAlert:
                showMultiChoiceListAlert();
                break;
        }
    }
}