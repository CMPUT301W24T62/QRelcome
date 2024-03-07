package com.example.qrelcome;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.qrelcome.databinding.FragmentFirstBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class FirstFragment extends Fragment {

    private FirebaseFirestore db;
    private CollectionReference usersRef;
    private FragmentFirstBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                UserProfile user = new UserProfile();
                user.setUuid(UUID.randomUUID());

                UserProfile testUser = new UserProfile();
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("contact", "apple@apple.com");
                map.put("name", "Steve Jobs");
                map.put("imageLink", "https://firebasestorage.googleapis.com/v0/b/qrelcome.appspot.com/o/steve.jpg?alt=media&token=8b6cbf0e-774a-4696-a354-420b7eabf40e");
                map.put("homepage", "apple.com");
                map.put("geolocationOn", "true");

                testUser.setData(map);
                testUser.setUuid(UUID.randomUUID());
                UserDB db = new UserDB();
                db.addNewUserProfile(testUser);

                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}