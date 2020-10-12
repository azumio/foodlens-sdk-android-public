package com.azumio.android.foodlenslibrary.fragment;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.azumio.android.foodlenslibrary.R;

public class ResultFragmentDirections {
  private ResultFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionResultToCamera() {
    return new ActionOnlyNavDirections(R.id.action_result_to_camera);
  }

  @NonNull
  public static NavDirections actionResultToReview() {
    return new ActionOnlyNavDirections(R.id.action_result_to_review);
  }
}
