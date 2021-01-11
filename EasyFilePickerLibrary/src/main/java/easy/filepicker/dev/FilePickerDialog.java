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

public class FilePickerDialog extends BottomSheetDialogFragment {

    public static final String TAG = "FilePickerDialog";

    private CardView dialogCard;
    private RecyclerView itemRv;
    
    private static int dialogMargin;
    private static int dialogCorner;
    private static int dialogElevation;
    
    private OnSelectedListener mListener;
    
    public FilePickerDialog() {
        this.dialogMargin = getContext().getResources().getInteger(R.integer.default_dialog_margin);
        this.dialogCorner = getContext().getResources().getInteger(R.integer.default_corner_radius);
        this.dialogElevation = getContext().getResources().getInteger(R.integer.default_dialog_elevation);
        
    }

    public static FilePickerDialog newInstance() {
        return new FilePickerDialog();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.file_dialog, container, false);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        BottomSheetDialog dialog = new BottomSheetDialog(getContext(), R.style.MyBottomSheetDialogTheme);
        if (dialog != null) {
            final FrameLayout bottomSheet = dialog.findViewById(com.google.android.material.R.id.design_bottom_sheet);
            if (bottomSheet != null) {
                BottomSheetBehavior.from(bottomSheet).setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        }
        return dialog;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dialogCard = view.findViewById(R.id.dialog_card);
        itemRv = view.findViewById(R.id.item_rv);
        
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) dialogCard.getLayoutParams();
        if (params != null) {
            params.setMargins(dialogMargin, dialogMargin, dialogMargin, dialogMargin);
            dialogCard.setLayoutParams(params);
        }
        
        dialogCard.setRadius((float) dialogCorner);
        dialogCard.setElevation((float) dialogElevation);
    }
    
    public void setDialogMargin(int margin) {
        this.dialogMargin = margin;
    }
    
    public void setDialogCornerRadius(int cornerRadius) {
        this.dialogCorner = cornerRadius;
    }
    
    public void setDialogElevation(int elevation) {
        this.dialogElevation = elevation;
    }

    public interface OnSelectedListener {
        public void onSelected(FilePath path);
    }

}
