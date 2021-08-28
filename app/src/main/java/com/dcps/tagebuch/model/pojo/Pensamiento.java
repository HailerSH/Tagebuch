package com.dcps.tagebuch.model.pojo;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "pensamientos",
        foreignKeys = {@ForeignKey(entity = Categoria.class,
                                   parentColumns = "id",
                                   childColumns = "categoria_id",
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
    @ColumnInfo(name = "categoria_id")
    private String categoriaId;

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

    public String getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(String categoriaId) {
        this.categoriaId = categoriaId;
    }
}
