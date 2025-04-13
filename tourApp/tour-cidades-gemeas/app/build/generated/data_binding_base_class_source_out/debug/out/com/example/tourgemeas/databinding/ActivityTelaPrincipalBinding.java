// Generated by view binder compiler. Do not edit!
package com.example.tourgemeas.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.tourgemeas.R;
import com.google.android.material.tabs.TabLayout;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityTelaPrincipalBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final FrameLayout contentFrame;

  @NonNull
  public final LinearLayout main;

  @NonNull
  public final ImageView menuHamburguer;

  @NonNull
  public final TabLayout tabLayout;

  private ActivityTelaPrincipalBinding(@NonNull LinearLayout rootView,
      @NonNull FrameLayout contentFrame, @NonNull LinearLayout main,
      @NonNull ImageView menuHamburguer, @NonNull TabLayout tabLayout) {
    this.rootView = rootView;
    this.contentFrame = contentFrame;
    this.main = main;
    this.menuHamburguer = menuHamburguer;
    this.tabLayout = tabLayout;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityTelaPrincipalBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityTelaPrincipalBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_tela_principal, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityTelaPrincipalBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.content_frame;
      FrameLayout contentFrame = ViewBindings.findChildViewById(rootView, id);
      if (contentFrame == null) {
        break missingId;
      }

      LinearLayout main = (LinearLayout) rootView;

      id = R.id.menuHamburguer;
      ImageView menuHamburguer = ViewBindings.findChildViewById(rootView, id);
      if (menuHamburguer == null) {
        break missingId;
      }

      id = R.id.tabLayout;
      TabLayout tabLayout = ViewBindings.findChildViewById(rootView, id);
      if (tabLayout == null) {
        break missingId;
      }

      return new ActivityTelaPrincipalBinding((LinearLayout) rootView, contentFrame, main,
          menuHamburguer, tabLayout);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
