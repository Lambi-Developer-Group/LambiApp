// Generated by view binder compiler. Do not edit!
package com.capstone.lambiapp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.capstone.lambiapp.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentHomeBinding implements ViewBinding {
  @NonNull
  private final ScrollView rootView;

  @NonNull
  public final Button backBtn;

  @NonNull
  public final ImageView imageView5;

  @NonNull
  public final ConstraintLayout mainCardview;

  @NonNull
  public final ProgressBar progressBar;

  @NonNull
  public final RecyclerView recyclerViewRecommendation;

  @NonNull
  public final TextView tvHello;

  @NonNull
  public final TextView tvName;

  @NonNull
  public final TextView tvTitleHome;

  private FragmentHomeBinding(@NonNull ScrollView rootView, @NonNull Button backBtn,
      @NonNull ImageView imageView5, @NonNull ConstraintLayout mainCardview,
      @NonNull ProgressBar progressBar, @NonNull RecyclerView recyclerViewRecommendation,
      @NonNull TextView tvHello, @NonNull TextView tvName, @NonNull TextView tvTitleHome) {
    this.rootView = rootView;
    this.backBtn = backBtn;
    this.imageView5 = imageView5;
    this.mainCardview = mainCardview;
    this.progressBar = progressBar;
    this.recyclerViewRecommendation = recyclerViewRecommendation;
    this.tvHello = tvHello;
    this.tvName = tvName;
    this.tvTitleHome = tvTitleHome;
  }

  @Override
  @NonNull
  public ScrollView getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentHomeBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentHomeBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_home, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentHomeBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.back_btn;
      Button backBtn = ViewBindings.findChildViewById(rootView, id);
      if (backBtn == null) {
        break missingId;
      }

      id = R.id.imageView5;
      ImageView imageView5 = ViewBindings.findChildViewById(rootView, id);
      if (imageView5 == null) {
        break missingId;
      }

      id = R.id.main_cardview;
      ConstraintLayout mainCardview = ViewBindings.findChildViewById(rootView, id);
      if (mainCardview == null) {
        break missingId;
      }

      id = R.id.progress_bar;
      ProgressBar progressBar = ViewBindings.findChildViewById(rootView, id);
      if (progressBar == null) {
        break missingId;
      }

      id = R.id.recyclerViewRecommendation;
      RecyclerView recyclerViewRecommendation = ViewBindings.findChildViewById(rootView, id);
      if (recyclerViewRecommendation == null) {
        break missingId;
      }

      id = R.id.tvHello;
      TextView tvHello = ViewBindings.findChildViewById(rootView, id);
      if (tvHello == null) {
        break missingId;
      }

      id = R.id.tv_name;
      TextView tvName = ViewBindings.findChildViewById(rootView, id);
      if (tvName == null) {
        break missingId;
      }

      id = R.id.tv_title_home;
      TextView tvTitleHome = ViewBindings.findChildViewById(rootView, id);
      if (tvTitleHome == null) {
        break missingId;
      }

      return new FragmentHomeBinding((ScrollView) rootView, backBtn, imageView5, mainCardview,
          progressBar, recyclerViewRecommendation, tvHello, tvName, tvTitleHome);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
