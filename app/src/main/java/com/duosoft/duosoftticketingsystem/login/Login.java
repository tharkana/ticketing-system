package com.duosoft.duosoftticketingsystem.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.duosoft.duosoftticketingsystem.MainActivity;
import com.duosoft.duosoftticketingsystem.R;
import com.duosoft.duosoftticketingsystem.rest_api.ApiClient;
import com.duosoft.duosoftticketingsystem.rest_api.ApiInterface;
import com.duosoft.duosoftticketingsystem.rest_api.SessionManager;
import com.duosoft.duosoftticketingsystem.rest_api.pojo.UserAuth;
import com.duosoft.duosoftticketingsystem.rest_api.pojo.UserAuthResponse;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Login extends AppCompatActivity implements Validator.ValidationListener {

    private static final String TAG = "Login";

    @NotEmpty( sequence = 1, message = "Please enter your email address.")
    @Email(sequence = 2 )
    @Bind(R.id.input_email) EditText _emailText;

    @NotEmpty(sequence = 1, message = "Please enter your password")
    @Password(sequence = 2, min=5, scheme = Password.Scheme.ALPHA_NUMERIC_MIXED_CASE)
    @Bind(R.id.input_password) EditText _passwordText;

    @Bind(R.id.btn_login) Button _loginButton;

    private Validator validator;
    private ApiInterface apiInterface;
    private SessionManager sessionManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        //TODO: Remove this For testing purposes
//        _emailText.setText("kasun.g@duosoftware.com");
//        _passwordText.setText("ADTest123!");

        sessionManager = new SessionManager(getApplicationContext());
        validator = new Validator(this);
        validator.setValidationListener(this);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                validator.validate();
            }
        });

    }

    @Override
    public void onBackPressed() {
        // Disable going back to the MainActivity
        moveTaskToBack(true);
    }

    @Override
    public void onValidationSucceeded() {
        _loginButton.setEnabled(false);
        final ProgressDialog progressDialog = new ProgressDialog(Login.this,
               R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        ArrayList scope = new ArrayList();
        scope.add("all_all");

        UserAuth user = new UserAuth(email, password, scope, "AGENT_CONSOLE", "e8ea7bb0-5026-11e7-a69b-b153a7c332b9" );
        Call<UserAuthResponse> call1 = apiInterface.authenticate(user);
        call1.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                if (response.isSuccessful() && response.body() instanceof UserAuthResponse ) {
                    UserAuthResponse userAuthResponse = (UserAuthResponse) response.body();

                    progressDialog.dismiss();
                    Log.d(TAG, userAuthResponse.getToken() );
                    sessionManager.createLoginSession("", userAuthResponse.getToken() );
                    onLoginSuccess();
                }else{
                    String message = "Invalid email and/or password";
                    try {
                        Log.d(TAG, response.errorBody().string() );

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    progressDialog.dismiss();
                    onLoginFailed(message);
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                call.cancel();
            }
        });

    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);

            // Display error messages ;)
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {

            }
        }
    }

    public void onLoginSuccess() {
        _loginButton.setEnabled(true);
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
        finish();
    }

    public void onLoginFailed(String message) {
        Toast.makeText(getBaseContext(), message, Toast.LENGTH_LONG).show();

        _loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }
}
