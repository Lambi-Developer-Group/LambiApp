// Generated by view binder compiler. Do not edit!
package com.capstone.lambiapp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.camera.view.PreviewView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.capstone.lambiapp.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityCameraXactivityBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ImageView btnCaptureCamera;

  @NonNull
  public final PreviewView previewCameraX;

  private ActivityCameraXactivityBinding(@NonNull ConstraintLayout rootView,
      @NonNull ImageView btnCaptureCamera, @NonNull PreviewView previewCameraX) {
    this.rootView = rootView;
    this.btnCaptureCamera = btnCaptureCamera;
    this.previewCameraX = previewCameraX;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityCameraXactivityBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityCameraXactivityBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_camera_xactivity, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityCameraXactivityBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btn_capture_camera;
      ImageView btnCaptureCamera = ViewBindings.findChildViewById(rootView, id);
      if (btnCaptureCamera == null) {
        break missingId;
      }

      id = R.id.preview_camera_x;
      PreviewView previewCameraX = ViewBindings.findChildViewById(rootView, id);
      if (previewCameraX == null) {
        break missingId;
      }

      return new ActivityCameraXactivityBinding((ConstraintLayout) rootView, btnCaptureCamera,
          previewCameraX);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
