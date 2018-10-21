package com.sekreative.sekreative.ui.auth;


import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sekreative.sekreative.R;
import com.sekreative.sekreative.ui.MainActivity;
import com.sekreative.sekreative.utils.NetworkUtils;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class AuthFragment extends Fragment {


    public AuthFragment() {
        // Required empty public constructor
    }

    public static AuthFragment instantiate() {
        return new AuthFragment();
    }

    private static final String TAG = "AuthFragment";
    private Animation fromBottom , fromTop;
    private String verificationCode;
    private String mVerificationId;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private FirebaseAuth mFirebaseauth;
    private String phonenumber;
    private boolean isConnected;
    private String phn;
    private long phoneLong;

    @BindView(R.id.pb_main)
    ProgressBar progressBar;

    @BindView(R.id.btn_verify)
    FloatingActionButton verify_button;

    @BindView(R.id.tv_sekreative_label)
    TextView seKreative_label;

    @BindView(R.id.tv_terms)
    TextView tv_terms;

    @BindView(R.id.tv_beCreative_label)
    TextView beCreative_label;

    @BindView(R.id.tv_send_otp)
    TextView tv_send_otp;

    @BindView(R.id.et_phone_number)
    EditText phone_edit_text;

    @BindView(R.id.btn_send_otp)
    FloatingActionButton fab;

    @BindView(R.id.cb_age)
    CheckBox checkBox;

    @BindView(R.id.et_otp)
    EditText otp_editText;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_auth, container, false);

        ButterKnife.bind(this, view);

        progressBar.setVisibility(View.INVISIBLE);
        otp_editText.setVisibility(View.INVISIBLE);

        verify_button.hide();
        mFirebaseauth = FirebaseAuth.getInstance();
        fromBottom = AnimationUtils.loadAnimation(getActivity() , R.anim.from_bottom);
        fromTop = AnimationUtils.loadAnimation(getActivity() , R.anim.from_top);


        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                Log.e(TAG, "Entered method onVerificationCompleted");
            }
            @Override
            public void onVerificationFailed(FirebaseException e) {
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(getActivity(), "Error occurred! Please try again", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "Entered method onVerificationFailed", e);
            }
            @Override
            public void onCodeSent(String verificationId,
                                   PhoneAuthProvider.ForceResendingToken token) {
                // The SMS verification code has been sent to the provided phone number, we
                // now need to ask the user to enter the code and then construct a credential
                // by combining the code with a verification ID.
                progressBar.setVisibility(View.INVISIBLE);
                Log.e(TAG, "Entered method onCodeSent");
                // Save verification ID and resending token so we can use them later
                mVerificationId = verificationId;
                Log.e(TAG, "Verification Id is " + mVerificationId);
                mResendToken = token;
                verifyUser(mVerificationId , mResendToken);
            }
        };

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        phone_edit_text.setAnimation(fromBottom);
        seKreative_label.setAnimation(fromTop);
        beCreative_label.setAnimation(fromTop);
        checkBox.setAnimation(fromBottom);
        tv_send_otp.setAnimation(fromBottom);
        fab.setAnimation(fromTop);
        tv_terms.setAnimation(fromBottom);

        fab.setOnClickListener(v -> {

            if (checkBox.isChecked()) {
                isConnected = NetworkUtils.checkInternetConnection(getContext());
                if (isConnected) {
                    //we are connected to a network
                    phonenumber = phone_edit_text.getText().toString();
                    phonenumber = "+91" + phonenumber;
                    authenticateUser(phonenumber);
                } else {
                    //connected = false;
                    Toast.makeText(getActivity(), "No Internet Connection", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(getActivity(), "You need to be 13 years or older to use SeKreative!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void authenticateUser(String phonenumber){

        if (phonenumber.length() >= 10) {
            progressBar.setVisibility(View.VISIBLE);
            PhoneAuthProvider.getInstance().verifyPhoneNumber(phonenumber, 60, TimeUnit.SECONDS, getActivity(), mCallbacks);
        } else {
            phone_edit_text.setError("Invalid Phone Number");
        }

    }
    private void verifyUser(String mVerificationId , PhoneAuthProvider.ForceResendingToken mResendToken){

        phone_edit_text.setVisibility(View.GONE);
        checkBox.setVisibility(View.GONE);
        tv_send_otp.setVisibility(View.GONE);
        fab.hide();
        tv_terms.setVisibility(View.GONE);
        otp_editText.setVisibility(View.VISIBLE);
        verify_button.show();

        otp_editText.setAnimation(fromTop);
        verify_button.setAnimation(fromTop);

        verify_button.setOnClickListener(view -> {

            verificationCode = otp_editText.getText().toString();
            if(!(TextUtils.isEmpty(verificationCode))){

                progressBar.setVisibility(View.VISIBLE);
                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, verificationCode);
                isConnected = NetworkUtils.checkInternetConnection(getContext());
                if (isConnected) {
                    //Connected to a network
                    signInWithPhoneAuthCredential(credential);
                } else {
                    Toast.makeText(getActivity(), "No Internet Connection", Toast.LENGTH_SHORT).show();
                }

            } else {
                otp_editText.setError("Please enter OTP");
            }
        });
    }
    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {

        mFirebaseauth.signInWithCredential(credential)
                .addOnCompleteListener(getActivity(), task -> {

                    if (task.isSuccessful()) {

                        progressBar.setVisibility(View.INVISIBLE);
                        final FirebaseUser user = task.getResult().getUser();
                        phn = user.getPhoneNumber();
                        final String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
                        Log.e("OtpActivtiySignUp", "User Id Sent to intent extra: " + userId);
                        phoneLong = Long.parseLong(phn);
                        final DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference().child("phoneNumbers");
                        rootRef.addListenerForSingleValueEvent(new ValueEventListener() {

                            @Override
                            public void onDataChange(DataSnapshot snapshot) {
                                if (snapshot.hasChild(phn)) {
                                    Toast.makeText(getActivity(), "Sign In Successful!", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getActivity(),MainActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    intent.putExtra("phn",phn);
                                    startActivity(intent);
                                    requireActivity().finish();

                                } else{
                                    Log.e(TAG , "New User , Send to Data Collection Fragment");
                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                Log.e(TAG, "Database Error Message: " + databaseError.toString());
                            }
                        });

                    } else {
                        if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                            // The verification code entered was invalid
                            Toast.makeText(getActivity(), "Invalid Code entered", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.INVISIBLE);
                        }
                    }
                });
            }

    }
