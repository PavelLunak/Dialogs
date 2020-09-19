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
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnNoButtonAlert;
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
    Button btnCustomDialog;
    Button btnCustomDialogInfo;
    Button btnCustomDialogWarn;
    Button btnCustomDialogError;
    Button btnCustomDialogYesNo;
    Button btnCustomDialogInput;
    Button btnCustomDialogSelect;
    Button btnCustomDatePicker;
    Button btnCustomTimePicker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNoButtonAlert = findViewById(R.id.btnNoButtonAlert);
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
        btnCustomDialog = findViewById(R.id.btnCustomDialog);
        btnCustomDialogInfo = findViewById(R.id.btnCustomDialogInfo);
        btnCustomDialogWarn = findViewById(R.id.btnCustomDialogWarn);
        btnCustomDialogError = findViewById(R.id.btnCustomDialogError);
        btnCustomDialogYesNo = findViewById(R.id.btnCustomDialogYesNo);
        btnCustomDialogInput = findViewById(R.id.btnCustomDialogInput);
        btnCustomDialogSelect = findViewById(R.id.btnCustomDialogSelect);
        btnCustomDatePicker = findViewById(R.id.btnCustomDatePicker);
        btnCustomTimePicker = findViewById(R.id.btnCustomTimePicker);

        btnNoButtonAlert.setOnClickListener(this);
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
        btnCustomDialog.setOnClickListener(this);
        btnCustomDialogInfo.setOnClickListener(this);
        btnCustomDialogWarn.setOnClickListener(this);
        btnCustomDialogError.setOnClickListener(this);
        btnCustomDialogYesNo.setOnClickListener(this);
        btnCustomDialogInput.setOnClickListener(this);
        btnCustomDialogSelect.setOnClickListener(this);
        btnCustomDatePicker.setOnClickListener(this);
        btnCustomTimePicker.setOnClickListener(this);
    }

    /*
    Zobrazí dialog bez tlačítek.
    */
    public void showNoButtonAlert() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Titulek");
        alertDialogBuilder.setMessage("Tento dialog lze zavřít tlačítkem ZPĚT nebo kliknutí mimo dialog.");
        alertDialogBuilder.setIcon(getResources().getDrawable(R.drawable.ic_store));

        // Reakce na událost zavření dialogu
        alertDialogBuilder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                Toast.makeText(MainActivity.this, "Dialog byl zrušen.", Toast.LENGTH_LONG).show();
            }
        });

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

        alertDialogBuilder.setNeutralButton("Zrušit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Dialog byl zavřen tlačítkem ZRUŠIT.", Toast.LENGTH_LONG).show();
            }
        });

        alertDialogBuilder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                Toast.makeText(MainActivity.this, "Dialog byl zavřen jinak než tlačítkem ZRUŠIT.", Toast.LENGTH_LONG).show();
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
        alertDialogBuilder.setMessage("Dialog s tlačítky ANO, NE a ZRUŠIT. Tento dialog je nastaven tak, že jej lze zavřít pouze některým z tlačítek.");

        //Zakázání zavření dialogu tlačítkem ZPĚT
        alertDialogBuilder.setCancelable(false);

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

        alertDialogBuilder.setNeutralButton("Zrušit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Dialog byl zavřen tlačítkem ZRUŠIT.", Toast.LENGTH_LONG).show();
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
                Toast.makeText(MainActivity.this, selectedAnimal, Toast.LENGTH_LONG).show();
            }
        });

        alertDialogBuilder.setNeutralButton("Zrušit", null);

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
                Toast.makeText(MainActivity.this, adapter.getItem(item), Toast.LENGTH_LONG).show();
            }
        });

        alertDialogBuilder.setNeutralButton("Zrušit", null);

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
                Toast.makeText(MainActivity.this, "Vybranáno:\n" + items.get(which).getName(), Toast.LENGTH_LONG).show();
            }
        });

        alertDialogBuilder.setNeutralButton("Zrušit", null);

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
        alertDialogBuilder.setTitle("Vyber den v týdnu");

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

        final String[] daysInWeek = new String[]{
                "Pondělí",
                "Úterý",
                "Středa",
                "Čtvrtek",
                "Pátek",
                "Sobota",
                "Neděle"
        };

        final String[] selectedItem = {""};

        alertDialogBuilder.setSingleChoiceItems(
                daysInWeek,
                -1,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        selectedItem[0] = daysInWeek[i];
                    }
                });

        alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (selectedItem[0].equals("")) {
                    Toast.makeText(MainActivity.this, "Nebyl vybrán žádný den...", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "Vybráno:\n" + selectedItem[0], Toast.LENGTH_LONG).show();
                }
            }
        });

        alertDialogBuilder.setNeutralButton("Zrušit", null);

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

        alertDialogBuilder.setNeutralButton("Zrušit", null);

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

                Toast.makeText(MainActivity.this, "Name: " + name + ", Password: " + password, Toast.LENGTH_LONG).show();
            }
        });

        alertDialogBuilder.setNeutralButton("Zrušit", null);

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    /*
    Zobrazí defaultní AlertDialog bez hlavičky. Tělo dialogu tvoří vlastní layout.
    */
    public void showAlertInfo(String message) {
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        //final View customLayout = getLayoutInflater().inflate(R.layout.custom_error_alert_layout, null);
        final View customLayout = getLayoutInflater().inflate(R.layout.custom_info_alert_layout, null);
        alertDialogBuilder.setView(customLayout);

        ImageView errorImg = customLayout.findViewById(R.id.errorImg);
        TextView lbaelMessage = customLayout.findViewById(R.id.errorMessage);
        TextView btnClose = customLayout.findViewById(R.id.errorButton);

        final AlertDialog alertDialog = alertDialogBuilder.create();

        // Nastavení průhledného pozadí dialogu.
        // Aby odkryté pozadí kolem zakulacení rohů nebylo bílé
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        lbaelMessage.setText(message);

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        alertDialog.show();

        ObjectAnimator objectAnimatorAlpha = ObjectAnimator.ofFloat(errorImg, View.ALPHA, 0f, 1f);
        objectAnimatorAlpha.setInterpolator(new LinearInterpolator());
        objectAnimatorAlpha.setRepeatMode(ObjectAnimator.RESTART);

        ObjectAnimator objectAnimatorScaleX = ObjectAnimator.ofFloat(errorImg, View.SCALE_X, 0f, 1.15f, 0.9f, 1.1f, 0.95f, 1.05f, 1f);
        objectAnimatorScaleX.setInterpolator(new LinearInterpolator());

        ObjectAnimator objectAnimatorScaleY = ObjectAnimator.ofFloat(errorImg, View.SCALE_Y, 0f, 1.15f, 0.9f, 1.1f, 0.95f, 1.05f, 1f);
        objectAnimatorScaleY.setInterpolator(new LinearInterpolator());

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(400);
        animatorSet.setStartDelay(200);
        animatorSet.playTogether(objectAnimatorScaleX, objectAnimatorScaleY);
        animatorSet.start();
    }

    /*
    Zobrazení vlastního dialogu s vlastním vzhledem.
    */
    public void showCustomDialog() {
        CustomLoginDialog dialog = new CustomLoginDialog(this);

        dialog.setTitle("Přihlášení");
        dialog.setMessage("Pro pokračování je nutné se přihlásit. Zadej své přihlašovací jméno a heslo a potvrď tlačítkem OK.");
        dialog.setIcon(R.drawable.ic_lock);

        // Zde není vyžadována hodnota odkazující do resources. Stačí hodnota typu int
        dialog.setColorHeaderText(Color.WHITE);

        // Následující zakomentovaný řádek je špatně - parametrem musí být hodnota odkazující do resources.
        //dialog.setColorHeaderBackground(Color.BLACK);

        dialog.setColorHeaderBackground(R.color.test_dialog_header_background_color);
        dialog.setColorMessageText(R.color.test_dialog_message_text_color);
        dialog.setColorButtonsText(R.color.test_dialog_buttons_text_color);
        dialog.setColorButtonsBackground(R.color.test_dialog_buttons_background_color);

        dialog.setListener(new CustomLoginDialog.OnLoginListener() {
            @Override
            public void onLogin(String name, String password) {
                Toast.makeText(MainActivity.this, "Name: " + name + ", Password: " + password, Toast.LENGTH_LONG).show();
            }
        });

        dialog.show();
    }

    public void showCustomDialogInfo() {
        CustomDialog dialog = new CustomDialog(this, CustomDialog.DialogType.INFO);
        dialog.setTitle("Info");
        dialog.setMessage("Toto je vlastní dialog s informační zprávou.");
        dialog.show();
    }

    public void showCustomDialogWarn() {
        CustomDialog dialog = new CustomDialog(this, CustomDialog.DialogType.WARN);
        dialog.setTitle("Upozornění");
        dialog.setMessage("Toto je vlastní dialog s upozorněním.");
        dialog.show();
    }

    public void showCustomDialogError() {
        CustomDialog dialog = new CustomDialog(this, CustomDialog.DialogType.ERROR);
        dialog.setTitle("Chyba");
        dialog.setMessage("Toto je vlastní chybový dialog.");
        dialog.show();
    }

    public void showCustomDialogYesNo() {
        CustomDialog dialog = new CustomDialog(this, CustomDialog.DialogType.YES_NO);
        dialog.setTitle("Odhálšení");
        dialog.setMessage("Opravdu se chcete odhlásit?");
        dialog.setListener(new CustomDialog.OnCustomDialogButtonClickListener() {
            @Override
            public void onCustomDialogOkClicked() {

            }

            @Override
            public void onCustomDialogYesClicked() {
                Toast.makeText(MainActivity.this, "Stisknuto ANO", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCustomDialogNoClicked() {
                Toast.makeText(MainActivity.this, "Stisknuto NE", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCustomDialogInputInserted(String inputText) {

            }

            @Override
            public void onCustomDialogItemSelected(String inputText, int position) {

            }
        });
        dialog.show();
    }

    public void showCustomDialogInput() {
        CustomDialog dialog = new CustomDialog(this, CustomDialog.DialogType.INPUT);
        dialog.setTitle("Input");
        dialog.setMessage("Zadej nějaký text:");
        dialog.setListener(new CustomDialog.OnCustomDialogButtonClickListener() {
            @Override
            public void onCustomDialogOkClicked() {

            }

            @Override
            public void onCustomDialogYesClicked() {

            }

            @Override
            public void onCustomDialogNoClicked() {

            }

            @Override
            public void onCustomDialogInputInserted(String inputText) {
                Toast.makeText(MainActivity.this, "Zadaný text: " + inputText, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCustomDialogItemSelected(String inputText, int position) {

            }
        });
        dialog.show();
    }

    public void showCustomDialogSelect() {
        CustomDialog dialog = new CustomDialog(this, CustomDialog.DialogType.SELECT);
        dialog.setTitle("Výběr");
        dialog.setMessage("Vyber položku:");

        String[] items = new String[] {
                "První položka",
                "Druhá položka",
                "Třetí položka",
                "Čtvrtá položka",
                "Pátá položka",
                "Šestá položka",
                "Sedmá položka",
                "Osmá položka",
                "Devátá položka",
                "Desátá položka",
                "Jedenáctá položka",
                "Dvanáctá položka",
                "Třináctá položka",
                "Čtrnáctá položka",
                "Patnáctá položka"};

        dialog.setItems(items);

        dialog.setListener(new CustomDialog.OnCustomDialogButtonClickListener() {
            @Override
            public void onCustomDialogOkClicked() {

            }

            @Override
            public void onCustomDialogYesClicked() {

            }

            @Override
            public void onCustomDialogNoClicked() {

            }

            @Override
            public void onCustomDialogInputInserted(String inputText) {

            }

            @Override
            public void onCustomDialogItemSelected(String selectedItem, int position) {
                Toast.makeText(MainActivity.this, "Zvoleno: " + selectedItem + ", Pozice v poli: " + position, Toast.LENGTH_LONG).show();
            }
        });
        dialog.show();
    }

    /*
    Zobrazení vlastního dialogu s nastavením data.
    */
    public void showCustomDatePicker() {
        CustomDatePicker datePicker = new CustomDatePicker(this, new Date());

        datePicker.setListener(new CustomDatePicker.OnDateSelectedListener() {
            @Override
            public void onDateSelected(Date date) {
                SimpleDateFormat sdf = new SimpleDateFormat("d.MMMM y");
                String dateToString = sdf.format(date);
                Toast.makeText(MainActivity.this, dateToString, Toast.LENGTH_LONG).show();
            }
        });

        datePicker.show();
    }

    /*
    Zobrazení vlastního dialogu s nastavením času.
    */
    public void showCustomTimePicker() {
        CustomTimePicker timePicker = new CustomTimePicker(this, new Date());

        timePicker.setListener(new CustomTimePicker.OnTimeSelectedListener() {
            @Override
            public void onTimeSelected(Date time) {
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                String dateToString = sdf.format(time);
                Toast.makeText(MainActivity.this, dateToString, Toast.LENGTH_LONG).show();
            }
        });

        timePicker.show();
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
                showAlertInfo("Všechno se podařilo :-)");
                break;
            case R.id.btnCustomDialog:
                showCustomDialog();
                break;
            case R.id.btnCustomDialogInfo:
                showCustomDialogInfo();
                break;
            case R.id.btnCustomDialogWarn:
                showCustomDialogWarn();
                break;
            case R.id.btnCustomDialogError:
                showCustomDialogError();
                break;
            case R.id.btnCustomDialogYesNo:
                showCustomDialogYesNo();
                break;
            case R.id.btnCustomDialogInput:
                showCustomDialogInput();
                break;
            case R.id.btnCustomDialogSelect:
                showCustomDialogSelect();
                break;
            case R.id.btnCustomDatePicker:
                showCustomDatePicker();
                break;
            case R.id.btnCustomTimePicker:
                showCustomTimePicker();
                break;
        }
    }
}