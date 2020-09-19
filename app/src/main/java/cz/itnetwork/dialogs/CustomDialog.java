package cz.itnetwork.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class CustomDialog extends Dialog {

    public enum DialogType {INFO, WARN, ERROR, YES_NO, INPUT, SELECT}

    TextView labelTitle;
    TextView labelMessage;
    EditText etInput;
    ListView listView;

    TextView btnLeft;
    TextView btnRight;

    int colorInfo = 0xFF0098D6;     // Modrá
    int colorWarn = 0xFFD67A00;     // Oranžová
    int colorError = 0xFFD60000;    // Červená

    String title;
    String message;
    String[] items = new String[] {"no items!"};

    // Defaultní typ dialogu
    DialogType dialogType = DialogType.INFO;

    // Defaultní barva dialogu
    int color = colorInfo;

    OnCustomDialogButtonClickListener listener;


    public CustomDialog(Context context, DialogType type) {
        super(context);
        this.dialogType = type;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog);

        labelTitle = findViewById(R.id.labelTitle);
        labelMessage = findViewById(R.id.labelMessage);
        btnLeft = findViewById(R.id.btnLeft);
        btnRight = findViewById(R.id.btnRight);
        etInput = findViewById(R.id.etInput);
        listView = findViewById(R.id.listView);

        setDialogColor();
        labelTitle.setText(title);
        labelMessage.setText(message);

        if (dialogType == DialogType.INFO || dialogType == DialogType.WARN || dialogType == DialogType.ERROR) {
            btnLeft.setVisibility(View.GONE);
            btnRight.setText("OK");
            etInput.setVisibility(View.GONE);
            listView.setVisibility(View.GONE);
        } else if (dialogType == DialogType.YES_NO) {
            btnLeft.setVisibility(View.VISIBLE);
            btnRight.setVisibility(View.VISIBLE);
            btnLeft.setText("Ne");
            btnRight.setText("Ano");
            etInput.setVisibility(View.GONE);
            listView.setVisibility(View.GONE);
        } else if (dialogType == DialogType.INPUT) {
            btnLeft.setVisibility(View.GONE);
            btnRight.setText("OK");
            etInput.setVisibility(View.VISIBLE);
            listView.setVisibility(View.GONE);
        } else if (dialogType == DialogType.SELECT) {
            btnLeft.setVisibility(View.GONE);
            btnRight.setVisibility(View.GONE);
            etInput.setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);
            labelMessage.setVisibility(View.GONE);

            ArrayAdapter arrayAdapter = new ArrayAdapter<String>(
                    getContext(),
                    android.R.layout.simple_list_item_1
            );

            for (int i = 0; i < items.length; i ++) {
                arrayAdapter.add(items[i]);
            }

            listView.setAdapter(arrayAdapter);

            /*
            listView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (listener != null) {
                        listener.onCustomDialogItemSelected(((TextView) view).getText().toString(), position);
                    }

                    dismiss();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            */

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if (listener != null) {
                        listener.onCustomDialogItemSelected(((TextView) view).getText().toString(), position);
                    }

                    dismiss();
                }
            });
        }

        btnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    if (dialogType == DialogType.YES_NO) {
                        listener.onCustomDialogNoClicked();
                    }
                }

                dismiss();
            }
        });

        btnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    if (dialogType == DialogType.INFO || dialogType == DialogType.WARN || dialogType == DialogType.ERROR) {
                        listener.onCustomDialogOkClicked();
                    } else if (dialogType == DialogType.YES_NO) {
                        listener.onCustomDialogYesClicked();
                    } else if (dialogType == DialogType.INPUT) {
                        listener.onCustomDialogInputInserted(etInput.getText().toString());
                    }
                }

                dismiss();
            }
        });
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setItems(String[] items) {
        this.items = items;
    }

    private void setDialogColor() {
        switch (dialogType) {
            case YES_NO:
            case INPUT:
            case INFO:
                color = colorInfo;
                break;
            case WARN:
                color = colorWarn;
                break;
            case ERROR:
                color = colorError;
                break;
        }

        labelTitle.setBackgroundColor(color);
    }

    public void setListener(OnCustomDialogButtonClickListener listener) {
        this.listener = listener;
    }

    public interface OnCustomDialogButtonClickListener {
        void onCustomDialogOkClicked();
        void onCustomDialogYesClicked();
        void onCustomDialogNoClicked();
        void onCustomDialogInputInserted(String inputText);
        void onCustomDialogItemSelected(String selectedItem, int position);
    }
}
