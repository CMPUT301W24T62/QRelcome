package com.example.qrelcome;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.qrelcome.databinding.FragmentFirstBinding;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class FirstFragment extends Fragment {

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
                HashMap<String, Object> map = new HashMap<String, Object>();
                map.put("contact", "apple@apple.com");
                map.put("name", "Steve Jobs");
                map.put("imageLink", "//upload.wikimedia.org/wikipedia/commons/thumb/d/dc/Steve_Jobs_Headshot_2010-CROP_%28cropped_2%29.jpg/220px-Steve_Jobs_Headshot_2010-CROP_%28cropped_2%29.jpg");
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