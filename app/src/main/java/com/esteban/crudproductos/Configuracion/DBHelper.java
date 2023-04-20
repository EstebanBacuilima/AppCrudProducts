package com.esteban.crudproductos.Configuracion;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    Context context;
    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE 'tblproduct' ('id' INTEGER PRIMARY KEY AUTOINCREMENT,'codigo' varchar(20) NOT NULL,'nombre' varchar(20) NOT NULL, 'precio' number NOT NULL);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //sqLiteDatabase.execSQL("DROP TABLE IF EXISTS 'tblproduct'");
    }

    public void addProduct(String codigo, String nombre, Number precio){
        ContentValues values = new ContentValues();
        values.put("codigo", codigo);
        values.put("nombre", nombre);
        values.put("precio", precio.toString());

        this.getWritableDatabase().insertOrThrow("tblproduct","",values);
    }

    // LISTAR PRODUCTOS
    public ArrayList listProducts()
    {
        ArrayList list = new ArrayList();
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT * FROM tblproduct",null);
        while(cursor.moveToNext())
        {
            list.add(cursor.getInt(1) + "  " + cursor.getString(2) + "  " + cursor.getString(3));
        }
        return list;
    }

    // BUSCAR PRODUCTOS
    public Cursor findByCodigo(String codigo)
    {
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT * FROM tblproduct WHERE codigo='"+codigo+"'",null);
        return cursor;
    }

    // ACTUALIZAR PRODUCTOS
    public void updateProducto(String newName,Number newPrecio,String codigo){
        this.getWritableDatabase().execSQL("UPDATE tblproduct SET nombre='"+ newName +"',precio='" + newPrecio + "' WHERE codigo='"+ codigo +"'");
    }

    // ELIMINAR PRODUCTOS
    public void deleteProducto(String codigo){
        this.getWritableDatabase().execSQL("DELETE FROM tblproduct WHERE codigo='"+ codigo +"'");
    }
}
