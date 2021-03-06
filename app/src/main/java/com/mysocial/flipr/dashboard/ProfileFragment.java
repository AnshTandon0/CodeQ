package com.mysocial.flipr.dashboard;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.mysocial.flipr.MainActivity;
import com.mysocial.flipr.ProfileActivity;
import com.mysocial.flipr.R;
import com.mysocial.flipr.models.DetailsModel;

import java.io.Serializable;

public class ProfileFragment extends Fragment {

    DetailsModel detailsModel;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;


    public ProfileFragment(DetailsModel detailsModel , Context context) {
        this.detailsModel = detailsModel;
        sharedPreferences = context.getSharedPreferences("Fundon", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        View view = getView();
        if (view != null) {
            TextView name = view.findViewById(R.id.disp_name);
            TextView username = view.findViewById(R.id.disp_username);
            TextView email = view.findViewById(R.id.disp_email);
            TextView mobile = view.findViewById(R.id.disp_mobile);
            TextView occupation = view.findViewById(R.id.disp_occupation);
            TextView adress = view.findViewById(R.id.disp_address);

            name.setText(detailsModel.getName());
            username.setText(detailsModel.getUserName());
            email.setText(detailsModel.getEmail());
            mobile.setText(detailsModel.getMobile());
            occupation.setText(detailsModel.getOccupation());
            adress.setText(detailsModel.getAddress());

            Button edit = view.findViewById(R.id.edit);
            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getContext(), ProfileActivity.class);
                    intent.putExtra("string" , "modify");
                    intent.putExtra("abba", (Serializable) detailsModel);
                    startActivity(intent);
                }
            });

            Button signOut = view.findViewById(R.id.signOut);
            signOut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    editor.putString("token" , "ansh");
                    editor.commit();
                    Intent intent = new Intent(getContext(), MainActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                }
            });

        }
    }

}