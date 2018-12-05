package com.example.elliotnam0411.csfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SecondActivity extends AppCompatActivity {

    private EditText RoomNumber;
    private TextView name1;
    private TextView name2;
    private TextView name3;
    private TextView name4;
    private EditText ResidentName;
    private TextView message;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        name1.setText("aa");
        Intent intent = new Intent;
        /*RoomNumber = (EditText)findViewById(R.id.roomNumber);
        name1 = (TextView)findViewById(R.id.n1);
        name2 = (TextView)findViewById(R.id.n2);
        name3 = (TextView)findViewById(R.id.n3);
        name4 = (TextView)findViewById(R.id.n4);
        ResidentName = (EditText)findViewById(R.id.firstName);
        message = (TextView)findViewById(R.id.messageSent);
        residentQuery(RoomNumber.getText().toString());
        sendMessage(ResidentName.getText().toString());*/
    }
    protected void residentQuery(String roomNumber) {
        Intent intent = new Intent(SecondActivity.this, );
        startActivity(intent);
        int roomNum = Integer.parseInt(roomNumber);
        if (roomNum == 713) {
            name1.setText("Hideki Maeda");
            name2.setText("Yinfei Zhu");
            name3.setText("Yudu Chen");
            name4.setText("Elliot Nam");
        }
    }
    protected void sendMessage(String name) {
        if (name.equals("Elliot")) {
            message.setText("Message has been sent to" + name);
        }
    }
}
