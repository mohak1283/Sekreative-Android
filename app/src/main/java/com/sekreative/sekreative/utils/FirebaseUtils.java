package com.sekreative.sekreative.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.sekreative.sekreative.R;
import com.sekreative.sekreative.models.User;
import com.sekreative.sekreative.models.UserAccountSettings;
import com.sekreative.sekreative.ui.auth.AuthActivity;

public class FirebaseUtils {

    private static final String TAG = "FirebaseUtils";

    //firebase
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;
    private StorageReference mStorageReference;
    private String userID;
    private DatabaseReference ref;

    //vars
    private Activity mActivity;

    public FirebaseUtils(Activity activity) {
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();
        mStorageReference = FirebaseStorage.getInstance().getReference();
        mActivity = activity;

        if(mAuth.getCurrentUser() != null){

            userID = mAuth.getCurrentUser().getUid();

        } else {
            Intent intent = new Intent(activity, AuthActivity.class);
            activity.startActivity(intent);
            activity.finish();

        }

    }


    public void updateUsername(String username){
        Log.d(TAG, "updateUsername: upadting username to: " + username);

        myRef.child(Constants.dbChildUsers)
                .child(userID)
                .child(Constants.dbChildUsername)
                .setValue(username);

        myRef.child(Constants.dbChildUserAccountSettings)
                .child(userID)
                .child(Constants.dbChildUsername)
                .setValue(username);

    }

    public void updateProfileImage(String profileImage) {


        Log.e(TAG, "Inside UpdateProfileImage profile image : " + profileImage);
        myRef.child(Constants.dbChildUserChatSettings)
                .child(userID)
                .child(Constants.dbChildPhoto)
                .setValue(profileImage);

    }

    /**
     * Add information to the users nodes
     * Add information to the user_account_settings node
     * @param //email
     * @param username
     * @param description
     * @param website
     * @param profile_photo
     */

    public void addNewUser(long phone, String username, String description, String website, String profile_photo, int creations, int coins, final int sno,String cover_photo){

        final String id = FirebaseAuth.getInstance().getCurrentUser().getUid();

        User user = new User( id,  phone,  "",  username);

        myRef.child(Constants.dbChildUsers)
                .child(id)
                .setValue(user);

        UserAccountSettings settings = new UserAccountSettings(
                description,
                username,
                0,
                0,
                0,
                profile_photo,
                username,
                website,
                id,
                creations,
                coins,
                sno,
                cover_photo
        );

        myRef.child(Constants.dbChildUserAccountSettings)
                .child(id)
                .setValue(settings);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Integer snoCurrent  = dataSnapshot.child(Constants.dbChildNewSno).getValue(Integer.class);

                myRef.child(Constants.dbChildUserAccountSettings)
                        .child(id)
                        .child(Constants.dbChildSno)
                        .setValue(snoCurrent);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

    }



}
