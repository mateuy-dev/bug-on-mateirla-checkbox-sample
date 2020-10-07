package com.mateuyabar.checkboxbugsample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;

import com.mateuyabar.checkboxbugsample.databinding.CheckboxTestActivityBinding;
import com.mateuyabar.checkboxbugsample.databinding.FirstTabBinding;
import com.mateuyabar.checkboxbugsample.databinding.SecondTabBinding;
import com.mateuyabar.checkboxbugsample.databinding.ThirdTabBinding;


public class CheckboxTestActivity extends AppCompatActivity {
    CheckboxTestActivityBinding activityMainBinding;
    View[] views;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityMainBinding = CheckboxTestActivityBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());
        createViews(activityMainBinding.viewPager);
        activityMainBinding.viewPager.setAdapter(new ViewPagerAdapter(views));
        activityMainBinding.viewPager.setOffscreenPageLimit(10);

        disableInputs();
    }

    private void disableInputs() {
        checkboxBinding.checkboxCheckedDisabled.setEnabled(false);
        checkboxBinding.checkboxCheckedEnabled.setEnabled(true);
        checkboxBinding.checkboxNotCheckedDisabled.setEnabled(false);
        checkboxBinding.checkboxNotCheckedEnabled.setEnabled(true);
    }


    FirstTabBinding firstTabBinding;
    SecondTabBinding checkboxBinding;
    ThirdTabBinding thirdTabBinding;
    public void createViews(ViewPager2 parent){
        firstTabBinding = FirstTabBinding.inflate(getLayoutInflater(), parent, false);
        checkboxBinding = SecondTabBinding.inflate(getLayoutInflater(), parent, false);
        thirdTabBinding = ThirdTabBinding.inflate(getLayoutInflater(), parent, false);

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            checkboxBinding.checkboxCheckedDisabled.setChecked(true);
            checkboxBinding.checkboxCheckedEnabled.setChecked(true);
            checkboxBinding.checkboxNotCheckedDisabled.setChecked(false);
            checkboxBinding.checkboxNotCheckedEnabled.setChecked(false);

            thirdTabBinding.policeForce.setEndIconDrawable(R.drawable.ic_arrow_drop_down_gray_24);

        }, 400);


        views = new View[]{firstTabBinding.getRoot(), checkboxBinding.getRoot(), thirdTabBinding.getRoot()};
    }

    public static class ViewPagerAdapter extends RecyclerView.Adapter<ViewPagerAdapter.ViewHolder> {
        View[] views;
        public ViewPagerAdapter(View[] views) {
            this.views = views;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ViewHolder(views[viewType]);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        }

        @Override
        public int getItemViewType(int position) {
            return position;
        }

        @Override
        public int getItemCount() {
            return views.length;
        }

        public static  class ViewHolder extends RecyclerView.ViewHolder{
            public ViewHolder(@NonNull View itemView) {
                super(itemView);
            }
        }
    }


}