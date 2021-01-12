package easy.filepicker.dev;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.os.Bundle;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.app.Dialog;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import easy.filepicker.dev.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import android.widget.FrameLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.cardview.widget.CardView;
import android.widget.LinearLayout;
import androidx.fragment.app.FragmentManager;
import android.widget.Toast;
import android.content.Context;
import com.google.android.material.button.MaterialButton;
import android.content.DialogInterface;
import androidx.core.widget.TextViewCompat;
import android.widget.TextView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import android.transition.Visibility;

public class FilePickerDialog extends BottomSheetDialogFragment {

    public static final String TAG = "FilePickerDialog";

    private CardView dialogCard;
    private RecyclerView fileListRv;
    private MaterialButton negativeButton;
    private MaterialButton positiveButton;
    private TextView dialogTitle;
    private FloatingActionButton fabAddNewFolder;

    private static float dialogRadius;
    private static float dialogElevation;
    private static int dialogMargin;
    private static boolean showPathAsTitle;
    private static boolean showAddNewFolder;
    private static String dialogStringTitle;
    private static FilePickerType dialogType;

    private OnSelectedListener mOnSelectedListener;
    private OnPositiveButtonListener mOnPositiveButtonListener;
    private OnNegativeButtonListener mOnNegativeButtonListener;

    public static enum FilePickerType {FILE, FOLDER};

    public static FilePickerDialog newInstance(Builder builder) {
        dialogRadius = builder.dialogRadius;
        dialogElevation = builder.dialogElevation;
        dialogMargin = builder.dialogMargin;
        dialogStringTitle = builder.dialogStringTitle;
        showPathAsTitle = builder.showPathAsTitle;
        showAddNewFolder = builder.showAddNewFolder;
        dialogType = builder.dialogType;

        return new FilePickerDialog();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.file_dialog, container, false);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getContext(), R.style.MyBottomSheetDialogTheme);

        bottomSheetDialog.setOnShowListener(new DialogInterface.OnShowListener() {
                @Override
                public void onShow(DialogInterface dialog) {
                    BottomSheetDialog d = (BottomSheetDialog) dialog;
                    FrameLayout bottomSheet = d.findViewById(com.google.android.material.R.id.design_bottom_sheet);
                    final BottomSheetBehavior behavior = BottomSheetBehavior.from(bottomSheet);
                    behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {

                            @Override
                            public void onStateChanged(View bottomSheet, int newState) {
                                behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                            }

                            @Override
                            public void onSlide(View bottomSheet, float slideOffset) {
                            }


                        });
                }
            });

        return bottomSheetDialog;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dialogCard = view.findViewById(R.id.dialog_card);
        dialogTitle = view.findViewById(R.id.dialog_header);
        fabAddNewFolder = view.findViewById(R.id.fab_add);
        fileListRv = view.findViewById(R.id.file_list_rv);
        negativeButton = view.findViewById(R.id.negative_button);
        positiveButton = view.findViewById(R.id.positive_button);

        if (dialogCard != null) {
            FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) dialogCard.getLayoutParams();
            if (params != null) {
                params.setMargins(dialogMargin, dialogMargin, dialogMargin, dialogMargin);
                dialogCard.setLayoutParams(params);
            }

            dialogCard.setRadius(dialogRadius);
            dialogCard.setCardElevation(dialogElevation);
            
            
            
        } else {
            Toast.makeText(getContext(), "CardView Null Object Reference", Toast.LENGTH_SHORT).show();
        }

        if (dialogStringTitle != null) {
            dialogTitle.setText(dialogStringTitle);
        } else {
            switch (dialogType) {
                case FILE:
                    dialogTitle.setText("Choose a File");
                    break;
                case FOLDER:
                    dialogTitle.setText("Choose a Folder");
                    break;
                default:
                    dialogTitle.setText("Choose a File");
            }
        }
        
        switch (dialogType) {
            case FILE:
                positiveButton.setVisibility(View.GONE);
                break;
            case FOLDER:
                positiveButton.setVisibility(View.VISIBLE);
                break;
            default:
                positiveButton.setVisibility(View.GONE);
        }

        if (showAddNewFolder) {
            fabAddNewFolder.setVisibility(View.VISIBLE);
        } else {
            fabAddNewFolder.setVisibility(View.GONE);
        }
    }

    public static class Builder {

        private float dialogRadius = 8;
        private float dialogElevation = 8;
        private int dialogMargin = 8;
        private boolean showPathAsTitle = false;
        private boolean showAddNewFolder = false;
        private String dialogStringTitle;
        private FilePickerType dialogType = FilePickerType.FILE;

        private Context context;

        public Builder withContext(Context context) {
            this.context = context;
            return this;
        }

        public Builder setDialogMargin(int margin) {
            this.dialogMargin = margin;
            return this;
        }

        public Builder setDialogCornerRadius(int cornerRadius) {
            this.dialogRadius = cornerRadius;
            return this;
        }

        public Builder setDialogElevation(int elevation) {
            this.dialogElevation = elevation;
            return this;
        }

        public Builder showPathAsTitle(boolean state) {
            this.showPathAsTitle = state;
            return this;
        }

        public Builder setDialogTitle(String title) {
            this.dialogStringTitle = title;
            return this;
        }

        public Builder setFilePickerTyoe(FilePickerType type) {
            this.dialogType = type;
            return this;
        }

        public Builder showAddNewFolder(boolean state) {
            this.showAddNewFolder = state;
            return this;
        }

        public FilePickerDialog build() {
            return newInstance(this);
        }

        public void show(FragmentManager fm, String tag) {
            FilePickerDialog dialog = build();
            dialog.show(fm, tag);
        }

    }

    public interface OnSelectedListener {
        void onSelected(String path);
    }

    public interface OnPositiveButtonListener {
        void onSelected(String path);
    }

    public interface OnNegativeButtonListener {
        void onCancel();
    }

}
