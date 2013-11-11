package Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class IsOnlineDialogFragment extends DialogFragment {
  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
      // Use the Builder class for convenient dialog construction
      AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
      builder.setMessage("No internet detected. Connection is required. Restart app?")
             .setPositiveButton("Restart", new DialogInterface.OnClickListener() {
                 public void onClick(DialogInterface dialog, int id) {
                	 Intent i = getActivity().getPackageManager()
                       .getLaunchIntentForPackage( getActivity().getPackageName() );
          i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
          startActivity(i);
                 }
             })
             .setNegativeButton("Proceed", new DialogInterface.OnClickListener() {
                 public void onClick(DialogInterface dialog, int id) {
                     // User cancelled the dialog
                 }
             });
      // Create the AlertDialog object and return it
      return builder.create();
  }
 }