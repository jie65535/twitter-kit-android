package com.fanhl.twitterdemo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterConfig;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;

public class TwitterDemoActivity extends AppCompatActivity {
    private static final String TAG = "TwitterDemoActivity";

    private TwitterAuthClient client;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_td);

        TwitterConfig config = new TwitterConfig.Builder(this)
                .twitterAuthConfig(new TwitterAuthConfig("eUhTxf2eEsremRu5ub2lF2tZt", "F0fKvrMPCLaMZjtJEmOyM7nwswoab3t619qgGoxj2oxfqubjQA"))
                .build();
        Twitter.initialize(config);

        findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (client == null) {
                    client = new TwitterAuthClient();
                }

                client.authorize(TwitterDemoActivity.this, new Callback<TwitterSession>() {
                    @Override
                    public void success(Result<TwitterSession> result) {
                        TwitterSession data = result.data;
                        long userId = data.getUserId();
                        String userName = data.getUserName();
                        TwitterAuthToken authToken = data.getAuthToken();
                        String msg = "userId: " + userId + ", userName: " + userName + ", authToken:" + authToken.token + " | " + authToken.secret;
                        Log.i(TAG, msg);
                        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();

//                        requestEmailAddress(getApplicationContext(), result.data);
                    }

                    @Override
                    public void failure(TwitterException exception) {
                        // Upon error, show a toast message indicating that authorization request failed.
                        Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        client.onActivityResult(requestCode, resultCode, data);
    }
}
