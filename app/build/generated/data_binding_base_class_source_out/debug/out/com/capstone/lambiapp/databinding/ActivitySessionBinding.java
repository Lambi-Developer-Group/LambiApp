// Generated by view binder compiler. Do not edit!
package com.capstone.lambiapp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.capstone.lambiapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivitySessionBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final FloatingActionButton addSession;

  @NonNull
  public final ImageView imageViewBack;

  @NonNull
  public final ProgressBar progressBar;

  @NonNull
  public final RecyclerView rvSession;

  @NonNull
  public final TextView textViewBack;

  @NonNull
  public final TextView tvName;

  private ActivitySessionBinding(@NonNull ConstraintLayout rootView,
      @NonNull FloatingActionButton addSession, @NonNull ImageView imageViewBack,
      @NonNull ProgressBar progressBar, @NonNull RecyclerView rvSession,
      @NonNull TextView textViewBack, @NonNull TextView tvName) {
    this.rootView = rootView;
    this.addSession = addSession;
    this.imageViewBack = imageViewBack;
    this.progressBar = progressBar;
    this.rvSession = rvSession;
    this.textViewBack = textViewBack;
    this.tvName = tvName;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivitySessionBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivitySessionBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_session, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivitySessionBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.addSession;
      FloatingActionButton addSession = ViewBindings.findChildViewById(rootView, id);
      if (addSession == null) {
        break missingId;
      }

      id = R.id.imageViewBack;
      ImageView imageViewBack = ViewBindings.findChildViewById(rootView, id);
      if (imageViewBack == null) {
        break missingId;
      }

      id = R.id.progress_bar;
      ProgressBar progressBar = ViewBindings.findChildViewById(rootView, id);
      if (progressBar == null) {
        break missingId;
      }

      id = R.id.rv_session;
      RecyclerView rvSession = ViewBindings.findChildViewById(rootView, id);
      if (rvSession == null) {
        break missingId;
      }

      id = R.id.textViewBack;
      TextView textViewBack = ViewBindings.findChildViewById(rootView, id);
      if (textViewBack == null) {
        break missingId;
      }

      id = R.id.tv_name;
      TextView tvName = ViewBindings.findChildViewById(rootView, id);
      if (tvName == null) {
        break missingId;
      }

      return new ActivitySessionBinding((ConstraintLayout) rootView, addSession, imageViewBack,
          progressBar, rvSession, textViewBack, tvName);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}