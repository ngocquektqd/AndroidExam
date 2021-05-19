package com.example.lengocque;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView tvTitle;
    EditText edFullname, edEmail, edDescribe;
    Spinner spinner;
    CheckBox checkBox;
    Button btFeedback;
    AppDatabase userDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTitle = findViewById(R.id.tvTitle);
        edFullname = findViewById(R.id.edFullname);
        edEmail = findViewById(R.id.edEmail);
        edDescribe = findViewById(R.id.edDescribe);
        spinner = findViewById(R.id.spinner);
        checkBox = findViewById(R.id.checkBox);
        btFeedback = findViewById(R.id.btFeedback);
        userDatabase = AppDatabase.getAppDatabase(this);

        String[] gripe= {"Gripe","Report","Unknow"};
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,gripe);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edFullname.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this,"Please enter your full name",Toast.LENGTH_LONG).show();
                    return;
                }
                if(edEmail.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this,"Please enter your Email",Toast.LENGTH_LONG).show();
                    return;
                }
                if(edDescribe.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this,"Please enter Description",Toast.LENGTH_LONG).show();
                    return;
                }
//                if(!checkBox.isChecked()){
//                    Toast.makeText(MainActivity.this,"Please agree rules",Toast.LENGTH_LONG).show();
//                    return;
//                }

                User user = new User();
                user.setFullname(edFullname.getText().toString());
                user.setEmail(edEmail.getText().toString());
                user.setDescribe(edDescribe.getText().toString());
                userDatabase.userDao().insertUser(user);
//                Toast.makeText(MainActivity.this,userDatabase.userDao().count(),Toast.LENGTH_LONG).show();
                Log.d("TAG", "onClick: " +userDatabase.userDao().count());

                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Co tat ca : " +userDatabase.userDao().count() +" ban ghi");
                AlertDialog dialog=builder.create();
                dialog.show();
            }
        });



    }
}