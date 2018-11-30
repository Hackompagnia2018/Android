package com.federicobenedetti.res;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.auth0.android.Auth0;
import com.auth0.android.authentication.AuthenticationAPIClient;
import com.auth0.android.authentication.AuthenticationException;
import com.auth0.android.callback.BaseCallback;
import com.auth0.android.provider.AuthCallback;
import com.auth0.android.provider.CustomTabsOptions;
import com.auth0.android.provider.WebAuthProvider;
import com.auth0.android.result.Credentials;
import com.auth0.android.result.UserProfile;


public class LoginActivity extends AppCompatActivity {

    private final String TAG = "LoginActivity";
    private AuthCallback authCallback;
    private Auth0 account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        authCallback = new AuthCallback() {
            @Override
            public void onFailure(@NonNull Dialog dialog) {
                Log.i(TAG, "onFailure");
                finish();
            }

            @Override
            public void onFailure(AuthenticationException exception) {
                Log.i(TAG, "onFailure AuthException");
                finish();
            }

            @Override
            public void onSuccess(@NonNull Credentials credentials) {
                Log.i(TAG, "authCallback credentials AccessToken: " + credentials.getAccessToken());

                AuthenticationAPIClient authentication = new AuthenticationAPIClient(account);

                authentication
                        .userInfo(credentials.getAccessToken())
                        .start(new BaseCallback<UserProfile, AuthenticationException>() {
                            @Override
                            public void onSuccess(UserProfile information) {
                                Log.i(TAG, "onSuccess, EMAIL: " + information.getEmail());
                                Log.i(TAG, "onSuccess, NICKNAME: " + information.getNickname());
                                Log.i(TAG, "onSuccess, NAME: " + information.getName());
                                Log.i(TAG, "onSuccess, PictureURL: " + information.getPictureURL());

                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                intent.putExtra("name", information.getName());
                                intent.putExtra("email", information.getEmail());
                                intent.putExtra("pURL", information.getPictureURL());
                                startActivity(intent);
                                finish();
                            }

                            @Override
                            public void onFailure(AuthenticationException error) {
                                finish();
                            }
                        });
            }
        };

        this.account = new Auth0(getApplicationContext());
        this.account.setOIDCConformant(true);

        CustomTabsOptions options = CustomTabsOptions.newBuilder()
                .withToolbarColor(R.color.colorPrimary)
                .showTitle(true)
                .build();

        WebAuthProvider.init(account)
                .withAudience("https://" + getString(R.string.com_auth0_domain) + "/userinfo")
                .withScope("openid profile")
                .withCustomTabsOptions(options)
                .start(this, authCallback);
    }

    @Override
    public void onBackPressed() {
        finish();
    }


}
