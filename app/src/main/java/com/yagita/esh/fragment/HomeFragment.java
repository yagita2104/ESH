package com.yagita.esh.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.cast.framework.media.ImagePicker;
import com.yagita.esh.R;
import com.yagita.esh.activity.home.ContributeProfileScreen;
import com.yagita.esh.activity.home.ExerciseScreen;
import com.yagita.esh.activity.home.StatisticScreen;
import com.yagita.esh.activity.home.VocabScreen;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class HomeFragment extends Fragment {
    Button btnVocabulary, btnExercise, btnStatistics, btnContribute;
    ImageView imgViewProfile, btnEditImg;
    TextView txtSpecialize, txtName;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        getWidget(view);
        addAction();
        return view;
    }
    public void getWidget(View view){
        btnVocabulary = view.findViewById(R.id.btnVocabulary);
        btnExercise = view.findViewById(R.id.btnExercise);
        btnStatistics = view.findViewById(R.id.btnStatistics);
        btnContribute = view.findViewById(R.id.btnContribute);
        btnEditImg = view.findViewById(R.id.btnEditImg);

        imgViewProfile = view.findViewById(R.id.imgViewProfile);
        txtName = view.findViewById(R.id.txtName);
        txtSpecialize = view.findViewById(R.id.txtSpecialize);

        //Nhận dữ liệu tên và chuyên ngành
        // Nhận dữ liệu tên và chuyên ngành từ SharedPreferences
//        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(requireContext());
        SharedPreferences preferences = getActivity().getSharedPreferences("MyPrefs", getActivity().MODE_PRIVATE);
        String name = preferences.getString("name", "");
        String specialize = preferences.getString("spec", "");
        if (!name.isEmpty() && !specialize.isEmpty()) {
            txtName.setText(name);
            specialize = "Khoa " + specialize;
            txtSpecialize.setText(specialize);
        }

    }
    public void addAction(){
        btnVocabulary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), VocabScreen.class));
            }
        });
        btnExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ExerciseScreen.class));
            }
        });
        btnStatistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), StatisticScreen.class));
            }
        });
        btnContribute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ContributeProfileScreen.class));
            }
        });
        btnEditImg.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
            @Override
            public void onClick(View view) {
                showEditOptionsDialog();
                //imageChooser();
            }
        });
    }
    private void showEditOptionsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Cập nhật")
                .setItems(R.array.edit_options, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                imageChooser();
                                break;
                            case 1:
                                showEditNameDialog();
                                break;
                        }
                    }
                });
        builder.create().show();
    }
    private void showEditNameDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_edit_name, null);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) EditText editTextName = dialogView.findViewById(R.id.editTextName);
        // Lấy tên hiện tại và đặt vào EditText
        SharedPreferences preferences = getActivity().getSharedPreferences("MyPrefs", Activity.MODE_PRIVATE);
        String name = preferences.getString("name", "");
        editTextName.setText(name);

        builder.setView(dialogView)
                .setTitle("Cập nhật tên hiển thị")
                .setPositiveButton("Lưu", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Lấy tên đã chỉnh sửa từ EditText
                        String newName = editTextName.getText().toString().trim();
                        if (!newName.isEmpty()) {
                            // Lưu tên mới vào SharedPreferences
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString("name", newName);
                            editor.apply();
                            // Hiển thị tên mới trên TextView
                            txtName.setText(newName);

                        } else {
                            Toast.makeText(requireContext(), "Tên hiển thị không được để trống.", Toast.LENGTH_SHORT).show();
                            showEditNameDialog();
                        }
                    }
                })
                .setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        builder.create().show();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Hiển thị hình ảnh đã lưu trong SharedPreferences
        Bitmap savedImage = loadImageFromPreferences();
        if (savedImage != null) {
            imgViewProfile.setImageBitmap(savedImage);
        }
    }

    private void imageChooser() {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        launcher.launch(i);
    }

    private void saveImageToPreferences(Bitmap bitmap) {
        SharedPreferences preferences = requireActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        String imageEncoded = Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT);
        editor.putString("profile_image", imageEncoded);
        editor.apply();
    }

    private Bitmap loadImageFromPreferences() {
        SharedPreferences preferences = requireActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String imageEncoded = preferences.getString("profile_image", "");
        if (!imageEncoded.isEmpty()) {
            byte[] imageBytes = Base64.decode(imageEncoded, Base64.DEFAULT);
            return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
        }
        return null;
    }

    ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() == Activity.RESULT_OK) {
            Intent data = result.getData();
            if (data != null && data.getData() != null) {
                Uri selectedImageUri = data.getData();
                Bitmap selectedImageBitmap = null;
                try {
                    selectedImageBitmap = MediaStore.Images.Media.getBitmap(requireActivity().getContentResolver(), selectedImageUri);
                    // Hiển thị hình ảnh đã chọn
                    imgViewProfile.setImageBitmap(selectedImageBitmap);
                    // Lưu hình ảnh vào SharedPreferences
                    saveImageToPreferences(selectedImageBitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    });


}