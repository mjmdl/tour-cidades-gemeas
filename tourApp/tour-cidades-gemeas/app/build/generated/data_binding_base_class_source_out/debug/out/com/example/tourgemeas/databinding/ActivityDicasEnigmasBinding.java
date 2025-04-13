// Generated by view binder compiler. Do not edit!
package com.example.tourgemeas.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.tourgemeas.R;
import com.google.android.material.tabs.TabLayout;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityDicasEnigmasBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final TextView dica;

  @NonNull
  public final LinearLayout enigma1;

  @NonNull
  public final Button inicarAventura;

  @NonNull
  public final LinearLayout main;

  @NonNull
  public final ImageView menuHamburguer;

  @NonNull
  public final Button outraDica;

  @NonNull
  public final TabLayout tabLayout;

  private ActivityDicasEnigmasBinding(@NonNull LinearLayout rootView, @NonNull TextView dica,
      @NonNull LinearLayout enigma1, @NonNull Button inicarAventura, @NonNull LinearLayout main,
      @NonNull ImageView menuHamburguer, @NonNull Button outraDica, @NonNull TabLayout tabLayout) {
    this.rootView = rootView;
    this.dica = dica;
    this.enigma1 = enigma1;
    this.inicarAventura = inicarAventura;
    this.main = main;
    this.menuHamburguer = menuHamburguer;
    this.outraDica = outraDica;
    this.tabLayout = tabLayout;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityDicasEnigmasBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityDicasEnigmasBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_dicas_enigmas, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityDicasEnigmasBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.dica;
      TextView dica = ViewBindings.findChildViewById(rootView, id);
      if (dica == null) {
        break missingId;
      }

      id = R.id.enigma1;
      LinearLayout enigma1 = ViewBindings.findChildViewById(rootView, id);
      if (enigma1 == null) {
        break missingId;
      }

      id = R.id.inicarAventura;
      Button inicarAventura = ViewBindings.findChildViewById(rootView, id);
      if (inicarAventura == null) {
        break missingId;
      }

      LinearLayout main = (LinearLayout) rootView;

      id = R.id.menuHamburguer;
      ImageView menuHamburguer = ViewBindings.findChildViewById(rootView, id);
      if (menuHamburguer == null) {
        break missingId;
      }

      id = R.id.outraDica;
      Button outraDica = ViewBindings.findChildViewById(rootView, id);
      if (outraDica == null) {
        break missingId;
      }

      id = R.id.tabLayout;
      TabLayout tabLayout = ViewBindings.findChildViewById(rootView, id);
      if (tabLayout == null) {
        break missingId;
      }

      return new ActivityDicasEnigmasBinding((LinearLayout) rootView, dica, enigma1, inicarAventura,
          main, menuHamburguer, outraDica, tabLayout);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
