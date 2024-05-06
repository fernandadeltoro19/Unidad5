package com.example.crudapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        TextView id= (TextView)findViewById(R.id.edittextid);
        TextView name= (TextView)findViewById(R.id.edittextname);
        TextView address= (TextView)findViewById(R.id.edittextaddress);
        Button btninsert=(Button)findViewById(R.id.btnadd);
        Button btnupdate=(Button)findViewById(R.id.btnUpdate);
        Button btndelete=(Button)findViewById(R.id.btndelete);
        Button btnget=(Button)findViewById(R.id.btnget);

        btninsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
              Connection connection=connectionclass();
              try
              {
                if (connection !=null)
                {
                    String sqlinsert="Insert into UserInfo_Tab values ('"+id.getText().toString()+"','"+name.getText().toString()+"','"+address.getText().toString()+"')";
                    Statement st= connection.createStatement();
                    ResultSet rs=st.executeQuery(sqlinsert);
                }

              }
              catch (Exception exception)
              {
                  Log.e("Error", exception.getMessage());
              }
            }
        });

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Connection connection=connectionclass();
                try
                {
                    if (connection !=null)
                    {
                        String sqlinsert= "UPDATE UserInfo_Tab SET Name='" + name.getText().toString() + "', Address='" + address.getText().toString() + "' WHERE ID='" + id.getText().toString() + "'";
                        Statement st= connection.createStatement();
                        ResultSet rs=st.executeQuery(sqlinsert);
                    }

                }
                catch (Exception exception)
                {
                    Log.e("Error", exception.getMessage());
                }
            }

        });

        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Connection connection=connectionclass();
                try
                {
                    if (connection !=null)
                    {
                        String sqldelete= "delete UserInfo_Tab WHERE ID='" + id.getText().toString() + "'";
                        Statement st= connection.createStatement();
                        ResultSet rs=st.executeQuery(sqldelete);
                    }

                }
                catch (Exception exception)
                {
                    Log.e("Error", exception.getMessage());
                }
            }

        });

        btnget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Connection connection=connectionclass();
                try
                {
                    if (connection !=null)
                    {
                        String sqldelete= "select * from UserInfo_Tab WHERE ID='" + id.getText().toString() + "'";
                        Statement st= connection.createStatement();
                        ResultSet rs=st.executeQuery(sqldelete);

                        while (rs.next())
                        {
                            name.setText(rs.getString(2));
                            address.setText(rs.getString(3));

                        }

                    }

                }
                catch (Exception exception)
                {
                    Log.e("Error", exception.getMessage());
                }
            }

        });




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }



    @SuppressLint("NewApi")
    public Connection connectionclass()
    {
        Connection con=null;
        String ip="192.168.1.2", port="50226", username="sa",password="2001", databasename="CRUDAndroidDB";
        StrictMode.ThreadPolicy tp= new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(tp);
        try
        {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            String connectionUrl="jdbc:jtds:sqlserver://" + ip + ":" + port + ";" + "databasename=" + databasename + ";user=" + username + ";password=" + password + ";";
            con= DriverManager.getConnection(connectionUrl);
        }
        catch (Exception exception)
        {
            Log.e("Error",exception.getMessage());
        }
        return con;
    }
}