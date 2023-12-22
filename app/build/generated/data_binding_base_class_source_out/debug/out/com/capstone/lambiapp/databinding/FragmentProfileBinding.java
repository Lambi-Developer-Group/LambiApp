// Generated by view binder compiler. Do not edit!
package com.capstone.lambiapp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.capstone.lambiapp.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentProfileBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final LinearLayout btnDarkmode;

  @NonNull
  public final LinearLayout btnDonate;

  @NonNull
  public final LinearLayout btnInfo;

  @NonNull
  public final LinearLayout btnLogout;

  @NonNull
  public final ImageView imageView;

  @NonNull
  public final ImageView ivProfile;

  @NonNull
  public final ProgressBar progressBar;

  @NonNull
  public final SwitchCompat switchDarkmode;

  @NonNull
  public final TextView tvEmail;

  @NonNull
  public final TextView tvNamaProfil;

  @NonNull
  public final TextView tvTitleProfile;

  private FragmentProfileBinding(@NonNull ConstraintLayout rootView,
      @NonNull LinearLayout btnDarkmode, @NonNull LinearLayout btnDonate,
      @NonNull LinearLayout btnInfo, @NonNull LinearLayout btnLogout, @NonNull ImageView imageView,
      @NonNull ImageView ivProfile, @NonNull ProgressBar progressBar,
      @NonNull SwitchCompat switchDarkmode, @NonNull TextView tvEmail,
      @NonNull TextView tvNamaProfil, @NonNull TextView tvTitleProfile) {
    this.rootView = rootView;
    this.btnDarkmode = btnDarkmode;
    this.btnDonate = btnDonate;
    this.btnInfo = btnInfo;
    this.btnLogout = btnLogout;
    this.imageView = imageView;
    this.ivProfile = ivProfile;
    this.progressBar = progressBar;
    this.switchDarkmode = switchDarkmode;
    this.tvEmail = tvEmail;
    this.tvNamaProfil = tvNamaProfil;
    this.tvTitleProfile = tvTitleProfile;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentProfileBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentProfileBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_profile, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentProfileBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btn_darkmode;
      LinearLayout btnDarkmode = ViewBindings.findChildViewById(rootView, id);
      if (btnDarkmode == null) {
        break missingId;
      }

      id = R.id.btn_donate;
      LinearLayout btnDonate = ViewBindings.findChildViewById(rootView, id);
      if (btnDonate == null) {
        break missingId;
      }

      id = R.id.btn_info;
      LinearLayout btnInfo = ViewBindings.findChildViewById(rootView, id);
      if (btnInfo == null) {
        break missingId;
      }

      id = R.id.btn_logout;
      LinearLayout btnLogout = ViewBindings.findChildViewById(rootView, id);
      if (btnLogout == null) {
        break missingId;
      }

      id = R.id.imageView;
      ImageView imageView = ViewBindings.findChildViewById(rootView, id);
      if (imageView == null) {
        break missingId;
      }

      id = R.id.iv_profile;
      ImageView ivProfile = ViewBindings.findChildViewById(rootView, id);
      if (ivProfile == null) {
        break missingId;
      }

      id = R.id.progress_bar;
      ProgressBar progressBar = ViewBindings.findChildViewById(rootView, id);
      if (progressBar == null) {
        break missingId;
      }

      id = R.id.switch_darkmode;
      SwitchCompat switchDarkmode = ViewBindings.findChildViewById(rootView, id);
      if (switchDarkmode == null) {
        break missingId;
      }

      id = R.id.tv_email;
      TextView tvEmail = ViewBindings.findChildViewById(rootView, id);
      if (tvEmail == null) {
        break missingId;
      }

      id = R.id.tv_nama_profil;
      TextView tvNamaProfil = ViewBindings.findChildViewById(rootView, id);
      if (tvNamaProfil == null) {
        break missingId;
      }

      id = R.id.tv_title_profile;
      TextView tvTitleProfile = ViewBindings.findChildViewById(rootView, id);
      if (tvTitleProfile == null) {
        break missingId;
      }

      return new FragmentProfileBinding((ConstraintLayout) rootView, btnDarkmode, btnDonate,
          btnInfo, btnLogout, imageView, ivProfile, progressBar, switchDarkmode, tvEmail,
          tvNamaProfil, tvTitleProfile);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}