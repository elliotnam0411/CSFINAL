package com.example.elliotnam0411.csfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.os.AsyncTask;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import android.os.StrictMode;
import org.w3c.dom.Text;
//package com.example.rzleb.adminloginpage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.Toast;
import android.widget.ProgressBar;

public class SecondActivity extends AppCompatActivity {
    static ResultSet rs;
    static PreparedStatement st;
    static Connection con;
    String str = "new";
    String un,pass,db,ip;
    Statement stmt = null;
    final int dorm_id = 1;
    Button button1, button2, button3, button4, button5;
    TextView date1,date2,date3,date4,date5;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        final Button button1 = (Button) findViewById(R.id.firstbutton);
        button1.setEnabled(false);
        final Button button2 = (Button) findViewById(R.id.secondbutton);
        button2.setEnabled(false);
        final Button button3 = (Button) findViewById(R.id.thirdbutton);
        button3.setEnabled(false);
        final Button button4 = (Button) findViewById(R.id.fourthbutton);
        button4.setEnabled(false);
        final Button button5 = (Button) findViewById(R.id.fifthbutton);
        button5.setEnabled(false);
        final TextView date1 = (TextView) findViewById(R.id.firstdate);
        final TextView date2 = (TextView) findViewById(R.id.seconddate);
        final TextView date3 = (TextView) findViewById(R.id.thirddate);
        final TextView date4 = (TextView) findViewById(R.id.fourthdate);
        final TextView date5 = (TextView) findViewById(R.id.fifthdate);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        ip = "61.73.127.241";
        db = "uiuc_package";
        un = "root";
        pass = "kenken@14mysql";
        Connection connection = null;
        String ConnectionURL = null;

        RetrievePackage retrievePackage = new RetrievePackage();
        retrievePackage.execute();

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button1.setEnabled(false);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button2.setEnabled(false);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button3.setEnabled(false);
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button4.setEnabled(false);
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button5.setEnabled(false);
            }
        });


    }
    public class RetrievePackage extends AsyncTask<String, String, String> {
        String z = "";
        Boolean isSuccess = false;
        String ConnectionURL;
        Connection connection;
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
        }
        protected String doInBackground(String...params) {
            ArrayList result = new ArrayList();
            try {
                Class.forName("com.mysql.jdbc.Driver");

                //jdbc:mysql://127.0.0.1:3306/expeditor?zeroDateTimeBehavior=convertToNull&user=root&password=onelife
                ConnectionURL = "jdbc:mysql://" + ip +":3306/" + db +"?zeroDateTimeBehavior=convertToNull";
                connection = DriverManager.getConnection(ConnectionURL,un,pass);

                Class.forName("com.mysql.jdbc.Driver");
                Class.forName("net.sourceforge.jtds.jdbc.Driver");
                ConnectionURL = "jdbc:mysql://" + ip + "/" + db +"?useUnicode=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
                connection = DriverManager.getConnection(ConnectionURL,un,pass);


                //  이곳이 배달온 package list 넘기는 부분.
                st=connection.prepareStatement("select package_no,package_delivery_date from package_table where package_user_netid = " + MainActivity.netid + " and package_delivery_status = 0");
                rs=st.executeQuery();

                int count = 0;
                while (count < 5 && rs.next()) {
                    if (count == 0) {
                        date1.setText(rs.getTimestamp("package_delivery_date").toString());
                        button1.setEnabled(true);
                    }
                    if (count == 1 && rs.next()) {
                        date2.setText(rs.getTimestamp("package_delivery_date").toString());
                        button2.setEnabled(true);
                    }
                    if (count == 2 && rs.next()) {
                        date3.setText(rs.getTimestamp("package_delivery_date").toString());
                        button3.setEnabled(true);
                    }
                    if (count == 3 && rs.next()) {
                        date4.setText(rs.getTimestamp("package_delivery_date").toString());
                        button4.setEnabled(true);
                    }
                    if (count == 4 && rs.next()) {
                        date5.setText(rs.getTimestamp("package_delivery_date").toString());
                        button5.setEnabled(true);
                    }
                    count++;
                }


                /*** test part
                 java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
                 java.util.Date d = sdf.parse("11/12/2018");
                 Package tt = new Package(1,d);
                 result.add(tt);
                 ****/



                ////******************  여기까지
            }
            catch(Exception e) {
                    e.printStackTrace();
                //tv.setText(str);
            }
            z = "";
            return z;
        }
        protected void onPostExecute(String r) {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(SecondActivity.this, r, Toast.LENGTH_LONG).show();

        }
    }
    public Connection connectionClass(String user, String password, String database, String server) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection = null;
        String ConnectionURL = null;
        try {
            //Class.forName("net.sourceforge.jtds.jdbc.Driver");
            Class.forName("com.mysql.jdbc.Driver");
            ConnectionURL = "jdbc:mysql://" + server + ":3306/" + database;
            connection = DriverManager.getConnection(ConnectionURL,user,password);
        }
        catch (ClassNotFoundException e) {
            Log.e("error here 1 :", e.getMessage());
        }
        catch (SQLException e) {
            Log.e("error 2: ", e.getMessage());
        }
        catch (Exception e) {
            Log.e("error 3: ", e.getMessage());
        }
        return connection;
    }
}


/*
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
        RoomNumber = (EditText)findViewById(R.id.roomNumber);
        name1 = (TextView)findViewById(R.id.n1);
        name2 = (TextView)findViewById(R.id.n2);
        name3 = (TextView)findViewById(R.id.n3);
        name4 = (TextView)findViewById(R.id.n4);
        ResidentName = (EditText)findViewById(R.id.firstName);
        message = (TextView)findViewById(R.id.messageSent);
        residentQuery(RoomNumber.getText().toString());
        sendMessage(ResidentName.getText().toString());
    }
    protected void residentQuery(String roomNumber) {
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
*/
