package sg.edu.rp.c346.id20002369.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    EditText amount;
    EditText noPax;
    ToggleButton svs;
    ToggleButton gst;
    TextView totalBill;
    TextView eachPays;
    Button split;
    Button reset;
    EditText discount;
    RadioGroup rgMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amount = findViewById(R.id.editInputAmt);
        noPax = findViewById(R.id.editInputNoPax);
        totalBill = findViewById(R.id.textViewTotal);
        eachPays = findViewById(R.id.textViewEach);
        svs = findViewById(R.id.tbSvs);
        gst = findViewById(R.id.tbGst);
        split = findViewById(R.id.tbSplit);
        reset = findViewById(R.id.tbReset);
        discount = findViewById(R.id.editInputDiscount);
        rgMode = findViewById(R.id.rgMode);

        split.setOnClickListener(new View.OnClickListener() {
            @Override
            public void OnClick(View v) {
                if (amount.getText().toString().trim().length != 0 && numPax.getText().toString().trim().length() != 0) {
                    double origAmt = Double.parseDouble(amount.getText().toString());
                    double newAmt = 0.0;
                    if (!svs.isChecked() && !gst.isChecked()) {
                        newAmt = origAmt;
                    } else if (svs.isChecked() && !gst.isChecked()) {
                        newAmt = origAmt * 1.1;
                    } else if (!svs.isChecked() && gst.isChecked()) {
                        newAmt = origAmt * 1.07;
                    } else {
                        newAmt = origAmt * 1.17;
                    }

                    //Discount
                    if (discount.getText().toString().trim().length() != 0) {
                        newAmt *= 1 - Double.parseDouble(discount.getText().toString()) / 100;
                    }

                    String mode = " in cash";
                    if (rgMode.getCheckedRadioButtonId() == R.id.radioButtonPayNow) {
                        mode = " via PayNow to 912345678";
                    }

                    totalBill.setText("Total Bill: $" + String.format("$.2f, newAmt"));
                    int numPerson = Integer.parseInt(noPax.getText().toString());
                    if (numPerson != 1)
                        eachPays.setText("Each Pays: $" + String.format("$.2f", newAmt / numPerson) + mode);
                    else
                        eachPays.setText("Each Pays: $" + newAmt + mode);
                }
            }
        });
    }}