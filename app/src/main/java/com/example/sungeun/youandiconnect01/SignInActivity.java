package com.example.sungeun.youandiconnect01;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.sungeun.youandiconnect01.activity.BaseActivity;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignInActivity extends BaseActivity implements GoogleApiClient.OnConnectionFailedListener {



    FirebaseAuth.AuthStateListener mFirebaseAuthListener;
    FirebaseUser mFirebaseUser;

    SignInButton mSigninGoogleButton;
    GoogleApiClient mGoogleApiClient;

    LoginButton mSigninFacebookButton;
    CallbackManager mFacebookCallbackManager;

    static final String TAG = SignInActivity.class.getName();
    static final int RC_GOOGLE_SIGN_IN = 9001;

    boolean isDone = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        setTitle("Login");

        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        if (mFirebaseUser == null){
            Toast.makeText(this, "로그인이 필요합니다", Toast.LENGTH_SHORT).show();
        }

        mSigninGoogleButton = (SignInButton) findViewById(R.id.sign_in_google_button);
        mSigninGoogleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                startActivityForResult(signInIntent, RC_GOOGLE_SIGN_IN);
            }
        });

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();




         /*
         Facebook
         Login
         */

        mFacebookCallbackManager = CallbackManager.Factory.create();
        mSigninFacebookButton = (LoginButton) findViewById(R.id.sign_in_facebook_button);
        mSigninFacebookButton.setReadPermissions("email", "public_profile");
        mSigninFacebookButton.registerCallback(mFacebookCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                AuthCredential credential = FacebookAuthProvider.getCredential(loginResult.getAccessToken().getToken());
                mFirebaseAuth.signInWithCredential(credential).addOnCompleteListener(SignInActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithCredential:onComplete:" + task.isSuccessful());
                    }
                });;

            }

            @Override
            public void onCancel() {
                Log.d(TAG, "Facebook login canceled.");
            }

            @Override
            public void onError(FacebookException error) {
                Log.d(TAG, "Facebook Login Error", error);
            }
        });


        mFirebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if ( user != null && !isDone) {
                    isDone = true;
                    Log.d(TAG, "sign in");
                    finish();
                    startActivity(new Intent(SignInActivity.this, MainActivity.class));
                }

                else {
                    Log.d(TAG, "sign out");
                }

            }
        };
    }


    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mFirebaseAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if ( mFirebaseAuthListener != null )
            mFirebaseAuth.removeAuthStateListener(mFirebaseAuthListener);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //페이스북 로그인 인증 매니저
        mFacebookCallbackManager.onActivityResult(requestCode, resultCode, data);


        //구글 로그인 인증 매니저
        if ( requestCode == RC_GOOGLE_SIGN_IN ) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if ( result.isSuccess() ) {
                String token = result.getSignInAccount().getIdToken();

                Intent intent = new Intent(this, ModeActivity.class);

                intent.putExtra("google_token", token);

                startActivity(intent);
            }
            else {
                Log.d(TAG, "Google Login Failed." + result.getStatus());
            }
        }
    }

}
