package com.fend.temanmuslim.activity;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.fend.temanmuslim.BuildConfig;
import com.fend.temanmuslim.R;
import com.fend.temanmuslim.model.Users;
import com.fend.temanmuslim.utils.DialogUtils;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import static com.fend.temanmuslim.utils.CekConnections.isConnectingToInternet;

public class SplashScreenActivity extends AppCompatActivity {
    final static String TAG = "SplashScreenActivity";
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        TextView version = findViewById(R.id.versi);
        String vers ="Version "+ BuildConfig.VERSION_NAME;
        version.setText(vers);
        mAuth = FirebaseAuth.getInstance();
        mAuth.signInAnonymously()
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        FirebaseUser user = mAuth.getCurrentUser();
                        assert user != null;
                        startProcess(user.getUid());
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.e(TAG, "signInAnonymously:failure", task.getException());
                    }
                });

    }

    //cek koneksi
    private void startProcess(String uid) {
        if (!isConnectingToInternet(this)) {
            startActivityMainDelay();
        }else {
            getCurrentFirebaseToken(uid);
        }
    }

    //pesan info tidak ada koneksi
    private void popUpConection() {
        Dialog dialog = new DialogUtils(SplashScreenActivity.this)
                .buildDialogWarning(
                        R.string.title_no_internet,
                        R.string.msg_no_internet,
                        R.string.TRY_AGAIN,
                        R.string.CLOSE,
                        R.drawable.ic_no_wifi,
                        new DialogUtils.CallbackDialog() {
                            @Override
                            public void onPositiveClick(Dialog dialog) {
                                dialog.dismiss();
                                retryOpenApplication();
                            }

                            @Override
                            public void onNegativeClick(Dialog dialog) {
                                dialog.dismiss();
                                finish();
                            }
                        });
        dialog.show();
    }

    //mengulang cek koneksi
    private void retryOpenApplication() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                FirebaseUser user = mAuth.getCurrentUser();
//                startProcess(user.getUid());
                startActivityMainDelay();
            }
        }, 500);
    }

    //    otomatis berpindah ke intro
    private void startActivityMainDelay() {
        // Show splash screen for 2 seconds
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Intent i = new Intent(SplashScreenActivity.this, DashboardActivity.class);
                startActivity(i);
                finish(); // kill current activity
            }
        };
        new Timer().schedule(task, 500);
    }

    private void getCurrentFirebaseToken(final String uid){
        FirebaseApp.initializeApp(this);
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w("TAG", "getInstanceId failed", task.getException());
                            return;
                        }

                        // Get new Instance ID token
                        String token = task.getResult().getToken();
                        int versi_code_program = 0;
                        try {
                            PackageInfo pInfo = SplashScreenActivity.this.getPackageManager().getPackageInfo(getPackageName(), 0);
                            versi_code_program = pInfo.versionCode;
                        } catch (PackageManager.NameNotFoundException e) {
                            e.printStackTrace();
                        }
                        String android_id = Settings.Secure.getString(SplashScreenActivity.this.getContentResolver(),
                                Settings.Secure.ANDROID_ID);

                        String manufacturer = Build.MANUFACTURER;
                        String model = Build.MODEL;
                        int api = Build.VERSION.SDK_INT;
                        String nama_os = "";
                        if (api==16) {
                            nama_os = "Jelly Bean. ";
                        }else if (api==17) {
                            nama_os = "Jelly Bean. ";
                        }else if (api==18) {
                            nama_os = "Jelly Bean. ";
                        }else if (api==19) {
                            nama_os = "KitKat. ";
                        }else if (api==20) {
                            nama_os = "KitKat. ";
                        }else if (api==21) {
                            nama_os = "Lollipop. ";
                        }else if (api==22) {
                            nama_os = "Lollipop. ";
                        }else if (api==23) {
                            nama_os = "Marshmallow. ";
                        }else if (api==24) {
                            nama_os = "Nougat. ";
                        }else if (api==25) {
                            nama_os = "Nougat. ";
                        }else if (api==26) {
                            nama_os = "Oreo. ";
                        }else if (api==27) {
                            nama_os = "Oreo. ";
                        }else if (api==28) {
                            nama_os = "Pie. ";
                        }else if (api==29) {
                            nama_os = "10. ";
                        } else {
                            nama_os = api+" ";
                        }

                        String version_android = nama_os +Build.VERSION.RELEASE;

                        Calendar c = Calendar.getInstance();
                        SimpleDateFormat outFormat = new SimpleDateFormat("dd MMM yyyy HH:mm:ss", Locale.getDefault());
                        String time = outFormat.format(c.getTime());

                        saveUserToRD(token, versi_code_program, android_id, manufacturer, model, version_android, time, uid);
                    }
                });
    }

    private void saveUserToRD( String token, int versi_code_program, String android_id, String manufacturer, String model, String version_android, String time, String uid) {
        Users dataUser =  new Users(uid, token, versi_code_program, android_id, manufacturer, model, version_android, time);
        DatabaseReference saveDB = FirebaseDatabase.getInstance().getReference("users");
        saveDB.child(uid).setValue(dataUser)
                .addOnSuccessListener( new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        //sukses saving data
                        startActivityMainDelay();
                    }
                })
                .addOnCanceledListener(new OnCanceledListener() {
                    @Override
                    public void onCanceled() {
                        Log.w(TAG, "save canceled");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(SplashScreenActivity.this, "Opsss.... Something wrong", Toast.LENGTH_SHORT).show();
                    }
                });


    }
}