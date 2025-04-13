// Generated by view binder compiler. Do not edit!
package com.example.tourgemeas.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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

public final class ActivityIniciarSessaoBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button btnIniciarSessao;

  @NonNull
  public final EditText editEmail;

  @NonNull
  public final EditText editSenha;

  @NonNull
  public final ImageView imageView6;

  @NonNull
  public final ConstraintLayout main;

  @NonNull
  public final TextView textView;

  @NonNull
  public final TextView textView2;

  @NonNull
  public final TextView textView3;

  @NonNull
  public final TextView textView5;

  private ActivityIniciarSessaoBinding(@NonNull ConstraintLayout rootView,
      @NonNull Button btnIniciarSessao, @NonNull EditText editEmail, @NonNull EditText editSenha,
      @NonNull ImageView imageView6, @NonNull ConstraintLayout main, @NonNull TextView textView,
      @NonNull TextView textView2, @NonNull TextView textView3, @NonNull TextView textView5) {
    this.rootView = rootView;
    this.btnIniciarSessao = btnIniciarSessao;
    this.editEmail = editEmail;
    this.editSenha = editSenha;
    this.imageView6 = imageView6;
    this.main = main;
    this.textView = textView;
    this.textView2 = textView2;
    this.textView3 = textView3;
    this.textView5 = textView5;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityIniciarSessaoBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityIniciarSessaoBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_iniciar_sessao, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityIniciarSessaoBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnIniciarSessao;
      Button btnIniciarSessao = ViewBindings.findChildViewById(rootView, id);
      if (btnIniciarSessao == null) {
        break missingId;
      }

      id = R.id.editEmail;
      EditText editEmail = ViewBindings.findChildViewById(rootView, id);
      if (editEmail == null) {
        break missingId;
      }

      id = R.id.editSenha;
      EditText editSenha = ViewBindings.findChildViewById(rootView, id);
      if (editSenha == null) {
        break missingId;
      }

      id = R.id.imageView6;
      ImageView imageView6 = ViewBindings.findChildViewById(rootView, id);
      if (imageView6 == null) {
        break missingId;
      }

      ConstraintLayout main = (ConstraintLayout) rootView;

      id = R.id.textView;
      TextView textView = ViewBindings.findChildViewById(rootView, id);
      if (textView == null) {
        break missingId;
      }

      id = R.id.textView2;
      TextView textView2 = ViewBindings.findChildViewById(rootView, id);
      if (textView2 == null) {
        break missingId;
      }

      id = R.id.textView3;
      TextView textView3 = ViewBindings.findChildViewById(rootView, id);
      if (textView3 == null) {
        break missingId;
      }

      id = R.id.textView5;
      TextView textView5 = ViewBindings.findChildViewById(rootView, id);
      if (textView5 == null) {
        break missingId;
      }

      return new ActivityIniciarSessaoBinding((ConstraintLayout) rootView, btnIniciarSessao,
          editEmail, editSenha, imageView6, main, textView, textView2, textView3, textView5);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
