package com.dcps.tagebuch.model.pojo;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "pensamientos",
        foreignKeys = {@ForeignKey(entity = Categoria.class,
                                   parentColumns = "nombre",
                                   childColumns = "categoria_nombre",
                                   onDelete = ForeignKey.CASCADE)}
        )
public class Pensamiento {

    @PrimaryKey
    @NonNull
    private Date fecha;
    @NonNull
    private String titulo;
    @NonNull
    private String descripcion;
    @ColumnInfo(name = "categoria_nombre")
    @NonNull
    private String categoriaNombre;

    @NonNull
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(@NonNull Date fecha) {
        this.fecha = fecha;
    }

    @NonNull
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(@NonNull String titulo) {
        this.titulo = titulo;
    }

    @NonNull
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(@NonNull String descripcion) {
        this.descripcion = descripcion;
    }

    @NonNull
    public String getCategoriaNombre() {
        return categoriaNombre;
    }

    public void setCategoriaNombre(@NonNull String categoriaNombre) {
        this.categoriaNombre = categoriaNombre;
    }
}
