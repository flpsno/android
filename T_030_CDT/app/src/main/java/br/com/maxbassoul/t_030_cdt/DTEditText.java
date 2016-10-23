package br.com.maxbassoul.t_030_cdt;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by nalmir on 22/10/2016.
 */
public class DTEditText extends EditText implements View.OnClickListener{
    private Context context;
    //
    private SimpleDateFormat dateFormat;
    private String titulo = "Select a Date";

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public DTEditText(Context context) {
        super(context);
        //
        inicializar(context);
    }

    public DTEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        //
        inicializar(context);
    }

    public DTEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //
        inicializar(context);
    }

    private void inicializar(Context context) {
        this.context = context;
        //
        dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        //
        setInputType(InputType.TYPE_NULL);
        //
        setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String mConteudo = getText().toString();
        //
        Calendar mDate = Calendar.getInstance();
        //
        try{
            mDate.setTime(dateFormat.parse(mConteudo));
        } catch (Exception e){
        }
        //
        int mAno = mDate.get(Calendar.YEAR);
        int mMes = mDate.get(Calendar.MONTH);
        int mDia = mDate.get(Calendar.DAY_OF_MONTH);
        //
        DatePickerDialog mDatePicker = new DatePickerDialog(
                context,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Calendar mcAux = Calendar.getInstance();
                        //
                        mcAux.set(year, monthOfYear, dayOfMonth);
                        //
                        setText(dateFormat.format(mcAux.getTime()));
                    }
                },
                mAno,
                mMes,
                mDia
        );
        //
        mDatePicker.setTitle(titulo);
        mDatePicker.setButton(DatePickerDialog.BUTTON_POSITIVE, "Ok", mDatePicker);
        mDatePicker.setButton(DatePickerDialog.BUTTON_NEGATIVE, "Cancelar", (DialogInterface.OnClickListener)null);
        //
        mDatePicker.show();
    }
}
