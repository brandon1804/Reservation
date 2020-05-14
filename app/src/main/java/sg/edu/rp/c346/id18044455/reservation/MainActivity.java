package sg.edu.rp.c346.id18044455.reservation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Time;

public class MainActivity extends AppCompatActivity {
    TextView lR;
    TextView fN;
    EditText fullN;
    TextView pN;
    EditText fullP;
    TextView gS;
    EditText fullGS;
    TextView fullD;
    DatePicker dP;
    TextView fullT;
    TimePicker tP;
    TextView cSA;
    CheckBox sA;
    CheckBox nSA;
    Button rE;
    Button rES;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lR = findViewById(R.id.lR);
        fN = findViewById(R.id.FN);
        fullN = findViewById(R.id.editFN);
        pN = findViewById(R.id.PN);
        fullP = findViewById(R.id.editP);
        gS = findViewById(R.id.GS);
        fullGS = findViewById(R.id.editGS);
        fullD  = findViewById(R.id.sD);
        dP = findViewById(R.id.datePicker);
        fullT = findViewById(R.id.sT);
        tP  = findViewById(R.id.timePicker);
        cSA  = findViewById(R.id.sTA);
        sA  = findViewById(R.id.checkBoxSA);
        nSA  = findViewById(R.id.checkBoxNSA);
        rE  = findViewById(R.id.btnReserve);
        rES  = findViewById(R.id.btnReset);
        dP.setMinDate(System.currentTimeMillis() + 86400000);

        tP.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                if(hourOfDay <= 8 || hourOfDay >= 20) {
                    tP.setCurrentHour(20);
                }

            }
        });

        rE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tA = "";
                if(sA.isChecked() == true && nSA.isChecked() == true){
                    tA = "Smoking and Non-Smoking Area Selected";
                }
                else if(sA.isChecked() == true && nSA.isChecked() != true){
                    tA = "Smoking Area Selected";
                }
                else if(sA.isChecked() != true && nSA.isChecked() == true){
                    tA = "Non-Smoking Area Selected";
                }
                String name = String.format("Full Name: %s",fullN.getText().toString());
                String phoneN = String.format("Phone Number: %s", fullP.getText().toString());
                String groupS = String.format("Group Size: %s",fullGS.getText().toString());
                String date = String.format(("Date: %d/%d/%d"), dP.getDayOfMonth(), dP.getMonth() + 1, dP.getYear());
                String time = String.format(("Time: %d:%02d"), tP.getCurrentHour(),tP.getCurrentMinute());

                final String confirmation = String.format("%s\n%s\n%s\n%s\n%s\n%s", name, phoneN, groupS, tA, date, time);

                if(fullN.getText().toString().trim().length() == 0 || fullP.getText().toString().trim().length() == 0 || fullGS.getText().toString().trim().length() == 0 || sA.isChecked() == false && nSA.isChecked() == false){
                    Toast.makeText(MainActivity.this, "Please ensure no fields are empty", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this, confirmation, Toast.LENGTH_SHORT).show();
                }
            }
        });

        rES.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fullN.getText().clear();
                fullN.setHint("Enter your name");
                fullP.getText().clear();
                fullP.setHint("Enter your phone number");
                fullGS.getText().clear();
                fullGS.setHint("Enter your group size");
                sA.setChecked(false);
                nSA.setChecked(false);
                tP.setCurrentHour(19);
                tP.setCurrentMinute(30);
                dP.updateDate(2020,5,1);
            }
        });


    }//end of method
}//end of class
