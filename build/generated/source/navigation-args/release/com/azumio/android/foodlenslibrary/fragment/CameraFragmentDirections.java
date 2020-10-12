package com.azumio.android.foodlenslibrary.fragment;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.azumio.android.foodlenslibrary.R;

public class CameraFragmentDirections {
  private CameraFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionCameraToPermissions() {
    return new ActionOnlyNavDirections(R.id.action_camera_to_permissions);
  }

  @NonNull
  public static NavDirections actionCameraToResult() {
    return new ActionOnlyNavDirections(R.id.action_camera_to_result);
  }
}
