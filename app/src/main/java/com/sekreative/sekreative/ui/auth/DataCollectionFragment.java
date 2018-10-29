package com.sekreative.sekreative.ui.auth;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.sekreative.sekreative.R;
import com.sekreative.sekreative.ui.MainActivity;
import com.sekreative.sekreative.utils.Constants;
import com.sekreative.sekreative.utils.FirebaseUtils;
import butterknife.BindView;
import butterknife.ButterKnife;



/**
 * A simple {@link Fragment} subclass.
 */
public class DataCollectionFragment extends Fragment {


    public DataCollectionFragment() {
        // Required empty public constructor
    }


    private static final String TAG = "DataCollectionFragment";

    @BindView(R.id.iv_profile_photo)
    ImageView ivProfileAvatar;

    @BindView(R.id.edt_name)
    EditText edtName;

    @BindView(R.id.btn_register)
    Button btnRegister;

    @BindView(R.id.pb_profile)
    ProgressBar pbProfile;

    @BindView(R.id.pb_main)
    ProgressBar pbMain;

    private String defaultImageUrl = "https://yt3.ggpht.com/a-/AN66SAyk49uNWUtt2mDTTxOdMNy5afiVHK3dFIvPVQ=s900-mo-c-c0xffffffff-rj-k-no";
    private FirebaseUtils firebaseUtils;

    private String username, phoneNumber;
    private String currentUserId;

    // Firebase
    private FirebaseAuth mAuth;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mRef;
    private StorageReference mStorageReference;

    private static final String KEY_PHONE = "key-phone";
    private static final String KEY_USER_ID = "key-userid";
    private static final String KEY_USERNAME = "key-username";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_data_collection, container, false);

        ButterKnife.bind(this, view);
        firebaseUtils = new FirebaseUtils(getActivity());

        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mRef = mFirebaseDatabase.getReference();
        mStorageReference = FirebaseStorage.getInstance().getReference();

        Bundle args = getArguments();
        if (args.containsKey(KEY_PHONE)) {
            phoneNumber = args.getString(KEY_PHONE);
            currentUserId = args.getString(KEY_USER_ID);
        }


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        pbMain.setVisibility(View.GONE);
        pbProfile.setVisibility(View.GONE);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public  void onClick(View view) {

                username = edtName.getText().toString().toLowerCase();
                pbMain.setVisibility(View.VISIBLE);

                if (TextUtils.isEmpty(edtName.getText().toString().toLowerCase())){
                    edtName.setError("Please Enter Username");
                    pbMain.setVisibility(View.GONE);


                } else {
                    checkIfUsernameExists(phoneNumber, username);
                }
            }

        });


    }


    private void checkIfUsernameExists(final String phoneNumber, final String username) {

        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        Query query = reference

                .child(Constants.dbChildUsers)

                .orderByChild(Constants.dbChildUsername)

                .equalTo(username);

        query.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override

            public void onDataChange(DataSnapshot dataSnapshot) {



                if(!dataSnapshot.exists()){

                    //add the username
                    firebaseUtils.updateUsername(username.toLowerCase());
                    firebaseUtils.updateProfileImage(defaultImageUrl);
                   // firebaseMethods.initChat(username, firebaseUrl.toString());



                    mRef.child(Constants.dbChildUserAccountSettings)
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .child(Constants.dbChildProfilePhoto)
                            .setValue(defaultImageUrl);

                    mRef.child(Constants.dbChildUsers)
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(Constants.dbChildPhoto).setValue(defaultImageUrl);

                    Log.e(TAG, "Username received: " + username);
                    // Log.e(TAG, "Profile Image Received: " + settings.getProfile_photo().toString());
                    Log.e(TAG, "Phone Number received: " + phoneNumber);

                    reference.child(Constants.dbChildPhoneNumbers).child(phoneNumber).setValue(FirebaseAuth.getInstance().getCurrentUser().getUid());

                    firebaseUtils.addNewUser(Long.parseLong(phoneNumber), username, "Hey, there I am using SeKreative", "", defaultImageUrl, 50, 0, 0,"https://upload.wikimedia.org/wikipedia/commons/b/b9/No_Cover.jpg");

                    pbMain.setVisibility(View.GONE);

                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    //intent.putExtra("userId", currentUserId);
                    intent.putExtra(KEY_USERNAME, username);
                    startActivity(intent);
                    requireActivity().finish();
                    Toast.makeText(getActivity(), "Sign Up Successfull", Toast.LENGTH_SHORT).show();

                } else {
                    pbMain.setVisibility(View.GONE);
                    edtName.setError("Username Already Exists");
                }

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

}
