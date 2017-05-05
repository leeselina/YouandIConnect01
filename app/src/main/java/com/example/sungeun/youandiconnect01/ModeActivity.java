package com.example.sungeun.youandiconnect01;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.example.sungeun.youandiconnect01.activity.BaseActivity;
import com.example.sungeun.youandiconnect01.model.expert.Expert;
import com.example.sungeun.youandiconnect01.model.expert.ExpertInfo;
import com.example.sungeun.youandiconnect01.model.user.User;
import com.example.sungeun.youandiconnect01.model.user.UserInfo;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;

import java.util.HashMap;


public class ModeActivity extends BaseActivity {

    Boolean isUser = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode);

        final RadioGroup rg = (RadioGroup)findViewById(R.id.radioButtonGroup);
        Button registerBtn = (Button)findViewById(R.id.registerBtn);
        //final FrameLayout fl = (FrameLayout) findViewById(R.id.fl);
        final ConstraintLayout patientlayout = (ConstraintLayout) findViewById(R.id.patientLayout);
        final FrameLayout trainerlayout = (FrameLayout) findViewById(R.id.trainerLayout);

        //구글에 대한 로그인 정보 받아오기. getIntent로 토큰받는다.

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.patientRadio){
                    isUser = true;
                    patientlayout.setVisibility(View.VISIBLE);
                    trainerlayout.setVisibility(View.GONE);

                }else{
                    isUser = false;
                    trainerlayout.setVisibility(View.VISIBLE);
                    patientlayout.setVisibility(View.GONE);
                }
            }
        });


        //등록버튼 눌렀을 때 가입자 모드값, 정보 저장 후 바로 로그인되서 홈화면보이기.

        registerBtn.setOnClickListener(new View.OnClickListener() {

            Intent intent = getIntent();
            String token = intent.getStringExtra("google_token");

            HashMap<String, Object> info;
            String type;

            //환자 가입 정보
            EditText address = (EditText)findViewById(R.id.user_address);
            String str_address = address.getText().toString();

            EditText method = (EditText)findViewById(R.id.user_method);
            String str_method = method.getText().toString();

            EditText date = (EditText)findViewById(R.id.user_date);
            String str_date = date.getText().toString();

            EditText weight = (EditText)findViewById(R.id.user_weight);
            String str_weight = weight.getText().toString();
            Integer int_weight = Integer.parseInt(str_weight);

            EditText recent = (EditText)findViewById(R.id.user_recent);
            String str_recent = recent.getText().toString();
            Integer int_recent = Integer.parseInt(str_recent);

            EditText height = (EditText)findViewById(R.id.user_height);
            String str_height = height.getText().toString();
            Integer int_height = Integer.parseInt(str_height);

            //전문가 가입 정보
            EditText expert_name = (EditText)findViewById(R.id.expert_name);
            String str_expert_name = expert_name.getText().toString();

            EditText expert_resident_number = (EditText)findViewById(R.id.expert_resident_number);
            String str_expert_resident_number = expert_resident_number.getText().toString();
            Long long_expert_resident_number = Long.parseLong(str_expert_resident_number);

            EditText expert_certification_number = (EditText)findViewById(R.id.expert_name);
            String str_expert_certification_number = expert_certification_number.getText().toString();
            Long long_expert_certification_number = Long.parseLong(str_expert_certification_number);

            EditText expert_address = (EditText)findViewById(R.id.expert_address);
            String str_expert_address = expert_address.getText().toString();

            EditText expert_brief_history = (EditText)findViewById(R.id.expert_brief_history);
            String str_expert_brief_history = expert_brief_history.getText().toString();

            EditText expert_description = (EditText)findViewById(R.id.expert_description);
            String str_expert_description = expert_description.getText().toString();

            @Override
            public void onClick(View v) {
                if (isUser) { //환자 가입 정보

                    UserInfo userInfo = new UserInfo(str_date, str_address, str_method, int_weight, int_recent, int_height );

                    info = new User(userInfo, "Name", mFirebaseAuth.getCurrentUser().getUid()).newUser();
                    type = "user";

                } else { //전문가 가입 정보

                    ExpertInfo expertInfo = new ExpertInfo(str_expert_description, str_expert_address, long_expert_certification_number, long_expert_resident_number);

                    info = new Expert(mFirebaseAuth.getCurrentUser().getUid(), "name", "description", expertInfo).newExpert();
                    type = "expert";

                }

                signInFirebase(token, type, info);
            }
        });

    }

    public void signInFirebase(String token, String type,  HashMap<String, Object> info ) {
                AuthCredential credential = GoogleAuthProvider.getCredential(token,null);
                mFirebaseAuth.signInWithCredential(credential);

        DatabaseReference databaseReference = firebaseDatabase.getReference(type);

        databaseReference.updateChildren(info);


    }

}
