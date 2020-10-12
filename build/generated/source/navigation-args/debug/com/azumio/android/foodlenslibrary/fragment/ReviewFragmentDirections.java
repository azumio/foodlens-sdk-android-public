package com.azumio.android.foodlenslibrary.fragment;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.azumio.android.foodlenslibrary.R;

public class ReviewFragmentDirections {
  private ReviewFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionReviewToResult() {
    return new ActionOnlyNavDirections(R.id.action_review_to_result);
  }
}
