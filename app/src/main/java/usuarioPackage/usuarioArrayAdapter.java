package usuarioPackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import wanderlp.com.mantenimientoabc.R;

/**
 * Created by wlopez on 15/07/2015.
 */
public class usuarioArrayAdapter<T> extends ArrayAdapter<T> {

    public usuarioArrayAdapter(Context context, List<T> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View listItemView = convertView;

        if (convertView == null)
            listItemView = inflater.inflate(R.layout.image_list_item, parent, false);

        TextView titulo = (TextView)listItemView.findViewById(R.id.text1);
        TextView subtitulo = (TextView)listItemView.findViewById(R.id.text2);
        ImageView imagen = (ImageView)listItemView.findViewById(R.id.image1);

        T item = (T)getItem(position);
        usuario usr = (usuario)item;
        titulo.setText(usr.getNombres() + ' ' + usr.getApellidos());
        subtitulo.setText(usr.getEmail());
        imagen.setImageResource(R.mipmap.photo);

        return listItemView;
    }
}
