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

public class FilePickerDialog extends BottomSheetDialogFragment {

    public static final String TAG = "FilePickerDialog";

    private OnSelectedListener mListener;

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
    }

    public interface OnSelectedListener {
        public void onSelected(FilePath path);
    }

}
