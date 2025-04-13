// Generated by view binder compiler. Do not edit!
package com.example.tourgemeas.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.tourgemeas.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityLoguinBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button btnInicialSessao;

  @NonNull
  public final Button btnRegistrar;

  @NonNull
  public final Button button3;

  @NonNull
  public final TextView editSenha;

  @NonNull
  public final ImageView imageView;

  @NonNull
  public final ImageView imageView2;

  @NonNull
  public final ConstraintLayout main;

  private ActivityLoguinBinding(@NonNull ConstraintLayout rootView,
      @NonNull Button btnInicialSessao, @NonNull Button btnRegistrar, @NonNull Button button3,
      @NonNull TextView editSenha, @NonNull ImageView imageView, @NonNull ImageView imageView2,
      @NonNull ConstraintLayout main) {
    this.rootView = rootView;
    this.btnInicialSessao = btnInicialSessao;
    this.btnRegistrar = btnRegistrar;
    this.button3 = button3;
    this.editSenha = editSenha;
    this.imageView = imageView;
    this.imageView2 = imageView2;
    this.main = main;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityLoguinBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityLoguinBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_loguin, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityLoguinBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnInicialSessao;
      Button btnInicialSessao = ViewBindings.findChildViewById(rootView, id);
      if (btnInicialSessao == null) {
        break missingId;
      }

      id = R.id.btnRegistrar;
      Button btnRegistrar = ViewBindings.findChildViewById(rootView, id);
      if (btnRegistrar == null) {
        break missingId;
      }

      id = R.id.button3;
      Button button3 = ViewBindings.findChildViewById(rootView, id);
      if (button3 == null) {
        break missingId;
      }

      id = R.id.editSenha;
      TextView editSenha = ViewBindings.findChildViewById(rootView, id);
      if (editSenha == null) {
        break missingId;
      }

      id = R.id.imageView;
      ImageView imageView = ViewBindings.findChildViewById(rootView, id);
      if (imageView == null) {
        break missingId;
      }

      id = R.id.imageView2;
      ImageView imageView2 = ViewBindings.findChildViewById(rootView, id);
      if (imageView2 == null) {
        break missingId;
      }

      ConstraintLayout main = (ConstraintLayout) rootView;

      return new ActivityLoguinBinding((ConstraintLayout) rootView, btnInicialSessao, btnRegistrar,
          button3, editSenha, imageView, imageView2, main);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
