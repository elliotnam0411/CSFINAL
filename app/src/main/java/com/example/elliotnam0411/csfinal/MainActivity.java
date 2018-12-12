package com.example.elliotnam0411.csfinal;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ProgressBar;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import android.os.AsyncTask;

import java.sql.*;
import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private EditText Name;
    private EditText Password;
    private Button Login;
    private ProgressBar progressBar;
    private static Connection myConn;
    public static String netid;
    String un,pass,db,ip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        Name = (EditText)findViewById(R.id.etName);
        Password = (EditText)findViewById(R.id.etPassword);
        Login = (Button)findViewById(R.id.btnLogin);
        progressBar.setVisibility(View.GONE);
        ip = "61.73.127.241";
        db = "uiuc_delivery";
        un = "root";
        pass = "kenken@14mysql";
        //myConn = connectionClass(un,pass,db,ip);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Validate validate = new Validate();
                validate.execute();
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });
    }
    public class Validate extends AsyncTask<String, String, String> {
        String z = "";
        Boolean isSuccess = false;
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
        }
        protected String doInBackground(String...params) {

            String username = Name.getText().toString();
            String password = Password.getText().toString();
            if (username.trim().equals("") || password.trim().equals("")) {
                z = "Please enter Username and Password";
            } else {
                try {
                    myConn = connectionClass(un,pass,db,ip);
                    if (myConn == null) {
                        z = "Check your Internet Connection";
                    } else {
                        String query = "select from user_table where user_id = '" + user name +"' and user_password = '" + password + "'";
                        Statement stmt = myConn.createStatement();
                        ResultSet rs = stmt.executeQuery(query);
                        if (rs.next()) { //while(rs.next) { }
                            netid = username;
                            z = "Login Successful";
                            isSuccess = true;
                            myConn.close();
                        } else {
                            z = "Invalid Credentials";
                            isSuccess = false;
                        }
                    }
                } catch (Exception e) {
                    isSuccess = false;
                    z = e.getMessage();
                }
            }
            return z;

        }
        protected void onPostExecute(String r) {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(MainActivity.this, r, Toast.LENGTH_LONG).show();
            if (isSuccess) {
                Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_LONG).show();
            }
        }
    }
    /*
    private void validate(String userName, String userPassword) {
        int resultSize = 0;
            String query = "SELECT user_password FROM user_table WHERE user_netid = 'a' AND user_password = 'a'";
            try {
                myConn = DriverManager.getConnection(url, "root", "kenken@14mysql");
                myStmt = myConn.createStatement();
                myRs = myStmt.executeQuery(query);
                while (myRs.next()) {
                    resultSize++;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            if (resultSize==1) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            } else {
                Login.setEnabled(false); //cancelling button
            }
        }
        */

    /*finally {
            try {
                myConn.close();
            } catch (SQLException e) {}
            try {
                myStmt.close();
            } catch (SQLException e) {}
            try {
                myRs.close();
            } catch (SQLException e) {}
        }*/



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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


