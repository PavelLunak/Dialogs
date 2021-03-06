package cz.itnetwork.dialogs;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ScrollView contextView;

    Button btnOnlyMessageAlert;
    Button btnNoButtonAlert;
    Button btnAlertWithIcon;
    Button btnCancelButtonAlert;
    Button btnYesNoButtonsAlert;
    Button btnListAlert;
    Button btnListAlertWithAdapter;
    Button btnListAlertWithCustomAdapter;
    Button btnSingleChoiceListAlert;
    Button btnMultiChoiceListAlert;
    Button btnAlertWithCustomHeader;
    Button btnAlertWithCustomLayout;
    Button btnAlertError;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnOnlyMessageAlert = findViewById(R.id.btnOnlyMessageAlert);
        btnNoButtonAlert = findViewById(R.id.btnNoButtonAlert);
        btnAlertWithIcon = findViewById(R.id.btnAlertWithIcon);
        btnCancelButtonAlert = findViewById(R.id.btnCancelButtonAlert);
        btnYesNoButtonsAlert = findViewById(R.id.btnYesNoButtonsAlert);
        btnListAlert = findViewById(R.id.btnListAlert);
        btnListAlertWithAdapter = findViewById(R.id.btnListAlertWithAdapter);
        btnListAlertWithCustomAdapter = findViewById(R.id.btnListAlertWithCustomAdapter);
        btnSingleChoiceListAlert = findViewById(R.id.btnSingleChoiceListAlert);
        btnMultiChoiceListAlert = findViewById(R.id.btnMultiChoiceListAlert);
        btnAlertWithCustomHeader = findViewById(R.id.btnAlertWithCustomHeader);
        btnAlertWithCustomLayout = findViewById(R.id.btnAlertWithCustomLayout);
        btnAlertError = findViewById(R.id.btnAlertError);

        btnOnlyMessageAlert.setOnClickListener(this);
        btnNoButtonAlert.setOnClickListener(this);
        btnAlertWithIcon.setOnClickListener(this);
        btnCancelButtonAlert.setOnClickListener(this);
        btnYesNoButtonsAlert.setOnClickListener(this);
        btnListAlert.setOnClickListener(this);
        btnListAlertWithAdapter.setOnClickListener(this);
        btnListAlertWithCustomAdapter.setOnClickListener(this);
        btnSingleChoiceListAlert.setOnClickListener(this);
        btnMultiChoiceListAlert.setOnClickListener(this);
        btnAlertWithCustomHeader.setOnClickListener(this);
        btnAlertWithCustomLayout.setOnClickListener(this);
        btnAlertError.setOnClickListener(this);
    }

    private void showInfo(String text) {
        Snackbar snackbar = Snackbar
                .make(findViewById(android.R.id.content), text, Snackbar.LENGTH_LONG)
                .setTextColor(getResources().getColor(R.color.snackbar_text_color))
                .setAction("Zavřít", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
        snackbar.show();
    }

    /*
    Zobrazí dialog obsahující pouze zprávu - bez titulku a bez tlačítek.
    */
    public void showOnlyMessageAlert() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Titulek");
        alertDialogBuilder.setIcon(getResources().getDrawable(R.drawable.ic_store));
        alertDialogBuilder.setMessage("Text zobrazený v dialogu");

        alertDialogBuilder.setNeutralButton("Zavřít", null);

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
        /*
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Dialog obsahující pouze zprávu. Neobsahuje titulek ani žádná tlačítka.");
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
        */
    }

    /*
    Zobrazí dialog bez tlačítek.
    */
    public void showNoButtonAlert() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Titulek");
        alertDialogBuilder.setMessage("Tento dialog lze zavřít tlačítkem ZPĚT nebo kliknutí mimo dialog.");

        // Reakce na událost zavření dialogu
        alertDialogBuilder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                showInfo("Dialog byl zrušen.");
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void showAlertWithIcon() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Titulek");
        alertDialogBuilder.setMessage("Dialog s hlavičkou obsahující ikonu.");
        alertDialogBuilder.setIcon(getResources().getDrawable(R.drawable.ic_store));
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    /*
    Zobrazí dialog s jedním tlačítkem "ZAVŘÍT".
    */
    public void showCancelButtonAlert() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Titulek");
        alertDialogBuilder.setMessage("Dialog s jedním tlačítkem.");

        alertDialogBuilder.setNeutralButton("Zavřít", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showInfo("Dialog byl zavřen tlačítkem ZAVŘÍT.");
            }
        });

        alertDialogBuilder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                showInfo("Dialog byl zavřen jinak než tlačítkem ZAVŘÍT.");
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    /*
    Zobrazí dialog s vlastní hlavičkou a s tlačítky "ANO" a "Ne".
    */
    public void showYesNoButtonsAlert() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Titulek");
        //alertDialogBuilder.setMessage("Dialog s tlačítky ANO, NE a ZAVŘÍT. Tento dialog je nastaven tak, že jej lze zavřít pouze některým z tlačítek.");
        alertDialogBuilder.setMessage("Máte velký hlad?");

        //Zakázání zavření dialogu tlačítkem ZPĚT
        alertDialogBuilder.setCancelable(false);

        alertDialogBuilder.setPositiveButton("Ano", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showInfo("ANO");
            }
        });

        alertDialogBuilder.setNegativeButton("Ne", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showInfo("NE");
            }
        });

        alertDialogBuilder.setNeutralButton("Zavřít", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showInfo("Dialog byl zavřen tlačítkem ZAVŘÍT.");
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    /*
    Zobrazí dialogové okno se seznamem položek.
    Lze vybrat pouze jednu položku.
    Po výběru položky je dailog automaticky zavřen;
    */
    public void showListAlert() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Vyber zvíře");

        // Nesmí být nastaven následující zakomentovaný parametr. Jinak nebude seznam zobrazen!
        //alertDialogBuilder.setMessage("Zpráva pro uživatele");

        final String[] animals = new String[]{
                "Pes",
                "Kočka",
                "Králík",
                "Slepice",
                "Kráva",
                "Prase",
                "Ovce",
                "Kuna",
                "Jelen",
                "Orel"
        };

        alertDialogBuilder.setItems(animals, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String selectedAnimal = animals[which];
                showInfo(selectedAnimal);
            }
        });

        alertDialogBuilder.setNeutralButton("Zavřít", null);

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    /*
    Zobrazí dialogové okno se seznamem položek.
    Lze vybrat pouze jednu položku.
    Po výběru položky je dailog automaticky zavřen;
    */
    public void showListAlertWithAdapter() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Zvol částku");

        // Nesmí být nastaven následující zakomentovaný parametr. Jinak nebude seznam zobrazen!
        //alertDialogBuilder.setMessage("Zpráva pro uživatele");

        /*
        android.R.layout.select_dialog_singlechoice - položky s RadioButton
        android.R.layout.select_dialog_multichoice - položky s CheckBox
        android.R.layout.select_dialog_item - položky bez CheckBox a bez RadioButton
        */
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item);
        adapter.add("100,- Kč");
        adapter.add("200,- Kč");
        adapter.add("300,- Kč");
        adapter.add("400,- Kč");
        adapter.add("500,- Kč");
        adapter.add("600,- Kč");
        adapter.add("700,- Kč");
        adapter.add("800,- Kč");
        adapter.add("900,- Kč");
        adapter.add("1000,- Kč");

        alertDialogBuilder.setAdapter(adapter, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                showInfo(adapter.getItem(item));
            }
        });

        alertDialogBuilder.setNeutralButton("Zavřít", null);

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    /*
    Zobrazí dialogové okno se seznamem položek.
    Jednotlivé položky mají nastaven náš vlastní vzhled díky našemu vlastnímu adaptéru.
    Lze vybrat pouze jednu položku.
    Po výběru položky je dailog automaticky zavřen;
    */
    public void showListAlertWithCustomAdapter() {
        final ArrayList<ListViewAdapterItem> items = new ArrayList<>();
        items.add(new ListViewAdapterItem("První položka", "Popis první položky", true));
        items.add(new ListViewAdapterItem("Druhá položka", "Popis druhé položky", true));
        items.add(new ListViewAdapterItem("Třetí položka", "Popis třetí položky", false));
        items.add(new ListViewAdapterItem("Čtvrtá položka", "Popis čtvrté položky", true));
        items.add(new ListViewAdapterItem("Pátá položka", "Popis páté položky", false));
        items.add(new ListViewAdapterItem("Šestá položka", "Popis šesté položky", false));
        items.add(new ListViewAdapterItem("Sedmá položka", "Popis sedmé položky", true));

        ListViewAdapter adapter = new ListViewAdapter(this, items);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Vyber položku");

        // Nesmí být nastaven následující zakomentovaný parametr. Jinak nebude seznam zobrazen!
        //alertDialogBuilder.setMessage("Zpráva pro uživatele");

        alertDialogBuilder.setAdapter(adapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showInfo("Vybranáno: " + items.get(which).getName());
            }
        });

        alertDialogBuilder.setNeutralButton("Zavřít", null);

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    /*
    Zobrazí dialogové okno se seznamem položek, kde každá má vlastní RadioButton.
    Lze vybrat pouze jednu položku.
    Po vybrání položky není dialog automaticky zavřen. Výběr musíme potvrdit nějakým definovaným tlačítkem.
    */
    public void showSingleChoiceListAlert() {
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Vyber zvíře");

        // Nesmí být nastaven následující zakomentovaný parametr. Jinak nebude seznam zobrazen
        //alertDialogBuilder.setMessage("Zpráva pro uživatele");

        // TODO - popsat možnost vlastního adaptéru místo pole
        /*
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
        */

        final String[] animals = new String[]{
                "Pes",
                "Kočka",
                "Králík",
                "Slepice",
                "Kráva",
                "Prase",
                "Ovce",
                "Kuna",
                "Jelen",
                "Orel"
        };

        final String[] selectedItem = {""};

        alertDialogBuilder.setSingleChoiceItems(
                animals,
                -1,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        selectedItem[0] = animals[i];
                    }
                });

        alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (selectedItem[0].equals("")) {
                    showInfo("Nebylo vybráno žádné zvíře...");
                } else {
                    showInfo("Vybráno: " + selectedItem[0]);
                }
            }
        });

        alertDialogBuilder.setNeutralButton("Zavřít", null);

        alertDialogBuilder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                if (selectedItem[0].equals("")) {
                    showInfo("Nebylo vybráno žádné zvíře...");
                } else {
                    showInfo("Vybráno: " + selectedItem[0]);
                }
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    /*
    Zobrazí dialogové okno se seznamem položek, kde každá má vlastní CheckBox.
    Lze vybrat více než jednu položku.
    Výběr musíme potvrdit nějakým definovaným tlačítkem.
    */
    public void showMultiChoiceListAlert() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Vyber zvířata");

        // Nesmí být nastaven následující zakomentovaný parametr. Jinak nebude seznam zobrazen
        //alertDialogBuilder.setMessage("Zpráva pro uživatele");

        final String[] animals = new String[]{
                "Pes",
                "Kočka",
                "Králík",
                "Slepice"
        };

        final boolean[] checkedAnimals = new boolean[]{
                false,
                false,
                false,
                false,
                false,
                false,
                false
        };

        alertDialogBuilder.setMultiChoiceItems(
                animals,
                checkedAnimals,
                new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        checkedAnimals[which] = isChecked;
                    }
                });

        alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String result = null;

                for (int i = 0; i < checkedAnimals.length; i++) {
                    if (checkedAnimals[i] == true) {
                        if (result == null) {
                            result = "";
                        } else {
                            result += ", ";
                        }

                        result += animals[i];
                    }
                }

                if (result == null) {
                    showInfo("Nebyla vybráno žádné zvíře...");
                } else {
                    showInfo(result);
                }
            }
        });

        alertDialogBuilder.setNeutralButton("Zavřít", null);

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    /*
    Zobrazení defaultního dialogu s hlavičkou, tvořenou vlastním layoutem.
    */
    public void showAlertWithCustomHeader() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Titulek");
        alertDialogBuilder.setMessage("Dialog s vlastní hlavičkou.");

        //Vytvoření a nastavení vlastní hlavičky
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.custom_title, null);
        TextView labelTitle = view.findViewById(R.id.labelTitle);
        labelTitle.setText("Vlastní titulek");
        alertDialogBuilder.setCustomTitle(view);

        alertDialogBuilder.setNeutralButton("Zavřít", null);

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    /*
    Zobrazení defaultního dialogu, jehož tělo je tvořeno vlastním layoutem.
    */
    public void showAlertWithCustomLayout() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Přihlášení");
        alertDialogBuilder.setMessage("Hlavička dialogu je deafultní, tělo je tvořeno vlastním layoutem.");

        final View customLayout = getLayoutInflater().inflate(R.layout.custom_alert_layout, null);
        alertDialogBuilder.setView(customLayout);

        alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                EditText etName = customLayout.findViewById(R.id.dialogEtName);
                EditText etPassword = customLayout.findViewById(R.id.etPassword);

                String name = etName.getText().toString();
                String password = etPassword.getText().toString();

                showInfo("Name: " + name + "\nPassword: " + password);
            }
        });

        alertDialogBuilder.setNeutralButton("Zavřít", null);

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    /*
    Zobrazí defaultní AlertDialog bez hlavičky. Tělo dialogu tvoří vlastní layout.
    */
    public void showAlertInfo(String title, String message) {
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        final View customLayout = getLayoutInflater().inflate(R.layout.custom_info_alert_layout, null);
        alertDialogBuilder.setView(customLayout);

        ImageView dialogImg = customLayout.findViewById(R.id.dialogImg);
        TextView dialogTitle = customLayout.findViewById(R.id.dialogTitle);
        TextView dialogMessage = customLayout.findViewById(R.id.dialogMessage);
        TextView dialogButton = customLayout.findViewById(R.id.dialogButton);

        final AlertDialog alertDialog = alertDialogBuilder.create();

        // Nastavení průhledného pozadí dialogu.
        // Aby odkryté pozadí kolem zakulacení rohů nebylo bílé
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        dialogTitle.setText(title);
        dialogMessage.setText(message);

        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        alertDialog.show();

        ObjectAnimator objectAnimatorAlpha = ObjectAnimator.ofFloat(dialogImg, View.ALPHA, 0f, 1f);
        objectAnimatorAlpha.setInterpolator(new LinearInterpolator());

        ObjectAnimator objectAnimatorScaleX = ObjectAnimator.ofFloat(dialogImg, View.SCALE_X, 0f, 1.15f, 0.9f, 1.1f, 0.95f, 1.05f, 1f);
        objectAnimatorScaleX.setInterpolator(new LinearInterpolator());

        ObjectAnimator objectAnimatorScaleY = ObjectAnimator.ofFloat(dialogImg, View.SCALE_Y, 0f, 1.15f, 0.9f, 1.1f, 0.95f, 1.05f, 1f);
        objectAnimatorScaleY.setInterpolator(new LinearInterpolator());

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(400);
        animatorSet.setStartDelay(200);
        animatorSet.playTogether(objectAnimatorScaleX, objectAnimatorScaleY);
        animatorSet.start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnOnlyMessageAlert:
                showOnlyMessageAlert();
                break;
            case R.id.btnNoButtonAlert:
                showNoButtonAlert();
                break;
            case R.id.btnAlertWithIcon:
                showAlertWithIcon();
                break;
            case R.id.btnCancelButtonAlert:
                showCancelButtonAlert();
                break;
            case R.id.btnYesNoButtonsAlert:
                showYesNoButtonsAlert();
                break;
            case R.id.btnListAlert:
                showListAlert();
                break;
            case R.id.btnListAlertWithAdapter:
                showListAlertWithAdapter();
                break;
            case R.id.btnListAlertWithCustomAdapter:
                showListAlertWithCustomAdapter();
                break;
            case R.id.btnSingleChoiceListAlert:
                showSingleChoiceListAlert();
                break;
            case R.id.btnMultiChoiceListAlert:
                showMultiChoiceListAlert();
                break;
            case R.id.btnAlertWithCustomHeader:
                showAlertWithCustomHeader();
                break;
            case R.id.btnAlertWithCustomLayout:
                showAlertWithCustomLayout();
                break;
            case R.id.btnAlertError:
                showAlertInfo("Dobrá zpráva!", "Všechno se podařilo :-)");
                break;
        }
    }
}