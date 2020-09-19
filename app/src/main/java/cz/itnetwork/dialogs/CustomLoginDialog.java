package cz.itnetwork.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;


public class CustomLoginDialog extends Dialog {

    RelativeLayout dialogHeader;    // Layout hlavičky

    ImageView dialogIcon;           // Ikona v hlavičce
    TextView dialogTitle;           // TextView v hlavičce
    TextView dialogMessage;         // TextView pro zprávu pro uživatele

    EditText dialogEtName;          // Políčko pro zadání jména
    EditText dialogEtPassword;      // Políčko pro zadání hesla

    TextView dialogBtnCancel;       // Tlačítko pro zavření dialogu
    TextView dialogBtnOk;           // Tlačítko pro potvrzení zadaných údajů

    private String title = null;
    private String message = null;
    private OnLoginListener listener;

    // Barva pozadí hlavičky
    @ColorRes
    private int colorHeaderBackground;

    // Barva textu hlavičky - záměrně není s anotací @ColorRes
    private int colorHeaderText = 0;

    // Barva textu zprávy
    @ColorRes
    private int colorMessageText;

    // Barva textu tlačítek
    @ColorRes
    private int colorButtonsText;

    // Barva pozadí tlačítek
    @ColorRes
    private int colorButtonsBackground;

    @DrawableRes
    private int icon;

    public CustomLoginDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_login_dialog);

        dialogHeader = findViewById(R.id.dialogHeader);

        dialogIcon = findViewById(R.id.dialogIcon);
        dialogTitle = findViewById(R.id.dialogTitle);
        dialogMessage = findViewById(R.id.dialogMessage);

        dialogEtName = findViewById(R.id.dialogEtName);
        dialogEtPassword = findViewById(R.id.dialogEtPassword);

        dialogBtnCancel = findViewById(R.id.dialogBtnCancel);
        dialogBtnOk = findViewById(R.id.dialogBtnOk);

        updateViews();

        dialogBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        dialogBtnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onLogin(dialogEtName.getText().toString(), dialogEtPassword.getText().toString());
                }

                dismiss();
            }
        });
    }

    private void updateViews() {
        // Nastavení ikony
        if (icon != 0) {
            dialogIcon.setVisibility(View.VISIBLE);
            dialogIcon.setImageDrawable(getContext().getResources().getDrawable(icon));
        }

        // Nastavení textu hlavičky ----------------------------------------------------------------
        dialogTitle.setText(title);

        if (colorHeaderText != 0) {
            dialogTitle.setTextColor(colorHeaderText);
        }
        // -----------------------------------------------------------------------------------------

        // Nastavení barvy pozadí hlavičky ---------------------------------------------------------
        if (colorHeaderBackground != 0) {
            dialogHeader.setBackgroundColor(getContext().getResources().getColor(colorHeaderBackground));
        }
        // -----------------------------------------------------------------------------------------


        // Nastavení textu zprávy ------------------------------------------------------------------
        if (message == null) {
            dialogMessage.setVisibility(View.GONE);
        } else {
            dialogMessage.setVisibility(View.VISIBLE);
            dialogMessage.setText(message);

            if (colorMessageText != 0) {
                dialogMessage.setTextColor(getContext().getResources().getColor(colorMessageText));
            }
        }
        // -----------------------------------------------------------------------------------------

        // Nastavení barvy textu tlačítek ----------------------------------------------------------
        if (colorButtonsText != 0) {
            dialogBtnCancel.setTextColor(getContext().getResources().getColor(colorButtonsText));
            dialogBtnOk.setTextColor(getContext().getResources().getColor(colorButtonsText));
        }
        // -----------------------------------------------------------------------------------------

        // Nastavení barvy pozadí tlačítek ---------------------------------------------------------
        if (colorButtonsBackground != 0) {
            dialogBtnCancel.setBackgroundColor(getContext().getResources().getColor(colorButtonsBackground));
            dialogBtnOk.setBackgroundColor(getContext().getResources().getColor(colorButtonsBackground));
        }
        // -----------------------------------------------------------------------------------------
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setColorHeaderBackground(@ColorRes int color) {
        colorHeaderBackground = color;
    }

    public void setColorHeaderText(int color) {
        colorHeaderText = color;
    }

    public void setColorMessageText(@ColorRes int color) {
        colorMessageText = color;
    }

    public void setIcon(@DrawableRes int icon) {
        this.icon = icon;
    }

    public void setColorButtonsText(@ColorRes int color) {
        this.colorButtonsText = color;
    }

    public void setColorButtonsBackground(@ColorRes int color) {
        this.colorButtonsBackground = color;
    }

    public void setListener(OnLoginListener listener) {
        this.listener = listener;
    }

    public interface OnLoginListener {
        void onLogin(String name, String password);
    }
}
