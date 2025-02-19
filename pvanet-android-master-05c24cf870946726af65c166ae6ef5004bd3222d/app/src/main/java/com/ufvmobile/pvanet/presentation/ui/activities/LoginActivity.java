package com.ufvmobile.pvanet.presentation.ui.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


/**
 * A login screen that offers login via email/password.
 */

import android.util.Log;

import android.content.Intent;
import android.widget.TextView;

import com.ufvmobile.pvanet.BuildConfig;
import com.ufvmobile.pvanet.R;
import com.ufvmobile.pvanet.domain.model.Student;
import com.ufvmobile.pvanet.domain.repository.StudentRepository;
import com.ufvmobile.pvanet.domain.repository.impl.StudentRepositoryImpl;
import com.ufvmobile.pvanet.network.ApiClient;
import com.ufvmobile.pvanet.network.PvanetApiEndPointInterface;
import com.ufvmobile.pvanet.presentation.ui.activities.base.BaseActivity;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;

public class LoginActivity extends BaseActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    @BindView(R.id.input_email) EditText mRegistrationText;
    @BindView(R.id.input_password) EditText mPasswordText;
    @BindView(R.id.btn_login) Button mLoginButton;
    @BindView(R.id.link_signup) TextView mErrorText;

    final String ERROR_NO_CONNECTION = "Sem conexão de rede";
    final String ERROR_INVALID = "Matricula ou senha inválidos";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

      /*  mLoginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });*/

        /*mErrorText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });*/
    }

    @Override
    public void onBackPressed() {

        // disable going back to the MainActivity
        moveTaskToBack(true);
    }

    @OnClick(R.id.btn_login)
    public void onLoginClicked() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed(ERROR_INVALID);
            return;
        }

        mLoginButton.setEnabled(false);
        mErrorText.setVisibility(View.GONE);

       /* final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme_PopupOverlay);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();
        progressDialog.getWindow().setGravity(Gravity.CENTER_VERTICAL);*/

        final String registration = mRegistrationText.getText().toString();
        final String password = mPasswordText.getText().toString();

        PvanetApiEndPointInterface apiService = ApiClient.getClient().
                create(PvanetApiEndPointInterface.class);

        Call<Student> callCourses = apiService.getStudent(registration,
                BuildConfig.PVANET_API_TOKEN,
                password);
        callCourses.enqueue(new Callback<Student>() {
            @Override
            public void onResponse(Call<Student> call, Response<Student> response) {

                if (response.isSuccessful()) {

                    Student student = response.body();
                    student.setMatriculaSapiens(registration);
                    student.setPassword(password);

                    StudentRepository repo = new StudentRepositoryImpl(LoginActivity.this);
                    repo.createStudent(student);

                    Log.d(TAG, "logged successful");

                    // start main activity
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    onLoginSuccess();

                } else {

                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        String error =  jObjError.getString("message");
                        onLoginFailed(error);
                    } catch (Exception e) {
                        onLoginFailed(ERROR_INVALID);
                    }
                }
            }

            @Override
            public void onFailure(Call<Student> call, Throwable t) {

                //TODO: Tratar erro de resposta
                onLoginFailed(ERROR_NO_CONNECTION);

                Log.d(TAG, "Login fail 2");

                Log.e(TAG, t.toString());
            }
        });
    }


    public void onLoginSuccess() {
        mLoginButton.setEnabled(true);
        mErrorText.setVisibility(View.GONE);
        finish();
    }

    public void onLoginFailed(String error) {
        mErrorText.setText(error);
        mErrorText.setVisibility(View.VISIBLE);

        mLoginButton.setEnabled(true);
    }

    private boolean validate() {
        boolean valid = true;

        String email = mRegistrationText.getText().toString();
        String password = mPasswordText.getText().toString();

        if (email.isEmpty() || (email.length() != 5)) {
            mRegistrationText.setError("Entre uma matricula válida");
            valid = false;
        } else {
            mRegistrationText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4) {
            mPasswordText.setError("Senha de no minimo 4 caracteres");
            valid = false;
        } else {
            mPasswordText.setError(null);
        }

        return valid;
    }
}
