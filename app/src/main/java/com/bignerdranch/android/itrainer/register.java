package com.bignerdranch.android.itrainer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bignerdranch.android.itrainer.database.CustomerBaseHelper;

/**
 * Created by mperez5 on 9/27/2016.
 */

public class Register extends AppCompatActivity {

    Button register;
    EditText first;
    EditText last;
    EditText user;
    EditText pass;

    //Create instance of database
    CustomerBaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_user);

        //Initialize myDb
        myDb = new CustomerBaseHelper(this);

        first = (EditText)findViewById(R.id.f_name1);
        last = (EditText)findViewById(R.id.l_name1);
        user = (EditText)findViewById(R.id.userName1);
        pass = (EditText)findViewById(R.id.password1);

        register = (Button)findViewById(R.id.btRegister);
        register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                String f_name = first.getText().toString();
                String l_name = last.getText().toString();
                String user_name = user.getText().toString();
                String password = pass.getText().toString();

                boolean isInserted = myDb.insertUser(f_name, l_name, user_name, password);
                if(isInserted)
                {
                    //Doe nothing
                }
                else  Toast.makeText(Register.this, "FAILED TO ADD USER", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Register.this, LoginActivity.class);
                startActivity(intent);

            }
        });

    }


}
